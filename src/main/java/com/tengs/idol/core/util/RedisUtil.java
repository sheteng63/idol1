package com.tengs.idol.core.util;

import org.redisson.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    @Autowired
    private RedissonClient redissonClient;

    /**
     * 获取对象值
     *
     * @param key
     * @param <T>
     * @return
     */
    public <T> T getValue(String key) {
        RBucket<T> bucket = redissonClient.getBucket(key);
        return bucket.get();
    }

    /**
     * 设置对象的值
     *
     * @param key   键
     * @param value 值
     * @param time  缓存时间 单位毫秒 -1 永久缓存
     * @return
     */
    public <T> void setValue(String key, T value, Long time) {
        RBucket<Object> bucket = redissonClient.getBucket(key);
        if (time == -1) {
            bucket.set(value);
        } else {
            bucket.set(value, time, TimeUnit.SECONDS);
        }
    }

    /**
     * 设置对象的值
     *
     * @param key   键
     * @param value 值
     * @param time  缓存时间 单位毫秒 -1 永久缓存
     * @return
     */
    public <T> void setValue(String key, T value, Long time, TimeUnit timeUnit) {
        RBucket<Object> bucket = redissonClient.getBucket(key);
        bucket.set(value, time, timeUnit);
    }

    /**
     * 如果值已经存在则则不设置
     *
     * @param key   键
     * @param value 值
     * @param time  缓存时间 单位毫秒
     * @return true 设置成功,false 值存在,不设置
     */
    public <T> Boolean trySetValue(String key, T value, Long time) {
        RBucket<Object> bucket = redissonClient.getBucket(key);
        boolean b;
        if (time == -1) {
            b = bucket.trySet(value);
        } else {
            b = bucket.trySet(value, time, TimeUnit.SECONDS);
        }
        return b;
    }

    /**
     * 如果值已经存在则则不设置
     *
     * @param key   键
     * @param value 值
     * @param time  缓存时间 单位毫秒
     * @return true 设置成功,false 值存在,不设置
     */
    public <T> Boolean trySetValue(String key, T value, Long time,TimeUnit timeUnit) {
        RBucket<Object> bucket = redissonClient.getBucket(key);
        boolean b = bucket.trySet(value, time, timeUnit);
        return b;
    }

    /**
     * 删除对象
     *
     * @param key 键
     * @return true 删除成功,false 不成功
     */
    public Boolean delete(String key) {
        return redissonClient.getBucket(key).delete();
    }

    /**
     * 获取map集合
     *
     * @param name
     * @param <K>
     * @param <V>
     * @return
     */
    public <K, V> RMap<K, V> getMap(String name) {
        return redissonClient.getMap(name);
    }

    /**
     * 设置map集合
     *
     * @param name
     * @param data
     * @param time 缓存时间,单位毫秒 -1永久缓存
     * @return
     */
    public void setMapValues(String name, Map data, Long time) {
        RMap map = redissonClient.getMap(name);
        map.expire(time, TimeUnit.SECONDS);
        map.putAll(data);
    }


    /**
     * 获取List集合
     *
     * @param name
     * @return
     */
    public <T> RList<T> getList(String name) {
        return redissonClient.getList(name);
    }

    /**
     * 设置List集合
     *
     * @param name
     * @param data
     * @param time 缓存时间,单位毫秒 -1永久缓存
     * @return
     */
    public void setListValues(String name, List data, Long time) {
        RList list = redissonClient.getList(name);
        list.expire(time, TimeUnit.SECONDS);
        list.addAll(data);
    }
    /**
     * 设置List集合
     *
     * @param name
     * @param data
     * @param time 缓存时间,单位毫秒 -1永久缓存
     * @return
     */
    public void setListValues(String name, List data, Long time,TimeUnit timeUnit) {
        RList list = redissonClient.getList(name);
        list.expire(time, timeUnit);
        list.addAll(data);
    }

    /**
     * 获取锁
     *
     * @param key
     * @return
     */
    public RLock getRLock(String key) {
        RLock rLock = redissonClient.getLock(key);
        return rLock;
    }
}
