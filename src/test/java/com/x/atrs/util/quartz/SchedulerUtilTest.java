
package com.x.atrs.util.quartz;

import com.x.atrs.util.time.DateUtil;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xuewenke
 * @since 2020/11/29 下午3:06
 */
public class SchedulerUtilTest {


    /**
     * 测试执行一次的任务，在不改时间的情况下，重新发布会不会把点前的任务终止
     */
    @Test
    public void reSchedulerTest() throws Exception {
        Date executeTime = DateUtil.toDate("2020-11-29 16:03:00");
        Date executeEndTime = DateUtil.toDate("2020-12-05 16:00:00");
        Map<String, Object> one = new HashMap<>();
        one.put("id", 1);
        SchedulerUtil.getInstance().addNewJobWithHour("test123", TestJob.class, one, executeTime, executeEndTime, 4);

        // 等待10
        System.out.println("等待修改时间");
        Thread.sleep(60 * 1000);

        // 重新发布
        System.out.println("重新发布工作");
        SchedulerUtil.getInstance().reloadJobs();

        //
        Thread.sleep(1000 * 1000);
    }
}