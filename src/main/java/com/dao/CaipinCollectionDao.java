package com.dao;

import com.entity.CaipinCollectionEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.CaipinCollectionView;

/**
 * 菜品收藏 Dao 接口
 *
 * @author 
 */
public interface CaipinCollectionDao extends BaseMapper<CaipinCollectionEntity> {

   List<CaipinCollectionView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
