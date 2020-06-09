
package com.x.atrs.cache.guavacache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author xuewenke
 */
public class CacheBuilderDemo {


    public static void main(String[] args) throws Exception{
        Cache<String, String> cache = simpleCache();
        cache.put("",null);
    }

    @Test
    public void testTimeOut() throws Exception{
        Cache<String, String> cache = simpleConfigCache();
        for (int i = 0; i < 10; i++) {
            cache.put(String.valueOf(i), String.valueOf(i));
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(cache.getIfPresent(String.valueOf(i)));
        }

        TimeUnit.SECONDS.sleep(3);

        for (int i = 0; i < 10; i++) {
            System.out.println(cache.getIfPresent(String.valueOf(i)));
        }
    }


    /**
     * 单纯的使用一个cache
     * 这里没有做任何的配置设置，所以这个时候你需要去看，他的默认配置是什么。
     * 例如，最大存储，过期时间
     * @return
     */
    public static Cache<String, String> simpleCache() {
        Cache<String, String> cache = CacheBuilder.newBuilder()
                .build();
        return cache;
    }


    public static Cache<String, String> simpleConfigCache() {
        // 设置最大存储和写入后的超时时间
        Cache<String, String> cache = CacheBuilder.newBuilder().maximumSize(50)
                .expireAfterWrite(2, TimeUnit.SECONDS).build();
        return cache;
    }




}