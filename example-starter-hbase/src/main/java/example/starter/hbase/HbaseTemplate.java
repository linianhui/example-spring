package example.starter.hbase;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import example.starter.hbase.admin.HbaseAdmin;
import example.starter.hbase.admin.impl.HbaseAdminImpl;
import example.starter.hbase.mapper.*;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;

public class HbaseTemplate {
    private final Connection connection;

    public HbaseTemplate(Configuration configuration) throws IOException {
        this.connection = ConnectionFactory.createConnection(configuration);
    }

    public void put(final String tableName, final RowPut rowPut) {
        Put put = PutMapper.map(rowPut);
        doPut(tableName, put);
    }

    public void put(final String tableName, final List<RowPut> rowPuts) {
        List<Put> puts = PutMapper.map(rowPuts);
        doPut(tableName, puts);
    }

    public RowResult get(final String tableName, final RowGet rowGet) {
        Get get = GetMapper.map(rowGet);
        Result result = doGet(tableName, get);
        return ResultMapper.map(result);
    }

    public List<RowResult> get(final String tableName, final List<RowGet> rowGets) {
        List<Get> gets = GetMapper.map(rowGets);
        Result[] results = doGet(tableName, gets);
        return ResultMapper.map(results);
    }

    public List<RowResult> scan(final String tableName, final RowScan rowScan) {
        Scan scan = ScanMapper.map(rowScan);
        List<Result> results = doScan(tableName, scan);
        return ResultMapper.map(results);
    }

    public HbaseAdmin getAdmin() {
        try {
            return new HbaseAdminImpl(connection.getAdmin());
        } catch (IOException e) {
            throw new HbaseException("getAdmin error", e);
        }
    }

    private void doPut(final String tableName, final Put put) {
        try (Table table = getTable(tableName)) {
            table.put(put);
        } catch (IOException e) {
            throw new HbaseException("doPut error", e);
        }
    }

    private void doPut(final String tableName, final List<Put> puts) {
        try (Table table = getTable(tableName)) {
            table.put(puts);
        } catch (IOException e) {
            throw new HbaseException("doPut list error", e);
        }
    }

    private Result doGet(final String tableName, final Get get) {
        try (Table table = getTable(tableName)) {
            return table.get(get);
        } catch (IOException e) {
            throw new HbaseException("doGet error", e);
        }
    }

    private Result[] doGet(final String tableName, final List<Get> gets) {
        try (Table table = getTable(tableName)) {
            return table.get(gets);
        } catch (IOException e) {
            throw new HbaseException("doGet list error", e);
        }
    }

    private List<Result> doScan(final String tableName, final Scan scan) {
        try (Table table = getTable(tableName)) {
            ResultScanner scanner = table.getScanner(scan);
            List<Result> result = new ArrayList<>();
            for (Result rawResult : scanner) {
                result.add(rawResult);
            }
            return result;
        } catch (IOException e) {
            throw new HbaseException("doGet list error", e);
        }
    }

    private Table getTable(final String tableName) {
        try {
            return connection.getTable(TableName.valueOf(tableName));
        } catch (IOException e) {
            throw new HbaseException("getTable error", e);
        }
    }
}
