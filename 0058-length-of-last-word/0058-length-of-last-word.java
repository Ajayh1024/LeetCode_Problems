class Solution {
    public int lengthOfLastWord(String s) {
        int count = 0;

        // Start from the end
        int i = s.length() - 1;

        // Ignore spaces at the end
        while (i >= 0 && s.charAt(i) == ' ') {
            i--;
        }

        // Count characters of the last word
        while (i >= 0 && s.charAt(i) != ' ') {
            count++;
            i--;
        }

        return count;
    }
}