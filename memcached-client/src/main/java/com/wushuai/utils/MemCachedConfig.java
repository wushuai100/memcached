package com.wushuai.utils;

/**
 * @author gaochuanjun
 * @since 15/1/7
 */
public interface MemCachedConfig {

    public String MEMCACHE_HOST = "memcache.host";

    public String WEIGHTS = "weights";

    public String INIT_CONN = "init.conn";

    public String MIN_CONN = "min.conn";

    public String MAX_CONN = "max.conn";

    public String MAX_IDLE = "max.idle";

    public String MAINT_SLEEP = "maint.sleep";

    public String NAGLE = "nagle";

    public String SOCKET_TO = "socket.to";

    public String SOCKET_CONNECT_TO = "socket.connect.to";

    public String MEMCACHED_OPEN = "memcached.open";
}
