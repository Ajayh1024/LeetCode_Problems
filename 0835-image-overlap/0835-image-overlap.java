import java.util.*;

class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        List<int[]> ones1 = new ArrayList<>();
        List<int[]> ones2 = new ArrayList<>();

        // Collect coordinates of all 1s in both images
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (img1[i][j] == 1) ones1.add(new int[]{i, j});
                if (img2[i][j] == 1) ones2.add(new int[]{i, j});
            }
        }

        Map<String, Integer> shiftCount = new HashMap<>();
        int maxOverlap = 0;

        // For every pair of 1s (one from each image), compute the shift vector
        for (int[] p1 : ones1) {
            for (int[] p2 : ones2) {
                int dx = p2[0] - p1[0];
                int dy = p2[1] - p1[1];
                String key = dx + "," + dy;

                int count = shiftCount.getOrDefault(key, 0) + 1;
                shiftCount.put(key, count);
                maxOverlap = Math.max(maxOverlap, count);
            }
        }

        // Edge case: if either image has no 1s at all, no overlap is possible
        return maxOverlap;
    }
}