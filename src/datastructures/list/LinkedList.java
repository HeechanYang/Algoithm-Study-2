package datastructures.list;

public class LinkedList {
    private Node root;
    private Node last;
    private int size;

    public LinkedList() {
        root = null;
        size = 0;
    }

    public Node getNode(int position) {
        if (position < 0 || position > size - 1) {
            throw new RuntimeException("No element at: " + position);
        }
        Node node = root;
        for (int i = 0; i < position; i++) {
            node = node.getNext();
        }
        return node;
    }

    public int get(int position) {
        return getNode(position).data;
    }

    public void add(int val) {
        if (size == 0) {
            root = new Node(val);
            last = root;
        } else { // 이렇게 하면 맨 뒤까지 옮길 필요 없음
            last.next = new Node(val);
            last = last.next;
        }
        size++;
    }

    public int delete(int position) {
        if (position < 0 || position > size - 1) {
            throw new RuntimeException("Cannot delete at: " + position);
        }
        Node before = getNode(position - 1);
        before.next = before.next.next;
        size--;
        return before.data;
    }

    public void print() {
        Node node = root;
        System.out.print("[");
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
        System.out.print("]");
        System.out.println();
    }

    public int size() {
        return this.size;
    }

    class Node {
        private int data;
        private Node next;

        public Node(int data) {
            this.data = data;
        }

        public int getData() {
            return data;
        }

        public Node getNext() {
            return next;
        }
    }

    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);

        linkedList.print();
        System.out.println("linkedList.get(0): " + linkedList.get(0));
        System.out.println("linkedList.get(1): " + linkedList.get(1));
        System.out.println("linkedList.get(2): " + linkedList.get(2));

        System.out.println("linkedList.delete(2): " + linkedList.delete(2));

        linkedList.print();

    }
}
