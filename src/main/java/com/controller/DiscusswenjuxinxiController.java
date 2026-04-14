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

import com.entity.DiscusswenjuxinxiEntity;
import com.entity.OrdersEntity;
import com.entity.view.DiscusswenjuxinxiView;

import com.service.DiscusswenjuxinxiService;
import com.service.OrdersService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MD5Util;
import com.utils.MPUtil;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 文具信息评论表
 * 后端接口
 * @author 
 * @email 
 * @date 2023-06-28 16:37:17
 */
@RestController
@RequestMapping("/discusswenjuxinxi")
public class DiscusswenjuxinxiController {
    @Autowired
    private DiscusswenjuxinxiService discusswenjuxinxiService;

    @Autowired
    private OrdersService ordersService;


    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,DiscusswenjuxinxiEntity discusswenjuxinxi,
		HttpServletRequest request){
        EntityWrapper<DiscusswenjuxinxiEntity> ew = new EntityWrapper<DiscusswenjuxinxiEntity>();

		PageUtils page = discusswenjuxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discusswenjuxinxi), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,DiscusswenjuxinxiEntity discusswenjuxinxi, 
		HttpServletRequest request){
        EntityWrapper<DiscusswenjuxinxiEntity> ew = new EntityWrapper<DiscusswenjuxinxiEntity>();

		PageUtils page = discusswenjuxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discusswenjuxinxi), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( DiscusswenjuxinxiEntity discusswenjuxinxi){
       	EntityWrapper<DiscusswenjuxinxiEntity> ew = new EntityWrapper<DiscusswenjuxinxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( discusswenjuxinxi, "discusswenjuxinxi")); 
        return R.ok().put("data", discusswenjuxinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(DiscusswenjuxinxiEntity discusswenjuxinxi){
        EntityWrapper< DiscusswenjuxinxiEntity> ew = new EntityWrapper< DiscusswenjuxinxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( discusswenjuxinxi, "discusswenjuxinxi")); 
		DiscusswenjuxinxiView discusswenjuxinxiView =  discusswenjuxinxiService.selectView(ew);
		return R.ok("查询文具信息评论表成功").put("data", discusswenjuxinxiView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        DiscusswenjuxinxiEntity discusswenjuxinxi = discusswenjuxinxiService.selectById(id);
        return R.ok().put("data", discusswenjuxinxi);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        DiscusswenjuxinxiEntity discusswenjuxinxi = discusswenjuxinxiService.selectById(id);
        return R.ok().put("data", discusswenjuxinxi);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody DiscusswenjuxinxiEntity discusswenjuxinxi, HttpServletRequest request){
    	discusswenjuxinxi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(discusswenjuxinxi);
        discusswenjuxinxiService.insert(discusswenjuxinxi);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody DiscusswenjuxinxiEntity discusswenjuxinxi, HttpServletRequest request){
    	Long userId = (Long) request.getSession().getAttribute("userId");
    	if(userId == null) {
    		return R.error(401, "请先登录");
    	}
    	String tableName = (String) request.getSession().getAttribute("tableName");
    	if(!"yonghu".equals(tableName)) {
    		return R.error("只有用户可以评价");
    	}
    	if(discusswenjuxinxi.getRefid() == null) {
    		return R.error("缺少商品信息");
    	}
    	int completedOrderCount = ordersService.selectCount(
    			new EntityWrapper<OrdersEntity>()
    					.eq("userid", userId)
    					.eq("goodid", discusswenjuxinxi.getRefid())
    					.eq("tablename", "wenjuxinxi")
    					.eq("status", "已完成")
    	);
    	if(completedOrderCount <= 0) {
    		return R.error("请在完成交易后再进行评价");
    	}
    	int commentCount = discusswenjuxinxiService.selectCount(
    			new EntityWrapper<DiscusswenjuxinxiEntity>()
    					.eq("userid", userId)
    					.eq("refid", discusswenjuxinxi.getRefid())
    	);
    	if(commentCount >= completedOrderCount) {
    		return R.error("每完成一笔订单只能评价一次");
    	}
    	discusswenjuxinxi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(discusswenjuxinxi);
    	discusswenjuxinxi.setUserid(userId);
        discusswenjuxinxiService.insert(discusswenjuxinxi);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody DiscusswenjuxinxiEntity discusswenjuxinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discusswenjuxinxi);
        discusswenjuxinxiService.updateById(discusswenjuxinxi);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        discusswenjuxinxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	









}
