
/*
 * @Copyright 2014-2020 云安宝 (www.yunanbao.com.cn).
 *
 * 注意：本内容仅限于深圳云安宝科技有限公司内部传阅，禁止外泄以及用于其他商业目的
 *
 */

package com.x.atrs.util.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Map;

/**
 * @author xuewenke
 * @since 2020/11/29 下午3:10
 */
public class TestJob implements Job {


    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        Map<String, Object> data = context.getMergedJobDataMap();
        long nowTime = System.currentTimeMillis();
        while (true) {
            System.out.println(" test job work id = " + Thread.currentThread().getId());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

//            long afterTime = System.currentTimeMillis();
//            if ((afterTime - nowTime) > 1000 * 1000) {
//                System.out.println("test job end ");
//                break;
//            }

        }
    }
}