package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.YuleshangpinEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.YuleshangpinVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.YuleshangpinView;


/**
 * 娱乐商品
 *
 * @author 
 * @email 
 * @date 2023-06-28 16:37:16
 */
public interface YuleshangpinService extends IService<YuleshangpinEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<YuleshangpinVO> selectListVO(Wrapper<YuleshangpinEntity> wrapper);
   	
   	YuleshangpinVO selectVO(@Param("ew") Wrapper<YuleshangpinEntity> wrapper);
   	
   	List<YuleshangpinView> selectListView(Wrapper<YuleshangpinEntity> wrapper);
   	
   	YuleshangpinView selectView(@Param("ew") Wrapper<YuleshangpinEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<YuleshangpinEntity> wrapper);
   	

}

