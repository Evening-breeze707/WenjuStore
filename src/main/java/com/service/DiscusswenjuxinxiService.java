package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.DiscusswenjuxinxiEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.DiscusswenjuxinxiVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.DiscusswenjuxinxiView;


/**
 * 文具信息评论表
 *
 * @author 
 * @email 
 * @date 2023-06-28 16:37:17
 */
public interface DiscusswenjuxinxiService extends IService<DiscusswenjuxinxiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DiscusswenjuxinxiVO> selectListVO(Wrapper<DiscusswenjuxinxiEntity> wrapper);
   	
   	DiscusswenjuxinxiVO selectVO(@Param("ew") Wrapper<DiscusswenjuxinxiEntity> wrapper);
   	
   	List<DiscusswenjuxinxiView> selectListView(Wrapper<DiscusswenjuxinxiEntity> wrapper);
   	
   	DiscusswenjuxinxiView selectView(@Param("ew") Wrapper<DiscusswenjuxinxiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DiscusswenjuxinxiEntity> wrapper);
   	

}

