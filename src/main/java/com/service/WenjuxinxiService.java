package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.WenjuxinxiEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.WenjuxinxiVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.WenjuxinxiView;


/**
 * 文具信息
 *
 * @author 
 * @email 
 * @date 2023-06-28 16:37:16
 */
public interface WenjuxinxiService extends IService<WenjuxinxiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<WenjuxinxiVO> selectListVO(Wrapper<WenjuxinxiEntity> wrapper);
   	
   	WenjuxinxiVO selectVO(@Param("ew") Wrapper<WenjuxinxiEntity> wrapper);
   	
   	List<WenjuxinxiView> selectListView(Wrapper<WenjuxinxiEntity> wrapper);
   	
   	WenjuxinxiView selectView(@Param("ew") Wrapper<WenjuxinxiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<WenjuxinxiEntity> wrapper);
   	

}

