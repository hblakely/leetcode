import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    /*
    Given an array of integers nums and an integer target, return indices of the two numbers such
    that they add up to target.

    You may assume that each input would have exactly one solution, and you may not use the same
    element twice.

    You can return the answer in any order.

    Constraints:
    2 <= nums.length <= 104
    -109 <= nums[i] <= 109
    -109 <= target <= 109
    Only one valid answer exists.

    Follow-up: Can you come up with an algorithm that is less than O(n2) time complexity?
     */

    // Brute force O(n^2) complexity. Space O(1).
    // 72ms, 45.7mb leetcode.com
    public int[] twoSumBruteForce(int[] nums, int target) {
        for (int i = 0; i<nums.length-1; i++)
            for(int j = i+1; j<nums.length; j++)
                if(nums[i]+nums[j]==target)
                    return new int[]{i,j};
        return new int[]{}; // No match found.
    }

    // O(n) traverse n*2. Space O(1).
    // 8ms, 47.4mb leetcode.com
    public int[] twoSumTwoPassHash(int[] nums, int target) {
        Map<Integer,Integer> hash = new HashMap<>();
        for(int i = 0; i<nums.length; i++){
            hash.put(nums[i], i);
        }
        for (int i = 0; i<nums.length; i++){
            int compliment = target-nums[i];
            Integer hashed = hash.get(compliment);
            if(hashed!=null && hashed!=i)
                return new int[]{i,hashed};
        }
        return new int[]{}; // No match found.
    }

    // O(n) traverse once. Space O(1).
    // 1ms, 42.5mb leetcode.com
    public int[] twoSumOnePassHash(int[] nums, int target) {
        Map<Integer, Integer> hash = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            // Check before putting to avoid overwriting duplicate values
            if (hash.get(nums[i]) == null)
                hash.put(nums[i], i);
            int compliment = target - nums[i];
            Integer hashed = hash.get(compliment);
            if (hashed != null && hashed != i)
                return new int[]{hashed, i};
        }
        return new int[]{}; // No match found.
    }
}

