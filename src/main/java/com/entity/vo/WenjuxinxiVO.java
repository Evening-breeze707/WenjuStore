package com.entity.vo;

import com.entity.WenjuxinxiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 文具信息
 * 手机端接口返回实体辅助类 
 * （主要作用去除一些不必要的字段）
 * @author 
 * @email 
 * @date 2023-06-28 16:37:16
 */
public class WenjuxinxiVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
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
