package com.practice.utils;

/**
 * @author Xushd  2018/2/1 23:58
 */
public class OrderNumUtil {

    /** 开始时间截 ，(2018-01-01) */
    private final long start = 1514764800000L;

    /** 生成序列的掩码 */
    private final long sequenceMax = (1 << 14) - 1;

    /** 毫秒内序列(0~sequenceMax) */
    private long sequence = 0L;

    /** 上次生成ID的时间截 */
    private long lastTimestamp = -1L;

    private static OrderNumUtil instance = new OrderNumUtil();

    public OrderNumUtil() {
    }

    public static long getId(int serverId) {
        return instance.nextId(serverId);
    }

    /**
     * 获得下一个ID (该方法是线程安全的)
     *
     * @return SnowflakeId
     */
    public synchronized long nextId(int serverId) {
        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {
            // 如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过
            long offset = lastTimestamp - timestamp;
            if (offset <= 10) {
                //如果发生时钟回退在可接受的范围以内
                try {
                    Thread.sleep(offset + 1);
                }catch(Exception e) {
                    e.printStackTrace();
                }
                //再次获取时间戳
                timestamp = timeGen();
            }
            if (timestamp < lastTimestamp) {
                //如果仍然小于
                throw new RuntimeException(String.format(
                        "Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
            }
        }else if (lastTimestamp == timestamp) {
            // 如果是同一毫秒生成的，则进行毫秒内序列
            sequence = (sequence + 1) & sequenceMax;
            // 毫秒内序列溢出
            if (sequence == 0) {
                // 阻塞到下一个毫秒,获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {// 时间戳改变，毫秒内序列重置
            sequence = 0L;
        }
        // 上次生成ID的时间截
        lastTimestamp = timestamp;
        // 移位并通过或运算拼到一起组成64位的ID
        return ((timestamp - start) << 22) | (serverId << 14) | sequence;
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     *
     * @param lastTimestamp
     *            上次生成ID的时间截
     * @return 当前时间戳
     */
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 返回以毫秒为单位的当前时间
     *
     * @return 当前时间(毫秒)
     */
    protected long timeGen() {
        return System.currentTimeMillis();
    }
}
