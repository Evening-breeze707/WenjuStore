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


import com.dao.DiscusswenjuxinxiDao;
import com.entity.DiscusswenjuxinxiEntity;
import com.service.DiscusswenjuxinxiService;
import com.entity.vo.DiscusswenjuxinxiVO;
import com.entity.view.DiscusswenjuxinxiView;

@Service("discusswenjuxinxiService")
public class DiscusswenjuxinxiServiceImpl extends ServiceImpl<DiscusswenjuxinxiDao, DiscusswenjuxinxiEntity> implements DiscusswenjuxinxiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DiscusswenjuxinxiEntity> page = this.selectPage(
                new Query<DiscusswenjuxinxiEntity>(params).getPage(),
                new EntityWrapper<DiscusswenjuxinxiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<DiscusswenjuxinxiEntity> wrapper) {
		  Page<DiscusswenjuxinxiView> page =new Query<DiscusswenjuxinxiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<DiscusswenjuxinxiVO> selectListVO(Wrapper<DiscusswenjuxinxiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public DiscusswenjuxinxiVO selectVO(Wrapper<DiscusswenjuxinxiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<DiscusswenjuxinxiView> selectListView(Wrapper<DiscusswenjuxinxiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public DiscusswenjuxinxiView selectView(Wrapper<DiscusswenjuxinxiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
