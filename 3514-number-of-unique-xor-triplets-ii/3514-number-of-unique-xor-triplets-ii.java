class Solution {
    public int uniqueXorTriplets(int[] nums) {
        boolean[] hasNum = new boolean[2048];
        int uniqueCount = 0;
        for (int x : nums) {
            if (!hasNum[x]) {
                hasNum[x] = true;
                uniqueCount++;
            }
        }

        int[] uniqueNums = new int[uniqueCount];
        int idx = 0;
        for (int i = 0; i < 2048; i++) {
            if (hasNum[i]) {
                uniqueNums[idx++] = i;
            }
        }

        boolean[] pairXors = new boolean[2048];
        for (int i = 0; i < uniqueCount; i++) {
            for (int j = i; j < uniqueCount; j++) { 
                pairXors[uniqueNums[i] ^ uniqueNums[j]] = true;
            }
        }

        boolean[] tripletXors = new boolean[2048];
        for (int p = 0; p < 2048; p++) {
            if (pairXors[p]) {
                for (int x : uniqueNums) {
                    tripletXors[p ^ x] = true;
                }
            }
        }
        int ans = 0;
        for (boolean present : tripletXors) {
            if (present) {
                ans++;
            }
        }

        return ans;
    }
}