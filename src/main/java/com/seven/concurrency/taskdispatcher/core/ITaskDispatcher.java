package com.seven.concurrency.taskdispatcher.core;

import java.util.List;

/**
 * All rights Reserved, Designed By 云安宝
 *
 * @author xuewenke
 * @version V3.0
 * @Package demo5
 * @Description (用一句话描述该文件做什么)
 * @date 2018/6/21 下午5:12
 * @Copyright 2018 www.yunanbao.com.cn Inc. All rights reserved.
 * <p>
 * <p>
 * <p>
 * 注意：本内容仅限于深圳云安宝科技有限公司内部传阅，禁止外泄以及用于其他商业目的
 */
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
