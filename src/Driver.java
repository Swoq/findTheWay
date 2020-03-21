import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        Maze maze = new Maze("src/input_field/input_field");
        Scanner in = new Scanner(System.in);
        int[][] matrix = maze.checkMazeNodes();
        Dijkstra dijkstra = new Dijkstra(matrix);


        System.out.print("Start:\n----> ");
        int start = in.nextInt();
        System.out.print("End:\n----> ");
        int end = in.nextInt();
        maze.showMazeWithPath(dijkstra.dijkstraRMQ(start, end));
        dijkstra.showShortWay();
    }
}
