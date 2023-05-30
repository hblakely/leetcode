public class MaxProfit {
    /*
    https://leetcode.com/study-plan/leetcode-75/?progress=xdlhlco7 Level 1
    You are given an array prices where prices[i] is the price of a given stock on the ith day.

    You want to maximize your profit by choosing a single day to buy one stock and choosing a different
    day in the future to sell that stock.

    Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
     */

    public int maxProfit(int[] prices) { // Iterate through prices once - O(n) complexity
        int profit = 0;
        if(prices.length <2) // invalid input
            return profit;

        int lowest = prices[0]; // keep track of lowest price and compare difference of current price with lowest
        for(int i = 1; i<prices.length; i++){
            int diff = prices[i]-lowest;
            if(diff>profit) profit = diff; // update profit if larger profit is found

            if(prices[i]<lowest)
                lowest = prices[i];
        }
        return profit;
    }


    public int maxProfitFirstAttempt(int[] prices) {
        int profit =0;
        if(prices.length <2) // invalid input
            return profit;
        for(int i = 0; i<prices.length; i++){ // N^2 suckkkkksssssss..... WAY slow.
            for(int j=i+1; j<prices.length; j++){
                int val = prices[j]-prices[i];
                profit = val>profit?val:profit;
            }
        }

        return profit;
    }
}
