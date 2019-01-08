package swexpertacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15StKqAQkCFAYD&categoryId=AV15StKqAQkCFAYD&categoryType=CODE&&&
 * <p>
 * 1251. [S/W 문제해결 응용] 4. 하나로
 */

public class P1251_OneRoute {
    public static int T, N;
    public static double E;
    public static Island[] islands;
    public static boolean[] visited;
    public static double MAX_DISTANCE;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            T = Integer.parseInt(br.readLine());

            // Loop T times
            for (int t = 1; t <= T; t++) {
                N = Integer.parseInt(br.readLine());
                islands = new Island[N];

                visited = new boolean[N];
                MAX_DISTANCE = Math.sqrt(2000000000001L);

                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int i = 0; i < N; i++) {
                    islands[i] = new Island();
                    islands[i].x = Integer.parseInt(st.nextToken());
                }

                st = new StringTokenizer(br.readLine());
                for (int i = 0; i < N; i++) {
                    islands[i].y = Integer.parseInt(st.nextToken());
                }

                E = Double.parseDouble(br.readLine());

                // Print result
                System.out.printf("#%d %d\n", t, Math.round(solution()));
            }
        }
    }


    private static double solution() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        long distance = 0;
        int visitCnt = 1;

        Island a = islands[0];
        visited[0] = true;
        for (int i = 1; i < N; i++) {
            Island b = islands[i];
            pq.add(new Edge(0, i, getDistance(a.x, a.y, b.x, b.y)));
        }

        do {
            Edge e = pq.poll();

            if (visited[e.end]) {
                continue;
            }

            visited[e.end] = true;
            visitCnt++;

            distance += e.distance;

            a = islands[e.end];
            for (int i = 0; i < N; i++) {
                if (i != e.end && !visited[i]) {
                    Island b = islands[i];
                    pq.add(new Edge(e.end, i, getDistance(a.x, a.y, b.x, b.y)));
                }
            }
        } while (!pq.isEmpty() && visitCnt < N);

        return E * distance;
    }

    private static double getDistance(int x1, int y1, int x2, int y2) {
        return Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2);
    }

    static class Island {
        private int x;
        private int y;
    }

    static class Edge implements Comparable<Edge> {
        private final int start;
        private final int end;
        private final double distance;

        public Edge(int start, int end, double distance) {
            this.start = start;
            this.end = end;
            this.distance = distance;
        }

        @Override
        public int compareTo(Edge o) {
            return this.distance > o.distance ? 1 : -1;
        }
    }
}