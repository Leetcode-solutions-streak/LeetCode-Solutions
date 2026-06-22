class Solution {
    public int climbStairs(int n, int[] costs) {
        // memo array to store results of subproblems
        int[] memo = new int[n + 1];
        java.util.Arrays.fill(memo, -1);
        
        return helper(n, costs, memo);
    }
    
    private int helper(int j, int[] costs, int[] memo) {
        // Base case: reaching the ground step costs nothing
        if (j == 0) return 0;
        
        // If already calculated, return the cached result
        if (memo[j] != -1) return memo[j];
        
        int minCost = Integer.MAX_VALUE;
        
        // Try all 3 possible incoming jumps (from j-1, j-2, j-3)
        for (int jump = 1; jump <= 3; jump++) {
            int i = j - jump;
            if (i >= 0) {
                int prevCost = helper(i, costs, memo);
                if (prevCost != Integer.MAX_VALUE) {
                    int currentCost = prevCost + costs[j - 1] + (jump * jump);
                    minCost = Math.min(minCost, currentCost);
                }
            }
        }
        
        memo[j] = minCost;
        return memo[j];
    }
}