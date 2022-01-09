package example.cms.dao.hbase.define;

import java.util.List;

import com.google.common.collect.ImmutableList;

public interface BlogTableDefines {
    String TABLE_NAME = "blog";

    String FAMILY = "cf";

    String COLUMN_TITLE = "t";
    String COLUMN_CONTENT = "c";
    String COLUMN_UPDATED_AT = "ut";

    byte[] FAMILY_BYTES = FAMILY.getBytes();

    byte[] COLUMN_TITLE_BYTES = COLUMN_TITLE.getBytes();
    byte[] COLUMN_CONTENT_BYTES = COLUMN_CONTENT.getBytes();
    byte[] COLUMN_UPDATED_AT_BYTES = COLUMN_UPDATED_AT.getBytes();

    List<byte[]> COLUMN_QUALIFIERS = ImmutableList.of(
        COLUMN_TITLE_BYTES,
        COLUMN_CONTENT_BYTES,
        COLUMN_UPDATED_AT_BYTES
    );
    int CELL_COUNT = COLUMN_QUALIFIERS.size();

}

