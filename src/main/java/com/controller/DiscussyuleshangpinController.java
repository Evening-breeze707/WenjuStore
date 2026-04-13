package com.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;

import com.entity.DiscussyuleshangpinEntity;
import com.entity.view.DiscussyuleshangpinView;

import com.service.DiscussyuleshangpinService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MD5Util;
import com.utils.MPUtil;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 娱乐商品评论表
 * 后端接口
 * @author 
 * @email 
 * @date 2023-06-28 16:37:17
 */
@RestController
@RequestMapping("/discussyuleshangpin")
public class DiscussyuleshangpinController {
    @Autowired
    private DiscussyuleshangpinService discussyuleshangpinService;


    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,DiscussyuleshangpinEntity discussyuleshangpin,
		HttpServletRequest request){
        EntityWrapper<DiscussyuleshangpinEntity> ew = new EntityWrapper<DiscussyuleshangpinEntity>();

		PageUtils page = discussyuleshangpinService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussyuleshangpin), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,DiscussyuleshangpinEntity discussyuleshangpin, 
		HttpServletRequest request){
        EntityWrapper<DiscussyuleshangpinEntity> ew = new EntityWrapper<DiscussyuleshangpinEntity>();

		PageUtils page = discussyuleshangpinService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussyuleshangpin), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( DiscussyuleshangpinEntity discussyuleshangpin){
       	EntityWrapper<DiscussyuleshangpinEntity> ew = new EntityWrapper<DiscussyuleshangpinEntity>();
      	ew.allEq(MPUtil.allEQMapPre( discussyuleshangpin, "discussyuleshangpin")); 
        return R.ok().put("data", discussyuleshangpinService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(DiscussyuleshangpinEntity discussyuleshangpin){
        EntityWrapper< DiscussyuleshangpinEntity> ew = new EntityWrapper< DiscussyuleshangpinEntity>();
 		ew.allEq(MPUtil.allEQMapPre( discussyuleshangpin, "discussyuleshangpin")); 
		DiscussyuleshangpinView discussyuleshangpinView =  discussyuleshangpinService.selectView(ew);
		return R.ok("查询娱乐商品评论表成功").put("data", discussyuleshangpinView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        DiscussyuleshangpinEntity discussyuleshangpin = discussyuleshangpinService.selectById(id);
        return R.ok().put("data", discussyuleshangpin);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        DiscussyuleshangpinEntity discussyuleshangpin = discussyuleshangpinService.selectById(id);
        return R.ok().put("data", discussyuleshangpin);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody DiscussyuleshangpinEntity discussyuleshangpin, HttpServletRequest request){
    	discussyuleshangpin.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(discussyuleshangpin);
        discussyuleshangpinService.insert(discussyuleshangpin);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody DiscussyuleshangpinEntity discussyuleshangpin, HttpServletRequest request){
    	discussyuleshangpin.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(discussyuleshangpin);
        discussyuleshangpinService.insert(discussyuleshangpin);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody DiscussyuleshangpinEntity discussyuleshangpin, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discussyuleshangpin);
        discussyuleshangpinService.updateById(discussyuleshangpin);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        discussyuleshangpinService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	









}
