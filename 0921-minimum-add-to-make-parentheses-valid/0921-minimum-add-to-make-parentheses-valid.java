class Solution {
    public int minAddToMakeValid(String s) {
        int openNeeded = 0;  // unmatched '(' so far
        int insertions = 0;  // ')' needed to insert immediately

        for (char c : s.toCharArray()) {
            if (c == '(') {
                openNeeded++;
            } else {
                if (openNeeded > 0) {
                    openNeeded--; // matches an existing unmatched '('
                } else {
                    insertions++; // no '(' to match this ')', must insert one
                }
            }
        }

        // any remaining unmatched '(' each need a matching ')' inserted
        return insertions + openNeeded;
    }
}