import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        Integer[] sortedIndices = new Integer[n];
        for (int i = 0; i < n; i++) {
            sortedIndices[i] = i;
        }
        Arrays.sort(sortedIndices, Comparator.comparingInt(i -> nums[i]));

        int[] rank = new int[n];
        for (int i = 0; i < n; i++) {
            rank[sortedIndices[i]] = i;
        }

        int LOG = 18;
        int[][] up = new int[n][LOG];
        
        int right = 0;
        for (int left = 0; left < n; left++) {
            int u = sortedIndices[left];
            while (right < n && nums[sortedIndices[right]] - nums[u] <= maxDiff) {
                right++;
            }
            int parent = sortedIndices[right - 1];
            up[u][0] = parent;
        }

        for (int j = 1; j < LOG; j++) {
            for (int i = 0; i < n; i++) {
                up[i][j] = up[up[i][j - 1]][j - 1];
            }
        }

        int[] ans = new int[queries.length];

        for (int q = 0; q < queries.length; q++) {
            int u = queries[q][0];
            int v = queries[q][1];

            if (u == v) {
                ans[q] = 0;
                continue;
            }

            if (nums[u] > nums[v]) {
                int temp = u;
                u = v;
                v = temp;
            }

            int targetRank = rank[v];
            int distance = 0;
            int curr = u;

            for (int j = LOG - 1; j >= 0; j--) {
                int next = up[curr][j];
                if (rank[next] < targetRank) {
                    curr = next;
                    distance += (1 << j);
                }
            }

            if (rank[up[curr][0]] >= targetRank) {
                ans[q] = distance + 1;
            } else {
                ans[q] = -1;
            }
        }

        return ans;
    }
}