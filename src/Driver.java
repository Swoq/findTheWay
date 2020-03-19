public class Driver {
    public static void main(String[] args) {
        Dijkstra dijkstra = new Dijkstra();
        dijkstra.graph = new Dijkstra.MultiList(9, 14);
        dijkstra.vNum = 9;

        dijkstra.graph.add(0, 1, 1);
        dijkstra.graph.add(1, 2, 1);
        dijkstra.graph.add(2, 5, 1);
        dijkstra.graph.add(5, 6, 1);
        dijkstra.graph.add(6, 7, 1);
        dijkstra.graph.add(7, 0, 1);
        dijkstra.graph.add(1, 7, 1);
        dijkstra.graph.add(7, 8, 1);
        dijkstra.graph.add(8, 2, 1);
        dijkstra.graph.add(2, 3, 1);
        dijkstra.graph.add(8, 6, 1);
        dijkstra.graph.add(3, 4, 1);
        dijkstra.graph.add(3, 5, 1);
        dijkstra.graph.add(4, 5, 1);

        dijkstra.dijkstraRMQ(0, 5);
    }
}
