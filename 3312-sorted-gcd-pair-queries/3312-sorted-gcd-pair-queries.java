class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int maxVal = 0;
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
        }
        int[] cnt = new int[maxVal + 1];
        for (int num : nums) {
            cnt[num]++;
        }
        
        long[] div_cnt = new long[maxVal + 1];
        for (int g = 1; g <= maxVal; g++) {
            for (int multiple = g; multiple <= maxVal; multiple += g) {
                div_cnt[g] += cnt[multiple];
            }
        }
        long[] exact_pairs = new long[maxVal + 1];
        for (int g = maxVal; g >= 1; g--) {
            long total_pairs = (div_cnt[g] * (div_cnt[g] - 1)) / 2;
            for (int multiple = 2 * g; multiple <= maxVal; multiple += g) {
                total_pairs -= exact_pairs[multiple];
            }
            exact_pairs[g] = total_pairs;
        }
        long[] pref = new long[maxVal + 1];
        for (int g = 1; g <= maxVal; g++) {
            pref[g] = pref[g - 1] + exact_pairs[g];
        }
        
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            long q = queries[i];
            int low = 1, high = maxVal;
            int res = maxVal;
            while (low <= high) {
                int mid = (low + high) >>> 1;
                if (pref[mid] > q) {
                    res = mid;
                    high = mid - 1; 
                } else {
                    low = mid + 1;
                }
            }
            ans[i] = res;
        }
        
        return ans;
    }
}