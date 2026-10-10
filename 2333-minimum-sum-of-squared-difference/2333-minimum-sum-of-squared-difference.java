class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        int maxDiff = 0;
        int[] diff = new int[n];

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            maxDiff = Math.max(maxDiff, diff[i]);
        }

        // cnt[d] = how many positions currently have an absolute difference of d
        long[] cnt = new long[maxDiff + 1];
        for (int d : diff) cnt[d]++;

        long k = (long) k1 + k2; // only the total number of moves matters

        // Always shrink the largest differences first
        for (int d = maxDiff; d > 0 && k > 0; d--) {
            if (cnt[d] == 0) continue;

            if (k >= cnt[d]) {
                // lower every difference of size d down to d - 1
                k -= cnt[d];
                cnt[d - 1] += cnt[d];
                cnt[d] = 0;
            } else {
                // only enough moves for k of them
                cnt[d - 1] += k;
                cnt[d] -= k;
                k = 0;
            }
        }

        long result = 0;
        for (int d = 1; d <= maxDiff; d++) {
            result += cnt[d] * (long) d * d;
        }
        return result;
    }
}