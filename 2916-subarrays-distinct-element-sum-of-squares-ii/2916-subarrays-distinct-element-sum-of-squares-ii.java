class Solution {
    private long[] sum, sq, lazy;
    private int n;

    public int sumCounts(int[] nums) {
        final long MOD = 1_000_000_007L;
        n = nums.length;
        sum = new long[4 * n];
        sq = new long[4 * n];
        lazy = new long[4 * n];

        Map<Integer, Integer> last = new HashMap<>();
        long ans = 0;

        for (int j = 0; j < n; j++) {
            int prev = last.getOrDefault(nums[j], -1);
            // nums[j] is a new distinct value for every start index in (prev, j]
            update(1, 0, n - 1, prev + 1, j, 1);
            ans = (ans + sq[1]) % MOD; // sum of d[i]^2 over all starts i <= j
            last.put(nums[j], j);
        }

        return (int) ans;
    }

    private void apply(int node, int len, long v) {
        sq[node] += 2 * v * sum[node] + v * v * len; // (x + v)^2 = x^2 + 2vx + v^2
        sum[node] += v * len;
        lazy[node] += v;
    }

    private void push(int node, int l, int r) {
        if (lazy[node] != 0) {
            int mid = (l + r) / 2;
            apply(2 * node, mid - l + 1, lazy[node]);
            apply(2 * node + 1, r - mid, lazy[node]);
            lazy[node] = 0;
        }
    }

    private void update(int node, int l, int r, int ql, int qr, long v) {
        if (qr < l || r < ql) return;
        if (ql <= l && r <= qr) {
            apply(node, r - l + 1, v);
            return;
        }
        push(node, l, r);
        int mid = (l + r) / 2;
        update(2 * node, l, mid, ql, qr, v);
        update(2 * node + 1, mid + 1, r, ql, qr, v);
        sum[node] = sum[2 * node] + sum[2 * node + 1];
        sq[node] = sq[2 * node] + sq[2 * node + 1];
    }
}