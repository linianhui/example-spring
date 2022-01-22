package example.cms.web.hbase;

import java.util.*;

import com.google.common.collect.Maps;
import example.starter.hbase.HbaseTemplate;
import example.starter.hbase.admin.HbaseAdmin;
import example.starter.hbase.admin.dto.ServerNameDto;
import example.starter.hbase.admin.dto.TableNameDto;
import example.starter.hbase.admin.mapper.ServerNameMapper;
import example.starter.hbase.admin.mapper.TableNameMapper;
import org.apache.commons.collections.CollectionUtils;
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
        result.put("tableRegions", getTableRegionsOfAll());
        result.put("serverRegions", getServerRegionsOfAll());
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

    @GetMapping(path = "table/all")
    public Object getTableOfAll() {
        HbaseAdmin hbaseAdmin = getHbaseAdmin();
        List<TableNameDto> tableNames = hbaseAdmin.getTableNames();
        if (CollectionUtils.isEmpty(tableNames)) {
            return new HashMap<>();
        }

        Map<String, Object> result = Maps.newTreeMap();
        for (TableNameDto tableNameDto : tableNames) {
            String tableName = tableNameDto.toString();
            result.put(tableName, getTable(tableName));
        }
        return result;
    }

    @GetMapping(path = "table/{tableName}")
    public Object getTable(
        @PathVariable(name = "tableName") String tableName
    ) {
        return getHbaseAdmin().getTableDescriptor(TableNameMapper.map(tableName));
    }

    @GetMapping(path = "table-region/all")
    public Object getTableRegionsOfAll() {
        HbaseAdmin hbaseAdmin = getHbaseAdmin();
        List<TableNameDto> tableNames = hbaseAdmin.getTableNames();
        if (CollectionUtils.isEmpty(tableNames)) {
            return new HashMap<>();
        }

        Map<String, Object> result = Maps.newTreeMap();
        for (TableNameDto tableNameDto : tableNames) {
            String tableName = tableNameDto.toString();
            result.put(tableName, getTableRegions(tableName));
        }
        return result;
    }

    @GetMapping(path = "table-region/{tableName}")
    public Object getTableRegions(
        @PathVariable(name = "tableName") String tableName
    ) {
        return getHbaseAdmin().getRegions(TableNameMapper.map(tableName));
    }

    @GetMapping(path = "server-region/all")
    public Object getServerRegionsOfAll() {
        HbaseAdmin hbaseAdmin = getHbaseAdmin();
        List<ServerNameDto> serverNames = hbaseAdmin.getRegionServerNames();
        if (CollectionUtils.isEmpty(serverNames)) {
            return new HashMap<>();
        }

        Map<String, Object> result = Maps.newTreeMap();
        for (ServerNameDto serverNameDto : serverNames) {
            String serverName = serverNameDto.toString();
            result.put(serverName, getServerRegions(serverName));
        }
        return result;
    }

    @GetMapping(path = "server-region/{serverName}")
    public Object getServerRegions(
        @PathVariable(name = "serverName") String serverName
    ) {
        return getHbaseAdmin().getRegions(ServerNameMapper.map(serverName));
    }

    @GetMapping(path = "row-region/{tableName}")
    public Object getRowRegions(
        @PathVariable(name = "tableName") String tableName,
        @RequestParam(name = "row") List<String> rows
    ) {
        HbaseAdmin hbaseAdmin = getHbaseAdmin();
        TableNameDto tableNameDto = TableNameMapper.map(tableName);
        Map<String, Object> result = new TreeMap<>();
        for (String row : rows) {
            result.put(row, hbaseAdmin.getRegionLocation(tableNameDto, row));
        }
        return result;
    }

    private HbaseAdmin getHbaseAdmin() {
        return hbaseTemplate.getAdmin();
    }
}
