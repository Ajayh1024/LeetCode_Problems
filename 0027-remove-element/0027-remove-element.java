class Solution {
    public int removeElement(int[] nums, int val) {
        int k = 0; // index where the next "keep" element should go
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[k] = nums[i];
                k++;
            }
        }
        
        return k;
    }
}