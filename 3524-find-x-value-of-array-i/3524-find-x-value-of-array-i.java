class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] result = new long[k];
        long[] cnt = new long[k];   // subarrays ending at previous index, by product % k

        for (int num : nums) {
            long[] next = new long[k];
            int a = num % k;

            // extend all previous subarrays with the current element
            for (int r = 0; r < k; r++) {
                if (cnt[r] > 0) {
                    next[(r * a) % k] += cnt[r];
                }
            }
            // start a new subarray with just the current element
            next[a]++;

            for (int r = 0; r < k; r++) {
                result[r] += next[r];
            }
            cnt = next;
        }
        return result;
    }
}