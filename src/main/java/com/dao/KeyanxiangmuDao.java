package com.dao;

import com.entity.KeyanxiangmuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.KeyanxiangmuView;

/**
 * 科研项目 Dao 接口
 *
 * @author 
 */
public interface KeyanxiangmuDao extends BaseMapper<KeyanxiangmuEntity> {

   List<KeyanxiangmuView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
