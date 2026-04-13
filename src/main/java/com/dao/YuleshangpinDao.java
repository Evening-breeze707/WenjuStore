package com.dao;

import com.entity.YuleshangpinEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.YuleshangpinVO;
import com.entity.view.YuleshangpinView;


/**
 * 娱乐商品
 * 
 * @author 
 * @email 
 * @date 2023-06-28 16:37:16
 */
public interface YuleshangpinDao extends BaseMapper<YuleshangpinEntity> {
	
	List<YuleshangpinVO> selectListVO(@Param("ew") Wrapper<YuleshangpinEntity> wrapper);
	
	YuleshangpinVO selectVO(@Param("ew") Wrapper<YuleshangpinEntity> wrapper);
	
	List<YuleshangpinView> selectListView(@Param("ew") Wrapper<YuleshangpinEntity> wrapper);

	List<YuleshangpinView> selectListView(Pagination page,@Param("ew") Wrapper<YuleshangpinEntity> wrapper);
	
	YuleshangpinView selectView(@Param("ew") Wrapper<YuleshangpinEntity> wrapper);
	

}
