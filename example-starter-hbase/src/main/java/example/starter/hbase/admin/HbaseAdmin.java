package example.starter.hbase.admin;

import java.util.Collection;
import java.util.List;

import example.starter.hbase.admin.dto.*;

public interface HbaseAdmin {
    ConfigDto getClientConfig();

    ServerNameDto getMasterServerName();

    List<ServerNameDto> getRegionServerNames();

    List<TableNameDto> getTableNames();

    List<RegionDto> getRegions(TableNameDto tableName);

    List<RegionDto> getRegions(ServerNameDto serverName);

    TableDescriptorDto getTableDescriptor(TableNameDto tableName);

    List<RegionLocationDto> getRegionLocations(TableNameDto tableName, Collection<String> rows);
}
