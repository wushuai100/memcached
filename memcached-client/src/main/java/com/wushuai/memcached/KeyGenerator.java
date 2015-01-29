package com.wushuai.memcached;

/**
 * @author gaochuanjun
 * @since 15/1/19
 */
public abstract class KeyGenerator {
    private static final String SPLIT_CHAR = "_";
    private static final String PREFIX = "product";

    public static String buildKey(String tableName, String columnName, Long value, String metaData) {
        return PREFIX + SPLIT_CHAR + tableName + SPLIT_CHAR + columnName + SPLIT_CHAR + value + SPLIT_CHAR + metaData;
    }

    public static String buildKey(String tableName, String columnName, String version, Long value, String metaData) {
        return PREFIX + SPLIT_CHAR + tableName + SPLIT_CHAR + columnName + SPLIT_CHAR + version + SPLIT_CHAR + value + SPLIT_CHAR + metaData;
    }
}
