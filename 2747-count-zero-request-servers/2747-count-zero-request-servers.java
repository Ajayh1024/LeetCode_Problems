class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));

        int m = queries.length;
        Integer[] order = new Integer[m];
        for (int i = 0; i < m; i++) order[i] = i;
        Arrays.sort(order, (a, b) -> Integer.compare(queries[a], queries[b]));

        int[] cnt = new int[n + 1];       // requests per server inside the window
        int distinct = 0;                 // servers with at least one request in the window
        int[] ans = new int[m];

        int l = 0, r = 0;                 // window is logs[l .. r-1]
        for (int k = 0; k < m; k++) {
            int qi = order[k];
            int q = queries[qi];

            // add logs with time <= q
            for (; r < logs.length && logs[r][1] <= q; r++) {
                if (cnt[logs[r][0]]++ == 0) distinct++;
            }
            // remove logs with time < q - x
            for (; l < r && logs[l][1] < q - x; l++) {
                if (--cnt[logs[l][0]] == 0) distinct--;
            }
            ans[qi] = n - distinct;
        }
        return ans;
    }
}