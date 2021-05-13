package io.github.linianhui.springexample.bean;

public class TestBean {
    private String name;

    public TestBean() {
        LogUtil.logCaller();
    }

    public String getName() {
        LogUtil.logCaller();
        return name;
    }

    public void setName(String name) {
        this.name = name;
        LogUtil.logCaller(name);
    }

    public void beanInitMethod() {
        LogUtil.logCaller();
    }

    public void beanDestroyMethod() {
        LogUtil.logCaller();
    }

    @Override
    public String toString() {
        return "TestBean{" +
                "name='" + name + '\'' +
                '}';
    }
}
