package baekjoon;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1012
 * 1012. 유기농 배추
 * <p>
 *
 */

public class P1012_OrganicCabbage {
    private static int M, N, K;
    private static boolean[][] land;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringBuilder sb = new StringBuilder();

            int T = Integer.parseInt(br.readLine());

            for (int i = 0; i < T; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                M = Integer.parseInt(st.nextToken());
                N = Integer.parseInt(st.nextToken());
                K = Integer.parseInt(st.nextToken());

                land = new boolean[M][N];
                visited = new boolean[M][N];

                int x, y;
                for (int j = 0; j < K; j++) {
                    st = new StringTokenizer(br.readLine());
                    x = Integer.parseInt(st.nextToken());
                    y = Integer.parseInt(st.nextToken());
                    land[x][y] = true;
                }

                int areaCnt = 0;
                for (int m = 0; m < M; m++) {
                    for (int n = 0; n < N; n++) {
                        if (land[m][n]) {
                            makeArea(m, n);
                            areaCnt++;
                        }
                    }
                }

                sb.append(areaCnt).append('\n');
            }

            bw.write(sb.toString());
            bw.flush();
//            bw.close();
        }
    }

    private static void makeArea(int x, int y) {
        Queue<Coord> queue = new LinkedList<>();
        queue.add(new Coord(x, y));

        while (!queue.isEmpty()) {
            Coord thisCoord = queue.poll();
            x = thisCoord.getX();
            y = thisCoord.getY();
            land[x][y] = false;

            if (x > 0 && land[x - 1][y]) {
                queue.add(new Coord(x - 1, y));
            }
            if (x < M - 1 && land[x + 1][y]) {
                queue.add(new Coord(x + 1, y));
            }
            if (y > 0 && land[x][y - 1]) {
                queue.add(new Coord(x, y - 1));
            }
            if (y < N - 1 && land[x][y + 1]) {
                queue.add(new Coord(x, y + 1));
            }

        }
    }

    private static class Coord {
        private final int x;
        private final int y;

        public Coord(int x, int y) {
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
}