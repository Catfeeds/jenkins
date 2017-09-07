package com.hfocean.common.notify;

import com.hfocean.common.redis.RedisService;
import com.hfocean.common.util.AppContextHelper;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.concurrent.LinkedBlockingQueue;

/**
* 队列代理
* @author Leslie.Lam
* @create 2017-07-11 17:06
**/
public class LinkedBlockingQueueProxy implements InvocationHandler {

    private LinkedBlockingQueue<NotifyManager> queue = null;

    private static RedisService redisService=AppContextHelper.getBean(RedisService.class);

    public final static String key = "_notify";

    public LinkedBlockingQueueProxy(LinkedBlockingQueue<NotifyManager> queue){
        super();
        this.queue = queue;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //只代理 put ,take 方法
        String methodName = method.getName();
        method.setAccessible(true);
        Object result = method.invoke(queue, args);
        if(methodName.equals("put") || methodName.equals("take")){
            redisService.putObject(key,queue);
        }
        return result;
    }
}
