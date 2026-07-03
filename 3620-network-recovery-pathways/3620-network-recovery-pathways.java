import java.util.*;

class Solution {
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n = online.length;
        List<int[]>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        int[] inDegree = new int[n];
        
        for (int[] e : edges) {
            adj[e[0]].add(new int[]{e[1], e[2]});
            inDegree[e[1]]++;
        }
        
        // Find Topological Order
        int[] topo = new int[n];
        int idx = 0;
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) if (inDegree[i] == 0) q.offer(i);
        while (!q.isEmpty()) {
            int u = q.poll();
            topo[idx++] = u;
            for (int[] e : adj[u]) if (--inDegree[e[0]] == 0) q.offer(e[0]);
        }
        
        // Binary Search
        int low = 0, high = 1_000_000_000, ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (isPossible(mid, n, topo, adj, online, k)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }
    
    private boolean isPossible(int threshold, int n, int[] topo, List<int[]>[] adj, boolean[] online, long k) {
        long[] dp = new long[n];
        Arrays.fill(dp, Long.MAX_VALUE);
        dp[0] = 0;
        
        for (int u : topo) {
            if (dp[u] == Long.MAX_VALUE || !online[u]) continue;
            for (int[] e : adj[u]) {
                int v = e[0], cost = e[1];
                if (cost >= threshold && online[v]) {
                    dp[v] = Math.min(dp[v], dp[u] + cost);
                }
            }
            
        }
        return dp[n - 1] <= k;
    }
}