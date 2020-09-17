package com.shinho.bean.copier;

import net.sf.cglib.core.KeyFactory;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName BeanCopierManager
 * @Deacription
 * @Author liyongbing
 * @Date 2020/7/27 15:21
 * @Version 1.0
 **/
public class BeanCopierManager {

    private static final BeanCopierKey KEY_FACTORY = (BeanCopierKey) KeyFactory.create(BeanCopierKey.class);
    private Map<Object, BeanCopier> COPY_CACHE = new ConcurrentHashMap<>();
    private static BeanCopierManager INSTANCE = new BeanCopierManager();

    public static BeanCopierManager getInstance() {
        return INSTANCE;
    }

    private BeanCopierManager() {
    }

    public void copyPropertiesIgnoreNull(Object source, Object target) {
        Objects.requireNonNull(source, "Source must not be null");
        Objects.requireNonNull(target, "Target must not be null");
        Class<?> sourceClass = source.getClass();
        Class<?> targetClass = target.getClass();
        getBeanCopier(sourceClass, targetClass, true).copy(source, target, (sourceValue, targetValue, targetValueClass, context) -> {
            if (sourceValue == null) {
                return targetValue;
            }
            if (!targetValueClass.isAssignableFrom(sourceValue.getClass())) {//类型不一致忽略
                return targetValue;
            }
            return sourceValue;
        });
    }

    public void copyProperties(Object source, Object target) {
        Objects.requireNonNull(source, "Source must not be null");
        Objects.requireNonNull(target, "Target must not be null");
        Class<?> sourceClass = source.getClass();
        Class<?> targetClass = target.getClass();
        getBeanCopier(sourceClass, targetClass, false).copy(source, target, null);
    }

    public void copyProperties(Object source, Object target, String[] ignores) {
        Objects.requireNonNull(source, "Source must not be null");
        Objects.requireNonNull(target, "Target must not be null");
        Class<?> sourceClass = source.getClass();
        Class<?> targetClass = target.getClass();
        getBeanCopier(sourceClass, targetClass, true).copy(source, target, (sourceValue, targetValue, target1, context) -> {
            for (String ignore : ignores) {
                if (context.equals(ignore)) {
                    return targetValue;
                }
            }
            return sourceValue;
        });
    }

    public BeanCopier getBeanCopier(Class<?> sourceClass, Class<?> targetClass, boolean useConverter) {
        Object key = KEY_FACTORY.newInstance(sourceClass.getName(), targetClass.getName(), useConverter);
        BeanCopier beanCopier = COPY_CACHE.get(key);
        if (beanCopier == null) {
            COPY_CACHE.putIfAbsent(key, BeanCopier.create(sourceClass, targetClass, useConverter));
        }
        return COPY_CACHE.get(key);
    }

}
