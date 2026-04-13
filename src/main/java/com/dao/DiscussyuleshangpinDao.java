package com.dao;

import com.entity.DiscussyuleshangpinEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.DiscussyuleshangpinVO;
import com.entity.view.DiscussyuleshangpinView;


/**
 * 娱乐商品评论表
 * 
 * @author 
 * @email 
 * @date 2023-06-28 16:37:17
 */
public interface DiscussyuleshangpinDao extends BaseMapper<DiscussyuleshangpinEntity> {
	
	List<DiscussyuleshangpinVO> selectListVO(@Param("ew") Wrapper<DiscussyuleshangpinEntity> wrapper);
	
	DiscussyuleshangpinVO selectVO(@Param("ew") Wrapper<DiscussyuleshangpinEntity> wrapper);
	
	List<DiscussyuleshangpinView> selectListView(@Param("ew") Wrapper<DiscussyuleshangpinEntity> wrapper);

	List<DiscussyuleshangpinView> selectListView(Pagination page,@Param("ew") Wrapper<DiscussyuleshangpinEntity> wrapper);
	
	DiscussyuleshangpinView selectView(@Param("ew") Wrapper<DiscussyuleshangpinEntity> wrapper);
	

}
