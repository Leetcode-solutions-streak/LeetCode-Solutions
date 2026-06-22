class Solution {
    public int climbStairs(int n, int[] costs) {
        int[] memo = new int[n + 1];
        java.util.Arrays.fill(memo, -1);
        
        return helper(n, costs, memo);
    }
    
    private int helper(int j, int[] costs, int[] memo) {
        if (j == 0) return 0;
        
        if (memo[j] != -1) return memo[j];
        
        int minCost = Integer.MAX_VALUE;
        
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