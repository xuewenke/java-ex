package com.seven.concurrency.taskdispatcher.core;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;


/**
 * 任务分发器
 * @author xuewenke
 */
public class BaseTaskDispatcher<Work extends BaseWork, In extends  BaseInputData, Out extends BaseOutputData>  implements ITaskDispatcher {


    /**
     * 实现等待通知
     */
    private Object lockObj = new Object();

    /**
     * 存放结果的队列
     */
    protected ConcurrentLinkedQueue resultQueue = new ConcurrentLinkedQueue();

    private BaseThreadPoolExecutor executor;
    protected ConcurrentLinkedQueue concurrentLinkedQueue;

    private Class<Work> workClazz;

    private int LOCK_WAIT_TIME_OUT = 60 * 30 * 1000;


    public BaseTaskDispatcher() {
        executor = new BaseThreadPoolExecutor();
    }

    public BaseTaskDispatcher(ConcurrentLinkedQueue concurrentLinkedQueue) {
        this();
        this.concurrentLinkedQueue = concurrentLinkedQueue;

    }

    public BaseTaskDispatcher(Class<Work> clazz) {
        this();
        this.workClazz = clazz;
    }

    @Override
    public void dispathTask(BaseInputData inputData) {
        if (executor.isShutdown()) {
//            logger.info("线程池已停止接收任务");
            return;
        }
        Work baseWork = assemWorker((In)inputData);
        if (baseWork != null) {
            executor.execute(baseWork);
        }
    }


    /**
     * 组装 worker， 子类根据自己的业务需要可以重写组装的方法
     * @param inputData
     * @return
     */
    public Work assemWorker(In inputData) {
        BaseWork baseWork = null;
        if (workClazz == null) {
            baseWork = new BaseWork(inputData);
            baseWork.setConcurrentLinkedQueue(this.concurrentLinkedQueue);
        } else {
            try {
                baseWork = workClazz.newInstance();
                baseWork.setInputData(inputData);
                baseWork.setConcurrentLinkedQueue(this.concurrentLinkedQueue);
            } catch (Exception e) {
//                logger.error(e.getMessage(), e);
            }
        }
        return (Work) baseWork;

    }



    @Override
    public void awaitToComplete() {
        /**
         * 此 处不可以执行shutdown，shutdown 表示线程池不接收任务，
         * 但此时数据队列里可能还有数据
         */
        try {
            executor.shutdown();
            // 对象加枷锁 设置超时时间，防止死锁
            synchronized (lockObj) {
                if (executor.isTerminated()) {
                    return;
                }
                executor.setThreadPoolLockObj(lockObj);
                lockObj.wait(LOCK_WAIT_TIME_OUT);
            }

        } catch (Exception e ){
//            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void complete() {
        executor.shutdown();
    }

    @Override
    public void stopWork() {
        if (executor.isShutdown()) {
            return;
        }
        executor.purge();
        executor.shutdown();
    }

    @Override
    public List<Out> getProcessRes() {
        List<Out> resList = new ArrayList<>();
        if (concurrentLinkedQueue != null) {
            resList =  (List<Out>) concurrentLinkedQueue.stream().collect(Collectors.toList());
        }
        return resList;
    }
}
