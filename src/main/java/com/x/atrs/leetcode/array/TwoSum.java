

package com.x.atrs.leetcode.array;

import com.alibaba.fastjson.JSONObject;
import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xuewenke
 * @since 2020/7/20 4:46 下午
 * 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
 * <p>
 * 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
 * <p>
 * 说明:
 * <p>
 * 返回的下标值（index1 和 index2）不是从零开始的。
 * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
@UtilityClass
public class TwoSum {


    public int[] twoSum(int[] numbers, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> numMap = new HashMap<>(16);
        for (int i = 0; i < numbers.length; i++) {
            numMap.put(numbers[i], i);
        }
        for (int i = 0; i < numbers.length; i++) {
            int findTarget = target - numbers[i];
            Integer findTargetIndex = numMap.get(findTarget);
            if (findTargetIndex != null) {
                result[0] = i + 1;
                result[1] = findTargetIndex + 1;
                break;
            }
        }
        return result;
    }

    /**
     * [2,7,11,15]
     * 9
     *
     * @param args
     */
    public void main(String[] args) {
        int[] numbers = {2, 7, 11, 15};
        int target = 9;
        System.out.println(JSONObject.toJSONString(twoSum(numbers, target)));

    }

}