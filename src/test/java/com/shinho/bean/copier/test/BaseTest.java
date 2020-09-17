package com.shinho.bean.copier.test;

import com.shinho.bean.copier.BeanCopierManager;
import com.shinho.bean.copier.test.pojo.MA;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * @ClassName BaseTest
 * @Deacription
 * @Author liyongbing
 * @Date 2020/7/27 16:02
 * @Version 1.0
 **/
public class BaseTest {


    @Test
    public void test() {
        BeanCopierManager beanCopierManager = BeanCopierManager.getInstance();
        MA bean1 = new MA()
                .setLongP(1L)
                .setFloatP(3.5F)
                .setIntP(33)
                .setBooleanP(false)
                .setName("ss")
                .setStringP("test");
        MA bean2 = new MA();
        bean2.setId(43L);
        beanCopierManager.getBeanCopier(MA.class, MA.class, true);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            beanCopierManager.copyPropertiesIgnoreNull(bean1, bean2);
        }
        System.out.println(System.currentTimeMillis() - start);
        assertEquals(Long.valueOf(43L), bean2.getId());
        assertEquals("test", bean2.getStringP());
        start = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            BeanUtils.copyProperties(bean1, bean2, getNullPropertyNames(bean1));
        }
        System.out.println(System.currentTimeMillis() - start);
    }


    private static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

}
