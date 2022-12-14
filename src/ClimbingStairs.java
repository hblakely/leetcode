public class ClimbingStairs {
    /*
    Summary
    You are climbing a staircase. It takes n steps to reach to the top.

    Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
     */

    /** ########################################################
     * FROM LEETCODE.COM's OFFICIAL SOLUTION SET
        RECURSIVE BRUTE FORCE APPROACH
        In this brute force approach we take all possible step combinations i.e. 1 and 2, at every step.
        At every step we are calling the function climbStairs for step 1 and 2,
        and return the sum of returned values of both functions.

     climbStairs(i,n) = (i+1,n)+climbStairs(i+2,n)

     where i defines the current step and n defines the destination step.
     Time complexity : O(2^n)
     Size of recursion tree : 2^n
    ############################################################ **/
    // This solution makes sense to me. Do every option at each step, which is two times the number of iterations.
    // Visualizing as a branching tree helps me see how this works. This definitely isn't the fastest solution.
    public static int climbStairsRecursion(int n) {
        return climb(0, n);
    }
    public static int climb(int i, int n) {
        if (i > n)
            return 0;
        if (i == n)
            return 1;
        return climb(i + 1, n) + climb(i + 2, n);
    }



    /** ######################################################
     * FROM LEETCODE.COM's OFFICIAL SOLUTION SET
     * Recursion with Memoization APPROACH
     *
     * In the previous approach we are redundantly calculating the result for every step.
     * Instead, we can store the result at each step in memo array and directly
     * returning the result from the memo array whenever that function is called again.
     *
     * In this way we are pruning recursion tree with the help of memo array and
     * reducing the size of recursion tree up to n.
     *
     * Time Complexity: O(n) Size of recursion tree can go up to n.
     * Space Complexity: O(n) The depth of recursion tree can go up to n.
        ###################################################### */

    public static int climbStairsMomoized(int n) {
        int memo[] = new int[n + 1];
        return climb_Memoized(0, n, memo);
    }
    public static int climb_Memoized(int i, int n, int memo[]) {
        if (i > n) {
            return 0;
        }
        if (i == n) {
            return 1;
        }
        if (memo[i] > 0) {
            return memo[i];
        }
        memo[i] = climb_Memoized(i + 1, n, memo) + climb_Memoized(i + 2, n, memo);
        return memo[i];
    }



    /** ########################################################
     * FROM LEETCODE.COM's OFFICIAL SOLUTION SET
     * Dynamic Programming APPROACH
     *
     * As we can see this problem can be broken into subproblems, and it contains the optimal
     * substructure property i.e. its optimal solution can be constructed efficiently from optimal
     * solutions of its subproblems, we can use dynamic programming to solve this problem.
     *
     * One can reach the ith step in one of the two ways:
     *
     * Taking a single step from (i-1)^{th} step.
     *
     * Taking a step of 2 from (i-2)^{th} step.
     *
     * So, the total number of ways to reach ith
     * is equal to sum of ways of reaching (i−1)th
     * step and ways of reaching (i−2)th step.
     *
     * Let dp[i] denotes the number of ways to reach on ith step:
     *
     * dp[i]=dp[i−1]+dp[i−2]
     *
     * Time complexity : O(n). Single loop up to n.
     * Space complexity : O(n). dp array of size n is used.
     #############################################################*/

    public static int climbStairsDynamic(int n) {
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * FROM LEETCODE.COM's OFFICIAL SOLUTION SET
     * Fibonacci Number
     *
     * In the above approach we have used dpdpdp array where dp[i]=dp[i−1]+dp[i−2].
     * It can be easily analysed that dp[i] is nothing but ith fibonacci number.
     *
     * Fib(n)=Fib(n−1)+Fib(n−2)
     */
    public static int climbStairsFibonacci(int n){
        if (n == 1)
            return 1;

        int current = 1, previous = 0;
        for (int i = 0; i<n; i++){
            current+=previous;
            previous=current-previous;
        }
        return current;
    }

    public static void main(String[] args){
        int numStairs = 33;

        System.out.println("brute force recursion:  "+ climbStairsRecursion(numStairs));
        System.out.println("climb stairs memoized:  "+ climbStairsMomoized(numStairs));
        System.out.println("climb stairs dynamic:   "+climbStairsDynamic(numStairs));
        System.out.println("climb stairs fibonacci: "+ climbStairsFibonacci(numStairs));
    }

}
