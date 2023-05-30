public class RunningSum {
    /*
    https://leetcode.com/study-plan/leetcode-75/?progress=xdlhlco7 Level 1
    Given an array nums. We define a running sum of an array as runningSum[i] = sum(nums[0]…nums[i]).

    Return the running sum of nums.
     */
    public static int[] runningSum(int[] nums) {
        int sum=0;
        for ( int i = 0; i<nums.length; i++)
            nums[i]= sum+=nums[i]; // reuse original array for less memory usage
        return nums;
    }

    public static void main(String[] args){
        example1();
        example2();
        example3();
    }

    /**
     * Input: nums = [1,2,3,4]
     * Output: [1,3,6,10]
     * Explanation: Running sum is obtained as follows: [1, 1+2, 1+2+3, 1+2+3+4].
     */
    private static void example1(){
        int[] nums = {1,2,3,4};
        System.out.println("Example1: nums = {1,2,3,4};");
        nums=runningSum(nums);
        for(int i = 0; i<nums.length; i++)
            System.out.print(nums[i]);
        System.out.println("\n");

    }

    /**
     * Input: nums = [1,1,1,1,1]
     * Output: [1,2,3,4,5]
     * Explanation: Running sum is obtained as follows: [1, 1+1, 1+1+1, 1+1+1+1, 1+1+1+1+1].
     */
    private static void example2(){
        int[] nums = {1,1,1,1,1};
        System.out.println("Example1: nums = {1,1,1,1,1};");
        nums=runningSum(nums);
        for(int i = 0; i<nums.length; i++)
            System.out.print(nums[i]);
        System.out.println("\n");
    }
    /**
     * Input: nums = [3,1,2,10,1]
     * Output: [3,4,6,16,17]
     */
    private static void example3(){
        int[] nums = {3,1,2,10,1};
        System.out.println("Example1: nums = {3,1,2,10,1};");
        nums=runningSum(nums);
        for(int i = 0; i<nums.length; i++)
            System.out.print(nums[i]);
        System.out.println("\n");
    }

}
