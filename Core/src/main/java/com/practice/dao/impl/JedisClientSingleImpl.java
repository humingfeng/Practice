package com.practice.dao.impl;


import com.practice.dao.JedisClient;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

/**
 * JREDIS Daoimpl 单例
 * @author Xushd
 * @since 2017/2/4 0004 下午 1:04
 */
public class JedisClientSingleImpl implements JedisClient {

    @Resource
    private JedisPool jedisPool;


    @Override
    public String get(String key) {
        Jedis jedis = jedisPool.getResource();
        String value = jedis.get(key);
        jedis.close();
        return value;
    }

    @Override
    public String set(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        String ret = jedis.set(key, value);
        jedis.close();
        return ret;
    }

    @Override
    public String hget(String hkey, String key) {
        Jedis jedis = jedisPool.getResource();
        String value = jedis.hget(hkey, key);
        jedis.close();
        return value;
    }

    @Override
    public Long hset(String hkey, String key, String value) {
        Jedis jedis = jedisPool.getResource();
        Long ret = jedis.hset(hkey, key, value);
        jedis.close();
        return ret;
    }

    @Override
    public Long del(String key) {
        Jedis jedis = jedisPool.getResource();
        Long ret = jedis.del(key);
        jedis.close();
        return ret;
    }

    @Override
    public Long hdel(String hkey, String key) {
        Jedis jedis = jedisPool.getResource();
        Long ret = jedis.hdel(hkey, key);
        jedis.close();
        return ret;
    }

    @Override
    public Long incr(String key) {
        Jedis jedis = jedisPool.getResource();
        Long ret = jedis.incr(key);
        jedis.close();
        return ret;
    }

    @Override
    public Long expire(String key, int second) {
        Jedis jedis = jedisPool.getResource();
        Long ret = jedis.expire(key, second);
        jedis.close();
        return ret;
    }

    @Override
    public Long ttl(String key) {
        Jedis jedis = jedisPool.getResource();
        Long ret = jedis.ttl(key);
        jedis.close();
        return ret;
    }

    /**
     * is exit
     *
     * @param key
     * @return
     */
    @Override
    public Boolean isExit(String key) {
        Jedis jedis = jedisPool.getResource();
        Boolean exists = jedis.exists(key);
        jedis.close();
        return exists;
    }

    /**
     * 存储REDIS队列 顺序存储
     *
     * @param key   reids键名
     * @param value 键值
     */
    @Override
    public void lpush(byte[] key, byte[]... value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.lpush(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
    }

    /**
     * 获取队列数据
     *
     * @param key 键名
     * @return
     */
    @Override
    public byte[] rpop(byte[] key) {
        byte[] bytes = null;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            bytes = jedis.rpop(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
        return bytes;
    }

    /**
     * 获取集合的长度
     *
     * @param key
     * @return
     */
    @Override
    public long llen(byte[] key) {
        long len = 0;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            len = jedis.llen(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
        return len;
    }
}
