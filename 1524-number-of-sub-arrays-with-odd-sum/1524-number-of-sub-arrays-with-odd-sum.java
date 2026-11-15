class Solution {
    public int numOfSubarrays(int[] arr) {
        final int MOD = 1_000_000_007;
        long ans = 0;
        int even = 1, odd = 0; // empty prefix counts as even
        int parity = 0;

        for (int x : arr) {
            parity = (parity + (x & 1)) & 1;
            if (parity == 0) {
                ans += odd;
                even++;
            } else {
                ans += even;
                odd++;
            }
            ans %= MOD;
        }
        return (int) ans;
    }
}