package example.starter.hbase;


import java.io.IOException;
import java.util.List;

import example.starter.hbase.internal.RowResults;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
import example.starter.hbase.internal.Gets;
import example.starter.hbase.internal.Puts;

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

    public RowResult[] get(final String tableName, final List<RowGet> rowGets) {
        List<Get> gets = Gets.of(rowGets);
        Result[] results = doGet(tableName, gets);
        return RowResults.of(results);
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

    private Table getTable(final String tableName) throws IOException {
        return connection.getTable(TableName.valueOf(tableName));
    }
}
