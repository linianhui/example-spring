package example.util;

import java.lang.reflect.Constructor;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;
import org.springframework.boot.context.metrics.buffering.StartupTimeline;
import org.springframework.core.metrics.StartupStep;

@Slf4j
public class DescSortedBufferingApplicationStartup extends BufferingApplicationStartup {
    public DescSortedBufferingApplicationStartup(int capacity) {
        super(capacity);
    }

    private final Map<String, InvokeStartCountDto> counts = new ConcurrentHashMap<>();

    public List<InvokeStartCountDto> getCounts() {
        return counts.values()
            .stream()
            .sorted(Comparator.comparing(InvokeStartCountDto::getCount).reversed())
            .collect(Collectors.toList());
    }

    public StartupStep start(String name) {
        StartupStep result = super.start(name);
        InvokeStartCountDto dto = InvokeStartCountDto.of(Thread.currentThread());
        counts.computeIfAbsent(InvokeStartCountDto.key(dto), x -> dto).incCount();
        return result;
    }

    public StartupTimeline getBufferedTimeline() {
        try {
            StartupTimeline unSorted = super.getBufferedTimeline();

            List<StartupTimeline.TimelineEvent> timelineEventList = new ArrayList<>(unSorted.getEvents());
            timelineEventList.sort(Comparator.comparing(StartupTimeline.TimelineEvent::getDuration).reversed());
            Instant startTime = unSorted.getStartTime();

            Constructor<?> constructor = StartupTimeline.class.getDeclaredConstructor(Instant.class, List.class);
            constructor.setAccessible(true);

            Long sum = timelineEventList.stream().mapToLong(x -> x.getDuration().toMillis()).sum();
            log.info("StartupTimeline sum-ms={}", sum);
            return (StartupTimeline) constructor.newInstance(startTime, timelineEventList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Data
    public static class InvokeStartCountDto {
        private long threadId;
        private String threadName;
        private String className;
        private String methodName;
        private String fileName;
        private int line;
        private int count;

        public void incCount() {
            count++;
        }

        public static String key(InvokeStartCountDto dto) {
            return dto.threadId + dto.className + dto.fileName + dto.methodName + dto.line;
        }

        public static InvokeStartCountDto of(Thread thread) {
            InvokeStartCountDto result = new InvokeStartCountDto();
            result.threadId = thread.getId();
            result.threadName = thread.getName();
            StackTraceElement caller = thread.getStackTrace()[3];
            result.className = caller.getClassName();
            result.fileName = caller.getFileName();
            result.methodName = caller.getMethodName();
            result.line = caller.getLineNumber();
            return result;
        }
    }
}
