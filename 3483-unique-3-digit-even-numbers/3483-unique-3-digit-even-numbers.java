import java.util.HashSet;
import java.util.Set;

class Solution {
    public int totalNumbers(int[] digits) {
        Set<Integer> result = new HashSet<>();
        int n = digits.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j == i) continue;
                for (int k = 0; k < n; k++) {
                    if (k == i || k == j) continue;

                    int hundreds = digits[i];
                    int tens = digits[j];
                    int ones = digits[k];

                    // No leading zero
                    if (hundreds == 0) continue;

                    // Must be even
                    if (ones % 2 != 0) continue;

                    int number = hundreds * 100 + tens * 10 + ones;
                    result.add(number);
                }
            }
        }

        return result.size();
    }
}
