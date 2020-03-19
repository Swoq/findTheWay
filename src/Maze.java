import java.io.*;
//    path = "./src/input_files/input_field.txt"
public class Maze{
    String path;
    private File file;
    private int length;
    private int width;
    private char[][] maze;

    public Maze(String path){
        this.path = path;
        length = defineLength(path);
        width = defineWidth(path, length);
        maze = readMaze();
    }

    private static int defineLength(String path) {
        int length = 0;
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(path));
            length = bufferedReader.readLine().length();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return length;
    }

    private static int defineWidth(String path, int lenght){
        File file = new File(path);
        int file_size = (int) file.length();
        int width = file_size/lenght;
        return width;
    }

    private char[][] readMaze() {
        file = new File(path);
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

    public void showMaze(){
        int i,j;
        for (i = 0; i < width; i++) {
            for (j = 0; j < length; j++) {
                if(j < (length-1))
                    System.out.print(maze[i][j]);
                else
                    System.out.println(maze[i][j]);
            }
        }
        System.out.println();
    }

}