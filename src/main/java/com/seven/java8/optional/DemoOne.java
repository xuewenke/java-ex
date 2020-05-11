

package com.seven.java8.optional;

import org.testng.annotations.Test;

import java.util.Optional;

/**
 *
 * @author xuewenke
 */
public class DemoOne {

    @Test
    public void ifPresentTest() {
        StringBuilder sb = new StringBuilder("start ");
        Optional<String> stringOptional = Optional.of("123");
        Optional<String> notStringOptional = Optional.empty();
        stringOptional.ifPresent(s -> sb.append(s));
        notStringOptional.ifPresent(s -> sb.append(s));
        System.out.println(sb);
    }


}