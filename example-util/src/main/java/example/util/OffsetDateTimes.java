package example.util;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public final class OffsetDateTimes {
    private OffsetDateTimes() {
    }

    public static OffsetDateTime utc(long epochMilli) {
        return Instant.ofEpochMilli(epochMilli).atOffset(ZoneOffset.UTC);
    }
}
