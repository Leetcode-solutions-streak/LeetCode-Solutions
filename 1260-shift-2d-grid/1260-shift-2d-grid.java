import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int total = m * n;
        
        int[][] shifted = new int[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int flatIndex = i * n + j;
                int newIndex = (flatIndex + k) % total;
                shifted[newIndex / n][newIndex % n] = grid[i][j];
            }
        }
        
        List<List<Integer>> result = new ArrayList<>();
        for (int[] row : shifted) {
            List<Integer> listRow = new ArrayList<>();
            for (int val : row) {
                listRow.add(val);
            }
            result.add(listRow);
        }
        
        return result;
    }
}