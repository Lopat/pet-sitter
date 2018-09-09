package com.ps.beans.set;

import com.ps.beans.SimpleBeanImpl;
import com.ps.beans.ctr.ComplexBean2Impl;
import com.ps.sample.SimpleBean;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

/**
 * Created by iuliana.cosmina on 3/26/16.
 */
public class SIBeansTest {
    private Logger logger = LoggerFactory.getLogger(SIBeansTest.class);

    @Test
    public void testConfig() {
        // ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/set/sample-config-01.xml");
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/set/sample-config-02.xml");

        logger.info(" --- All beans in context --- ");
        for(String beanName :  ctx.getBeanDefinitionNames()) {
            logger.info("Bean " + beanName + " of type " + ctx.getBean(beanName).getClass().getSimpleName());
        }

        //TODO 4. Retrieve beans of types ComplexBean and make sure their dependencies were correctly set.

        ComplexBeanImpl complexBean0 = ctx.getBean("complexBean0", ComplexBeanImpl.class);
        assertFalse(complexBean0.isComplex());
        assertEquals(complexBean0.getSimpleBean().getClass(), SimpleBeanImpl.class);


        ComplexBeanImpl complexBean1 = (ComplexBeanImpl) ctx.getBean("complexBean1");
        assertTrue(complexBean1.isComplex());
        assertEquals(complexBean1.getSimpleBean().getClass(), SimpleBeanImpl.class);

        ComplexBean2Impl complexBean2 = ctx.getBean("complexBean1", ComplexBean2Impl.class);
        assertEquals(complexBean2.getSimpleBean1().getClass(), SimpleBeanImpl.class);
        assertEquals(complexBean2.getSimpleBean2().getClass(), SimpleBeanImpl.class);
    }
}
