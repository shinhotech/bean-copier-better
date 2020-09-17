package com.shinho.bean.copier;

/**
 * @ClassName Converter
 * @Deacription
 * @Author liyongbing
 * @Date 2020/7/27 13:52
 * @Version 1.0
 **/
@FunctionalInterface
public interface Converter {

    Object convert(Object sourceValue, Object targetValue, Class<?> targetValueClass, Object context);
}
