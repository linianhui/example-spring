package example.cms.web.hbase;

import java.util.*;

import example.starter.hbase.HbaseAdmin;
import example.starter.hbase.HbaseTemplate;
import example.starter.hbase.dto.*;
import example.starter.hbase.mapper.ServerNameMapper;
import example.starter.hbase.mapper.TableNameMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1/hbase")
public class HbaseController {

    @Autowired
    private HbaseTemplate hbaseTemplate;

    @GetMapping
    public Object getAll() {
        Map<String, Object> result = new TreeMap<>();
        result.put("clientConfig", getClientConfig());
        result.put("masterServerName", getMasterServerName());
        result.put("regionServerNames", getRegionServerNames());
        result.put("tableNames", getTableNames());
        return result;
    }

    @GetMapping(path = "client-config")
    public Object getClientConfig() {
        return getHbaseAdmin().getClientConfig();
    }

    @GetMapping(path = "master-name")
    public Object getMasterServerName() {
        return getHbaseAdmin().getMasterServerName();
    }

    @GetMapping(path = "region-server-name")
    public Object getRegionServerNames() {
        return getHbaseAdmin().getRegionServerNames();
    }

    @GetMapping(path = "table-name")
    public Object getTableNames() {
        return getHbaseAdmin().getTableNames();
    }

    @GetMapping(path = "table/{tableName}")
    public Object getTable(
        @PathVariable(name = "tableName", required = false) String tableName
    ) {
        HbaseAdmin hbaseAdmin = getHbaseAdmin();
        if (tableName != null) {
            return hbaseAdmin.getTableDescriptor(TableNameMapper.map(tableName));
        } else {
            List<TableNameDto> tableNameDtoList = hbaseAdmin.getTableNames();
            List<TableDescriptorDto> result = new ArrayList<>();
            for (TableNameDto tableNameDto : tableNameDtoList) {
                result.add(hbaseAdmin.getTableDescriptor(tableNameDto));
            }
            return result;
        }
    }

    @GetMapping(path = "table-region/{tableName}")
    public Object getTableRegions(
        @PathVariable(name = "tableName", required = false) String tableName
    ) {
        HbaseAdmin hbaseAdmin = getHbaseAdmin();
        if (tableName != null) {
            return hbaseAdmin.getRegions(TableNameMapper.map(tableName));
        } else {
            List<TableNameDto> tableNameDtoList = hbaseAdmin.getTableNames();
            List<RegionDto> result = new ArrayList<>();
            for (TableNameDto tableNameDto : tableNameDtoList) {
                result.addAll(hbaseAdmin.getRegions(tableNameDto));
            }
            return result;
        }
    }

    @GetMapping(path = "server-region")
    public Object getServerRegions(
        @RequestParam(name = "serverName", required = false) String serverName
    ) {
        HbaseAdmin hbaseAdmin = getHbaseAdmin();
        if (serverName != null) {
            return hbaseAdmin.getRegions(ServerNameMapper.map(serverName));
        } else {
            List<ServerNameDto> serverNameDtoList = hbaseAdmin.getRegionServerNames();
            List<RegionDto> result = new ArrayList<>();
            for (ServerNameDto serverNameDto : serverNameDtoList) {
                result.addAll(hbaseAdmin.getRegions(serverNameDto));
            }
            return result;
        }
    }

    @GetMapping(path = "row-region/{tableName}")
    public Object getTableRegions(
        @PathVariable(name = "tableName", required = true) String tableName,
        @RequestParam(name = "row", required = true) List<String> rows
    ) {
        HbaseAdmin hbaseAdmin = getHbaseAdmin();
        return hbaseAdmin.getRegionLocations(TableNameMapper.map(tableName), rows);
    }

    private HbaseAdmin getHbaseAdmin() {
        return hbaseTemplate.getAdmin();
    }
}
