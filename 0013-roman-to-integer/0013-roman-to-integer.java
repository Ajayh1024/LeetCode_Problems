class Solution {
    public int romanToInt(String s) {
        Map<Character, Integer> values = new HashMap<>();
        values.put('I', 1);
        values.put('V', 5);
        values.put('X', 10);
        values.put('L', 50);
        values.put('C', 100);
        values.put('D', 500);
        values.put('M', 1000);

        int total = 0;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            int current = values.get(s.charAt(i));

            // Look ahead to the next symbol, if it exists
            if (i + 1 < n) {
                int next = values.get(s.charAt(i + 1));
                if (current < next) {
                    total -= current;   // subtractive case
                } else {
                    total += current;   // normal case
                }
            } else {
                total += current;   // last character, always add
            }
        }

        return total;
    }
}