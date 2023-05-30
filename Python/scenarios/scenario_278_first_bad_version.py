class first_bad_version(object):

    def __init__(self, num):
        self.first_bad = num

    def firstBadVersion(self, n):
        lo = 1
        hi = n
        # Loop until found or there are no more versions to check
        while lo <= hi:
            mid = (lo + hi) // 2
            current = self.isBadVersion(self, mid)
            previous = self.isBadVersion(self, mid - 1) if mid - 1 >= 1 else False

            # If current is bad, and previous is good, we found first bad.
            if current and not previous:
                return mid

            # If current is good, set lo to current index (mid)+1
            elif not current:
                lo = mid + 1

            # If current is bad, set hi to current index (mid)
            else:
                hi = mid - 1

        # No good versions
        return -1

    @staticmethod
    def isBadVersion(self, num):
        return False if not (self.first_bad and num) else num >= self.first_bad