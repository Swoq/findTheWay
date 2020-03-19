import java.util.Arrays;

public class Stack {
    private int[] data;

    public Stack() {
        data = new int[0];
    }

    public void add(int e) {
            data = transferData(increaseCapacity());
            data[data.length - 1] = e;
    }

    public int poll() {
        int queueEnd = data[data.length-1];
        data = transferData(decreaseCapacity());

        return queueEnd;
    }

    public int peek() {
        return data[data.length-1];
    }

    public int size() {
        return data.length;
    }

    private int[] increaseCapacity(){
        return new int[data.length+1];
    }

    private int[] decreaseCapacity(){
        return new int[data.length-1];
    }

    private int[] transferData(int[] newData){
        if(data.length > newData.length)
            System.arraycopy(data, 0, newData, 0, newData.length);
        else
            System.arraycopy(data, 0, newData, 0, data.length);
        return newData;
    }

    @Override
    public String toString() {
        return Arrays.toString(data);
    }

    public boolean isEmpty() {
        return data.length == 0;
    }

    private boolean alreadyExist(int e){
        return Arrays.binarySearch(data, e) > 0;
    }
}
