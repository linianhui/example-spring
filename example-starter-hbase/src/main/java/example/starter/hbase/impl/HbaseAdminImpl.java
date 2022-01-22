package example.starter.hbase.impl;

import java.io.IOException;
import java.util.*;

import example.starter.hbase.HbaseAdmin;
import example.starter.hbase.HbaseException;
import example.starter.hbase.dto.*;
import example.starter.hbase.mapper.*;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HRegionLocation;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.RegionLocator;
import org.apache.hadoop.hbase.util.Bytes;

public class HbaseAdminImpl implements HbaseAdmin {
    private final Admin admin;

    public HbaseAdminImpl(Admin admin) {
        this.admin = admin;
    }

    @Override
    public ConfigDto getClientConfig() {
        final Configuration configuration = admin.getConfiguration();
        return ConfigMapper.map(configuration);
    }

    @Override
    public ServerNameDto getMasterServerName() {
        try {
            return ServerNameMapper.map(admin.getMaster());
        } catch (IOException e) {
            throw new HbaseException("getMaster error", e);
        }
    }

    @Override
    public List<ServerNameDto> getRegionServerNames() {
        try {
            return ServerNameMapper.map(admin.getRegionServers());
        } catch (IOException e) {
            throw new HbaseException("getRegionServers error", e);
        }
    }

    @Override
    public List<TableNameDto> getTableNames() {
        try {
            return TableNameMapper.map(admin.listTableNames());
        } catch (IOException e) {
            throw new HbaseException("listTableNames error", e);
        }
    }

    @Override
    public List<RegionDto> getRegions(TableNameDto tableName) {
        try {
            return RegionMapper.map(admin.getRegions(TableNameMapper.map(tableName)));
        } catch (IOException e) {
            throw new HbaseException("getRegions error", e);
        }
    }

    @Override
    public List<RegionDto> getRegions(ServerNameDto serverName) {
        try {
            return RegionMapper.map(admin.getRegions(ServerNameMapper.map(serverName)));
        } catch (IOException e) {
            throw new HbaseException("getRegions error", e);
        }
    }

    @Override
    public TableDescriptorDto getTableDescriptor(TableNameDto tableName) {
        try {
            return TableDescriptorMapper.map(admin.getDescriptor(TableNameMapper.map(tableName)));
        } catch (IOException e) {
            throw new HbaseException("getRegions error", e);
        }
    }

    @Override
    public List<RegionLocationDto> getRegionLocations(TableNameDto tableName, Collection<String> rows) {
        try {
            RegionLocator locator = admin.getConnection().getRegionLocator(TableNameMapper.map(tableName));
            List<RegionLocationDto> result = new ArrayList<>(rows.size());
            for (String row : rows) {
                if (row != null) {
                    HRegionLocation hRegionLocation = locator.getRegionLocation(Bytes.toBytes(row));
                    RegionLocationDto dto = RegionLocationMapper.map(hRegionLocation);
                    if (dto != null) {
                        result.add(dto);
                    }
                }
            }
            return result;
        } catch (IOException e) {
            throw new HbaseException("getRegionLocations error", e);
        }
    }
}
