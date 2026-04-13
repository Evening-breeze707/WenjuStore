package com.entity.view;

import com.entity.WenjufenleiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
 

/**
 * 文具分类
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2023-06-28 16:37:16
 */
@TableName("wenjufenlei")
public class WenjufenleiView  extends WenjufenleiEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public WenjufenleiView(){
	}
 
 	public WenjufenleiView(WenjufenleiEntity wenjufenleiEntity){
 	try {
			BeanUtils.copyProperties(this, wenjufenleiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}
}
