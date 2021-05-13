package io.github.linianhui.springexample.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

public class TestBeanFactoryAware implements BeanFactoryAware {
    public TestBeanFactoryAware() {
        LogUtil.logCaller();
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        LogUtil.logCaller(beanFactory);
    }
}
