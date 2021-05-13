package io.github.linianhui.springexample.bean;

import java.util.Arrays;
import java.util.stream.Collectors;

public class LogUtil {
    public static void logCaller(Object... args) {
        final Thread thread = Thread.currentThread();
        final StackTraceElement caller = thread.getStackTrace()[2];
        final String argsString = Arrays.stream(args)
            .map(Object::toString)
            .collect(Collectors.joining(","));

        System.out.printf(
            "\n[CALL] pid=%d threadName=%s %s#%s args=[%s]\n",
            thread.getId(),
            thread.getName(),
            caller.getClassName(),
            caller.getMethodName(),
            argsString
        );
    }
}
