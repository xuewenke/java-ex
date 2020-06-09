
/*
 * @Copyright 2014-2020 云安宝 (www.yunanbao.com.cn).
 *
 * 注意：本内容仅限于深圳云安宝科技有限公司内部传阅，禁止外泄以及用于其他商业目的
 *
 */

package com.x.atrs.util;



import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * 数组转换工具类
 *
 * @author xuewenke
 */
public class ArraysConvertUtil {


    public static int [] stringArray2Int(String [] arrays) {
        return Stream.of(arrays).mapToInt(Integer::parseInt).toArray();
    }

    public static void main(String[] args) {

        String [] a1 = {"6","7"};
        String [] a2 = {"7","6"};
        System.out.println(ArrayUtils.isEquals(a1, a2));
        Arrays.sort(a1);
        Arrays.sort(a2);
        System.out.println(ArrayUtils.isEquals(a1, a2));
    }
}