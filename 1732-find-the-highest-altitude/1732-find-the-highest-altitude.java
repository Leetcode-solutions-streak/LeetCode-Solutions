class Solution {
    public int largestAltitude(int[] gain) {
        int sum=0;
        int highPoint=Integer.MIN_VALUE;
        for(int num : gain){
            sum+=num;
            highPoint=Math.max(highPoint,sum);
        }
        if(sum==0) return 0;
        return highPoint < 0 ? 0 : highPoint;
        
    }
}