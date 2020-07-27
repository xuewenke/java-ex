

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
 * 1.把两个数组合并后排序，找中间的数。
 * 2. 先确定好中位数的下标，然后两个数据对比，找出中位数。
 */
@UtilityClass
public class FindMedianSortedArrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length == 0) {
            return singleNums(nums2);
        }

        if (nums2.length == 0) {
            return singleNums(nums1);
        }

        int allLength = nums1.length + nums2.length;
        // 余数
        int remainder = allLength % 2;
        // 整除
        int divide = allLength / 2;
//        int cursor = 1;
        int nums1Index = 0;
        int nums2Index = 0;
        int singleTarget = 0;
        for (int i = 0; i <= divide; i++) {
            if (nums1[nums1Index] <= nums2[nums2Index]) {
                int temp = nums1Index++;
                if (i == divide) {
                    singleTarget = nums1[temp];
                }
            } else {
                int temp = nums2Index++;
                if (i == divide) {
                    singleTarget = nums1[temp];
                }

            }
        }

        if (remainder == 0) {
            return (nums1[nums1Index] + nums2[nums2Index]) / 2D;
        } else {
            return singleTarget;
        }
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
        System.out.println(findMedianSortedArrays(new int[]{1, 3, 5}, new int[]{2, 4, 6}));
    }
}