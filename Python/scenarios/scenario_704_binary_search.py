"""
Given an array of integers nums which is sorted in ascending order,
and an integer target, write a function to search target in nums.
If target exists, then return its index. Otherwise, return -1.

You must write an algorithm with O(log n) runtime complexity.
"""
def search(nums, target):
    # Nothing to work on, return not found (-1)
    if not (nums and target):
        return -1
    # index boundaries set lo to first, and hi to last index
    lo = 0
    hi = len(nums) - 1

    # loop til found or there are no more values to search (when lo > hi)
    while lo <= hi:
        #   In an even number of values, we would choose the lower-mid,
        #   by truncating the float value from the halfed value with //
        mid = (lo + hi) // 2

        #   If value at mid-index matcches target, return mid.
        if nums[mid] == target:
            return mid

        #   Set upper bound to one less than mid to reduce our search size
        #   if the value at mid-index is larger than our target.
        elif nums[mid] > target:
            hi = mid - 1

        #   Set lower bound to one more than mid to reduce our search size
        #   if the value at mid-index is less than our target.
        else:
            lo = mid + 1

    # target is not contained within our sorted list
    return -1



"""
The same as above, but recursive instead of iterative.
"""
def search_recusrive(nums, target):
    if not (nums and target):
        return -1
    return recurr(nums, target, 0, len(nums)-1)

def recurr(nums, target, lo, hi):
    # Not found
    if lo>hi:
        return -1

    mid = (lo + hi) // 2

    # Found
    if nums[mid] == target:
        return mid

    # Update search boundaries and continue searching
    elif nums[mid] > target:
        return recurr(nums, target, lo, mid-1)
    else:
        return recurr(nums, target, mid+1, hi)
