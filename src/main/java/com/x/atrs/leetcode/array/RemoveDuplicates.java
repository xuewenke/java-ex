
package com.x.atrs.leetcode.array;

import lombok.experimental.UtilityClass;

/**
 * @author xuewenke
 * @since 2020/8/4 12:41 下午
 * <p>
 * 给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 * <p>
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
@UtilityClass
public class RemoveDuplicates {


    public int removeDuplicates(int[] nums) {
        int duplicates = 0;
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[i] == nums[j]) {
                duplicates++;
            } else {
                i++;
                nums[i] = nums[j];
            }
        }
        return nums.length - duplicates;
    }


    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int du = removeDuplicates(nums);
        for (int i = 0; i < du; i++) {
            System.out.println(nums[i]);

        }
    }

}