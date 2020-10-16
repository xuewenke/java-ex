

package com.x.atrs.util;

import com.x.atrs.util.string.StringRegularUtil;
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