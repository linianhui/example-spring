package example.starter.hbase.admin.dto;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Map;

import lombok.Data;

@Data
public class RegionMetricsDto implements Serializable {
    private static final long serialVersionUID = 2888839426529509588L;

    private String name;

    private int storeCount;

    private int storeFileCount;
    private int storeFileRefCount;
    private int storeFileRefCountOfMaxCompacted;
    private SizeDto storeFileSize;
    private SizeDto storeFileIndexSize;
    private SizeDto storeFileRootLevelIndexSize;
    private SizeDto storeFileUncompressedSize;
    private SizeDto storeFileUncompressedDataIndexSize;

    private long compactingCellCount;
    private long compactedCellCount;

    private SizeDto memStoreSize;
    private SizeDto bloomFilterSize;

    private long requestCount;
    private long writeRequestCount;
    private long readRequestCount;
    private long filteredReadRequestCount;

    private float dataLocality;
    private float dataLocalityForSsd;

    private long blocksLocalWeight;
    private long blocksLocalWithSsdWeight;
    private long blocksTotalWeight;

    private String compactionState;
    private OffsetDateTime lastMajorCompactionTimestamp;
    private long completedSequenceId;
    private Map<String, Long> storeSequenceIds;
}
