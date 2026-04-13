package com.entity.view;

import com.entity.DiscusswenjuxinxiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
 

/**
 * 文具信息评论表
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2023-06-28 16:37:17
 */
@TableName("discusswenjuxinxi")
public class DiscusswenjuxinxiView  extends DiscusswenjuxinxiEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public DiscusswenjuxinxiView(){
	}
 
 	public DiscusswenjuxinxiView(DiscusswenjuxinxiEntity discusswenjuxinxiEntity){
 	try {
			BeanUtils.copyProperties(this, discusswenjuxinxiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}
}
