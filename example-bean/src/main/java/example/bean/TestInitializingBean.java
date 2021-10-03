package example.bean;

import org.springframework.beans.factory.InitializingBean;

public class TestInitializingBean implements InitializingBean {
    public TestInitializingBean() {
        LogUtil.logCaller();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        LogUtil.logCaller();
    }
}
