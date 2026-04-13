package com.dao;

import com.entity.WenjuxinxiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.WenjuxinxiVO;
import com.entity.view.WenjuxinxiView;


/**
 * 文具信息
 * 
 * @author 
 * @email 
 * @date 2023-06-28 16:37:16
 */
public interface WenjuxinxiDao extends BaseMapper<WenjuxinxiEntity> {
	
	List<WenjuxinxiVO> selectListVO(@Param("ew") Wrapper<WenjuxinxiEntity> wrapper);
	
	WenjuxinxiVO selectVO(@Param("ew") Wrapper<WenjuxinxiEntity> wrapper);
	
	List<WenjuxinxiView> selectListView(@Param("ew") Wrapper<WenjuxinxiEntity> wrapper);

	List<WenjuxinxiView> selectListView(Pagination page,@Param("ew") Wrapper<WenjuxinxiEntity> wrapper);
	
	WenjuxinxiView selectView(@Param("ew") Wrapper<WenjuxinxiEntity> wrapper);
	

}
