

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
 * 1. 已知两个数组为有序的数组，则可以使用归并排序的方法，合并成一个有序的数组,一个有序的数组求中位数，时间复杂度为常数
 *
 */
@UtilityClass
public class FindMedianSortedArrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] mergeNums = merge(nums1, nums2);
        return singleNums(mergeNums);
    }

    /**
     * 合并两个有序的数组不去重
     *
     * @param nums1
     * @param nums2
     * @return
     */
    private int[] merge(int[] nums1, int[] nums2) {
        int l1 = nums1.length;
        int l2 = nums2.length;

        if (l1 == 0) {
            return nums2;
        }
        if (l2 == 0) {
            return nums1;
        }
        int allLength = l1 + l2;

        int[] mergeNums = new int[allLength];

        int p1 = 0;
        int p2 = 0;

        int p = 0;
        for (int i = 0; i < allLength; i++) {
            p = i;
            if (p1 == l1 || p2 == l2) {
                break;
            }
            mergeNums[i] = (nums1[p1] < nums2[p2]) ? nums1[p1++] : nums2[p2++];
        }
        if (p1 < l1) {
            System.arraycopy(nums1, p1, mergeNums, p, l1 - p1);
        }

        if (p2 < l2) {
            System.arraycopy(nums2, p2, mergeNums, p, l2 - p2);
        }
        return mergeNums;
    }

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

//        int[] nums1 = {1, 3, 5};
//        int[] nums2 = {2, 4, 6};

        int[] nums1 = {1, 3, 5};
        int[] nums2 = {2, 4,};

        double d = findMedianSortedArrays(nums1, nums2);
        System.out.println(d);

    }
}