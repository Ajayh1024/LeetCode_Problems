import java.util.*;

class Solution {
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        // arr[i] = {l, r, weight, originalIndex}
        int[][] arr = new int[n][4];
        for (int i = 0; i < n; i++) {
            arr[i][0] = intervals.get(i).get(0);
            arr[i][1] = intervals.get(i).get(1);
            arr[i][2] = intervals.get(i).get(2);
            arr[i][3] = i;
        }
        Arrays.sort(arr, (a, b) -> a[0] - b[0]);

        // next[i] = first index j such that arr[j][0] > arr[i][1] (binary search)
        int[] next = new int[n];
        for (int i = 0; i < n; i++) {
            int lo = i + 1, hi = n;
            while (lo < hi) {
                int mid = (lo + hi) / 2;
                if (arr[mid][0] > arr[i][1]) {
                    hi = mid;
                } else {
                    lo = mid + 1;
                }
            }
            next[i] = lo;
        }

        long[][] f = new long[n + 1][5];
        List<Integer>[][] g = new List[n + 1][5];
        for (int k = 0; k <= 4; k++) {
            g[n][k] = new ArrayList<>();
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int k = 0; k <= 4; k++) {
                if (k == 0) {
                    f[i][k] = 0;
                    g[i][k] = new ArrayList<>();
                    continue;
                }
                long skipScore = f[i + 1][k];
                List<Integer> skipList = g[i + 1][k];

                long takeScore = f[next[i]][k - 1] + arr[i][2];
                List<Integer> takeList = new ArrayList<>(g[next[i]][k - 1]);
                int pos = Collections.binarySearch(takeList, arr[i][3]);
                if (pos < 0) pos = -(pos + 1);
                takeList.add(pos, arr[i][3]);

                if (takeScore > skipScore || (takeScore == skipScore && isLexSmaller(takeList, skipList))) {
                    f[i][k] = takeScore;
                    g[i][k] = takeList;
                } else {
                    f[i][k] = skipScore;
                    g[i][k] = skipList;
                }
            }
        }

        List<Integer> ans = g[0][4];
        int[] result = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) result[i] = ans.get(i);
        return result;
    }

    // Standard lexicographic comparison (shorter list that's a prefix counts as smaller)
    private boolean isLexSmaller(List<Integer> a, List<Integer> b) {
        int len = Math.min(a.size(), b.size());
        for (int i = 0; i < len; i++) {
            if (!a.get(i).equals(b.get(i))) {
                return a.get(i) < b.get(i);
            }
        }
        return a.size() < b.size();
    }
}