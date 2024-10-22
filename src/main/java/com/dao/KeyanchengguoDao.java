package com.dao;

import com.entity.KeyanchengguoEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.KeyanchengguoView;

/**
 * 科研成果 Dao 接口
 *
 * @author 
 */
public interface KeyanchengguoDao extends BaseMapper<KeyanchengguoEntity> {

   List<KeyanchengguoView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
