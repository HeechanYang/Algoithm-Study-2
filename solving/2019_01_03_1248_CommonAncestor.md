# 2019.01.03.(목)
## [1248. [S/W 문제해결 응용] 3일차 - 공통 조상](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15PTkqAPYCFAYD)

1. 그냥 문제 그대로 트리 만들어서 해결함.

2. Node Class 생성
```java
static class Node {
    private int value;
    private Node parent;
    private Node left;
    private Node right;

    public Node(int value) {
        this.value = value;
    }
}
```

3. 두 노드에서 차근차근 올라가며 공통조상을 찾는 함수 생성.
```java
private static void findAncestor() {
    Node n1 = nodeArr[N1];
    Node n2 = nodeArr[N2];

    int temp;

    while (true) {
        if (n1 != null) {
            temp = n1.value;
            if (isVisited[temp]) {
                commonAncestorIdx = temp;
                break;
            }
            isVisited[temp] = true;
            n1 = n1.parent;
        }

        if (n2 != null) {
            temp = n2.value;
            if (isVisited[temp]) {
                commonAncestorIdx = temp;
                break;
            }
            isVisited[temp] = true;
            n2 = n2.parent;
        }
    }
}
```

4. 백트래킹을 통해 해당 노드를 정점으로 하는 서브트리의 사이즈 구하는 함수 생성.
```java
private static void size(Node node) {
    size++;
    if (node.left != null) {
        size(node.left);
    }
    if (node.right != null) {
        size(node.right);
    }
}
```

5. 전체 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1248_CommonAncestor {
    public static int T, V, E, N1, N2;
    public static Node[] nodeArr;
    public static boolean[] isVisited;
    public static int commonAncestorIdx;
    public static int size;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            T = Integer.parseInt(br.readLine());

            // Loop T times
            for (int i = 1; i <= T; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                commonAncestorIdx = -1;
                V = Integer.parseInt(st.nextToken());
                E = Integer.parseInt(st.nextToken());
                N1 = Integer.parseInt(st.nextToken());
                N2 = Integer.parseInt(st.nextToken());
                size = 0;
                nodeArr = new Node[V + 1];
                isVisited = new boolean[V+1];

                st = new StringTokenizer(br.readLine());

                for (int j = 0; j < E; j++) {
                    int parentInt = Integer.parseInt(st.nextToken());
                    int childInt = Integer.parseInt(st.nextToken());
                    Node parent;
                    Node child;

                    if (nodeArr[parentInt] == null) {
                        parent = new Node(parentInt);
                        nodeArr[parentInt] = parent;
                    } else {
                        parent = nodeArr[parentInt];
                    }

                    if (nodeArr[childInt] == null) {
                        child = new Node(childInt);
                        nodeArr[childInt] = child;
                    } else {
                        child = nodeArr[childInt];
                    }

                    child.parent = parent;
                    if (parent.left == null) {
                        parent.left = child;
                    } else {
                        parent.right = child;
                    }
                }

                findAncestor();

                size(nodeArr[commonAncestorIdx]);

                // Print result
                System.out.printf("#%d %d %d\n", i, commonAncestorIdx, size);
            }
        }
    }

    private static void findAncestor() {
        Node n1 = nodeArr[N1];
        Node n2 = nodeArr[N2];

        int temp;

        while (true) {
            if (n1 != null) {
                temp = n1.value;
                if (isVisited[temp]) {
                    commonAncestorIdx = temp;
                    break;
                }
                isVisited[temp] = true;
                n1 = n1.parent;
            }

            if (n2 != null) {
                temp = n2.value;
                if (isVisited[temp]) {
                    commonAncestorIdx = temp;
                    break;
                }
                isVisited[temp] = true;
                n2 = n2.parent;
            }
        }
    }

    private static void size(Node node) {
        size++;
        if (node.left != null) {
            size(node.left);
        }
        if (node.right != null) {
            size(node.right);
        }
    }

    static class Node {
        private int value;
        private Node parent;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }
}
```