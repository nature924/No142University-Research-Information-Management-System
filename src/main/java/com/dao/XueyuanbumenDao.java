package com.dao;

import com.entity.XueyuanbumenEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.XueyuanbumenView;

/**
 * 学院部门 Dao 接口
 *
 * @author 
 */
public interface XueyuanbumenDao extends BaseMapper<XueyuanbumenEntity> {

   List<XueyuanbumenView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
