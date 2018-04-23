package com.taotaox.common.util.web.utils;

import java.lang.reflect.Array;

public class ArrayUtil {
	
	public static boolean isEmpty(Object[] array) {
		return (array == null || (array != null && array.length == 0)) ? true : false;
	}
	
	public static boolean nonEmpty(Object[] array) {
		return (array != null || (array != null && array.length > 0)) ? true : false;
	}
	
	public static String[] mergeArray(String[] array, String... elements) {
		return mergeArray(String.class, array, elements);
	}

	@SuppressWarnings("unchecked")
	public static <T> T[] mergeArray(Class<T> clazz, T[] array, T[] elements) {
		T[] subArray = ObjectUtils.value(array, (T[]) Array.newInstance(clazz, 0));
		if (ObjectUtils.isEmpty(elements)) {
			return subArray;
		}
		T[] mergedArray = (T[]) Array.newInstance(clazz, subArray.length + elements.length);
		System.arraycopy(subArray, 0, mergedArray, 0, subArray.length);
		System.arraycopy(elements, 0, mergedArray, subArray.length, elements.length);
		return mergedArray;
	}
	
}
