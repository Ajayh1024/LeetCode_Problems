class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[] sq = new long[n];

        for (int i = 0; i < n; i++) {
            sq[i] = (long) nums[i] * nums[i];
        }

        Arrays.sort(sq); // ascending

        int minusCount = n / 2; // positions that get subtracted
        long score = 0;

        for (int i = 0; i < n; i++) {
            if (i < minusCount) {
                score -= sq[i];  // smallest squares are subtracted
            } else {
                score += sq[i];  // largest squares are added
            }
        }

        return score;
    }
}