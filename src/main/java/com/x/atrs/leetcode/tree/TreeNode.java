
package com.x.atrs.leetcode.tree;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 *  二叉树
 * @author xuewenke
 * @since 2020/8/4 1:18 下午
 */
@Data
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x) {
        this.val = x;
    }

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


    /**
     *  最大深度
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftMax = maxDepth(root.left);
        int rightMax = maxDepth(root.right);
        return Math.max(leftMax, rightMax) + 1;
    }
}