package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.WenjufenleiEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.WenjufenleiVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.WenjufenleiView;


/**
 * 文具分类
 *
 * @author 
 * @email 
 * @date 2023-06-28 16:37:16
 */
public interface WenjufenleiService extends IService<WenjufenleiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<WenjufenleiVO> selectListVO(Wrapper<WenjufenleiEntity> wrapper);
   	
   	WenjufenleiVO selectVO(@Param("ew") Wrapper<WenjufenleiEntity> wrapper);
   	
   	List<WenjufenleiView> selectListView(Wrapper<WenjufenleiEntity> wrapper);
   	
   	WenjufenleiView selectView(@Param("ew") Wrapper<WenjufenleiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<WenjufenleiEntity> wrapper);
   	

}

