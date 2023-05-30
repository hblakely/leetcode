import java.util.Arrays;

public class UniquePaths {
    /*
    There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]).
    The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]).
    The robot can only move either down or right at any point in time.
    Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the
    bottom-right corner.
    The test cases are generated so that the answer will be less than or equal to 2 * 10^9.

    Constraints:
    1 <= m, n <= 100
     */


    /**
    // Starts at the end, goes backwards until reaching one of the two first steps away from origin.
    // populates d[1][0], and d[0][1] with 1, and all following calculations cascade building off those
    // original numbers. This works but is too slow to pass leetcode's time complexity tests.
     **/
    public static int uniquePathsSlowRecursion(int m, int n) {
        if (m == 1 || n == 1) {
            return 1;
        }
        return uniquePaths(m - 1, n) + uniquePaths(m, n - 1);
    }

    /**
    // Another one of those Dynamic Programming Problems. Use the array to keep track of the
    // previous calculations and prevent exponential time complexity.
    // Because the robot can only move right and down, the paths are limited.
    // d[row][col] = d[row][col-1] + d[row-1][col]
    // return d[m-1][n-1]
     **/
    // Iterative Dynamic approach. Basically memoized - using the array as the memo itself.
    public static int uniquePaths(int m, int n) {
        int[][] d = new int[m][n];
        for(int[] arr : d) // Pre-initialize array with 1's. First row and first column only have one possibility.
            Arrays.fill(arr,1);
        // For each edge that the robot could approach from, add previous possibilities and store in current index.
        for(int row = 1; row <d.length; row++) {
            for(int col = 1; col<d[0].length; col++)
                d[row][col] = d[row][col - 1] + d[row - 1][col];
        }
        // return the bottom right value,
        // it will have a value of accumulated calculations equaling number of unique paths to that point.
        return d[m-1][n-1];
    }

    /**
     Approach 2: Math (Python3 only)
     Could one do better than O(N×M)? The answer is yes.

     The problem is a classical combinatorial problem: there are h+vh + vh+v moves to do from start to finish,
     h=m−1 horizontal moves, and v=n−1 vertical ones. One could choose when to move
     to the right, i.e. to define hhh horizontal moves, and that will fix vertical ones. Or, one could choose
     when to move down, i.e. to define vvv vertical moves, and that will fix horizontal ones.
     In other words, we're asked to compute in how many ways one could choose p elements from p+k elements.
     In mathematics, that's called binomial coefficients

     The number of horizontal moves to do is h=m−1, the number of vertical moves is v=n−1.
     That results in a simple formula

     C h/h+v  = (m+n-2)!/(m-1!)(n-1)

     The job is done. Now time complexity will depend on the algorithm to compute factorial function
     (m+n−2)!(m + n - 2)!(m+n−2)!. In short, standard computation for k! using the definition requires
     O( k^2 logk ) time, and that will be not as good as DP algorithm.

     // best known algorithm to compute factorial function by  Peter Borwein
     http://www.cecm.sfu.ca/personal/pborwein/PAPERS/P29.pdf
     The idea is to express the factorial as a product of prime powers, so that k! can be computed in
     O(k( log k log log k) time. That's better than O(k2) and hence beats DP algorithm.

     The authors prefer not to discuss here various factorial function implementations,
     and hence provide Python3 solution only, with built-in divide and conquer factorial algorithm.
     http://www.luschny.de/math/factorial/description.html


     ######### PYTHON 3 ##########
     from math import factorial
     class Solution:
     def uniquePaths(self, m: int, n: int) -> int:
     return factorial(m + n - 2) // factorial(n - 1) // factorial(m - 1)
     ############################

     Complexity Analysis
     Time complexity: O(((M+N)(log(M+N)loglog(M+N))^2)
     Space complexity: O(1)

     In Python 3.8, there's a new function that calculates n-choose-k directly:

     def uniquePaths(self, m: int, n: int) -> int:
     return math.comb(m + n - 2, n - 1)
     */




    public static void main(String[] args){
        example1();
        example2();
    }

    /* EXAMPLE 1
    Input: m = 3, n = 7
    Output: 28
     */
    private static void example1(){ // Correct: 28
        System.out.println("Unique Paths m3,n7: "+uniquePaths(3,7));
    }


    /* EXAMPLE 2
    Input: m = 3, n = 2
    Output: 3
    Explanation: From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
    1. Right -> Down -> Down
    2. Down -> Down -> Right
    3. Down -> Right -> Down
     */
    private static void example2(){ // Correct: 3
        System.out.println("Unique Paths m3, n2 "+uniquePathsSlowRecursion(3,2));
    }
}
