package com.dao;

import com.entity.XueshuhuodongEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.XueshuhuodongView;

/**
 * 学术活动 Dao 接口
 *
 * @author 
 */
public interface XueshuhuodongDao extends BaseMapper<XueshuhuodongEntity> {

   List<XueshuhuodongView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
