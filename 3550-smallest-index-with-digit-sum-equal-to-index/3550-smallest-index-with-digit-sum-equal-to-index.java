class Solution {
    public int smallestIndex(int[] nums) {
        for(int i = 0; i<nums.length; i++){
            int n=nums[i];
            int ds=0;
            while(n>0){
                ds += n%10;
                n /=10;
            }
            if(ds == i){
                return i;
            }
        }
        return -1;
    }
}