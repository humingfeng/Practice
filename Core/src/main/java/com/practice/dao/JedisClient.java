package com.practice.dao;

/**
 * @author Xushd  2017/12/21 23:01
 */
public interface JedisClient {

    String get(String key);

    String set(String key, String value);

    String hget(String hkey, String key);

    Long hset(String hkey, String key, String value);

    Long del(String key);

    Long hdel(String hkey, String key);

    /**
     * 自增
     * @param key
     * @return
     */
    Long incr(String key);

    /**
     * 设置过期
     * @param key
     * @param second
     * @return
     */
    Long expire(String key, int second);

    /**
     * 查询是否过期 -2过期 -1 永久
     * @param key
     * @return
     */
    Long ttl(String key);


}
