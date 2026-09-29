
class Solution {
    public boolean hasValidPath(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // Valid parentheses need an even length
        if ((m + n) % 2 == 0) {
            return false;
        }

        // First character must be '('
        if (grid[0][0] != '(') {
            return false;
        }

        // Last character must be ')'
        if (grid[m - 1][n - 1] != ')') {
            return false;
        }

        Boolean[][][] dp = new Boolean[m][n][m + n];

        return dfs(grid, 0, 0, 0, dp);
    }

    private boolean dfs(char[][] grid, int i, int j,
                        int balance, Boolean[][][] dp) {

        int m = grid.length;
        int n = grid[0].length;

        // Update balance based on current character
        if (grid[i][j] == '(') {
            balance++;
        } else {
            balance--;
        }

        // Invalid if closing bracket appears first
        if (balance < 0) {
            return false;
        }

        // Not enough remaining cells to close brackets
        int remaining = (m - 1 - i) + (n - 1 - j);
        if (balance > remaining) {
            return false;
        }

        // Reached bottom-right cell
        if (i == m - 1 && j == n - 1) {
            return balance == 0;
        }

        // Already calculated
        if (dp[i][j][balance] != null) {
            return dp[i][j][balance];
        }

        boolean ans = false;

        // Move down
        if (i + 1 < m) {
            ans = dfs(grid, i + 1, j, balance, dp);
        }

        // Move right
        if (!ans && j + 1 < n) {
            ans = dfs(grid, i, j + 1, balance, dp);
        }

        dp[i][j][balance] = ans;
        return ans;
    }
}