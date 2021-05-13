package io.github.linianhui.springexample.bean;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        LogUtil.logCaller("start");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
            "beans.xml"
        );
        context.registerShutdownHook();
        LogUtil.logCaller("getBean");
        TestBean testBean = context.getBean("testBean", TestBean.class);
        System.out.println(testBean);
    }
}
