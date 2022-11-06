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

    private final Map<String, CallerCountDto> times = new ConcurrentHashMap<>();

    public List<CallerCountDto> getCounts() {
        return times.values()
            .stream()
            .sorted(Comparator.comparing(CallerCountDto::getCount).reversed())
            .collect(Collectors.toList());
    }

    public StartupStep start(String name) {
        StartupStep result = super.start(name);
        Thread thread = Thread.currentThread();
        StackTraceElement caller = thread.getStackTrace()[2];
        CallerCountDto dto = CallerCountDto.of(thread, caller);
        times.computeIfAbsent(CallerCountDto.key(dto), x -> dto).incCount();
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
    public static class CallerCountDto {
        private long threadId;
        private String className;
        private String method;
        private String file;
        private int line;
        private int count;

        public void incCount() {
            count++;
        }

        public static String key(CallerCountDto dto) {
            return dto.threadId + dto.className + dto.file + dto.method + dto.line;
        }

        public static CallerCountDto of(Thread thread, StackTraceElement element) {
            CallerCountDto result = new CallerCountDto();
            result.threadId = thread.getId();
            result.className = element.getClassName();
            result.file = element.getFileName();
            result.method = element.getMethodName();
            result.line = element.getLineNumber();
            return result;
        }
    }
}
