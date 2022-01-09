package example.cms.dao.hbase.impl;

import static example.cms.dao.hbase.define.BlogTableDefines.*;

import java.util.List;

import example.cms.dao.hbase.BlogDao;
import example.cms.dao.hbase.builder.BlogPoBuilder;
import example.cms.dao.hbase.utils.HBaseUtils;
import example.cms.dao.po.BlogPo;
import example.starter.hbase.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BlogDaoImpl implements BlogDao {

    @Autowired
    private HbaseTemplate hbaseTemplate;

    @Override
    public BlogPo getById(final String rowKey) {
        if (StringUtils.isBlank(rowKey)) {
            return null;
        }
        final RowGet rowGet = RowGet.of(rowKey, FAMILY_BYTES, COLUMN_QUALIFIERS);
        final RowResult rowResult = hbaseTemplate.get(TABLE_NAME, rowGet);
        return BlogPoBuilder.from(rowResult);
    }

    @Override
    public List<BlogPo> getByUserId(String userId) {
        if (StringUtils.isBlank(userId)) {
            return null;
        }
        final RowScan rowScan = new RowScan();
        String prefix = HBaseUtils.md5Prefix(userId);
        rowScan.setFilterPrefix(HBaseUtils.buildBlogRowKeyOfFilter(prefix, userId).getBytes());
        rowScan.setStartKey(HBaseUtils.buildBlogRowKeyOfStart(prefix, userId).getBytes());
        rowScan.setEndKey(HBaseUtils.buildBlogRowKeyOfEnd(prefix, userId).getBytes());
        rowScan.setFamily(FAMILY_BYTES);
        rowScan.setQualifiers(COLUMN_QUALIFIERS);
        final List<RowResult> rowResults = hbaseTemplate.scan(TABLE_NAME, rowScan);
        return BlogPoBuilder.from(rowResults);
    }

    @Override
    public String save(final BlogPo blog) {
        if (blog==null) {
            return null;
        }

        blog.setId(HBaseUtils.buildBlogRowKey(blog.getUserId(), blog.getCreatedAt()));
        final RowPut rowPut = BlogPoBuilder.toRowPut(blog);
        hbaseTemplate.put(TABLE_NAME, rowPut);
        return blog.getId();
    }
}
