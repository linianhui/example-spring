package example.starter.hbase;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import example.starter.hbase.internal.Gets;
import example.starter.hbase.internal.Puts;
import example.starter.hbase.internal.RowResults;
import example.starter.hbase.internal.Scans;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;

public class HbaseTemplate {
    private final Configuration configuration;
    private final Connection connection;

    public HbaseTemplate(Configuration configuration) throws IOException {
        this.configuration = configuration;
        this.connection = ConnectionFactory.createConnection(configuration);
    }

    public void put(final String tableName, final RowPut rowPut) {
        Put put = Puts.of(rowPut);
        doPut(tableName, put);
    }

    public void put(final String tableName, final List<RowPut> rowPuts) {
        List<Put> puts = Puts.of(rowPuts);
        doPut(tableName, puts);
    }

    public RowResult get(final String tableName, final RowGet rowGet) {
        Get get = Gets.of(rowGet);
        Result result = doGet(tableName, get);
        return RowResults.of(result);
    }

    public List<RowResult> get(final String tableName, final List<RowGet> rowGets) {
        List<Get> gets = Gets.of(rowGets);
        Result[] results = doGet(tableName, gets);
        return RowResults.of(results);
    }

    public List<RowResult> scan(final String tableName, final RowScan rowScan) {
        Scan scan = Scans.of(rowScan);
        List<Result> results = doScan(tableName, scan);
        return RowResults.of(results);
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

    private Table getTable(final String tableName) throws IOException {
        return connection.getTable(TableName.valueOf(tableName));
    }
}
