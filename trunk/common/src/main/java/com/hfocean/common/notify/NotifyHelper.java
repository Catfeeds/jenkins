package com.hfocean.common.notify;

import com.hfocean.common.redis.RedisService;
import com.hfocean.common.util.AppContextHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.lang.reflect.Proxy;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class NotifyHelper {

    @Resource
    private RedisService redisService;

	private NotifyHelper() {}

    public static <T extends NotifyManager>  void push(T t){
        newInstance().add(t);
    }

	public static NotifyHelper newInstance(){
		return AppContextHelper.getBean(NotifyHelper.class);
	}
	
	private static final transient Logger log = LoggerFactory.getLogger(NotifyHelper.class);
	
	private ExecutorService threadPool = Executors.newFixedThreadPool(3);
	
	private BlockingQueue<NotifyManager> proxy = null;
	
	public <T extends NotifyManager> void add(T t){
		try {
			proxy.put(t);
		} catch (InterruptedException e) {
			log.error("推送add方法错误",e);
		}
	}

	@PostConstruct
	public void doNotify(){
		threadPool.execute(new Runnable() {
			public void run() {
                try {
					Object obj = redisService.getObject(LinkedBlockingQueueProxy.key);
					if(obj==null) {
						redisService.putObject(LinkedBlockingQueueProxy.key, new LinkedBlockingQueue<NotifyManager>(Integer.MAX_VALUE));
					}
					LinkedBlockingQueue<NotifyManager> queue = (LinkedBlockingQueue<NotifyManager>) redisService.getObject(LinkedBlockingQueueProxy.key);
                    LinkedBlockingQueueProxy handler = new LinkedBlockingQueueProxy(queue);
			    	proxy = (BlockingQueue<NotifyManager>) Proxy.newProxyInstance(queue.getClass().getClassLoader(), queue.getClass().getInterfaces(),handler);
			    	NotifyManager t ;
					while((t = proxy.take())!=null){
						threadPool.execute(new NotifyThread(t));
					}
				} catch (Exception e) {
					log.error("推送doNotify方法错误",e);
				}
			}
		});
	}
	
	
	class NotifyThread extends Thread{
		
		private NotifyManager t;
		
		public NotifyThread(NotifyManager t){
			this.t = t;
		}
		
		public void run() {
			try {
				t.doNotify();
			} catch (Exception e) {
				log.error("推送doNotify方法错误",e);
			}
		}
	}
    /**
     * 队列代理
     */
//    public class LinkedBlockingQueueProxy implements InvocationHandler {
//
//        private LinkedBlockingQueue<NotifyManager> queue = null;
//
//        public final static String key = "_notify";
//
//
//        public LinkedBlockingQueueProxy(LinkedBlockingQueue<NotifyManager> queue){
//            super();
//            this.queue = queue;
//        }
//
//        @Override
//        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//            //只代理 put ,take 方法
//            String methodName = method.getName();
//			method.setAccessible(true);
//            Object result = method.invoke(queue, args);
//            if(methodName.equals("put") || methodName.equals("take")){
//                redisService.set(key,objectMapper.writeValueAsString(queue));
//            }
//            return result;
//        }
//
//    }
}
