import static java.util.Arrays.fill;

class RMQ {
    int INF = Integer.MAX_VALUE / 2;
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
