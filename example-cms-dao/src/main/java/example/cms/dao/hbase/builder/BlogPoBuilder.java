package example.cms.dao.hbase.builder;

import static example.cms.dao.hbase.define.BlogTableDefines.*;

import java.util.ArrayList;
import java.util.List;

import example.cms.dao.hbase.utils.HBaseUtils;
import example.cms.dao.po.BlogPo;
import example.starter.hbase.RowPut;
import example.starter.hbase.RowResult;

public class BlogPoBuilder {
    public static BlogPo from(final RowResult rowResult) {
        if (rowResult==null) {
            return null;
        }
        BlogPo result = new BlogPo();
        result.setId(rowResult.getKey());
        String[] keySplit = HBaseUtils.split(rowResult.getKey());
        result.setUserId(keySplit[1]);
        result.setCreatedAt(Long.parseLong(keySplit[2]));
        result.setTitle(rowResult.getString(COLUMN_TITLE, null));
        result.setContent(rowResult.getString(COLUMN_CONTENT, null));
        result.setUpdatedAt(rowResult.getLong(COLUMN_UPDATED_AT, 0));
        return result;
    }

    public static List<BlogPo> from(final List<RowResult> rowResults) {
        if (rowResults==null) {
            return null;
        }
        List<BlogPo> result = new ArrayList<>(rowResults.size());
        for (RowResult rowResult : rowResults) {
            BlogPo po = from(rowResult);
            if (po!=null) {
                result.add(po);
            }
        }
        return result;
    }

    public static RowPut toRowPut(final BlogPo po) {
        final RowPut result = RowPut.of(po.getId(), FAMILY_BYTES, CELL_COUNT);
        result.addCell(COLUMN_TITLE_BYTES, po.getTitle());
        result.addCell(COLUMN_CONTENT_BYTES, po.getContent());
        result.addCell(COLUMN_UPDATED_AT_BYTES, po.getUpdatedAt());
        return result;
    }
}
