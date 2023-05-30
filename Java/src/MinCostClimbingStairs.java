import java.util.HashMap;

public class MinCostClimbingStairs {
    /*
    You are given an integer array cost where cost[i] is the cost of ith step on a staircase.
    Once you pay the cost, you can either climb one or two steps.

    You can either start from the step with index 0, or the step with index 1.

    Return the minimum cost to reach the top of the floor.


    Constraints:

    2 <= cost.length <= 1000
    0 <= cost[i] <= 999
     */

    /**
    Approach 1: Bottom-Up Dynamic Programming (Tabulation)
    minimumCost[i] = min(minimumCost[i - 1] + cost[i - 1], minimumCost[i - 2] + cost[i - 2])
    The base cases are given in the problem description -
    we are allowed to start at either step 0 or step 1, so minimumCost[0] and minimumCost[1] are both 0.

    Define an array minimumCost, where minimumCost[i] represents the minimum cost of reaching the ith step.
    The array should be one element longer than costs and start with all elements set to 0.
    The array should contain one additional element is because we will treat the top floor as the step to reach.

    Iterate over the array starting at the 2nd index. The problem statement says we are allowed to start at the 0th
    or 1st step, so we know the minimum cost to reach those steps is 0.

    For each step, apply the recurrence relation -
    minimumCost[i] = min(minimumCost[i - 1] + cost[i - 1], minimumCost[i - 2] + cost[i - 2]).

    At the end, return the final element of minimumCost - We are treating this step as the top floor.

     ############################## Complexity Analysis ##############################
     Given NNN as the length of cost,
     Time complexity: O(N).
     We iterate N-1 times. At each iteration we apply an equation that requires O(1) time.
     Space complexity: O(N).
     The array minimumCost is always 1 element longer than the array cost.
     #################################################################################
     */
    public static int minCostClimbingStairs(int[] cost) {
        // The array's length should be 1 longer than the length of cost
        // Because we treat the "top floor" as a step to reach
        int minimumCost[] = new int[cost.length + 1];

        // Start iteration from step 2, since the minimum cost of reaching
        // step 0 and step 1 is 0
        for (int i = 2; i < minimumCost.length; i++) {
            int takeOneStep = minimumCost[i - 1] + cost[i - 1];
            int takeTwoSteps = minimumCost[i - 2] + cost[i - 2];
            minimumCost[i] = Math.min(takeOneStep, takeTwoSteps);
        }

        // The final element in minimumCost refers to the top floor
        return minimumCost[minimumCost.length - 1];
    }


    /**
     * Approach 2: Top-Down Dynamic Programming (Recursion + Memoization)
     * Top-down dynamic programming starts at the top and works its way down to the base cases.
     * Typically, this is implemented through recursion, and then made efficient using memoization.
     * Memoization refers to storing the results of expensive function calls in order to avoid duplicate computations -
     * we'll soon see why this is important for this problem.
     *
     * Similar to the first approach, we will make use of the recurrence relation we found.
     * This time, we will implement minimumCost as a function instead of an array.
     * Again, minimumCost(i) will represent the minimum cost to reach the ith step.
     * The base cases for this function will be minimumCost(0) = minimumCost(1) = 0,
     * since we are allowed to start on either step 0 or step 1.
     *
     * We know minimumCost(i) = min(cost[i-1] + minimumCost(i-1), cost[i-2] + minimumCost(i-2)).
     * If we want to find minimumCost(5), then we call minimumCost(3) and minimumCost(4).
     * However, minimumCost(4) will then call minimumCost(3), and both minimumCost(3)
     * calls will call minimumCost(2), on top of another minimumCost(2) call from minimumCost(4).
     *
     * We must avoid exponential recurring calls. We do so by storing calculated values in a memo.
     *
     * Define a hash map memo, where memo[i] represents the minimum cost of reaching the ith step.
     * Define a function minimumCost, where minimumCost(i) will determine the minimum cost to reach the ith step.
     * In our function minimumCost, first check the base cases: return 0 when i == 0 or i == 1.
     * Next, check if the argument i has already been calculated and stored in our hash map memo.
     * If so, return memo[i]. Otherwise, use the recurrence relation to calculate memo[i], and then return memo[i].
     * Call and return minimumCost(cost.length).
     * We can make use of approach 1 where we treat the top floor as an extra "step".
     * Since cost is 0-indexed, cost.length will be an index 1 step above the last element of cost.
     *
     * ###################### Complexity Analysis #####################
     * Given NNN as the length of cost,
     * Time complexity: O(N)
     * minimumCost is called from 0 to N. Because of our memoization, each call will only take O(1) time.
     * Space complexity: O(N)
     * The extra space used by this algorithm is the recursion call stack.
     * For example, minimumCost(10000) will call minimumCost(9999), which calls minimumCost(9998) etc.
     * All the way down until the base cases at minimumCost(0) and minimumCost(1).
     * In addition, our hash map memo will be of size N at the end, since we populate it with every index from 0 to N.
     * ################################################################
     */
    private static HashMap<Integer, Integer> memo = new HashMap<Integer, Integer>();

    public static int minCostClimbingStairsMemoized(int[] cost) {
        return minimumCostMemoized(cost.length, cost);
    }

