package com.entity.view;

import com.entity.YuleshangpinEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
 

/**
 * 娱乐商品
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2023-06-28 16:37:16
 */
@TableName("yuleshangpin")
public class YuleshangpinView  extends YuleshangpinEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public YuleshangpinView(){
	}
 
 	public YuleshangpinView(YuleshangpinEntity yuleshangpinEntity){
 	try {
			BeanUtils.copyProperties(this, yuleshangpinEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}
}
