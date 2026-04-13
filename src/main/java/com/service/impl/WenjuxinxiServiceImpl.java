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


import com.dao.WenjuxinxiDao;
import com.entity.WenjuxinxiEntity;
import com.service.WenjuxinxiService;
import com.entity.vo.WenjuxinxiVO;
import com.entity.view.WenjuxinxiView;

@Service("wenjuxinxiService")
public class WenjuxinxiServiceImpl extends ServiceImpl<WenjuxinxiDao, WenjuxinxiEntity> implements WenjuxinxiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<WenjuxinxiEntity> page = this.selectPage(
                new Query<WenjuxinxiEntity>(params).getPage(),
                new EntityWrapper<WenjuxinxiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<WenjuxinxiEntity> wrapper) {
		  Page<WenjuxinxiView> page =new Query<WenjuxinxiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<WenjuxinxiVO> selectListVO(Wrapper<WenjuxinxiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public WenjuxinxiVO selectVO(Wrapper<WenjuxinxiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<WenjuxinxiView> selectListView(Wrapper<WenjuxinxiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public WenjuxinxiView selectView(Wrapper<WenjuxinxiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
