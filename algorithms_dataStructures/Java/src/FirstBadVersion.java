public class FirstBadVersion {
    /*
    https://leetcode.com/study-plan/leetcode-75/?progress=xdlhlco7 Level 1
    You are a product manager and currently leading a team to develop a new product.
    Unfortunately, the latest version of your product fails the quality check.
    Since each version is developed based on the previous version, all the versions after a bad version are also bad.

    Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one,
    which causes all the following ones to be bad.

    You are given an API bool isBadVersion(version) which returns whether version is bad. Implement a
    function to find the first bad version. You should minimize the number of calls to the API.


    Example 1:
    Input: n = 5, bad = 4
    Output: 4
    Explanation:
    call isBadVersion(3) -> false
    call isBadVersion(5) -> true
    call isBadVersion(4) -> true
    Then 4 is the first bad version.

    Example 2:
    Input: n = 1, bad = 1
    Output: 1

    Constraints:
    1 <= bad <= n <= 231 - 1
     */

    public static int firstBadVersion(int n) {
        // First thought is a search function would be good, but we're not just matching a single target.
        // We're matching a target that has two parts.
        // isBadVersion(n) must be adjacent isBadVersion(n+1)
        // The exception to this would be if the very first version is bad. Then there were never any good versions.

        /* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); in leetcode.com's package.*/
        if(isBadVersion(1)) // The very first version was bad. Skip all the other searches.
            return 1;
        // At this point we know that we have at least one good version, so the first bad version is between 1 and n.

        // Using long to prevent lossy conversions and integer overflow.
        // When adding high and low (at the extreme cases), integer overflow occurrs and gives a negative number.
        long highBadVersion = n;
        long lowGoodVersion = 1;
        while(lowGoodVersion<highBadVersion){  // This is basically a binary search.
            long midVersion = (highBadVersion+lowGoodVersion)/2; // Midpoint between good and bad.
            // We're using low-midpoint, so answer should be stored in highBadVersion (the first bad one).
            if(isBadVersion((int)midVersion))
                highBadVersion = midVersion;
            else
                lowGoodVersion=midVersion+1; // adding 1 eventually breaks loop.
        }
        // Constraints say n was a bad version, so if search yields no match return n.
        return isBadVersion((int)highBadVersion)?(int)highBadVersion:n;
    }

    // Here's a dummy method to replicate one of the test-scenarios from leetcode.com
    protected static boolean isBadVersion(int version){
        int firstBadVersion = 1702766719;
        return version>=firstBadVersion;
    }

    public static void main(String[] args){
        int n = 2126753390; //total versions
        System.out.println("Total Versions: "+n+", first bad version: "+firstBadVersion(n));
    }
}
