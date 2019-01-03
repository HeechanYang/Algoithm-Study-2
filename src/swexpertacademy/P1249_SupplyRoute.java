package swexpertacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15QRX6APsCFAYD&categoryId=AV15QRX6APsCFAYD&categoryType=CODE&&&
 * <p>
 * 1249. [S/W 문제해결 응용] 4. 보급로
 */

public class P1249_SupplyRoute {
    public static int T, N;
    private static int NM;
    public static int[][] land;
    public static boolean[][] isVisited;
    public static int CHAR_ASCII = 48;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            T = Integer.parseInt(br.readLine());

            // Loop T times
            for (int i = 1; i <= T; i++) {
                N = Integer.parseInt(br.readLine());
                NM = N - 1;
                land = new int[N][N];
                isVisited = new boolean[N][N];

                for (int j = 0; j < N; j++) {
                    StringBuilder sb = new StringBuilder(br.readLine());

                    for (int k = 0; k < N; k++) {
                        land[j][k] = sb.charAt(k) - CHAR_ASCII;
                    }
                }

                // Print result
                System.out.printf("#%d %d\n", i, solution());
            }
        }
    }


    private static int solution() {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.offer(new Node(0, 0, 0));
        isVisited[0][0] = true;


        while (!queue.isEmpty()) {
            Node pop = queue.poll();
            int x = pop.x;
            int y = pop.y;
            int xm = x - 1;
            int xp = x + 1;
            int ym = y - 1;
            int yp = y + 1;
            int curDist = pop.dist;

            if(x==NM && y == NM){
                return curDist;
            }

            if (x > 1) {
                int newDist = curDist + land[y][xm];
                if (!isVisited[y][xm]) {
                    isVisited[y][xm] = true;
                    queue.add(new Node(xm, y, newDist));
                }
            }
            if (y > 1) {
                int newDist = curDist + land[ym][x];
                if (!isVisited[ym][x]) {
                    isVisited[ym][x] = true;
                    queue.add(new Node(x, ym, newDist));
                }
            }
            if (x < NM) {
                int newDist = curDist + land[y][xp];
                if (!isVisited[y][xp]) {
                    isVisited[y][xp] = true;
                    queue.add(new Node(xp, y, newDist));
                }
            }
            if (y < NM) {
                int newDist = curDist + land[yp][x];
                if (!isVisited[yp][x]) {
                    isVisited[yp][x] = true;
                    queue.add(new Node(x, yp, newDist));
                }
            }
        }

        return 0;
    }

    static class Node implements Comparable<Node>{
        private int x;
        private int y;
        private int dist;

        public Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }


        @Override
        public int compareTo(Node o) {
            return this.dist - o.dist;
        }
    }
}