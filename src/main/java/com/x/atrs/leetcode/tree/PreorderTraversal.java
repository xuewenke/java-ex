
package com.x.atrs.leetcode.tree;

import lombok.experimental.UtilityClass;

import java.util.LinkedList;
import java.util.List;

/**
 * @author xuewenke
 * @since 2020/8/4 1:20 下午
 * 给定一个二叉树，返回它的 前序 遍历。
 */
@UtilityClass
public class PreorderTraversal {

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> collect = new LinkedList<>();
        helper(collect, root);
        return collect;
    }

    /**
     * 二叉树 中序遍历
     *
     * @param collect
     * @param node
     */
    private void helper(List<Integer> collect, TreeNode node) {
        if (node == null) {
            return;
        }
        collect.add(node.val);
        if (node.left != null) {
            helper(collect, node.left);
        }

        if (node.right != null) {
            helper(collect, node.right);
        }
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode left = new TreeNode(1);
        TreeNode right = new TreeNode(2);
        root.setLeft(left);
        root.setRight(right);

        List<Integer> list = preorderTraversal(root);
        list.forEach(System.out::println);

    }
}