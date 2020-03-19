public class Dijkstra {
    static class MultiList {
        int[] head;
        int[] next;
        int[] vert;
        int[] cost;
        int cnt = 1;

        MultiList(int vNum, int eNum) {
            head = new int[vNum];
            next = new int[eNum * 2 + 1];
            vert = new int[eNum * 2 + 1];
            cost = new int[eNum * 2 + 1];
        }

        void add(int u, int v, int w) {
            next[cnt] = head[u];
            vert[cnt] = v;
            cost[cnt] = w;
            head[u] = cnt++;

            next[cnt] = head[v];
            vert[cnt] = u;
            cost[cnt] = w;
            head[v] = cnt++;

        }
    }
}
