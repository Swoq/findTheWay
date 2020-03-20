import java.io.*;
//    path = "./src/input_files/input_field.txt"
public class Maze{
    String path;
    private File file;
    private int length;
    private int width;
    private char[][] ch_maze;
    private int[][] digital_maze;
    private int path_cells_amount;

    public Maze(String path){
        this.path = path;
        length = defineLength(path);
        width = defineWidth(path, length);
        ch_maze = readMaze();
        digital_maze = digitizeMaze(ch_maze);
    }

    private static int defineLength(String path) {
        BufferedReader bufferedReader;
        int length = 0;
        try {
            bufferedReader = new BufferedReader(new FileReader(path));
            length = bufferedReader.readLine().length();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return length;
    }

    private static int defineWidth(String path, int length){
        BufferedReader bufferedReader;
        int width = 0;
        try {
            bufferedReader = new BufferedReader(new FileReader(path));
            while (bufferedReader.ready()) {
                bufferedReader.readLine();
                width++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return width;
    }

    private char[][] readMaze() {
        BufferedReader br = null;
        char[][] field = new char[width][length];
        char temp;
        int i, j;
        try {
            br = new BufferedReader(new FileReader(path));
            for (i = 0; i < width & br.ready(); i++) {
                for (j = 0; j < length; j++) {
                    if ((temp = (char) br.read()) != 10 && temp != 13) {
                        field[i][j] = temp;
                    } else
                        j--;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return field;
    }

    private int[][] digitizeMaze(char[][] ch_maze){
        int width = this.width;
        int length = this.length;
        int path_cells_counter = 0;
        int[][] num_maze = new int[width][length];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {
                if(ch_maze[i][j] == 'X')
                    num_maze[i][j] = -1;
                else if(ch_maze[i][j] == ' '){
                    num_maze[i][j] = path_cells_counter;
                    path_cells_counter++;
                }
            }
        }
        path_cells_amount = path_cells_counter;
        return num_maze;
    }

    public int[][] checkMazeNodes(){
        int[][] digital_maze = this.digital_maze;
        int[][] adjacent_nodes = new int[this.path_cells_amount][5];
        int k = 0;
        for (int i = 0; i < digital_maze.length; i++) {
            for (int j = 0; j < digital_maze[i].length; j++) {
                if (k < adjacent_nodes.length & digital_maze[i][j] != -1) {
                    adjacent_nodes[k] = checkNode(digital_maze, i, j);
                    k++;
                }
            }
        }
        return adjacent_nodes;
    }

    private static int[] checkNode(int[][] num_maze, int i, int j){
        int[] nums = new int[5];
        nums[0] = num_maze[i][j];
        int left = j - 1;
        int right = j + 1;
        int up = i - 1;
        int down = i + 1;
        if (left >= 0 && num_maze[i][left] > -1)
            nums[1] = num_maze[i][left];
        else
            nums[1] = -1;
        if (right < num_maze[i].length && num_maze[i][right] > -1)
            nums[2] = num_maze[i][right];
        else
            nums[2] = -1;
        if (up >= 0 && num_maze[up][j] > -1)
            nums[3] = num_maze[up][j];
        else
            nums[3] = -1;
        if(down < num_maze.length && num_maze[down][j] > -1)
            nums[4] = num_maze[down][j];
        else
            nums[4] = -1;
        return nums;
    }

    public void showMaze(){
        int i,j;
        for (i = 0; i < width; i++) {
            for (j = 0; j < length; j++) {
                if(j < (length-1))
                    System.out.print(ch_maze[i][j]);
                else
                    System.out.println(ch_maze[i][j]);
            }
        }
        System.out.println();
    }
}

