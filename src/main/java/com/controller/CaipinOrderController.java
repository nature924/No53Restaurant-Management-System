
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;

import com.alibaba.fastjson.JSONObject;

import java.util.*;

import org.springframework.beans.BeanUtils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.ContextLoader;

import javax.servlet.ServletContext;

import com.service.TokenService;
import com.utils.*;

import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 菜品订单
 * 后端接口
 *
 * @author
 * @email
 */
@RestController
@Controller
@RequestMapping("/caipinOrder")
public class CaipinOrderController {
    private static final Logger logger = LoggerFactory.getLogger(CaipinOrderController.class);

    @Autowired
    private CaipinOrderService caipinOrderService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service
    @Autowired
    private CaipinService caipinService;
    @Autowired
    private YonghuService yonghuService;
    @Autowired
    private CaipinCommentbackService caipinCommentbackService;


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request) {
        logger.debug("page方法:,,Controller:{},,params:{}", this.getClass().getName(), JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if (false)
            return R.error(511, "永不会进入");
        else if ("用户".equals(role))
            params.put("yonghuId", request.getSession().getAttribute("userId"));
        else if ("员工".equals(role))
            params.put("yuangongId", request.getSession().getAttribute("userId"));
        if (params.get("orderBy") == null || params.get("orderBy") == "") {
            params.put("orderBy", "id");
        }
        PageUtils page = caipinOrderService.queryPage(params);

        //字典表数据转换
        List<CaipinOrderView> list = (List<CaipinOrderView>) page.getList();
        for (CaipinOrderView c : list) {
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request) {
        logger.debug("info方法:,,Controller:{},,id:{}", this.getClass().getName(), id);
        CaipinOrderEntity caipinOrder = caipinOrderService.selectById(id);
        if (caipinOrder != null) {
            //entity转view
            CaipinOrderView view = new CaipinOrderView();
            BeanUtils.copyProperties(caipinOrder, view);//把实体数据重构到view中

            //级联表
            CaipinEntity caipin = caipinService.selectById(caipinOrder.getCaipinId());
            if (caipin != null) {
                BeanUtils.copyProperties(caipin, view, new String[]{"id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                view.setCaipinId(caipin.getId());
            }
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(caipinOrder.getYonghuId());
            if (yonghu != null) {
                BeanUtils.copyProperties(yonghu, view, new String[]{"id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                view.setYonghuId(yonghu.getId());
            }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        } else {
            return R.error(511, "查不到数据");
        }

    }

    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody CaipinOrderEntity caipinOrder, HttpServletRequest request) {
        logger.debug("save方法:,,Controller:{},,caipinOrder:{}", this.getClass().getName(), caipinOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if (false)
            return R.error(511, "永远不会进入");
        else if ("用户".equals(role))
            caipinOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        caipinOrder.setInsertTime(new Date());
        caipinOrder.setCreateTime(new Date());
        caipinOrderService.insert(caipinOrder);
        return R.ok();
    }

    /**
     * 后端修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody CaipinOrderEntity caipinOrder, HttpServletRequest request) {
        logger.debug("update方法:,,Controller:{},,caipinOrder:{}", this.getClass().getName(), caipinOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            caipinOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<CaipinOrderEntity> queryWrapper = new EntityWrapper<CaipinOrderEntity>()
                .eq("id", 0);

        logger.info("sql语句:" + queryWrapper.getSqlSegment());
        CaipinOrderEntity caipinOrderEntity = caipinOrderService.selectOne(queryWrapper);
        if (caipinOrderEntity == null) {
            caipinOrderService.updateById(caipinOrder);//根据id更新
            return R.ok();
        } else {
            return R.error(511, "表中有相同数据");
        }
    }


    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids) {
        logger.debug("delete:,,Controller:{},,ids:{}", this.getClass().getName(), ids.toString());
        caipinOrderService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save(String fileName, HttpServletRequest request) {
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}", this.getClass().getName(), fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            List<CaipinOrderEntity> caipinOrderList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields = new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if (lastIndexOf == -1) {
                return R.error(511, "该文件没有后缀");
            } else {
                String suffix = fileName.substring(lastIndexOf);
                if (!".xls".equals(suffix)) {
                    return R.error(511, "只支持后缀为xls的excel文件");
                } else {
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if (!file.exists()) {
                        return R.error(511, "找不到上传文件，请联系管理员");
                    } else {
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for (List<String> data : dataList) {
                            //循环
                            CaipinOrderEntity caipinOrderEntity = new CaipinOrderEntity();
//                            caipinOrderEntity.setCaipinOrderUuidNumber(data.get(0));                    //订单号 要改的
//                            caipinOrderEntity.setCaipinId(Integer.valueOf(data.get(0)));   //菜品 要改的
//                            caipinOrderEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            caipinOrderEntity.setBuyNumber(Integer.valueOf(data.get(0)));   //预定数量 要改的
//                            caipinOrderEntity.setCaipinOrderTime(sdf.parse(data.get(0)));          //预定时间 要改的
//                            caipinOrderEntity.setCaipinOrderTruePrice(data.get(0));                    //实付价格 要改的
//                            caipinOrderEntity.setCaipinOrderTypes(Integer.valueOf(data.get(0)));   //订单类型 要改的
//                            caipinOrderEntity.setCaipinOrderPaymentTypes(Integer.valueOf(data.get(0)));   //支付类型 要改的
//                            caipinOrderEntity.setInsertTime(date);//时间
//                            caipinOrderEntity.setCreateTime(date);//时间
                            caipinOrderList.add(caipinOrderEntity);


                            //把要查询是否重复的字段放入map中
                            //订单号
                            if (seachFields.containsKey("caipinOrderUuidNumber")) {
                                List<String> caipinOrderUuidNumber = seachFields.get("caipinOrderUuidNumber");
                                caipinOrderUuidNumber.add(data.get(0));//要改的
                            } else {
                                List<String> caipinOrderUuidNumber = new ArrayList<>();
                                caipinOrderUuidNumber.add(data.get(0));//要改的
                                seachFields.put("caipinOrderUuidNumber", caipinOrderUuidNumber);
                            }
                        }

                        //查询是否重复
                        //订单号
                        List<CaipinOrderEntity> caipinOrderEntities_caipinOrderUuidNumber = caipinOrderService.selectList(new EntityWrapper<CaipinOrderEntity>().in("caipin_order_uuid_number", seachFields.get("caipinOrderUuidNumber")));
                        if (caipinOrderEntities_caipinOrderUuidNumber.size() > 0) {
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for (CaipinOrderEntity s : caipinOrderEntities_caipinOrderUuidNumber) {
                                repeatFields.add(s.getCaipinOrderUuidNumber());
                            }
                            return R.error(511, "数据库的该表中的 [订单号] 字段已经存在 存在数据为:" + repeatFields.toString());
                        }
                        caipinOrderService.insertBatch(caipinOrderList);
                        return R.ok();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(511, "批量插入数据异常，请联系管理员");
        }
    }


    /**
     * 前端列表
     */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request) {
        logger.debug("list方法:,,Controller:{},,params:{}", this.getClass().getName(), JSONObject.toJSONString(params));

        // 没有指定排序字段就默认id倒序
        if (StringUtil.isEmpty(String.valueOf(params.get("orderBy")))) {
            params.put("orderBy", "id");
        }
        PageUtils page = caipinOrderService.queryPage(params);

        //字典表数据转换
        List<CaipinOrderView> list = (List<CaipinOrderView>) page.getList();
        for (CaipinOrderView c : list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
     * 前端详情
     */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request) {
        logger.debug("detail方法:,,Controller:{},,id:{}", this.getClass().getName(), id);
        CaipinOrderEntity caipinOrder = caipinOrderService.selectById(id);
        if (caipinOrder != null) {


            //entity转view
            CaipinOrderView view = new CaipinOrderView();
            BeanUtils.copyProperties(caipinOrder, view);//把实体数据重构到view中

            //级联表
            CaipinEntity caipin = caipinService.selectById(caipinOrder.getCaipinId());
            if (caipin != null) {
                BeanUtils.copyProperties(caipin, view, new String[]{"id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                view.setCaipinId(caipin.getId());
            }
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(caipinOrder.getYonghuId());
            if (yonghu != null) {
                BeanUtils.copyProperties(yonghu, view, new String[]{"id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                view.setYonghuId(yonghu.getId());
            }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        } else {
            return R.error(511, "查不到数据");
        }
    }


    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody CaipinOrderEntity caipinOrder, HttpServletRequest request) {
        logger.debug("add方法:,,Controller:{},,caipinOrder:{}", this.getClass().getName(), caipinOrder.toString());
        CaipinEntity caipinEntity = caipinService.selectById(caipinOrder.getCaipinId());
        if (caipinEntity == null) {
            return R.error(511, "查不到该菜品");
        }
        // Double caipinNewMoney = caipinEntity.getCaipinNewMoney();

        if (false) {
        } else if ((caipinEntity.getCaipinKucunNumber() - caipinOrder.getBuyNumber()) < 0) {
            return R.error(511, "购买数量不能大于库存数量");
        } else if (caipinEntity.getCaipinNewMoney() == null) {
            return R.error(511, "菜品价格不能为空");
        }

        //计算所获得积分
        Double buyJifen = 0.0;
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        YonghuEntity yonghuEntity = yonghuService.selectById(userId);
        if (yonghuEntity == null)
            return R.error(511, "用户不能为空");
        if (yonghuEntity.getNewMoney() == null)
            return R.error(511, "用户金额不能为空");
        double balance = yonghuEntity.getNewMoney() - caipinEntity.getCaipinNewMoney() * caipinOrder.getBuyNumber();//余额
        buyJifen = new BigDecimal(caipinEntity.getCaipinPrice()).multiply(new BigDecimal(caipinOrder.getBuyNumber())).doubleValue();//所获积分
        if (balance < 0)
            return R.error(511, "余额不够支付");
        caipinOrder.setCaipinOrderTypes(1); //设置订单状态为已支付
        caipinOrder.setCaipinOrderTruePrice(caipinEntity.getCaipinNewMoney() * caipinOrder.getBuyNumber()); //设置实付价格
        caipinOrder.setYonghuId(userId); //设置订单支付人id
        caipinOrder.setCaipinOrderUuidNumber(String.valueOf(new Date().getTime()));
        caipinOrder.setCaipinOrderPaymentTypes(1);
        caipinOrder.setInsertTime(new Date());
        caipinOrder.setCreateTime(new Date());
        caipinEntity.setCaipinKucunNumber(caipinEntity.getCaipinKucunNumber() - caipinOrder.getBuyNumber());
        caipinService.updateById(caipinEntity);
        caipinOrderService.insert(caipinOrder);//新增订单
        yonghuEntity.setNewMoney(balance);//设置金额
        yonghuEntity.setYonghuSumJifen(yonghuEntity.getYonghuSumJifen() + buyJifen); //设置总积分
        yonghuEntity.setYonghuNewJifen(yonghuEntity.getYonghuNewJifen() + buyJifen); //设置现积分
        if (yonghuEntity.getYonghuSumJifen() < 10000)
            yonghuEntity.setHuiyuandengjiTypes(1);
        else if (yonghuEntity.getYonghuSumJifen() < 100000)
            yonghuEntity.setHuiyuandengjiTypes(2);
        else if (yonghuEntity.getYonghuSumJifen() < 1000000)
            yonghuEntity.setHuiyuandengjiTypes(3);
        yonghuService.updateById(yonghuEntity);
        return R.ok();
    }

    /**
     * 退款
     */
    @RequestMapping("/refund")
    public R refund(Integer id, HttpServletRequest request) {
        logger.debug("refund方法:,,Controller:{},,id:{}", this.getClass().getName(), id);
        String role = String.valueOf(request.getSession().getAttribute("role"));

        CaipinOrderEntity caipinOrder = caipinOrderService.selectById(id);
        Integer buyNumber = caipinOrder.getBuyNumber();
        Integer caipinOrderPaymentTypes = caipinOrder.getCaipinOrderPaymentTypes();
        Integer caipinId = caipinOrder.getCaipinId();
        if (caipinId == null)
            return R.error(511, "查不到该菜品");
        CaipinEntity caipinEntity = caipinService.selectById(caipinId);
        if (caipinEntity == null)
            return R.error(511, "查不到该菜品");
        Double caipinNewMoney = caipinEntity.getCaipinNewMoney();
        if (caipinNewMoney == null)
            return R.error(511, "菜品价格不能为空");

        Integer userId = (Integer) request.getSession().getAttribute("userId");
        YonghuEntity yonghuEntity = yonghuService.selectById(userId);
        if (yonghuEntity == null)
            return R.error(511, "用户不能为空");
        if (yonghuEntity.getNewMoney() == null)
            return R.error(511, "用户金额不能为空");

        Double zhekou = 1.0;
        // 获取折扣
        Wrapper<DictionaryEntity> dictionary = new EntityWrapper<DictionaryEntity>()
                .eq("dic_code", "huiyuandengji_types")
                .eq("dic_name", "会员等级类型")
                .eq("code_index", yonghuEntity.getHuiyuandengjiTypes());
        DictionaryEntity dictionaryEntity = dictionaryService.selectOne(dictionary);
        if (dictionaryEntity != null) {
            zhekou = Double.valueOf(dictionaryEntity.getBeizhu());
        }


        //判断是什么支付方式 1代表余额 2代表积分
        if (caipinOrderPaymentTypes == 1) {//余额支付
            //计算金额
            Double money = caipinEntity.getCaipinNewMoney() * buyNumber * zhekou;
            //计算所获得积分
            Double buyJifen = 0.0;
            buyJifen = new BigDecimal(caipinEntity.getCaipinPrice()).multiply(new BigDecimal(buyNumber)).doubleValue();
            yonghuEntity.setNewMoney(yonghuEntity.getNewMoney() + money); //设置金额
            yonghuEntity.setYonghuSumJifen(yonghuEntity.getYonghuSumJifen() - buyJifen); //设置总积分
            if (yonghuEntity.getYonghuNewJifen() - buyJifen < 0)
                return R.error("积分已经消费,无法退款！！！");
            yonghuEntity.setYonghuNewJifen(yonghuEntity.getYonghuNewJifen() - buyJifen); //设置现积分

            if (yonghuEntity.getYonghuSumJifen() < 10000)
                yonghuEntity.setHuiyuandengjiTypes(1);
            else if (yonghuEntity.getYonghuSumJifen() < 100000)
                yonghuEntity.setHuiyuandengjiTypes(2);
            else if (yonghuEntity.getYonghuSumJifen() < 1000000)
                yonghuEntity.setHuiyuandengjiTypes(3);

        }

        caipinEntity.setCaipinKucunNumber(caipinEntity.getCaipinKucunNumber() + buyNumber);


        caipinOrder.setCaipinOrderTypes(2);//设置订单状态为退款
        caipinOrderService.updateById(caipinOrder);//根据id更新
        yonghuService.updateById(yonghuEntity);//更新用户信息
        caipinService.updateById(caipinEntity);//更新订单中菜品的信息
        return R.ok();
    }


    /**
     * 发货
     */
    @RequestMapping("/deliver")
    public R deliver(Integer id) {
        logger.debug("refund:,,Controller:{},,ids:{}", this.getClass().getName(), id.toString());
        CaipinOrderEntity caipinOrderEntity = new CaipinOrderEntity();
        ;
        caipinOrderEntity.setId(id);
        caipinOrderEntity.setCaipinOrderTypes(3);
        boolean b = caipinOrderService.updateById(caipinOrderEntity);
        if (!b) {
            return R.error("操作出错");
        }
        return R.ok();
    }


    /**
     * 收货
     */
    @RequestMapping("/receiving")
    public R receiving(Integer id) {
        logger.debug("refund:,,Controller:{},,ids:{}", this.getClass().getName(), id.toString());
        CaipinOrderEntity caipinOrderEntity = new CaipinOrderEntity();
        caipinOrderEntity.setId(id);
        caipinOrderEntity.setCaipinOrderTypes(4);
        boolean b = caipinOrderService.updateById(caipinOrderEntity);
        if (!b) {
            return R.error("操作出错");
        }
        return R.ok();
    }


    /**
     * 评价
     */
    @RequestMapping("/commentback")
    public R commentback(Integer id, String commentbackText, Integer caipinCommentbackPingfenNumber, HttpServletRequest request) {
        logger.debug("commentback方法:,,Controller:{},,id:{}", this.getClass().getName(), id);
        CaipinOrderEntity caipinOrder = caipinOrderService.selectById(id);
        if (caipinOrder == null)
            return R.error(511, "查不到该订单");
        if (caipinOrder.getCaipinOrderTypes() != 4)
            return R.error(511, "您不能评价");
        Integer caipinId = caipinOrder.getCaipinId();
        if (caipinId == null)
            return R.error(511, "查不到该菜品");

        CaipinCommentbackEntity caipinCommentbackEntity = new CaipinCommentbackEntity();
        caipinCommentbackEntity.setId(id);
        caipinCommentbackEntity.setCaipinId(caipinId);
        caipinCommentbackEntity.setYonghuId((Integer) request.getSession().getAttribute("userId"));
        caipinCommentbackEntity.setCaipinCommentbackText(commentbackText);
        caipinCommentbackEntity.setInsertTime(new Date());
        caipinCommentbackEntity.setReplyText(null);
        caipinCommentbackEntity.setUpdateTime(null);
        caipinCommentbackEntity.setCreateTime(new Date());
        caipinCommentbackService.insert(caipinCommentbackEntity);

        caipinOrder.setCaipinOrderTypes(5);//设置订单状态为已评价
        caipinOrderService.updateById(caipinOrder);//根据id更新
        return R.ok();
    }


}
