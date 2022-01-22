package example.starter.hbase.admin.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class TableDescriptorDto implements Comparable<TableDescriptorDto>, Serializable {
    private static final long serialVersionUID = -1168173193801041233L;

    private TableNameDto tableName;
    private String bloomFilter;
    private boolean inMemory;
    private long versions;
    private long minVersions;
    private boolean keepDeletedCells;
    private String dataBlockEncoding;
    private String compression;
    private String ttl;
    private boolean blockCache;
    private long blockSize;
    private boolean replicationScope;

    @Override
    public int compareTo(TableDescriptorDto o) {
        if (o == null) {
            return -1;
        }

        return this.tableName.compareTo(o.tableName);
    }
}
