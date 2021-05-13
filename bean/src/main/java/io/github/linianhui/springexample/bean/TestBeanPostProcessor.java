package io.github.linianhui.springexample.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class TestBeanPostProcessor implements BeanPostProcessor {

    public TestBeanPostProcessor() {
        LogUtil.logCaller();
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        LogUtil.logCaller(bean, beanName);
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        LogUtil.logCaller(bean, beanName);
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
