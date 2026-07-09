class Solution {
    private int count;
    public void calculate(int nums[] ,int index,int target,int sum){
        if(index == nums.length){
            if (sum == target){
                count++;
            }
            return;
        }
        calculate(nums,index+1,target,sum+nums[index]);
        calculate(nums,index+1,target,sum-nums[index]);

    }
    public int findTargetSumWays(int[] nums, int target) {
        count =0;
        calculate(nums, 0 , target ,0);
        return count;
        
    }
}