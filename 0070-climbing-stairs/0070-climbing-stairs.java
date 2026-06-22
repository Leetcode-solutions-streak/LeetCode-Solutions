class Solution {
    public int climbStairs(int n) {
        // Base cases
        if (n <= 2) {
            return n;
        }
        
        // Initialize the DP array inside the method
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        
        // Fill the array dynamically
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        
        return dp[n];
    }
}