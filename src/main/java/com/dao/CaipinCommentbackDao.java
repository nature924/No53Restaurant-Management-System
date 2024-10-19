package com.dao;

import com.entity.CaipinCommentbackEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.CaipinCommentbackView;

/**
 * 菜品评价 Dao 接口
 *
 * @author 
 */
public interface CaipinCommentbackDao extends BaseMapper<CaipinCommentbackEntity> {

   List<CaipinCommentbackView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