    private static int minimumCostMemoized(int i, int[] cost) {
        // Base case, we are allowed to start at either step 0 or step 1
        if (i <= 1)
            return 0;
        // Check if we have already calculated minimumCost(i)
        if (memo.containsKey(i))
            return memo.get(i);
        // If not, cache the result in our hash map and return it
        int downOne = cost[i - 1] + minimumCostMemoized(i - 1, cost);
        int downTwo = cost[i - 2] + minimumCostMemoized(i - 2, cost);
        memo.put(i, Math.min(downOne, downTwo));
        return memo.get(i);
    }
    /**
     * Approach 3: Bottom-Up, Constant Space
     * You may have noticed that our recurrence relation from the previous two approaches only cares
     * about 2 steps below the current step. For example, if we are calculating the minimum cost to reach step 12,
     * we only care about data from step 10 and step 11. While we would have needed to calculate the minimum cost
     * for steps 2-9 as well, at the time of the actual calculation for step 12,
     * we no longer care about any of those steps.
     *
     * Therefore, instead of using O(n) space to keep an array, we can improve to O(1) space using only two variables.
     *
     * Initialize two variables, downOne and downTwo, that represent the minimum cost to reach one step and
     * two steps below the current step, respectively. We will start iteration from step 2, which means these
     * variables will initially represent the minimum cost to reach steps 0 and 1, so we will initialize each
     * of them to 0.
     *
     * Iterate over the array, again with 1 extra iteration at the end to treat the top floor as the final "step".
     * At each iteration, simulate moving 1 step up. This means downOne will now refer to the current step, so apply
     * our recurrence relation to update downOne. downTwo will be whatever downOne was prior to the update, so let's
     * use a temporary variable to help with the update.
     *
     * In the end, since we treated the top floor as a step, downOne will refer to the minimum cost to reach the top
     * floor. Return downOne.
     */
    public static int minCostClimbingStairsConstantSpace(int[] cost) {
        int downOne = 0;
        int downTwo = 0;
        for (int i = 2; i < cost.length + 1; i++) {
            int temp = downOne;
            downOne = Math.min(downOne + cost[i - 1], downTwo + cost[i - 2]);
            downTwo = temp;
        }

        return downOne;
    }


    /*
    ###################### FIRST ATTEMPT FAILED #######################
    #### Example 3 ####
    cost:[1,0,2,2]
    spent:3

    ANSWER SHOULD BE 2.
    ###################################################################

    My lookahead had chosen index 0, because cost at index 0 + cost at index 1 equals 1,
    which was less than choosing index 1 + cost at index 2 (or 3) equals 2. However, the lookahead algorithm
    didn't take into account that by using index 1 as start, that 0 + 2 is the total cost of climbing all
    stairs, and using index 0 as start would cost 1+0+2 to climb all stairs.

    public int minCostClimbingStairs(int[] cost) {
        return climb(lookAhead(0,cost), cost, 0);
    }

    private int climb(int index, int[] cost, int spentSoFar){
        int spent = spentSoFar;
        if(index<cost.length){
            spent+=cost[index];
        }
        if(index+1==cost.length || index+2==cost.length)
            return spent;

        return climb(lookAhead(index+1,cost), cost, spent);
    }
    private int lookAhead(int index, int[] cost){

        int currentTake1Step = getCost(index,cost)+getCost(index+1,cost);
        int currentTake2Steps = getCost(index,cost)+getCost(index+2,cost);
        int potential1 =
                currentTake1Step < currentTake2Steps ? currentTake1Step : currentTake2Steps;

        int nextTake1Step = getCost(index+1,cost)+getCost(index+2,cost);
        int nextTake2Steps = getCost(index+1,cost)+getCost(index+3,cost);
        int potential2 =
                nextTake1Step < nextTake2Steps ? nextTake1Step : nextTake2Steps;


        if(potential1<potential2)
            return index;
        else
            return index+1;
    }
    private int getCost(int index, int[] cost){
        if(index>=cost.length)
            return 0;
        return cost[index];
    }
    */



    /**
    Example 1:

    Input: cost = [10,15,20]
    Output: 15
    Explanation: You will start at index 1.
    - Pay 15 and climb two steps to reach the top.
    The total cost is 15.
     */
    private static void example1(){
        int[] cost = {10,15,20};
        System.out.println("EXAMPLE1 input: cost = [10,15,20]");
        System.out.println("The minimum cost of climbing is: "+minCostClimbingStairs(cost)+"\n");
    }

    /**
    Example 2:

    Input: cost = [1,100,1,1,1,100,1,1,100,1]
    Output: 6
    Explanation: You will start at index 0.
    - Pay 1 and climb two steps to reach index 2.
    - Pay 1 and climb two steps to reach index 4.
    - Pay 1 and climb two steps to reach index 6.
    - Pay 1 and climb one step to reach index 7.
    - Pay 1 and climb two steps to reach index 9.
    - Pay 1 and climb one step to reach the top.
    The total cost is 6.*/

    private static void example2(){
        int[] cost = {1,100,1,1,1,100,1,1,100,1};
        System.out.println("EXAMPLE2 input: cost = [1,100,1,1,1,100,1,1,100,1]");
        System.out.println("The minimum cost of climbing is: "+minCostClimbingStairsMemoized(cost)+"\n");
    }

    /**
    Example 3:
    Input: cost = [1,0,2,2]
    Output 2:
    Eplanation: You will start at index 1.
    - Pay 0 and climb two steps to reach index 2.
    - Pay 2 and climb 1 to reach the top.
    - The tocal cost is 2.*/
    private static void example3(){
        int[] cost = {1,0,2,2};
        System.out.println("EXAMPLE3 input: cost = [1,0,2,2]");
        System.out.println("The minimum cost of climbing is: "+minCostClimbingStairsConstantSpace(cost)+"\n");
    }

    public static void main(String[] args){
        System.out.println("    You are given an integer array cost where cost[i] is the cost of ith step on a staircase.\n" +
                "    Once you pay the cost, you can either climb one or two steps.\n" +
                "    You can either start from the step with index 0, or the step with index 1.\n" +
                "    Return the minimum cost to reach the top of the floor.\n");
        example1();
        example2();
        example3();
    }

}
