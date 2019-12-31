package io.github.linianhui.util;

public abstract class BaseTest {

    protected ResourceUtil getResourceUtil() {
        return ResourceUtil.of();
    }

    protected AssertUtil getAssertUtil() {
        return AssertUtil.of(this.getResourceUtil());
    }
}
