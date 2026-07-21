class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int n = s.length();
        int totalOnes = 0;
        int maxZeroGain = 0;
        int prevZeroCount = 0;
        int zeroBlockCount = 0;
        
        int i = 0;
        while (i < n) {
            int j = i;
            while (j < n && s.charAt(j) == s.charAt(i)) {
                j++;
            }
            
            int blockLen = j - i;
            
            if (s.charAt(i) == '1') {
                totalOnes += blockLen;
            } else {
                zeroBlockCount++;
                if (zeroBlockCount >= 2) {
                    maxZeroGain = Math.max(maxZeroGain, prevZeroCount + blockLen);
                }
                prevZeroCount = blockLen;
            }
            
            i = j;
        }
        
        if (zeroBlockCount < 2) {
            return totalOnes;
        }
        
        return totalOnes + maxZeroGain;
    }
}