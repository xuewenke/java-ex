
/*
 * @Copyright 2014-2020 云安宝 (www.yunanbao.com.cn).
 *
 * 注意：本内容仅限于深圳云安宝科技有限公司内部传阅，禁止外泄以及用于其他商业目的
 *
 */

package com.x.atrs.leetcode.tree;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author xuewenke
 */
@Data
@Builder
@AllArgsConstructor
public class UrlTrieList {


    TrieNode root;

    public UrlTrieList() {
        root = new TrieNode("/");
    }

    public static void main(String[] args) {
        UrlTrieList urlPathTrie = new UrlTrieList();
        String url = "get/pam/v1/identity/users";
        String url2 = "post/pam/v1/identity/users";
        String url3 = "delete/pam/v1/identity/users/{id}";
        urlPathTrie.insert(url, "add");
        urlPathTrie.insert(url2, "post");
        urlPathTrie.insert(url3, "delete");
        System.out.println(JSONObject.toJSONString(urlPathTrie.getRoot()));
        System.out.println(urlPathTrie.getPrivilege(url));
    }


    /**
     * 插入url
     *
     * @param url
     * @param privilege
     */
    public void insert(String url, String privilege) {
        root.insertUrl(url, privilege);
    }

    public String getPrivilege(String url) {
        TrieNode endNode = root.getEndNode(url);
        if (endNode != null) {
            System.out.println(JSONObject.toJSONString(endNode));
            return endNode.getPrivilege();
        }
        return null;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TrieNode {
        private String name;
        /**
         * 用来标记是否结束节点
         */
        private boolean isEnd = false;

        /**
         * 权限枚举
         */
        private String privilege;

        /**
         * 用arrayList
         */
        private List<TrieNode> childNode;

        public TrieNode(String name) {
            this.name = name;
            this.childNode = new ArrayList<>();
        }

        public static TrieNode createEndNode(String name, String privilege) {
            return new TrieNode(name, true, privilege, new ArrayList<>());
        }

        public void insertUrl(String url, String privilege) {
            String[] units = url.split("/");
            TrieNode searchNode = this;
            int length = units.length;
            for (int i = 0; i < units.length; i++) {
                String unit = units[i];
                TrieNode node = searchNode.searchChildNode(unit);
                if (node != null) {
                    searchNode = node;
                } else {
                    TrieNode childNode;
                    if (testEnd(length, i)) {
                        childNode = createEndNode(unit, privilege);
                    } else {
                        childNode = new TrieNode(unit);
                    }
                    searchNode.addChildNode(childNode);
                    searchNode = childNode;
                }
            }
        }

        public TrieNode getEndNode(String url) {
            String[] units = url.split("/");
            TrieNode searchNode = this;
            for (String unit : units) {
                if (searchNode == null) {
                    break;
                }
                searchNode = searchNode.searchChildNode(unit);
            }
            return searchNode;
        }

        private static boolean testEnd(int length, int i) {
            return (length - 1) == i;
        }


        private TrieNode searchChildNode(String name) {
            if (StringUtils.isEmpty(name)) {
                return null;
            }
            List<TrieNode> childNodes = this.getChildNode();
            if (CollectionUtils.isEmpty(childNodes)) {
                return null;
            }
            for (TrieNode node : childNodes) {
                if (Objects.equals(name, node.getName())) {
                    return node;
                }
            }
            return null;
        }

        private void addChildNode(TrieNode node) {
            this.childNode.add(node);
        }
    }

}