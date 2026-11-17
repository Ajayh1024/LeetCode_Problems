class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        final int INF = 1_000_000;          // large, but safe from int overflow when added

        // cost[i][j] = min changes to make s[i..j] a semi-palindrome
        int[][] cost = new int[n][n];
        for (int[] row : cost) java.util.Arrays.fill(row, INF);

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int L = j - i + 1;
                int best = INF;
                for (int d = 1; d < L; d++) {
                    if (L % d != 0) continue;
                    int m = L / d;                  // size of each group
                    int changes = 0;
                    for (int r = 0; r < d; r++) {   // each residue class
                        int lo = i + r;             // first index of the group
                        int hi = lo + (m - 1) * d;  // last index of the group
                        while (lo < hi) {
                            if (s.charAt(lo) != s.charAt(hi)) changes++;
                            lo += d;
                            hi -= d;
                        }
                    }
                    best = Math.min(best, changes);
                }
                cost[i][j] = best;
            }
        }

        // dp[p][j]: first j chars split into p parts
        int[][] dp = new int[k + 1][n + 1];
        for (int[] row : dp) java.util.Arrays.fill(row, INF);
        dp[0][0] = 0;

        for (int p = 1; p <= k; p++) {
            for (int j = 2 * p; j <= n; j++) {              // each part has length >= 2
                int best = INF;
                for (int i = 2 * (p - 1); i <= j - 2; i++) { // last part = s[i..j-1], length >= 2
                    best = Math.min(best, dp[p - 1][i] + cost[i][j - 1]);
                }
                dp[p][j] = best;
            }
        }
        return dp[k][n];
    }
}