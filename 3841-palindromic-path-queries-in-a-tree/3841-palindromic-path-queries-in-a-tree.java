class Solution {
    private int n;
    private int[] bit;

    private void bitUpdate(int i, int v) {
        for (i++; i <= n + 1; i += i & -i) bit[i] ^= v;
    }

    private int bitQuery(int i) {
        int r = 0;
        for (i++; i > 0; i -= i & -i) r ^= bit[i];
        return r;
    }

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        this.n = n;
        char[] c = s.toCharArray();
        bit = new int[n + 3];

        // adjacency list
        int[] head = new int[n];
        java.util.Arrays.fill(head, -1);
        int[] to = new int[2 * (n - 1)], nxt = new int[2 * (n - 1)];
        int cnt = 0;
        for (int[] e : edges) {
            to[cnt] = e[1]; nxt[cnt] = head[e[0]]; head[e[0]] = cnt++;
            to[cnt] = e[0]; nxt[cnt] = head[e[1]]; head[e[1]] = cnt++;
        }

        int LOG = 1;
        while ((1 << LOG) < n) LOG++;
        int[][] up = new int[LOG + 1][n];
        int[] depth = new int[n], tin = new int[n], tout = new int[n], pre0 = new int[n];

        // iterative DFS from root 0
        int[] stack = new int[n];
        int[] it = head.clone();
        int sp = 0, timer = 0;
        stack[sp++] = 0;
        tin[0] = timer++;
        pre0[0] = 1 << (c[0] - 'a');
        while (sp > 0) {
            int v = stack[sp - 1];
            if (it[v] != -1) {
                int e = it[v];
                it[v] = nxt[e];
                int w = to[e];
                if (w == up[0][v]) continue;       // skip parent
                up[0][w] = v;
                depth[w] = depth[v] + 1;
                tin[w] = timer++;
                pre0[w] = pre0[v] ^ (1 << (c[w] - 'a'));
                stack[sp++] = w;
            } else {
                tout[v] = timer - 1;
                sp--;
            }
        }

        for (int k = 1; k <= LOG; k++)
            for (int v = 0; v < n; v++)
                up[k][v] = up[k - 1][up[k - 1][v]];

        List<Boolean> ans = new ArrayList<>();

        for (String q : queries) {
            String[] p = q.split(" ");
            if (p[0].equals("update")) {
                int u = Integer.parseInt(p[1]);
                char nc = p[2].charAt(0);
                if (c[u] != nc) {
                    int delta = (1 << (c[u] - 'a')) ^ (1 << (nc - 'a'));
                    bitUpdate(tin[u], delta);
                    bitUpdate(tout[u] + 1, delta);
                    c[u] = nc;
                }
            } else {
                int u = Integer.parseInt(p[1]), v = Integer.parseInt(p[2]);

                // LCA by binary lifting
                int a = u, b = v;
                if (depth[a] < depth[b]) { int t = a; a = b; b = t; }
                int diff = depth[a] - depth[b];
                for (int k = 0; k <= LOG; k++)
                    if ((diff >> k & 1) == 1) a = up[k][a];
                int l;
                if (a == b) {
                    l = a;
                } else {
                    for (int k = LOG; k >= 0; k--) {
                        if (up[k][a] != up[k][b]) { a = up[k][a]; b = up[k][b]; }
                    }
                    l = up[0][a];
                }

                int preU = pre0[u] ^ bitQuery(tin[u]);
                int preV = pre0[v] ^ bitQuery(tin[v]);
                int mask = preU ^ preV ^ (1 << (c[l] - 'a'));
                ans.add((mask & (mask - 1)) == 0);
            }
        }
        return ans;
    }
}