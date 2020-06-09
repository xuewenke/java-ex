package com.x.atrs.concurrency.taskdispatcher.core;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 默认线程池
 * @author xuewenke
 */
public class BaseThreadPoolExecutor extends ThreadPoolExecutor {


    /**
     *  核心线程数量
     */
    private static int corePoolSize = Runtime.getRuntime().availableProcessors() * 2;

    /**
     * 最大线程数
     */
    private static int maxPoolSize = corePoolSize * 2;

    /**
     *
     *空闲线程存活时间
     */
    private static int keepAliveTime = 60 * 10;


    /**
     * 线程池缓冲工作队列大小
     */
    protected static int maxWorkQueueSize = 100;


    /**
     * 工作队列
     */
    private static BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(maxWorkQueueSize);


    /**
     * 使用对象内置锁实现等待/通知
     */
    private Object threadPoolLockObj;

    public BaseThreadPoolExecutor() {
        super(corePoolSize, maxPoolSize, keepAliveTime, TimeUnit.SECONDS, workQueue, new CallerRunsPolicy());
    }

    public void setThreadPoolLockObj(Object threadPoolLockObj) {
        this.threadPoolLockObj = threadPoolLockObj;
    }

    @Override
    protected void terminated() {
        if(threadPoolLockObj != null) {
            synchronized (threadPoolLockObj) {
                threadPoolLockObj.notify();
            }
        }

    }

}
