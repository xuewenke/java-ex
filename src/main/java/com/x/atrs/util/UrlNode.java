//
///*
// * @Copyright 2014-2020 云安宝 (www.yunanbao.com.cn).
// *
// * 注意：本内容仅限于深圳云安宝科技有限公司内部传阅，禁止外泄以及用于其他商业目的
// *
// */
//
//package com.x.atrs.util;
//
//import com.alibaba.fastjson.JSONObject;
//import lombok.Builder;
//import lombok.Data;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// *
// * @author xuewenke
// */
//@Data
//@Builder
//public class UrlNode {
//
//
//    private UrlNode root;
//
//    private String name;
//    /**
//     * 用来标记是否结束节点
//     */
//    private boolean isEnd = true;
//
//    /**
//     * 用来装子节点的容器，写成map是为了更快的找到子节点
//     */
//    private Map<String,UrlNode> childNode = new HashMap<>(16);
//    /**
//     * 权限枚举
//     */
//    private String privilege;
//
//    public UrlNode() {
//        this.root = createRoot();
//    }
//
//
//    public static UrlNode createRoot() {
//        UrlNode root = new UrlNode();
//        root.setName("/");
//        Map<String, UrlNode> childMap = new HashMap<>(16);
//        childMap.put("get", createInsertNode("get"));
//        childMap.put("put", createInsertNode("put"));
//        childMap.put("post", createInsertNode("post"));
//        childMap.put("delete", createInsertNode("delete"));
//        root.setChildNode(childMap);
//        return root;
//    }
//
//    public static UrlNode createInsertNode(String name) {
//        return UrlNode.builder()
//                .name(name)
//                .build();
//    }
//
//    public static UrlNode createEndNode(String name, String privilege) {
//        return UrlNode.builder()
//                .name(name)
//                .isEnd(true)
//                .privilege(privilege)
//                .build();
//    }
//
//    /**
//     *
//     * @param root
//     * @param urlPath 类似 get/pam/v1/identity/users
//     */
//    public void insertNode(String urlPath, String privilege) {
//        String[] splitPaths = urlPath.split("/");
//        Map<String, UrlNode> childNode = root.getChildNode();
//        int length = splitPaths.length;
//
//
//    }
//
//    private void buildTree(Map<String, UrlNode> childNode, String[] splitPaths, int index, String privilege) {
//        if (isEnd(splitPaths.length, index)) {
//            String pathName = splitPaths[index];
//            UrlNode next = childNode.get(pathName);
//            if (next != null) {
//                // 报错
//            } else {
//                UrlNode endNode = createEndNode(pathName, privilege);
//                childNode.put(pathName, endNode);
//            }
//
//        }
//        String pathName = splitPaths[index];
//        UrlNode next = childNode.get(pathName);
//        if (next == null) {
//            next = createInsertNode(pathName);
//            childNode.put(pathName, next);
//        }
//        index++;
//        buildTree(next.getChildNode(),splitPaths, index, privilege);
//    }
//
//
//    private boolean isEnd(int length, int i) {
//        return i == (length -1);
//    }
//
//    public static void main(String[] args) {
//        UrlNode urlNode = new UrlNode();
//        String url = "get/pam/v1/identity/users";
//        urlNode.insertNode(url, "add");
//        System.out.println(JSONObject.toJSONString(url));
//
//    }
//
//
//
//}