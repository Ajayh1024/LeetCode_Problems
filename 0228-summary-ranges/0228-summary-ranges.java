class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        int n = nums.length;
        
        for (int i = 0; i < n; i++) {
            int start = i;
            
            // Extend as long as consecutive
            while (i + 1 < n && nums[i + 1] == nums[i] + 1) {
                i++;
            }
            
            // Build the range string
            if (start == i) {
                result.add(String.valueOf(nums[start]));
            } else {
                result.add(nums[start] + "->" + nums[i]);
            }
        }
        
        return result;
    }
}