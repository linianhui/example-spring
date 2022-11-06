package example.util;

import java.lang.reflect.Constructor;
import java.time.Instant;
import java.util.*;

import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;
import org.springframework.boot.context.metrics.buffering.StartupTimeline;

public class DescSortedBufferingApplicationStartup extends BufferingApplicationStartup {
    public DescSortedBufferingApplicationStartup(int capacity) {
        super(capacity);
    }

    public StartupTimeline getBufferedTimeline() {
        try {
            StartupTimeline unSorted = super.getBufferedTimeline();

            List<StartupTimeline.TimelineEvent> timelineEventList = new ArrayList<>(unSorted.getEvents());
            timelineEventList.sort(Comparator.comparing(StartupTimeline.TimelineEvent::getDuration).reversed());
            Instant startTime = unSorted.getStartTime();

            Constructor<?> constructor = StartupTimeline.class.getDeclaredConstructor(Instant.class, List.class);
            constructor.setAccessible(true);
            return (StartupTimeline) constructor.newInstance(startTime, timelineEventList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
