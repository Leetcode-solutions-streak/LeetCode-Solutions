/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list=new ArrayList<>();
        

        dfs(root,0,list);
        return list;
        
        
    }
    void dfs(TreeNode node,int depth,List<Integer> list){
        if(node ==null)
        return;

        if(depth==list.size())
        list.add(node.val);


        dfs(node.right,depth+1,list);
        dfs(node.left,depth+1,list);

    }
}