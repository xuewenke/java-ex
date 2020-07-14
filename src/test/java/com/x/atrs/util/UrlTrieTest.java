
/*
 * @Copyright 2014-2020 云安宝 (www.yunanbao.com.cn).
 *
 * 注意：本内容仅限于深圳云安宝科技有限公司内部传阅，禁止外泄以及用于其他商业目的
 *
 */

package com.x.atrs.util;

import com.alibaba.fastjson.JSONObject;
import com.x.atrs.leetcode.tree.UrlTrieMap;
import org.testng.annotations.Test;

/**
 * @author xuewenke
 * @since 2020/7/14 3:05 下午
 */
public class UrlTrieTest {

    @Test
    public void testUrlTrie() {
        UrlTrieMap urlPathTrie = new UrlTrieMap();
        String url = "get/pam/v1/identity/users";
        String url2 = "post/pam/v1/identity/users";
        String url3 = "delete/pam/v1/identity/users/{id}";
        urlPathTrie.insert(url, "add");
        urlPathTrie.insert(url2, "post");
        urlPathTrie.insert(url3, "delete");
        System.out.println(JSONObject.toJSONString(urlPathTrie.getRoot()));
        System.out.println(urlPathTrie.getPrivilege(url));

    }
}