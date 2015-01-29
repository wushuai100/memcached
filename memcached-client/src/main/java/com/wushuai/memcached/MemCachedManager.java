package com.wushuai.memcached;

import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;
import com.wushuai.utils.Config;
import com.wushuai.utils.MemCachedConfig;
import com.wushuai.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @author gaochuanjun
 * @since 15/1/6
 */
public class MemCachedManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(MemCachedManager.class);
    protected static MemCachedClient memCachedClient = new MemCachedClient();

    protected static MemCachedManager memCachedManager = new MemCachedManager();

    static {
        Config.load("/config.properties");//加载配置文件risk_controll.properties
        //MemCached.setEnabledCached(Config.getBoolean(MemCachedConfig.MEMCACHED_OPEN, true));
        String memCacheHost = Config.get(MemCachedConfig.MEMCACHE_HOST);
        String[] servers = memCacheHost.split(StringUtil.COMMA);
        String weightStr = Config.get(MemCachedConfig.WEIGHTS);
        String[] weightsToArray = weightStr.split(StringUtil.COMMA);
        Integer[] weights = new Integer[servers.length];
        for (int i = 0; i < servers.length; i++) {
            weights[i] = Integer.parseInt(weightsToArray[i]);
        }
        SockIOPool sockIOPool = SockIOPool.getInstance();

        sockIOPool.setServers(servers);
        sockIOPool.setWeights(weights);

        sockIOPool.setInitConn(Config.getInt(MemCachedConfig.INIT_CONN, 5));
        sockIOPool.setMinConn(Config.getInt(MemCachedConfig.MIN_CONN, 5));
        sockIOPool.setMaxConn(Config.getInt(MemCachedConfig.MAX_CONN, 250));
        sockIOPool.setMaxIdle(Config.getInt(MemCachedConfig.MAX_IDLE, 6) * 60 * 60 * 1000);


        sockIOPool.setMaintSleep(Config.getInt(MemCachedConfig.MAINT_SLEEP, 30));

        sockIOPool.setNagle(Config.getBoolean(MemCachedConfig.NAGLE, false));
        sockIOPool.setSocketTO(Config.getInt(MemCachedConfig.SOCKET_TO, 3) * 1000);
        sockIOPool.setSocketConnectTO(Config.getInt(MemCachedConfig.SOCKET_CONNECT_TO, 0) * 1000);

        sockIOPool.initialize();
    }

    protected MemCachedManager() {
    }

    public static MemCachedManager getInstance() {
        return memCachedManager;
    }

    public boolean add(String key, Object value) {
        try {
            return memCachedClient.add(key, value);
        } catch (RuntimeException e) {
            LOGGER.warn("An exception occurred in memcached while setting the value of key = {}, value = {}", key, value);
        }
        return false;
    }

    public boolean add(String key, Object value, Date expiry) {
        try {
            return memCachedClient.add(key, value, expiry);
        } catch (RuntimeException e) {
            LOGGER.warn("An exception occurred in memcached while setting the value of key = {}, value = {}, date = {}", key, value, expiry);
        }
        return false;
    }

    public boolean replace(String key, Object value) {
        try {
            return memCachedClient.replace(key, value);
        } catch (RuntimeException e) {
            LOGGER.warn("An exception occurred in memcached while replacing the value of key = {}, value = {}", key, value);
        }
        return false;
    }

    public boolean replace(String key, Object value, Date expiry) {
        try {
            return memCachedClient.replace(key, value, expiry);
        } catch (RuntimeException e) {
            LOGGER.warn("An exception occurred in memcached while replacing the value of key = {}, value = {}, date = {}", key, value, expiry);
        }
        return false;

    }

    public Object get(String key) {
        try {
            return memCachedClient.get(key);
        } catch (RuntimeException e) {
            LOGGER.warn("An exception occurred in memcached while getting the value of key = {}", key);
        }
        return null;
    }

    public boolean delete(String key) {
        try {
            return memCachedClient.delete(key);
        } catch (RuntimeException e) {
            LOGGER.warn("An exception occurred in memcached while deleting the value of key = {}", key);
        }
        return false;
    }
}
