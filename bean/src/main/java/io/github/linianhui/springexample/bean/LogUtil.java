package io.github.linianhui.springexample.bean;

public class LogUtil {
    public static void logCaller() {
        final Thread thread = Thread.currentThread();
        final StackTraceElement caller = thread.getStackTrace()[2];
        System.out.printf(
                "\n[CALL] pid=%d threadName=%s %s#%s\n",
                thread.getId(),
                thread.getName(),
                caller.getClassName(),
                caller.getMethodName()
        );
    }
}
