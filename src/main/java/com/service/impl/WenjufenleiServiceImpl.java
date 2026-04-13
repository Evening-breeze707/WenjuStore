package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;


import com.dao.WenjufenleiDao;
import com.entity.WenjufenleiEntity;
import com.service.WenjufenleiService;
import com.entity.vo.WenjufenleiVO;
import com.entity.view.WenjufenleiView;

@Service("wenjufenleiService")
public class WenjufenleiServiceImpl extends ServiceImpl<WenjufenleiDao, WenjufenleiEntity> implements WenjufenleiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<WenjufenleiEntity> page = this.selectPage(
                new Query<WenjufenleiEntity>(params).getPage(),
                new EntityWrapper<WenjufenleiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<WenjufenleiEntity> wrapper) {
		  Page<WenjufenleiView> page =new Query<WenjufenleiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<WenjufenleiVO> selectListVO(Wrapper<WenjufenleiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public WenjufenleiVO selectVO(Wrapper<WenjufenleiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<WenjufenleiView> selectListView(Wrapper<WenjufenleiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public WenjufenleiView selectView(Wrapper<WenjufenleiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
