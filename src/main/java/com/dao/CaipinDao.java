package com.dao;

import com.entity.CaipinEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.CaipinView;

/**
 * 菜品 Dao 接口
 *
 * @author 
 */
public interface CaipinDao extends BaseMapper<CaipinEntity> {

   List<CaipinView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
