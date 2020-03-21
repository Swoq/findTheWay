import java.util.Arrays;

import static java.util.Arrays.fill;

public class Dijkstra {
    private int INF = Integer.MAX_VALUE / 2;
    private int vNum;
    private MultiList graph;
    private int[] shortWay;

    public Dijkstra(int[][] matrix){
        this.vNum = matrix.length;
        graph = new MultiList(vNum, countEdges(matrix));
        initGraph(matrix);
    }

    public int[] dijkstraRMQ(int start, int end) {
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
            sp[i] = stack.poll();
        shortWay = sp.clone();
        return sp;
    }

    static class MultiList {
        int[] head;
        int[] next;
        int[] vert;
        int[] cost;
        int cnt = 1;

        MultiList(int vNum, int eNum) {
            head = new int[vNum];
            next = new int[eNum + 1];
            vert = new int[eNum + 1];
            cost = new int[eNum + 1];
        }

        void add(int u, int v, int w) {
            next[cnt] = head[u];
            vert[cnt] = v;
            cost[cnt] = w;
            head[u] = cnt++;
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

    public int countEdges(int[][] matrix){
        int numOfEdges = 0;
        for (int[] ints : matrix) {
            for (int j = 1; j < ints.length; j++) {
                if (ints[j] != -1) {
                    numOfEdges++;
                }
            }
        }
        return numOfEdges;
    }

    private void initGraph(int[][] matrix){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 1; j < matrix[i].length; j++) {
                if (matrix[i][j] != -1){
                    graph.add(i, matrix[i][j], 1);
                }
            }
        }
    }

    public void showShortWay(){
        System.out.println("Кратчайший путь: " + Arrays.toString(shortWay));
    }

}
