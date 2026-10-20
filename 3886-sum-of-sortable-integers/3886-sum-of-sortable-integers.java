class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;

        // pre[i] = number of descents at positions < i  (descent at i: nums[i] > nums[i+1])
        int[] pre = new int[n];
        // nxt[i] = first descent position >= i, or n if none
        int[] nxt = new int[n];
        nxt[n - 1] = n;
        for (int i = 0; i + 1 < n; i++) {
            pre[i + 1] = pre[i] + (nums[i] > nums[i + 1] ? 1 : 0);
        }
        for (int i = n - 2; i >= 0; i--) {
            nxt[i] = nums[i] > nums[i + 1] ? i : nxt[i + 1];
        }

        int sum = 0;
        for (int k = 1; k <= n; k++) {
            if (n % k != 0) continue;

            boolean ok = true;
            int prevMax = Integer.MIN_VALUE;

            for (int l = 0; l < n && ok; l += k) {
                int r = l + k;                       // block is [l, r)
                int cnt = pre[r - 1] - pre[l];       // descents inside the block
                boolean wrap = nums[r - 1] > nums[l];

                int mn, mx;
                if (cnt >= 2) {
                    ok = false;
                    break;
                } else if (cnt == 1) {
                    if (wrap) { ok = false; break; }
                    int p = nxt[l];
                    mn = nums[p + 1];
                    mx = nums[p];
                } else {
                    mn = nums[l];
                    mx = nums[r - 1];
                }

                if (mn < prevMax) { ok = false; break; }
                prevMax = mx;
            }

            if (ok) sum += k;
        }
        return sum;
    }
}