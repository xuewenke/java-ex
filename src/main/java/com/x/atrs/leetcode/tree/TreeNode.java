
package com.x.atrs.leetcode.tree;

import lombok.Data;

/**
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

}