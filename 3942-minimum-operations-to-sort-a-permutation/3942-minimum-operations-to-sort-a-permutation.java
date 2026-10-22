class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int k = nums[0];
        int best = Integer.MAX_VALUE;

        // Case 1: rotation of ascending
        boolean asc = true;
        for (int i = 0; i < n; i++) {
            if (nums[i] != (i + k) % n) { asc = false; break; }
        }
        if (asc) {
            best = (k == 0) ? 0 : Math.min(n - k, k + 2);
        }

        // Case 2: rotation of descending
        boolean desc = true;
        for (int i = 0; i < n; i++) {
            if (nums[i] != ((k - i) % n + n) % n) { desc = false; break; }
        }
        if (desc) {
            int a = (k + 1) % n;
            int cost = Math.min(a + 1, 1 + (n - a) % n);
            best = Math.min(best, cost);
        }

        return best == Integer.MAX_VALUE ? -1 : best;
    }
}