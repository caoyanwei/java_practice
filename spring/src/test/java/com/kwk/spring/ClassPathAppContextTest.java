package com.kwk.spring;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ClassPathAppContextTest {
    @Test
    public void basicTest() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"appcontext-main.xml"});
        BasicService service = (BasicService)context.getBean("basicService", BasicService.class);
        System.out.println(service.hello("Spring"));
        context.getAutowireCapableBeanFactory().autowireBean(null);
    }
}
