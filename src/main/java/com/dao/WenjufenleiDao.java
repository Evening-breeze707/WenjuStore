package com.dao;

import com.entity.WenjufenleiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.WenjufenleiVO;
import com.entity.view.WenjufenleiView;


/**
 * 文具分类
 * 
 * @author 
 * @email 
 * @date 2023-06-28 16:37:16
 */
public interface WenjufenleiDao extends BaseMapper<WenjufenleiEntity> {
	
	List<WenjufenleiVO> selectListVO(@Param("ew") Wrapper<WenjufenleiEntity> wrapper);
	
	WenjufenleiVO selectVO(@Param("ew") Wrapper<WenjufenleiEntity> wrapper);
	
	List<WenjufenleiView> selectListView(@Param("ew") Wrapper<WenjufenleiEntity> wrapper);

	List<WenjufenleiView> selectListView(Pagination page,@Param("ew") Wrapper<WenjufenleiEntity> wrapper);
	
	WenjufenleiView selectView(@Param("ew") Wrapper<WenjufenleiEntity> wrapper);
	

}
