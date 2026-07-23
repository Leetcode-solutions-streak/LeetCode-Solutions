class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;
        
        // Base cases for small n
        if (n < 3) {
            return n;
        }
        
        // Find the smallest power of 2 greater than n
        int highestBit = 31 - Integer.numberOfLeadingZeros(n);
        return 1 << (highestBit + 1);
    }
}