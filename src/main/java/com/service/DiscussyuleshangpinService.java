package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.DiscussyuleshangpinEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.DiscussyuleshangpinVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.DiscussyuleshangpinView;


/**
 * 娱乐商品评论表
 *
 * @author 
 * @email 
 * @date 2023-06-28 16:37:17
 */
public interface DiscussyuleshangpinService extends IService<DiscussyuleshangpinEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DiscussyuleshangpinVO> selectListVO(Wrapper<DiscussyuleshangpinEntity> wrapper);
   	
   	DiscussyuleshangpinVO selectVO(@Param("ew") Wrapper<DiscussyuleshangpinEntity> wrapper);
   	
   	List<DiscussyuleshangpinView> selectListView(Wrapper<DiscussyuleshangpinEntity> wrapper);
   	
   	DiscussyuleshangpinView selectView(@Param("ew") Wrapper<DiscussyuleshangpinEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DiscussyuleshangpinEntity> wrapper);
   	

}

