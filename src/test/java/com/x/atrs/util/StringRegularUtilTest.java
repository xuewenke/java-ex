
/*
 * @Copyright 2014-2020 云安宝 (www.yunanbao.com.cn).
 *
 * 注意：本内容仅限于深圳云安宝科技有限公司内部传阅，禁止外泄以及用于其他商业目的
 *
 */

package com.x.atrs.util;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

/**
 * @author xuewenke
 * @since 2020/7/16 11:58 上午
 */
public class StringRegularUtilTest {


    @Test
    public void testFindCharCount() {
        String s = "GET/pam/identity/v1/apps/{id}/{er}";
        int count = StringRegularUtil.matchCount(s, "\\{");
        AssertJUnit.assertEquals(count, 2);
    }
}