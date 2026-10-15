class Solution {
    public int sumCounts(List<Integer> nums) {
        int n = nums.size();
        int total = 0;

        for (int i = 0; i < n; i++) {
            Set<Integer> seen = new HashSet<>();
            for (int j = i; j < n; j++) {
                seen.add(nums.get(j));
                total += seen.size() * seen.size();
            }
        }

        return total;
    }
}