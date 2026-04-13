package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;


/**
 * 文具信息
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2023-06-28 16:37:16
 */
@TableName("wenjuxinxi")
public class WenjuxinxiEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public WenjuxinxiEntity() {
		
	}
	
	public WenjuxinxiEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 主键id
	 */
	@TableId
	private Long id;
	/**
	 * 文具名称
	 */
					
	private String wenjumingcheng;
	
	/**
	 * 文具分类
	 */
					
	private String wenjufenlei;
	
	/**
	 * 文具品牌
	 */
					
	private String wenjupinpai;
	
	/**
	 * 文具图片
	 */
					
	private String wenjutupian;
	
	/**
	 * 文具规格
	 */
					
	private String wenjuguige;
	
	/**
	 * 文具详情
	 */
					
	private String wenjuxiangqing;
	
	/**
	 * 单限
	 */
					
	private Integer onelimittimes;
	
	/**
	 * 库存
	 */
					
	private Integer alllimittimes;
	
	/**
	 * 最近点击时间
	 */
				
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 		
	private Date clicktime;
	
	/**
	 * 点击次数
	 */
					
	private Integer clicknum;
	
	/**
	 * 价格
	 */
					
	private Double price;
	
	
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date addtime;

	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 设置：文具名称
	 */
	public void setWenjumingcheng(String wenjumingcheng) {
		this.wenjumingcheng = wenjumingcheng;
	}
	/**
	 * 获取：文具名称
	 */
	public String getWenjumingcheng() {
		return wenjumingcheng;
	}
	/**
	 * 设置：文具分类
	 */
	public void setWenjufenlei(String wenjufenlei) {
		this.wenjufenlei = wenjufenlei;
	}
	/**
	 * 获取：文具分类
	 */
	public String getWenjufenlei() {
		return wenjufenlei;
	}
	/**
	 * 设置：文具品牌
	 */
	public void setWenjupinpai(String wenjupinpai) {
		this.wenjupinpai = wenjupinpai;
	}
	/**
	 * 获取：文具品牌
	 */
	public String getWenjupinpai() {
		return wenjupinpai;
	}
	/**
	 * 设置：文具图片
	 */
	public void setWenjutupian(String wenjutupian) {
		this.wenjutupian = wenjutupian;
	}
	/**
	 * 获取：文具图片
	 */
	public String getWenjutupian() {
		return wenjutupian;
	}
	/**
	 * 设置：文具规格
	 */
	public void setWenjuguige(String wenjuguige) {
		this.wenjuguige = wenjuguige;
	}
	/**
	 * 获取：文具规格
	 */
	public String getWenjuguige() {
		return wenjuguige;
	}
	/**
	 * 设置：文具详情
	 */
	public void setWenjuxiangqing(String wenjuxiangqing) {
		this.wenjuxiangqing = wenjuxiangqing;
	}
	/**
	 * 获取：文具详情
	 */
	public String getWenjuxiangqing() {
		return wenjuxiangqing;
	}
	/**
	 * 设置：单限
	 */
	public void setOnelimittimes(Integer onelimittimes) {
		this.onelimittimes = onelimittimes;
	}
	/**
	 * 获取：单限
	 */
	public Integer getOnelimittimes() {
		return onelimittimes;
	}
	/**
	 * 设置：库存
	 */
	public void setAlllimittimes(Integer alllimittimes) {
		this.alllimittimes = alllimittimes;
	}
	/**
	 * 获取：库存
	 */
	public Integer getAlllimittimes() {
		return alllimittimes;
	}
	/**
	 * 设置：最近点击时间
	 */
	public void setClicktime(Date clicktime) {
		this.clicktime = clicktime;
	}
	/**
	 * 获取：最近点击时间
	 */
	public Date getClicktime() {
		return clicktime;
	}
	/**
	 * 设置：点击次数
	 */
	public void setClicknum(Integer clicknum) {
		this.clicknum = clicknum;
	}
	/**
	 * 获取：点击次数
	 */
	public Integer getClicknum() {
		return clicknum;
	}
	/**
	 * 设置：价格
	 */
	public void setPrice(Double price) {
		this.price = price;
	}
	/**
	 * 获取：价格
	 */
	public Double getPrice() {
		return price;
	}

}
