package com.hfocean.common.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import redis.clients.jedis.Jedis;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    @Autowired(required =false)
    StringRedisTemplate restTemplate;

    @Autowired(required =false)
    private RedisTemplate redisTemplate;

    @Autowired(required =false)
    private JedisConnectionFactory jedisConnectionFactory;

    /**
     * put缓存永久(覆盖同key)
     * @param key 缓存key
     * @param obj 缓存对象
     */
    public  void putObject(final String key,final Object obj){
        putObject(key, obj,0);
    }

    /**
     * put缓存(覆盖同key)
     * @param key 缓存key
     * @param obj 缓存对象
     * @param timeout 有效时间(单位:秒)
     */
    public  void putObject(final String key,final Object obj,long timeout){
        Assert.hasLength(key, "缓存key不能为空");
        Assert.notNull(obj,"缓存obj不能为空");
        boolean b = obj instanceof Serializable;
        if(!b) throw new IllegalArgumentException("缓存obj必须实现Serializable接口");
        if(timeout<=0){
            redisTemplate.opsForValue().set(key, obj);
        }else{
            redisTemplate.opsForValue().set(key, obj, timeout, TimeUnit.SECONDS);
        }

    }

    /**
     * del缓存
     * @param key 缓存key
     */
    public  void delObject(final String key){
        Assert.hasLength(key, "缓存key不能为空");
        redisTemplate.delete(key);
    }

    /**
     * 获取缓存值
     * @param key 缓存key
     * @return obj
     */
    public  Object getObject(String key){
        return redisTemplate.opsForValue().get(key);
    }

    public <T> T excute(Function<Jedis,T> fun){
        Jedis jedis=null;
        try {
            if(null!=jedisConnectionFactory){
                jedis=(Jedis)jedisConnectionFactory.getConnection().getNativeConnection();
            }
            return fun.callback(jedis);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(null!=jedis) jedis.close();
        }
        return null;
    }

    public String set(final String key,final String value){
        return excute(new Function<Jedis,String>(){
            @Override
            public String callback(Jedis jedis) {
                return jedis.set(key,value);
            }
        });
    }

    public String set(final String key,final String value,final Integer timeout){
        return excute(new Function<Jedis,String>(){
            @Override
            public String callback(Jedis jedis) {
                String set = jedis.set(key, value);
                jedis.expire(key, timeout);
                return set;
            }

        });
    }

    public String get(final String key){
        return excute(new Function<Jedis,String>(){
            @Override
            public String callback(Jedis jedis) {
                return jedis.get(key);
            }
        });
    }

    public void del(final String key){
        excute(new Function<Jedis,Long>(){
            @Override
            public Long callback(Jedis jedis) {
                //jedis.del(key.getBytes());
                jedis.del(key);
                return null;
            }
        });
    }

    public Long expire(final String key,final Integer seconds){
        return excute(new Function<Jedis,Long>(){
            @Override
            public Long callback(Jedis jedis) {
                return jedis.expire(key, seconds);
            }
        });
    }

    public Long pttl(final String key){
        return excute(new Function<Jedis,Long>(){
            @Override
            public Long callback(Jedis jedis) {
                Long pttl = jedis.pttl(key);
                return pttl;
            }
        });
    }

    public Set<String> keys(final String pattern){
        return excute(new Function<Jedis,Set<String>>(){
            @Override
            public Set<String> callback(Jedis jedis) {
                return jedis.keys(pattern);
            }
        });
    }

    public String hget(final String key,final String field){
        return excute(new Function<Jedis,String>(){
            @Override
            public String callback(Jedis jedis) {
                return jedis.hget(key,field);
            }
        });
    }

    public Map<String,String> hgetAll(final String key){
        return excute(new Function<Jedis,Map<String,String>>(){
            @Override
            public Map<String,String> callback(Jedis jedis) {
                return jedis.hgetAll(key);
            }
        });
    }

    public Long hdel(final String key,final String...fields){
        return excute(new Function<Jedis,Long>(){
            @Override
            public Long callback(Jedis jedis) {
                return jedis.hdel(key,fields);
            }
        });
    }

    public Long hset(final String key,final String field,final String value){
        return excute(new Function<Jedis,Long>(){
            @Override
            public Long callback(Jedis jedis) {
                return jedis.hset(key,field,value);
            }
        });
    }

    public String type(final String key){
        return excute(new Function<Jedis,String>(){
            @Override
            public String callback(Jedis jedis) {
                return jedis.type(key);
            }
        });
    }

    public Set<String> hkeys(final String key){
        return excute(new Function<Jedis,Set<String>>(){
            @Override
            public Set<String> callback(Jedis jedis) {
                return jedis.hkeys(key);
            }
        });
    }

    public Long sadd(final String key,final String... values){
        return excute(new Function<Jedis,Long>(){
            @Override
            public Long callback(Jedis jedis) {
                return jedis.sadd(key,values);
            }
        });
    }

    public Long srem(final String key,final String... values){
        return excute(new Function<Jedis,Long>(){
            @Override
            public Long callback(Jedis jedis) {
                return jedis.srem(key,values);
            }
        });
    }

    public Set<String> smembers(final String key){
        return excute(new Function<Jedis,Set<String>>(){
            @Override
            public Set<String> callback(Jedis jedis) {
                return jedis.smembers(key);
            }
        });
    }
}
