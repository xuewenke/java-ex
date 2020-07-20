
package com.x.atrs.leetcode.tree;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xuewenke
 * @since 2020/7/14 2:10 下午
 */
public class UrlTrie {
    private final TrieNode root;

    private static final UrlTrie urlTrie = new UrlTrie();

    private UrlTrie() {
        root = new TrieNode("/");
    }

    public static UrlTrie getInstance() {
        return urlTrie;
    }

    /**
     * 插入url
     *
     * @param url
     * @param privilege
     */
    public void insert(String url, PrivilegeEnum privilege) {
        root.insertUrl(url, privilege);
    }

    /**
     * 根据url获取权限
     *
     * @param url
     * @return
     */
    public PrivilegeEnum getPrivilege(String url) {
        TrieNode endNode = root.getUrlEndNode(url);
        if (endNode != null) {
            return endNode.getPrivilege();
        }
        return null;
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TrieNode {
        /**
         * url节点名称
         */
        private String name;
        /**
         * 用来标记是否结束节点
         */
        private boolean isEndNode = false;

        /**
         * 权限枚举
         */
        private PrivilegeEnum privilege;

        /**
         * 子节点用map实现,方便查找
         */
        private Map<String, TrieNode> childNodeMap;

        public TrieNode(String name) {
            this.name = name;
            this.childNodeMap = new HashMap<>(16);
        }

        public static TrieNode createEndNode(String name, PrivilegeEnum privilege) {
            return new TrieNode(name, true, privilege, new HashMap<>(16));
        }

        public void insertUrl(String url, PrivilegeEnum privilege) {
            String[] units = urlSplit(url);
            TrieNode searchNode = this;
            int length = units.length;
            for (int i = 0; i < units.length; i++) {
                String unit = units[i];
                TrieNode node = searchNode.searchChildNode(unit);
                if (node != null) {
                    if (isEndUnit(length, i)) {
                        // 是尾节点但还有子节点的情况
                        node.setEndNode(true);
                        node.setPrivilege(privilege);
                    } else {
                        searchNode = node;
                    }
                } else {
                    TrieNode childNode;
                    if (isEndUnit(length, i)) {
                        childNode = createEndNode(unit, privilege);
                    } else {
                        childNode = new TrieNode(unit);
                    }
                    searchNode.addChildNode(childNode);
                    searchNode = childNode;
                }
            }
        }

        /**
         * 根据url查询末尾节点
         *
         * @param url
         * @return
         */
        public TrieNode getUrlEndNode(String url) {
            String[] units = urlSplit(url);
            TrieNode searchNode = this;
            for (String unit : units) {
                if (searchNode == null) {
                    break;
                }
                searchNode = searchNode.searchChildNode(unit);
            }
            return searchNode;
        }

        private TrieNode searchChildNode(String name) {
            if (StringUtils.isEmpty(name)) {
                return null;
            }
            Map<String, TrieNode> childNodes = this.getChildNodeMap();
            if (MapUtils.isEmpty(childNodes)) {
                return null;
            }
            TrieNode childNode = this.childNodeMap.get(name);
            if (childNode == null) {
                childNode = this.childNodeMap.get("*");
            }
            return childNode;
        }

        private void addChildNode(TrieNode node) {
            this.childNodeMap.put(node.getName(), node);
        }

        private static boolean isEndUnit(int length, int i) {
            return (length - 1) == i;
        }

        private static String[] urlSplit(String url) {
            return url.split("/");
        }
    }
}