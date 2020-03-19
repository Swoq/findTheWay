import java.util.Arrays;

import static java.util.Arrays.fill;

public class Dijkstra {
    int INF = Integer.MAX_VALUE / 2;
    int vNum;
    MultiList graph;

    void dijkstraRMQ(int start, int end) {
        boolean[] used = new boolean[vNum];
        int[] prev = new int[vNum];
        int[] dist = new int[vNum];
        RMQ rmq = new RMQ(vNum);

        fill(prev, -1);
        fill(dist, INF);

        rmq.set(start, dist[start] = 0);

        for (; ;) {
            int v = rmq.minIndex();
            if (v == -1 || v == end) break;

            used[v] = true;
            rmq.set(v, INF);

            for (int i = graph.head[v]; i != 0; i = graph.next[i]) {
                int nv = graph.vert[i];
                int cost = graph.cost[i];
                if (!used[nv] && dist[nv] > dist[v] + cost) {
                    rmq.set(nv, dist[nv] = dist[v] + cost);
                    prev[nv] = v;
                }
            }
        }

        Stack stack = new Stack();
        for (int v = end; v != -1; v = prev[v]) {
            stack.add(v);
        }
        int[] sp = new int[stack.size()];
        for (int i = 0; i < sp.length; i++)
            sp[i] = stack.poll() + 1;

        System.out.printf("Кратчайшее расстояние между %d и %d = %d%n", start + 1, end + 1, dist[end]);
        System.out.println("Кратчайший путь: " + Arrays.toString(sp));
    }

    static class MultiList {
        int[] head;
        int[] next;
        int[] vert;
        int[] cost;
        int cnt = 1;

        MultiList(int vNum, int eNum) {
            head = new int[vNum];
            next = new int[eNum*2 + 1];
            vert = new int[eNum*2 + 1];
            cost = new int[eNum*2 + 1];
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

    class RMQ {
        int n;
        int[] val;
        int[] ind;

        RMQ(int size) {
            n = size;
            val = new int[2 * n];
            ind = new int[2 * n];
            fill(val, INF);
            for (int i = 0; i < n; i++)
                ind[n + i] = i;
        }

        void set(int index, int value) {
            val[n + index] = value;
            for (int v = (n + index) / 2; v > 0; v /= 2) {
                int l = 2 * v;
                int r = l + 1;
                if (val[l] <= val[r]) {
                    val[v] = val[l];
                    ind[v] = ind[l];
                } else {
                    val[v] = val[r];
                    ind[v] = ind[r];
                }
            }
        }

        int minIndex() {
            return val[1] < INF ? ind[1] : -1;
        }
    }
}