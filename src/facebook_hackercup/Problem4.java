package facebook_hackercup;

import java.io.*;
import java.util.*;

public class Problem4 {
    private static boolean[] isVisited;
    private static List<Tree> treeList;

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringBuilder sb = new StringBuilder();

            int T = Integer.parseInt(br.readLine());
            String temp;

            for (int i = 1; i <= T; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int N = Integer.parseInt(st.nextToken());
                int M = Integer.parseInt(st.nextToken());
                boolean isSuccess = true;

                Queue<Line> lineQueue = new LinkedList<>();

                treeList = new ArrayList<>();
                for (int j = 0; j < N; j++) {
                    treeList.add(new Tree(j));
                }
                isVisited = new boolean[N];

                for (int j = 0; j < M; j++) {
                    st = new StringTokenizer(br.readLine());

                    int x = Integer.parseInt(st.nextToken()) - 1;
                    int y = Integer.parseInt(st.nextToken()) - 1;
                    int z = Integer.parseInt(st.nextToken()) - 1;

                    if (x != z) {
                        lineQueue.add(new Line(x, z));
                    }
                    if (y != z) {
                        lineQueue.add(new Line(y, z));
                    }
                }

                // make tree
                while (!lineQueue.isEmpty()) {
                    Line line = lineQueue.poll();
//                    System.out.println("poll : "+line.getX());
                    Tree x = treeList.get(line.getX());
                    Tree y = treeList.get(line.getY());

                    if (!y.isContainedAt(x.getParent()) && !y.isContainedAt(x)) {
                        x.setParent(y);
                        y.addChild(x);
                    } else if (!y.getChildren().contains(x)) {
                        isSuccess = false;
                        break;
                    }
                }

                // sum trees
                Tree root = null;
                for (Tree tree : treeList) {
                    if (tree.getParent() == null) {
                        if (root == null) {
                            root = tree;
                        } else {
                            tree.setParent(root);
                        }
                    }
                }

                if (isSuccess) {
                    root = treeList.get(0);
                    while (root.getParent() != null) {
                        root = root.getParent();
                    }
                    if (travel(root)) {
                        System.out.println(String.format("Case #%d: %s\n", i, print(treeList)));
                    } else {
                        System.out.println(String.format("Case #%d: Impossible\n", i));
                    }
                } else {
                    System.out.println("isSuccess : " + false);
                    System.out.println(String.format("Case #%d: Impossible\n", i));
                }
            }

            bw.write(sb.toString());
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String print(List<Tree> treeList) {
        StringBuilder sb = new StringBuilder();

        for (Tree tree : treeList) {
            if (tree.parent == null) {
                sb.append(0).append(' ');
            } else {
                sb.append(tree.getParent().getValue() + 1).append(' ');
            }
        }

        return sb.toString();
    }

    private static boolean travel(Tree root) {
        Queue<Tree> queue = new LinkedList<>();
        queue.add(root);
        System.out.println("root : " + root.getValue());

        while (!queue.isEmpty()) {
            Tree poppedTree = queue.poll();
            isVisited[poppedTree.getValue()] = true;

            for (Tree child : poppedTree.getChildren()) {
                if (isVisited[child.getValue()]) {
                    System.out.println("already visited : " + child.getValue());
                    return false;
                }
                queue.add(child);
            }
        }

//        int rootCnt = 0;
//        for (boolean b : isVisited) {
//            if (!b) {
//                rootCnt++;
//            }
//        }
//
//        if (rootCnt > 1) {
//            int firstRootIdx = -1;
//            for (int i = 0; i < isVisited.length; i++) {
//                if(!isVisited[i]) {
//                    if (firstRootIdx == -1) {
//                        firstRootIdx = i;
//                    } else {
//                        treeList.get(i).setParent(treeList.get(firstRootIdx));
//                    }
//                }
//            }
//        }


        return true;
    }

    static class Line {
        private final int x;
        private final int y;

        public Line(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    static class Tree {
        private Tree parent;
        private List<Tree> children;

        private final int value;
        private double level;

        public Tree(int value) {
            this.value = value;
            children = new ArrayList<>();
        }

        public Tree getParent() {
            return parent;
        }

        public void setParent(Tree parent) {
            if (this.parent != null) {
                this.parent.getChildren().remove(this);
            }
            this.parent = parent;
        }

        public List<Tree> getChildren() {
            return children;
        }

        public void addChild(Tree tree) {
            if (tree != this) {
                children.add(tree);
            }
        }

        public int getValue() {
            return value;
        }

        public double getLevel() {
            return level;
        }

        public void setLevel(double level) {
            this.level = level;
        }

        public boolean isContainedAt(Tree targetTree) {
            Tree parent = this.parent;
            while (parent != null) {
                if (parent == targetTree) {
                    return true;
                }
                parent = parent.getParent();
            }
            return false;
        }
    }
}
