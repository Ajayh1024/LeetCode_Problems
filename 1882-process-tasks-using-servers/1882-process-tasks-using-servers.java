class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length, m = tasks.length;

        // free: {weight, index}
        PriorityQueue<int[]> free = new PriorityQueue<>(
                (a, b) -> a[0] != b[0] ? Integer.compare(a[0], b[0]) : Integer.compare(a[1], b[1]));
        // busy: {finishTime, weight, index}  (finish time can reach about 2 * 10^5 * 2 * 10^5, so use long)
        PriorityQueue<long[]> busy = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return Long.compare(a[0], b[0]);
            if (a[1] != b[1]) return Long.compare(a[1], b[1]);
            return Long.compare(a[2], b[2]);
        });

        for (int i = 0; i < n; i++) free.add(new int[]{servers[i], i});

        int[] ans = new int[m];
        long t = 0;

        for (int j = 0; j < m; j++) {
            t = Math.max(t, j);                       // task j arrives at second j
            if (free.isEmpty()) {
                t = Math.max(t, busy.peek()[0]);      // wait until a server frees up
            }
            // release every server that has finished by time t
            for (; !busy.isEmpty() && busy.peek()[0] <= t; ) {
                long[] s = busy.poll();
                free.add(new int[]{(int) s[1], (int) s[2]});
            }
            int[] best = free.poll();
            ans[j] = best[1];
            busy.add(new long[]{t + tasks[j], best[0], best[1]});
        }
        return ans;
    }
}