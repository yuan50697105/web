package com.web.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

public class ObjectUtils {
	public static boolean IsAllNull(Object object) {
		Class<? extends Object> class1 = object.getClass();
		Field[] fields = class1.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			try {
				String str = BeanUtils.getProperty(object, field.getName());
				if (StringUtils.isNotEmpty(str)) {
					return false;
				}
			} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
}
