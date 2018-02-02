package com.practice.dao;

/**
 * @author Xushd  2017/12/21 23:01
 */
public interface JedisClient {

    /**
     * Get
     * @param key
     * @return
     */
    String get(String key);

    /**
     * Set
     * @param key
     * @param value
     * @return
     */
    String set(String key, String value);

    /**
     * Hget
     * @param hkey
     * @param key
     * @return
     */
    String hget(String hkey, String key);

    /**
     * Hset
     * @param hkey
     * @param key
     * @param value
     * @return
     */
    Long hset(String hkey, String key, String value);

    /**
     * Del
     * @param key
     * @return
     */
    Long del(String key);

    /**
     * Hdel
     * @param hkey
     * @param key
     * @return
     */
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

    /**
     * is exit
     * @param key
     * @return
     */
    Boolean isExit(String key);

    /**
     * 存储REDIS队列 顺序存储
     * @param key reids键名
     * @param value 键值
     */
    void lpush(byte[] key, byte[]... value);
    /**
     * 获取队列数据
     * @param  key 键名
     * @return
     */
    byte[] rpop(byte[] key);

    /**
     * 获取集合的长度
     * @param key
     * @return
     */
    long llen(byte[] key);

}
