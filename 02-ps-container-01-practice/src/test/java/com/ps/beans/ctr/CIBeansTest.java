package com.ps.beans.ctr;

import com.ps.beans.SimpleBeanImpl;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

/**
 * Created by iuliana.cosmina on 3/26/16.
 */
public class CIBeansTest {
    private Logger logger = LoggerFactory.getLogger(CIBeansTest.class);

    @Test
    public void testConfig() {
        // ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/ctr/sample-config-01.xml");
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/ctr/sample-config-02.xml");

        logger.info(" --- All beans in context --- ");
        for (String beanName : ctx.getBeanDefinitionNames()) {
            logger.info("Bean " + beanName + " of type " + ctx.getBean(beanName)
                                                              .getClass()
                                                              .getSimpleName());
        }

        //TODO 3. Retrieve beans of types ComplexBean and make sure their dependencies were correctly set.
        ComplexBeanImpl complexBean0 = ctx.getBean("complexBean0", ComplexBeanImpl.class);
        assertTrue(!complexBean0.isComplex());
        assertSame(complexBean0.getSimpleBean().getClass(), SimpleBeanImpl.class);

        ComplexBeanImpl complexBean1 = ctx.getBean("complexBean1", ComplexBeanImpl.class);
        assertSame(complexBean1.getSimpleBean().getClass(), SimpleBeanImpl.class);
        assertTrue(complexBean1.isComplex());

        ComplexBean2Impl complexBean2 = ctx.getBean("complexBean2", ComplexBean2Impl.class);
        assertSame(complexBean2.getSimpleBean1().getClass(), SimpleBeanImpl.class);
        assertSame(complexBean2.getSimpleBean2().getClass(), SimpleBeanImpl.class);
    }
}
