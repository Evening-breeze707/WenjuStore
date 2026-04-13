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


import com.dao.DiscussyuleshangpinDao;
import com.entity.DiscussyuleshangpinEntity;
import com.service.DiscussyuleshangpinService;
import com.entity.vo.DiscussyuleshangpinVO;
import com.entity.view.DiscussyuleshangpinView;

@Service("discussyuleshangpinService")
public class DiscussyuleshangpinServiceImpl extends ServiceImpl<DiscussyuleshangpinDao, DiscussyuleshangpinEntity> implements DiscussyuleshangpinService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DiscussyuleshangpinEntity> page = this.selectPage(
                new Query<DiscussyuleshangpinEntity>(params).getPage(),
                new EntityWrapper<DiscussyuleshangpinEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<DiscussyuleshangpinEntity> wrapper) {
		  Page<DiscussyuleshangpinView> page =new Query<DiscussyuleshangpinView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<DiscussyuleshangpinVO> selectListVO(Wrapper<DiscussyuleshangpinEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public DiscussyuleshangpinVO selectVO(Wrapper<DiscussyuleshangpinEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<DiscussyuleshangpinView> selectListView(Wrapper<DiscussyuleshangpinEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public DiscussyuleshangpinView selectView(Wrapper<DiscussyuleshangpinEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
