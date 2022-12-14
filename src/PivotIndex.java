public class PivotIndex {
    /*
                https://leetcode.com/study-plan/leetcode-75/?progress=xdlhlco7 Level 1
                Given an array of integers nums, calculate the pivot index of this array.

                The pivot index is the index where the sum of all the numbers strictly to the left of the index is equal
                to the sum of all the numbers strictly to the index's right.

                If the index is on the left edge of the array, then the left sum is 0 because there are no elements to
                the left. This also applies to the right edge of the array.

                Return the leftmost pivot index. If no such index exists, return -1.
     */

    /**
     * Simple and clean
     * @param nums
     * @return
     */
    public static int pivotIndexSolution(int[] nums) {
        int sum = 0, leftsum = 0;
        for (int x: nums) sum += x;
        for (int i = 0; i < nums.length; ++i) {
            if (leftsum == sum - leftsum - nums[i]) return i;
            leftsum += nums[i];
        }
        return -1;
    }




    /*
        This could easily become far too complex if we were to brute force by looping through
        the dataset to compare totals on the left of index and the right of index repeatedly.

        If we calculate running sums for each index we can subtract current index's total
         from the last index's total to get the sum of all items on the right side of current
         index. If the sum of all items on the right side of the current index is equal to index-1's
         running sum, we have a match at current index.

         We are asked to return -1 if no index satisfies the condition of being a pivot index.

         We are asked to return 0 if the index is on the left edge or the right edge of the array.

         We must return the LeftMost pivot index. This means that if we are given an array of [0,0,0,0],
         our answer would be the first index, because any of the indicis could be considered a pivot index.
         The fact that there could be multiple pivot indecis means we can't use a binary search to speed up
         the search.
        */

    /**
     * FIRST ATTEMPT, very wordy, and a bit messy.
     * @param nums
     * @return
     */
    public int pivotIndex(int[] nums) {

        int pivot_index=-1; // set default to fail value of -1
        if(nums.length ==0) // fail if invalid input
            return pivot_index;
        if(nums.length==1)  // return 0 if pivot. size 1 is nothing but edge, so we just return 0 here.
            return 0;

        // start at index 1, add i-1 to i, and store in i. We're looking for indicis, and don't need to keep data
        // in the array, so we'll reuse the original array to hold the running sum at each index.
        // first pass sets the running sum to the index
        for (int i =1; i<nums.length; i++){  // Adds N complexity
            nums[i]=nums[i-1]+nums[i];
        }

        // return 0 for edge cases, pivot index is first or last index.
        if(nums[nums.length-1]-nums[0]==0) // running sum at first and at last indexes are the same
            return 0;

        // second time through compares left side to right side (basically)
        for (int i = 1; i<nums.length; i++){ // Adds N complexity

            // subtract value at current index from total sum (value at last index)
            // to get running-sum of all right indexes.
            int right_running_sum=nums[nums.length-1]-nums[i];
            // nums[i-1] is left-side running sum.
            if(right_running_sum==nums[i-1]){ // compare left running-sum with right running-sum
                pivot_index = i; // leftmost pivot index found.
                break; // break and return
            }
        }
        return pivot_index;
    }

    public static void main(String[] args){
        example1();
        example2();
    }

    /**
     * Input: nums = [1,7,3,6,5,6]
     * Output: 3
     * Explanation:
     * The pivot index is 3.
     * Left sum = nums[0] + nums[1] + nums[2] = 1 + 7 + 3 = 11
     * Right sum = nums[4] + nums[5] = 5 + 6 = 11
     */
    private static void example1(){
        int[] nums = {1,7,3,6,5,6};
        System.out.println("Example2: nums = [1,7,3,6,5,6]");
        System.out.println(pivotIndexSolution(nums));
    }

    /**
     * Input: nums = [1,2,3]
     * Output: -1
     * Explanation:
     * There is no index that satisfies the conditions in the problem statement.
     */
    private static void example2(){
        int[] nums = {1,2,3};
        System.out.println("Example1: nums = [1,2,3]");
        System.out.println(pivotIndexSolution(nums));
    }
}
