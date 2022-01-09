package example.cms.dao.hbase.utils;

import java.nio.charset.StandardCharsets;

import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.hbase.util.MD5Hash;

public class HBaseUtils {
    private static final String DELIMITER = "-";
    private static final String END_DELIMITER = "~";

    public static String buildBlogRowKey(String userId, long createdAt) {
        String prefix = md5Prefix(userId);
        String suffix = suffix(createdAt);
        return buildRowKey(userId, prefix, suffix);
    }

    public static String buildBlogRowKeyOfFilter(String prefix, String userId) {
        return buildRowKey(DELIMITER, prefix, userId);
    }

    public static String buildBlogRowKeyOfStart(String prefix, String userId) {
        return buildRowKey(DELIMITER, prefix, userId, DELIMITER);
    }

    public static String buildBlogRowKeyOfEnd(String prefix, String userId) {
        return buildRowKey(DELIMITER, prefix, userId, END_DELIMITER);
    }

    private static String buildRowKey(String... args) {
        return String.join(DELIMITER, args);
    }

    public static String[] split(String rowKey) {
        return StringUtils.split(DELIMITER);
    }

    public static String md5Prefix(String value) {
        String md5 = MD5Hash.getMD5AsHex(value.getBytes(StandardCharsets.UTF_8));
        return md5.substring(0, 4);
    }

    private static String suffix(long time) {
        return String.valueOf(Long.MAX_VALUE - time);
    }
}
