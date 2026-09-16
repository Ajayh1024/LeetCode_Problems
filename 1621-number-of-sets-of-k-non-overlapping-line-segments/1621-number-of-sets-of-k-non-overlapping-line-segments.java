class Solution {
    public int numberOfSets(int n, int k) {
        final int MOD = 1_000_000_007;

        // dp0[j] = ways with j segments, current point unused
        // dp1[j] = ways with j segments, current point is the right endpoint of segment j
        long[] dp0 = new long[k + 1];
        long[] dp1 = new long[k + 1];
        dp0[0] = 1; // base case at point 0

        for (int i = 1; i < n; i++) {
            long[] newDp0 = new long[k + 1];
            long[] newDp1 = new long[k + 1];

            for (int j = 0; j <= k; j++) {
                newDp0[j] = (dp0[j] + dp1[j]) % MOD;

                newDp1[j] = dp1[j];
                if (j > 0) {
                    newDp1[j] = (newDp1[j] + dp0[j - 1] + dp1[j - 1]) % MOD;
                }
            }

            dp0 = newDp0;
            dp1 = newDp1;
        }

        return (int) ((dp0[k] + dp1[k]) % MOD);
    }
}