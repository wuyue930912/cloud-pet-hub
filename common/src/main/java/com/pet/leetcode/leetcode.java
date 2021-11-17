package com.pet.leetcode;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class leetcode {

    public static void main(String[] args) {
        List<ComparableTestEntity> list = new ArrayList<>();
        list.add(new ComparableTestEntity("xxp8", 10));
        list.add(new ComparableTestEntity("xxp2", 2));
        list.add(new ComparableTestEntity("xxp1", 1));
        list.add(new ComparableTestEntity("xxp3", 3));
        list.add(new ComparableTestEntity("xxp5", 5));
        list.add(new ComparableTestEntity("xxp4", 4));
        list.add(new ComparableTestEntity("xxp6", 6));
        list.add(new ComparableTestEntity("xxp7", 7));

        list.forEach(entity -> System.out.println(entity.toString()));

        Collections.sort(list);

        list.forEach(entity -> System.out.println(entity.toString()));
    }

    /**
     * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那 两个 整数，并返回它们的数组下标。
     * <p>
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
     * <p>
     * 你可以按任意顺序返回答案。
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [2,7,11,15], target = 9
     * 输出：[0,1]
     * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
     * 示例 2：
     * <p>
     * 输入：nums = [3,2,4], target = 6
     * 输出：[1,2]
     * 示例 3：
     * <p>
     * 输入：nums = [3,3], target = 6
     * 输出：[0,1]
     * 提示：
     * <p>
     * 2 <= nums.length <= 104
     * -109 <= nums[i] <= 109
     * -109 <= target <= 109
     * 只会存在一个有效答案
     * 进阶：你可以想出一个时间复杂度小于 O(n2) 的算法吗？
     * <p>
     * Related Topics
     * 数组
     * 哈希表
     */
    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if ((nums[i] + nums[j]) == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }
}

@Data
@AllArgsConstructor
class ComparableTestEntity implements Comparable<ComparableTestEntity> {
    private String name;
    private Integer age;

    @Override
    public int compareTo(ComparableTestEntity o) {
        if (o.getAge() >= this.age) {
            return -1;
        } else {
            return 1;
        }
    }
}
