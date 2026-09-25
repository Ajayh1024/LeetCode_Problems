class Solution {

    public List<String> braceExpansionII(String expression) {

        Set<String> result = solve(expression);

        List<String> ans = new ArrayList<>(result);
        Collections.sort(ans);

        return ans;
    }

    private Set<String> solve(String s) {

        Set<String> result = new HashSet<>();

        int balance = 0;
        int start = 0;

        for (int i = 0; i < s.length(); i++) {

            char ch = s.charAt(i);

            if (ch == '{') {
                balance++;
            } 
            else if (ch == '}') {
                balance--;
            }

            // Comma at current level
            if (ch == ',' && balance == 0) {

                result.addAll(solve(s.substring(start, i)));
                start = i + 1;
            }
        }

        // If there was a comma
        if (start > 0) {
            result.addAll(solve(s.substring(start)));
            return result;
        }

        // No comma: handle concatenation
        Set<String> current = new HashSet<>();
        current.add("");

        for (int i = 0; i < s.length();) {

            if (s.charAt(i) == '{') {

                int balance2 = 1;
                int j = i + 1;

                while (balance2 > 0) {

                    if (s.charAt(j) == '{') {
                        balance2++;
                    } 
                    else if (s.charAt(j) == '}') {
                        balance2--;
                    }

                    j++;
                }

                // Remove { }
                Set<String> inside =
                    solve(s.substring(i + 1, j - 1));

                current = combine(current, inside);

                i = j;

            } else {

                Set<String> letter = new HashSet<>();
                letter.add(String.valueOf(s.charAt(i)));

                current = combine(current, letter);

                i++;
            }
        }

        return current;
    }

    private Set<String> combine(Set<String> a, Set<String> b) {

        Set<String> result = new HashSet<>();

        for (String x : a) {
            for (String y : b) {
                result.add(x + y);
            }
        }

        return result;
    }
}