


package com.x.atrs;

import org.testng.annotations.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author xuewenke
 */
public class TestCode {




    @Test
    public void test() {
        String s = "123\n" +
                "  345";
        System.out.println(s);
        System.out.println(replaceBlank(s));
    }




    private int hash(Object key) {
        int h;
        System.out.println(" java hashCode = " + key.hashCode());
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }


    public static String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

}