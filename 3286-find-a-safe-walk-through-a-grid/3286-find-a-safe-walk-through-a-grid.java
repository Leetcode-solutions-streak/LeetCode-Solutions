import java.util.*;

class Solution {
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size(), n = grid.get(0).size();
        int[][] minhealth = new int[m][n];
        for (int[] row : minhealth) Arrays.fill(row, Integer.MAX_VALUE);

        Deque<int[]> deque = new ArrayDeque<>();
        minhealth[0][0] = grid.get(0).get(0);
        deque.offerFirst(new int[]{0, 0});

        int[] dirs = {-1, 0, 1, 0, -1};

        while (!deque.isEmpty()) {
            int[] curr = deque.pollFirst();
            int r = curr[0], c = curr[1];

            if (r == m - 1 && c == n - 1) {
                return health - minhealth[r][c] >= 1;
            }
            
            for (int i = 0; i < 4; i++) {
                int nr = r + dirs[i];
                int nc = c + dirs[i + 1];

                if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                    int nextcost = minhealth[r][c] + grid.get(nr).get(nc);
                 
                    if (nextcost < minhealth[nr][nc]) {
                        minhealth[nr][nc] = nextcost;
                      
                        if (grid.get(nr).get(nc) == 0) {
                            deque.offerFirst(new int[]{nr, nc});
                        } else {
                            deque.offerLast(new int[]{nr, nc});
                        }
                    }
                }
            }
        }
        
        return false;
    }
}