

package com.x.atrs.util;

import com.x.atrs.leetcode.tree.UrlTrie;
import org.testng.annotations.Test;

/**
 * @author xuewenke
 * @since 2020/7/14 3:05 下午
 */
public class UrlTrieTest {

    @Test
    public void testUrlTrie() {
        UrlTrie urlPathTrie = UrlTrie.getInstance();
        String url = "get/pam/v1/identity/users";
        String url2 = "post/pam/v1/identity/users";
        String url3 = "delete/pam/v1/identity/users/{id}";
        urlPathTrie.insert(url, null);
        urlPathTrie.insert(url2, null);
        urlPathTrie.insert(url3, null);
        System.out.println(urlPathTrie.getPrivilege(url));

    }
}