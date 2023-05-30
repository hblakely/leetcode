import unittest
from scenarios import scenario_278_first_bad_version

class MyTestCase(unittest.TestCase):

    def test_found_short(self):
        expected = 1
        firstBad = scenario_278_first_bad_version.first_bad_version(expected)
        result = firstBad.firstBadVersion(1)
        self.assertEqual(expected, result)

    def test_found_long(self):
        expected = 36
        firstBad = scenario_278_first_bad_version.first_bad_version(expected)
        result=firstBad.firstBadVersion(1234567890876543)
        self.assertEqual(expected, result)

    def test_not_found(self):
        expected = -1
        firstBad = scenario_278_first_bad_version.first_bad_version(None)
        result = firstBad.firstBadVersion(3)
        self.assertEqual(expected, result)


if __name__ == '__main__':
    unittest.main()
