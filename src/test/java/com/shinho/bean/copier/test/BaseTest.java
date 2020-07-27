package com.shinho.bean.copier.test;

import com.shinho.bean.copier.BeanCopierManager;
import com.shinho.bean.copier.test.pojo.MA;
import org.junit.Test;

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
        MA bean1 = new MA();
        bean1.setLongP(1L);
        bean1.setFloatP(3.5F);
        bean1.setIntP(33);
        bean1.setBooleanP(false);
        bean1.setName("ss");
        bean1.setStringP("test");
        MA bean2 = new MA();
        bean2.setId(43L);
        long start = System.currentTimeMillis();
        beanCopierManager.copyPropertiesIgnoreNull(bean1, bean2);
        System.out.println(System.currentTimeMillis() - start);
        start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            beanCopierManager.copyPropertiesIgnoreNull(bean1, bean2);
        }
        System.out.println(System.currentTimeMillis() - start);
        assertEquals(Long.valueOf(43L), bean2.getId());
        assertEquals("test", bean2.getStringP());
    }

}
