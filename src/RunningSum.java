public class RunningSum {
    /*
    https://leetcode.com/study-plan/leetcode-75/?progress=xdlhlco7 Level 1
    Given an array nums. We define a running sum of an array as runningSum[i] = sum(nums[0]…nums[i]).

    Return the running sum of nums.
     */
    public int[] runningSum(int[] nums) {
        int sum=0;
        for ( int i = 0; i<nums.length; i++)
            nums[i]= sum+=nums[i]; // reuse original array for less memory usage
        return nums;
    }
}
