import java.util.List;

class Solution {
    public int[] pathsWithMaxScore(List<String> board) {
        int MOD = 1_000_000_007;
        int n = board.size();
        int[][] maxScore = new int[n][n];
        for (int i = 0; i < n; i++) {
            java.util.Arrays.fill(maxScore[i], -1);
        }
        
        int[][] pathCount = new int[n][n];
        
        maxScore[0][0] = 0;
        pathCount[0][0] = 1;
        
        for (int i = 0; i < n; i++) {
            String rowStr = board.get(i);
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                
                char cell = rowStr.charAt(j);
                if (cell == 'X') {
                    continue;
                }
                
                int currentVal = (cell == 'S') ? 0 : (cell - '0');
                
                int maxPrevScore = -1;
                long ways = 0;
                
                int[][] prevCells = { {i - 1, j}, {i, j - 1}, {i - 1, j - 1} };
                
                for (int[] prev : prevCells) {
                    int pi = prev[0];
                    int pj = prev[1];
                    
                    if (pi >= 0 && pi < n && pj >= 0 && pj < n && maxScore[pi][pj] != -1) {
                        if (maxScore[pi][pj] > maxPrevScore) {
                            maxPrevScore = maxScore[pi][pj];
                            ways = pathCount[pi][pj];
                        } else if (maxScore[pi][pj] == maxPrevScore) {
                            ways = (ways + pathCount[pi][pj]) % MOD;
                        }
                    }
                }
                
                if (maxPrevScore != -1) {
                    maxScore[i][j] = maxPrevScore + currentVal;
                    pathCount[i][j] = (int) ways;
                }
            }
        }
        
        int finalScore = maxScore[n - 1][n - 1];
        int finalWays = pathCount[n - 1][n - 1];
        
        if (finalScore == -1) {
            return new int[]{0, 0};
        } else {
            return new int[]{finalScore, finalWays};
        }
    }
}