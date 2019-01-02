package swexpertacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15OZ4qAPICFAYD&categoryId=AV15OZ4qAPICFAYD&categoryType=CODE
 * <p>
 * 1247. [S/W 문제해결 응용] 2일차 - 최적 경로
 */

public class P1248_CommonAncestor {
    public static int T, V, E, N1, N2;
    public static Map<Integer, Node> nodeMap;
    public static boolean[] isVisited;
    public static int commonAncestorIdx;

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
                nodeMap = new HashMap<>();
                isVisited = new boolean[V];

                st = new StringTokenizer(br.readLine());

                for (int j = 0; j < E; j++) {
                    int parentInt = Integer.parseInt(st.nextToken());
                    int childInt = Integer.parseInt(st.nextToken());
                    Node parent;
                    Node child;

                    if (!nodeMap.containsKey(parentInt)) {
                        parent = new Node(parentInt);
                        nodeMap.put(parentInt, parent);
                    } else {
                        parent = nodeMap.get(parentInt);
                    }

                    if (!nodeMap.containsKey(childInt)) {
                        child = new Node(childInt);
                        nodeMap.put(childInt, child);
                    } else {
                        child = nodeMap.get(childInt);
                    }

                    child.parent = parent;
                    if (parent.left == null) {
                        parent.left = child;
                    } else {
                        parent.right = child;
                    }
                }

                solution();

                setSize(nodeMap.get(commonAncestorIdx));

                // Print result
                System.out.printf("#%d %d %d\n", i, commonAncestorIdx, size);
            }
        }
    }

    private static void solution() {
        Node n1 = nodeMap.get(N1);
        Node n2 = nodeMap.get(N2);

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

    public static int size;

    private static void setSize(Node node) {
        size++;
        if (node.left != null) {
            setSize(node.left);
        }
        if (node.right != null) {
            setSize(node.right);
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