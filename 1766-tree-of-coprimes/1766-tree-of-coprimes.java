import java.util.*;

class Solution {
    List<Integer>[] graph;
    int[] nums;
    int[] ans;
    
    int[] ancestorNode = new int[51];
    int[] ancestorDepth = new int[51];

    public int[] getCoprimes(int[] nums, int[][] edges) {
        int n = nums.length;
        this.nums = nums;
        ans = new int[n];
        graph = new ArrayList[n];
        
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }
        
        Arrays.fill(ancestorNode, -1);
        Arrays.fill(ancestorDepth, -1);
        
        dfs(0, -1, 0);
        return ans;
    }

    private void dfs(int node, int parent, int depth) {
        int currVal = nums[node];
        int closestAncestor = -1;
        int maxDepth = -1;
        
        for (int val = 1; val <= 50; val++) {
            if (ancestorNode[val] != -1 && gcd(currVal, val) == 1) {
                if (ancestorDepth[val] > maxDepth) {
                    maxDepth = ancestorDepth[val];
                    closestAncestor = ancestorNode[val];
                }
            }
        }
        ans[node] = closestAncestor;
        
        int prevNode = ancestorNode[currVal];
        int prevDepth = ancestorDepth[currVal];
        
        ancestorNode[currVal] = node;
        ancestorDepth[currVal] = depth;
        
        for (int child : graph[node]) {
            if (child != parent) dfs(child, node, depth + 1);
        }
        
        ancestorNode[currVal] = prevNode;
        ancestorDepth[currVal] = prevDepth;
    }
    
    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}