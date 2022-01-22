package example.starter.hbase.admin.mapper;

import example.starter.hbase.admin.dto.ClusterMetricsDto;
import org.apache.hadoop.hbase.ClusterMetrics;

public final class ClusterMetricsMapper {
    private ClusterMetricsMapper() {
    }

    public static ClusterMetricsDto map(final ClusterMetrics source) {
        if (source == null) {
            return null;
        }

        ClusterMetricsDto result = new ClusterMetricsDto();
        result.setHbaseVersion(source.getHBaseVersion());
        result.setClusterId(source.getClusterId());
        result.setMasterInfoPort(source.getMasterInfoPort());
        result.setBalancerOn(source.getBalancerOn());
        result.setMasterName(ServerNameMapper.map(source.getMasterName()));
        result.setServerNames(ServerNameMapper.map(source.getServersName()));
        result.setDeadServerNames(ServerNameMapper.map(source.getDeadServerNames()));
        result.setBackupMasterNames(ServerNameMapper.map(source.getBackupMasterNames()));
        result.setMasterCoprocessorNames(source.getMasterCoprocessorNames());
        result.setInTransitionRegions(RegionStateMapper.map(source.getRegionStatesInTransition()));
        result.setTableRegionStateCounts(RegionStateCountMapper.map(source.getTableRegionStatesCount()));
        result.setServerMetrics(ServerMetricsMapper.map(source.getLiveServerMetrics()));
        return result;
    }
}