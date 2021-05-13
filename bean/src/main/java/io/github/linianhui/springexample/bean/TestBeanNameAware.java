package io.github.linianhui.springexample.bean;

import org.springframework.beans.factory.BeanNameAware;

public class TestBeanNameAware implements BeanNameAware {
    public TestBeanNameAware() {
        LogUtil.logCaller();
    }

    @Override
    public void setBeanName(String name) {
        LogUtil.logCaller(name);
    }
}
