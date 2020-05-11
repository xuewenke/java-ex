package com.seven.concurrency.taskdispatcher.core;

import java.util.concurrent.ConcurrentLinkedQueue;


/**
 * 执行任务的工作对象，也就是业务代码
 * @author xuewenke
 */
public  class BaseWork< In extends  BaseInputData, Out extends  BaseOutputData> implements Runnable {


    /**
     * 需要处理的数据
     */
    protected In inputData;

    /**
     * 处理完的结果
     */
    protected ConcurrentLinkedQueue concurrentLinkedQueue;

    public BaseWork(In inputData) {
        this.inputData = inputData;
    }

    public void setInputData(In inputData) {
        this.inputData = inputData;
    }


    public void setConcurrentLinkedQueue(ConcurrentLinkedQueue concurrentLinkedQueue) {
        this.concurrentLinkedQueue = concurrentLinkedQueue;
    }

    @Override
    public void run() {
        process();
    }

    /**
     * 执行任务的业务代码
     */
    public void process() {
        BaseInputData baseInputData = this.inputData;
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("第" + inputData.getId() + "个任务执行完");

        collectProcessData((Out) new BaseOutputData());

    }


    /**
     * 存放处理后的返回结果
     *
     * @param baseOutputData
     */
    public void collectProcessData(Out baseOutputData) {
        if (concurrentLinkedQueue != null) {
            concurrentLinkedQueue.add(baseOutputData);
        }
    }
}
