
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
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xuewenke
 * @since 2020/7/14 2:10 下午
 */
@Data
@Builder
@AllArgsConstructor
public class UrlTrieMap {
    TrieNode root;

    public UrlTrieMap() {
        root = new TrieNode("/");
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
        private boolean isEndNode = false;

        /**
         * 权限枚举
         */
        private String privilege;

        /**
         * 子节点用map实现,方便查找
         */
        private Map<String, TrieNode> childNode;

        public TrieNode(String name) {
            this.name = name;
            this.childNode = new HashMap<>(16);
        }

        public static TrieNode createEndNode(String name, String privilege) {
            return new TrieNode(name, true, privilege, new HashMap<>(16));
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
                    if (isEnd(length, i)) {
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

        private static boolean isEnd(int length, int i) {
            return (length - 1) == i;
        }

        private TrieNode searchChildNode(String name) {
            if (StringUtils.isEmpty(name)) {
                return null;
            }
            Map<String, TrieNode> childNodes = this.getChildNode();
            if (MapUtils.isEmpty(childNodes)) {
                return null;
            }
            return childNodes.get(name);
        }

        private void addChildNode(TrieNode node) {
            this.childNode.put(node.getName(), node);
        }
    }
}