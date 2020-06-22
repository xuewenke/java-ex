package com.x.atrs.concurrency.taskdispatcher.core;

import java.util.List;

public interface ITaskDispatcher {


    /**
     * 添加需要处理的数据
     * @param inputData
     */
    void  dispathTask(BaseInputData inputData);


    /**
     * 等待线程池完成任务
     */
    void awaitToComplete();


    /**
     * 标识任务分发完成
     */
    void complete();


    /**
     * 终止线程池任务
     */
    void stopWork();


    /**
     * 返回处理结果
     * @param
     * @return
     */
    List getProcessRes();

}
