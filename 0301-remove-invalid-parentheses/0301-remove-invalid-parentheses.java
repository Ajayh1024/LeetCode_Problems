class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        visited.add(s);
        queue.add(s);
        boolean found = false;

        while (!queue.isEmpty()) {
            String current = queue.poll();

            if (isValid(current)) {
                result.add(current);
                found = true; // once we find valid strings at this level, don't go deeper
            }

            if (found) continue; // don't generate further removals once we've found valid results

            for (int i = 0; i < current.length(); i++) {
                char c = current.charAt(i);
                if (c != '(' && c != ')') continue; // only remove parentheses

                String next = current.substring(0, i) + current.substring(i + 1);
                if (!visited.contains(next)) {
                    visited.add(next);
                    queue.add(next);
                }
            }
        }

        return result;
    }

    private boolean isValid(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') count++;
            else if (c == ')') {
                count--;
                if (count < 0) return false;
            }
        }
        return count == 0;
    }
}