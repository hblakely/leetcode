import unittest
from scenarios import scenario_704_binary_search

"""
    Binary search through list of sorted integers to find target.
    Return -1 if target is not found.

    LeetCode Constraints:
    1 <= nums.length <= 104
    -104 < nums[i], target < 104
    All the integers in nums are unique.
    nums is sorted in ascending order.
    
    We're testing a bit further by throwing None and empty at our binary search
"""


class MyTestCase(unittest.TestCase):

    def test_target_found(self):
        nums = [-1, 0, 3, 5, 9, 12]
        target = 9
        expected = 4
        result = scenario_704_binary_search.search(nums, target)
        self.assertEqual(expected, result)
        result_recursive = scenario_704_binary_search.search_recusrive(nums, target)
        self.assertEqual(expected, result_recursive)

    def test_target_not_found(self):
        nums = [-1, 0, 3, 5, 9, 12]
        target = 2
        expected = -1
        result = scenario_704_binary_search.search(nums, target)
        self.assertEqual(expected, result)
        result_recursive = scenario_704_binary_search.search_recusrive(nums, target)
        self.assertEqual(expected, result_recursive)

    def test_target_none(self):
        nums = [-1, 0, 3, 5, 9, 12]
        target = None
        expected = -1
        result = scenario_704_binary_search.search(nums, target)
        self.assertEqual(expected, result)
        result_recursive = scenario_704_binary_search.search_recusrive(nums, target)
        self.assertEqual(expected, result_recursive)

    def test_list_none(self):
        nums = None
        target = 2
        expected = -1
        result = scenario_704_binary_search.search(nums, target)
        self.assertEqual(expected, result)
        result_recursive = scenario_704_binary_search.search_recusrive(nums, target)
        self.assertEqual(expected, result_recursive)

    def test_list_empty(self):
        nums = []
        target = 2
        expected = -1
        result = scenario_704_binary_search.search(nums, target)
        self.assertEqual(expected, result)
        result_recursive = scenario_704_binary_search.search_recusrive(nums, target)
        self.assertEqual(expected, result_recursive)


if __name__ == '__main__':
    unittest.main()
