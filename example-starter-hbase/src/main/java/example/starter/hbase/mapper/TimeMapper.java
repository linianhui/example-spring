package example.starter.hbase.mapper;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public final class TimeMapper {

    private TimeMapper() {
    }

    public static OffsetDateTime map(long epochMilli) {
        return Instant.ofEpochMilli(epochMilli).atOffset(ZoneOffset.UTC);
    }
}
