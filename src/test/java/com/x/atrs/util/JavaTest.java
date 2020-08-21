
/*
 * @Copyright 2014-2020 云安宝 (www.yunanbao.com.cn).
 *
 * 注意：本内容仅限于深圳云安宝科技有限公司内部传阅，禁止外泄以及用于其他商业目的
 *
 */

package com.x.atrs.util;

import org.testng.annotations.Test;

import java.text.DecimalFormat;

/**
 * @author xuewenke
 * @since 2020/8/21 11:42 上午
 */
public class JavaTest {


    @Test
    public void testName() {
        DecimalFormat format = new DecimalFormat("#.00");

        System.out.println(format.format(87.3134));
    }
}