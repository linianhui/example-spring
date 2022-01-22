package example.starter.hbase.admin.mapper;

import java.util.Map;

import example.starter.hbase.admin.dto.RegionMetricsDto;
import example.starter.hbase.mapper.MapMapper;
import example.starter.hbase.mapper.TimeMapper;
import org.apache.hadoop.hbase.RegionMetrics;
import org.apache.hadoop.hbase.util.Bytes;

public final class RegionMetricsMapper {
    private RegionMetricsMapper() {
    }

    public static Map<String, RegionMetricsDto> map(
        final Map<byte[], RegionMetrics> source) {
        return MapMapper.map(source, Bytes::toString, RegionMetricsMapper::map);
    }

    public static RegionMetricsDto map(final RegionMetrics source) {
        if (source == null) {
            return null;
        }


        RegionMetricsDto result = new RegionMetricsDto();
        result.setName(source.getNameAsString());
        result.setStoreCount(source.getStoreCount());
        result.setStoreFileCount(source.getStoreFileCount());
        result.setStoreFileRefCount(source.getStoreRefCount());
        result.setStoreFileRefCountOfMaxCompacted(source.getMaxCompactedStoreFileRefCount());
        result.setCompactedCellCount(source.getCompactedCellCount());
        result.setCompactingCellCount(source.getCompactingCellCount());
        result.setStoreFileSize(SizeMapper.map(source.getStoreFileSize()));
        result.setMemStoreSize(SizeMapper.map(source.getMemStoreSize()));
        result.setStoreFileIndexSize(SizeMapper.map(source.getStoreFileIndexSize()));
        result.setBloomFilterSize(SizeMapper.map(source.getBloomFilterSize()));
        result.setStoreFileUncompressedSize(SizeMapper.map(source.getUncompressedStoreFileSize()));
        result.setStoreFileUncompressedDataIndexSize(SizeMapper.map(source.getStoreFileUncompressedDataIndexSize()));
        result.setStoreFileRootLevelIndexSize(SizeMapper.map(source.getStoreFileRootLevelIndexSize()));
        result.setRequestCount(source.getRequestCount());
        result.setReadRequestCount(source.getReadRequestCount());
        result.setWriteRequestCount(source.getWriteRequestCount());
        result.setFilteredReadRequestCount(source.getFilteredReadRequestCount());
        result.setCompletedSequenceId(source.getCompletedSequenceId());
        result.setDataLocality(source.getDataLocality());
        result.setLastMajorCompactionTimestamp(TimeMapper.map(source.getLastMajorCompactionTimestamp()));
        result.setDataLocalityForSsd(source.getDataLocalityForSsd());
        result.setBlocksLocalWeight(source.getBlocksLocalWeight());
        result.setBlocksLocalWithSsdWeight(source.getBlocksLocalWithSsdWeight());
        result.setBlocksTotalWeight(source.getBlocksTotalWeight());
        result.setCompactionState(source.getCompactionState().name());
        result.setStoreSequenceIds(MapMapper.mapKey(source.getStoreSequenceId(), Bytes::toString));
        return result;
    }

}



