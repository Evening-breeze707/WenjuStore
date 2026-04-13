package com.dao;

import com.entity.DiscusswenjuxinxiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.DiscusswenjuxinxiVO;
import com.entity.view.DiscusswenjuxinxiView;


/**
 * 文具信息评论表
 * 
 * @author 
 * @email 
 * @date 2023-06-28 16:37:17
 */
public interface DiscusswenjuxinxiDao extends BaseMapper<DiscusswenjuxinxiEntity> {
	
	List<DiscusswenjuxinxiVO> selectListVO(@Param("ew") Wrapper<DiscusswenjuxinxiEntity> wrapper);
	
	DiscusswenjuxinxiVO selectVO(@Param("ew") Wrapper<DiscusswenjuxinxiEntity> wrapper);
	
	List<DiscusswenjuxinxiView> selectListView(@Param("ew") Wrapper<DiscusswenjuxinxiEntity> wrapper);

	List<DiscusswenjuxinxiView> selectListView(Pagination page,@Param("ew") Wrapper<DiscusswenjuxinxiEntity> wrapper);
	
	DiscusswenjuxinxiView selectView(@Param("ew") Wrapper<DiscusswenjuxinxiEntity> wrapper);
	

}
