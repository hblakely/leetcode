public class BinarySearch {
    /**
     * https://leetcode.com/study-plan/leetcode-75/?progress=xdlhlco7 Level 1
     * Given an array of integers nums which is sorted in ascending order,
     * and an integer target, write a function to search target in nums.
     * If target exists, then return its index. Otherwise, return -1.
     *
     * You must write an algorithm with O(log n) runtime complexity.
     *
     * CONSTRAINTS:
     * 1 <= nums.length <= 104
     * -104 < nums[i], target < 104
     * All the integers in nums are unique.
     * nums is sorted in ascending order.
     */

    public int search(int[] nums, int target) { // Using low-midpoint if array is even in size
        int lo = 0, hi = nums.length-1;
        while(lo<hi){
            int mid = (hi+lo)/2; // lower mid-point means result will be found in hi
            if(target>nums[mid])
                lo=mid+1; // incrementing lo eventually makes lo==hi, breaking us out of the loop.
            else
                hi=mid;
        }
        return nums[hi]==target?hi:-1;
    }

    public int searchHighMidPoint(int[] nums, int target){ // using high mid-point when array is even in size.
        /*
        If the constraints didn't specify 1<=nums.length<=10^4 (a non-empty array)
        we could return -1 here to avoid index out of bounds later.
        if(nums.length<1) return -1;
        */
        int lo = 0, hi = nums.length-1;
        while(lo<hi){
            // if the array is even, we could choose the lower or upper index as our midpoint.
            // upper mid: (hi+lo+1)/2,    mid = lo + (Math.floor(hi-lo+1)/2)
            // lower mid: (hi+lo)/2       mid = lo + (Math.floor(hi-lo)/2)
            int mid = (hi+lo+1)/2;

            // comparing target to mid will determine which side of the range our match could be located.
            // set hi index to one less than current mid point as the new maximum index, and will
            // eventually terminate the loop by reducing the hi value to less than the lo value.
            if(target<mid)
                hi=mid-1;
            else   // setting lo to mid reduces our search range by chopping off everything below mid
                lo=mid;  // If there is a match, the value will be stored in lo at the end of the loop.
        }
        return nums[lo]==target? nums[lo]:-1; // if value at lo index matches target return value, else -1 for no match
    }

    public int searchUpperMidPoint(int[] nums, int target) {
        /*
        If the constraints didn't specify 1<=nums.length<=10^4 (a non-empty array)
        we could return -1 here to avoid index out of bounds later.
        if(nums.length<1) return -1;
        */
        int lo = 0, hi = nums.length-1;
        while(lo<hi){
            // if the array is even, we could choose the lower or upper index as our midpoint.
            // upper mid: mid = lo + (Math.floor(hi-lo+1)/2)
            // lower mid: mid = lo + (Math.floor(hi-lo)/2)
            int mid = lo+(hi-lo+1)/2; // 4+(5-4+1)/2 = 5, which allows us to jump out of the loop with !5<5
            // This is also why we have to subtract 1 from the mid, when setting the new hi.

            // comparing target to mid will determine which side of the range our match could be located.
            // set hi index to one less than current mid point as the new maximum index, and will
            // eventually terminate the loop by reducing the hi value to less than the lo value.
            if(target<nums[mid])
                hi=mid-1;
            else   // setting lo to mid reduces our search range by chopping off everything below mid
                lo=mid;  // If there is a match, the value will be stored in lo at the end of the loop.
        }
        return nums[lo]==target?lo:-1; // if value at lo index matches target return value, else -1 for no match
    }


    // This first attempt is not elegant, and can be reduced to fewer lines with clearer logic.
    public int searchFirstAttempt(int[] nums, int target) {
        if(nums.length==0) // return -1 if array is empty
            return -1;

        int max = nums.length-1; // last index of array
        int min = 0; // first index of array

        // return -1 if target is not within range
        if(target>nums[max])
            return -1;
        if(target<nums[min])
            return -1;

        while(true){ // loop until one of the exit conditions is met.
            int mid=(max+min)/2; // Check value at midpoint, halfing search range every iteration.
            int val = nums[mid]; // set value for mid index.
            if(target==val) // if target is found at mid index, return mid index.
                return mid;
            if(target==nums[max]) // if target is found at max index, return max index.
                return max;
            if(target==nums[min]) // if target is found at min index, return min index.
                return min;

            // Update range to search within.
            if(target<val){
                max = mid;
            }
            if(target>val){
                min=mid;
            }

            // We've already checked if target is equal to min or max,
            //so if they're right next to each other we're done.
            if(min+1==max)
                break;
        }
        return -1;
    }
}
