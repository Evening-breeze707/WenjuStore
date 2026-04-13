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

import com.entity.WenjufenleiEntity;
import com.entity.WenjuxinxiEntity;
import com.entity.view.WenjufenleiView;

import com.service.WenjufenleiService;
import com.service.WenjuxinxiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MD5Util;
import com.utils.MPUtil;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 文具分类
 * 后端接口
 * @author 
 * @email 
 * @date 2023-06-28 16:37:16
 */
@RestController
@RequestMapping("/wenjufenlei")
public class WenjufenleiController {
    @Autowired
    private WenjufenleiService wenjufenleiService;

    @Autowired
    private WenjuxinxiService wenjuxinxiService;


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,WenjufenleiEntity wenjufenlei,
		HttpServletRequest request){
        EntityWrapper<WenjufenleiEntity> ew = new EntityWrapper<WenjufenleiEntity>();

		PageUtils page = wenjufenleiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, wenjufenlei), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,WenjufenleiEntity wenjufenlei, 
		HttpServletRequest request){
        EntityWrapper<WenjufenleiEntity> ew = new EntityWrapper<WenjufenleiEntity>();

		PageUtils page = wenjufenleiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, wenjufenlei), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( WenjufenleiEntity wenjufenlei){
       	EntityWrapper<WenjufenleiEntity> ew = new EntityWrapper<WenjufenleiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( wenjufenlei, "wenjufenlei")); 
        return R.ok().put("data", wenjufenleiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(WenjufenleiEntity wenjufenlei){
        EntityWrapper< WenjufenleiEntity> ew = new EntityWrapper< WenjufenleiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( wenjufenlei, "wenjufenlei")); 
		WenjufenleiView wenjufenleiView =  wenjufenleiService.selectView(ew);
		return R.ok("查询文具分类成功").put("data", wenjufenleiView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        WenjufenleiEntity wenjufenlei = wenjufenleiService.selectById(id);
        return R.ok().put("data", wenjufenlei);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        WenjufenleiEntity wenjufenlei = wenjufenleiService.selectById(id);
        return R.ok().put("data", wenjufenlei);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody WenjufenleiEntity wenjufenlei, HttpServletRequest request){
    	wenjufenlei.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(wenjufenlei);
        wenjufenleiService.insert(wenjufenlei);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody WenjufenleiEntity wenjufenlei, HttpServletRequest request){
    	wenjufenlei.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(wenjufenlei);
        wenjufenleiService.insert(wenjufenlei);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody WenjufenleiEntity wenjufenlei, HttpServletRequest request){
        //ValidatorUtils.validateEntity(wenjufenlei);
        WenjufenleiEntity oldWenjufenlei = wenjufenleiService.selectById(wenjufenlei.getId());
        wenjufenleiService.updateById(wenjufenlei);//全部更新
        if(oldWenjufenlei != null && StringUtils.isNotBlank(oldWenjufenlei.getWenjufenlei())
                && StringUtils.isNotBlank(wenjufenlei.getWenjufenlei())
                && !oldWenjufenlei.getWenjufenlei().equals(wenjufenlei.getWenjufenlei())) {
            WenjuxinxiEntity wenjuxinxiEntity = new WenjuxinxiEntity();
            wenjuxinxiEntity.setWenjufenlei(wenjufenlei.getWenjufenlei());
            wenjuxinxiService.update(wenjuxinxiEntity,
                    new EntityWrapper<WenjuxinxiEntity>().eq("wenjufenlei", oldWenjufenlei.getWenjufenlei()));
        }
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        wenjufenleiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	









}
