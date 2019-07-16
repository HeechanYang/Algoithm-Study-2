package datastructures.list;

public class ArrayList {
    private int[] arr;
    private int capacity;

    public ArrayList(int capacity){
        this.capacity= capacity;
        this.arr = new int[capacity];
    }
}
