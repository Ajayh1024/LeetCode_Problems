class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        TreeSet<Integer> free = new TreeSet<>();
        for (int i = 0; i < k; i++) free.add(i);

        // busy: {finishTime, serverId}
        PriorityQueue<long[]> busy = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));
        int[] count = new int[k];

        for (int i = 0; i < arrival.length; i++) {
            // release servers that finished by the time this request arrives
            for (; !busy.isEmpty() && busy.peek()[0] <= arrival[i]; ) {
                free.add((int) busy.poll()[1]);
            }
            if (free.isEmpty()) continue;               // all servers busy: request dropped

            Integer server = free.ceiling(i % k);
            if (server == null) server = free.first();  // wrap around

            free.remove(server);
            count[server]++;
            busy.add(new long[]{(long) arrival[i] + load[i], server});
        }

        int max = 0;
        for (int c : count) max = Math.max(max, c);

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (count[i] == max) result.add(i);
        }
        return result;
    }
}