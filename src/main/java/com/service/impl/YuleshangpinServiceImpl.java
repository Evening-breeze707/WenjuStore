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


import com.dao.YuleshangpinDao;
import com.entity.YuleshangpinEntity;
import com.service.YuleshangpinService;
import com.entity.vo.YuleshangpinVO;
import com.entity.view.YuleshangpinView;

@Service("yuleshangpinService")
public class YuleshangpinServiceImpl extends ServiceImpl<YuleshangpinDao, YuleshangpinEntity> implements YuleshangpinService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<YuleshangpinEntity> page = this.selectPage(
                new Query<YuleshangpinEntity>(params).getPage(),
                new EntityWrapper<YuleshangpinEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<YuleshangpinEntity> wrapper) {
		  Page<YuleshangpinView> page =new Query<YuleshangpinView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<YuleshangpinVO> selectListVO(Wrapper<YuleshangpinEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public YuleshangpinVO selectVO(Wrapper<YuleshangpinEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<YuleshangpinView> selectListView(Wrapper<YuleshangpinEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public YuleshangpinView selectView(Wrapper<YuleshangpinEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
