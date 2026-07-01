import java.util.*;

class Solution {
    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();
        
        // If start or end has a thief, safeness factor is 0
        if (grid.get(0).get(0) == 1 || grid.get(n - 1).get(n - 1) == 1) {
            return 0;
        }

        int[][] distToThief = new int[n][n];
        for (int[] row : distToThief) Arrays.fill(row, -1);
        Queue<int[]> queue = new LinkedList<>();

        // Step 1: Multi-source BFS to calculate distance to nearest thief for every cell
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (grid.get(r).get(c) == 1) {
                    queue.offer(new int[]{r, c});
                    distToThief[r][c] = 0;
                }
            }
        }

        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            for (int[] d : dirs) {
                int nr = curr[0] + d[0], nc = curr[1] + d[1];
                if (nr >= 0 && nr < n && nc >= 0 && nc < n && distToThief[nr][nc] == -1) {
                    distToThief[nr][nc] = distToThief[curr[0]][curr[1]] + 1;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }

        // Step 2: Binary Search for the maximum safeness factor
        int low = 0, high = 2 * n, ans = 0;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (isValidPath(distToThief, mid, n, dirs)) {
                ans = mid;       // 'mid' is possible, try to find a larger safe value
                low = mid + 1;
            } else {
                high = mid - 1;  // 'mid' is too risky, look for smaller values
            }
        }

        return ans;
    }

    // Helper method: Regular BFS to check if a path exists with minimum safeness 'v'
    private boolean isValidPath(int[][] dist, int v, int n, int[][] dirs) {
        if (dist[0][0] < v) return false;

        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];
        
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            if (curr[0] == n - 1 && curr[1] == n - 1) return true;

            for (int[] d : dirs) {
                int nr = curr[0] + d[0], nc = curr[1] + d[1];
                if (nr >= 0 && nr < n && nc >= 0 && nc < n && !visited[nr][nc] && dist[nr][nc] >= v) {
                    visited[nr][nc] = true;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }
        return false;
    }
}