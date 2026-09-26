class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        Map<String, String> kv = new HashMap<>();
        for (List<String> pair : knowledge) {
            kv.put(pair.get(0), pair.get(1));
        }

        StringBuilder result = new StringBuilder();
        int i = 0, n = s.length();

        while (i < n) {
            char c = s.charAt(i);
            if (c == '(') {
                int j = s.indexOf(')', i);
                String key = s.substring(i + 1, j);
                result.append(kv.getOrDefault(key, "?"));
                i = j + 1;
            } else {
                result.append(c);
                i++;
            }
        }

        return result.toString();
    }
}