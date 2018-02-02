package com.practice.utils;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;

import static com.dyuproject.protostuff.runtime.RuntimeSchema.getSchema;

/**
 * 序列化 和 反序列化
 * @author Xushd on 2018/2/2 14:12
 */
public class SerializeUtils {

    /**
     * show  protostuff 序列化
     * @author Xushd
     * @since 2017年1月4日 下午 9:18
     * @param obj
     * @return
     */
    public static <T> byte[] serializer(T obj) {

        Class<T> clazz = (Class<T>) obj.getClass();
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try {
            Schema<T> schema = getSchema(clazz);
            return ProtostuffIOUtil.toByteArray(obj, schema, buffer);
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        } finally {
            buffer.clear();
        }
    }

    /**
     * protostuff 反序列化
     *
     * @param data
     * @param clazz
     * @return
     */
    public static <T> T deserializer(byte[] data, Class<T> clazz) {
        try {
            T obj = clazz.newInstance();
            Schema<T> schema = getSchema(clazz);
            ProtostuffIOUtil.mergeFrom(data, obj, schema);
            return obj;
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }
}
