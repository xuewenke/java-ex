

package com.x.atrs.leetcode.array;

import lombok.experimental.UtilityClass;

/**
 * @author xuewenke
 * @since 2020/7/22 2:37 下午
 * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/
 * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
 * <p>
 * 请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * <p>
 * 你可以假设 nums1 和 nums2 不会同时为空。
 * 中位数就是有序的数组，中间的数。偶位数，取平均值。
 * 思路：
 * 1. 已知两个数组为有序的数组，则可以使用归并排序的方法，合并成一个有序的数组
 *
 */
@UtilityClass
public class FindMedianSortedArrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] mergeNums = merge(nums1, nums2);
        return singleNums(mergeNums);
    }

    /**
     * 合并两个有序的数组
     *
     * @param nums1
     * @param nums2
     * @return
     */
    private int[] merge(int[] nums1, int[] nums2) {
        if (nums1.length == 0) {
            return nums2;
        }

        if (nums2.length == 0) {
            return nums1;
        }

        int allLength = nums1.length + nums2.length;

        int[] mergeNums = new int[allLength];

        return mergeNums;
    }


//    public void merge(int[] nums1, int[] nums2) {
//        int m = nums1.length;
//        int n = nums2.length;
//        // Make a copy of nums1.
//        int [] nums1_copy = new int[m];
//        System.arraycopy(nums1, 0, nums1_copy, 0, m);
//
//        // Two get pointers for nums1_copy and nums2.
//        int p1 = 0;
//        int p2 = 0;
//
//        // Set pointer for nums1
//        int p = 0;
//
//        // Compare elements from nums1_copy and nums2
//        // and add the smallest one into nums1.
//        while ((p1 < m) && (p2 < n)) {
//            nums1[p++] = (nums1_copy[p1] < nums2[p2]) ? nums1_copy[p1++] : nums2[p2++];
//        }
//
//        // if there are still elements to add
//        if (p1 < m) {
//            System.arraycopy(nums1_copy, p1, nums1, p1 + p2, m + n - p1 - p2);
//        }
//        if (p2 < n) {
//            System.arraycopy(nums2, p2, nums1, p1 + p2, m + n - p1 - p2);
//        }
//    }


    private double singleNums(int[] nums) {
        // 余数
        int remainder = nums.length % 2;
        // 整除
        int divide = nums.length / 2;
        if (remainder != 0) {
            return nums[divide];
        } else {
            return (nums[divide] + nums[divide - 1]) / 2D;
        }
    }


    public static void main(String[] args) {
        System.out.println(findMedianSortedArrays(new int[]{1, 3, 5}, new int[]{2, 4, 6}));
    }
}