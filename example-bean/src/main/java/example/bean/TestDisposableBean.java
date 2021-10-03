package example.bean;

import org.springframework.beans.factory.DisposableBean;

public class TestDisposableBean implements DisposableBean {

    public TestDisposableBean() {
        LogUtil.logCaller();
    }

    @Override
    public void destroy() throws Exception {
        LogUtil.logCaller();
    }
}
