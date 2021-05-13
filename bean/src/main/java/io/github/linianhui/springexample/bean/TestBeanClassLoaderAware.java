package io.github.linianhui.springexample.bean;

import org.springframework.beans.factory.BeanClassLoaderAware;

public class TestBeanClassLoaderAware implements BeanClassLoaderAware {

    public TestBeanClassLoaderAware() {
        LogUtil.logCaller();
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        LogUtil.logCaller(classLoader);
    }
}
