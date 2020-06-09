


package com.x.atrs;

import org.testng.annotations.Test;

/**
 *
 * @author xuewenke
 */
public class TestCode {


    @Test
    public void test() {
        for (int i = 0; i < 100; i++) {
            System.out.println(hash(i));
        }
    }


    private int hash(Object key) {
        int h;
        System.out.println(" java hashCode = " + key.hashCode());
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
}