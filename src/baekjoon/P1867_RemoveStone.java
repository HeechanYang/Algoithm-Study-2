package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1867
 * 1867. 돌멩이 제거
 * <p>
 */

public class P1867_RemoveStone {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            Stone[] stones = new Stone[K];
            int remain = N;

            boolean[][] map = new boolean[N][N];

            int[] h = new int[N];
            int[] v = new int[N];

            int x, y;

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                x = Integer.parseInt(st.nextToken()) - 1;
                y = Integer.parseInt(st.nextToken()) - 1;

                h[x]++;
                v[y]++;

                map[y][x] = true;
                stones[i] = new Stone(x, y);
            }

            int cnt = 0;

            while (remain > 0) {
                boolean isHorizontal = true;
                int targetIdx = 0;
                int removedStone = 0;

                for (int i = 0; i < N; i++) {
                    if (removedStone < h[i]) {
                        removedStone = h[i];
                        targetIdx = i;
                    }
                }
                for (int i = 0; i < N; i++) {
                    if (removedStone < v[i]) {
                        isHorizontal = false;
                        removedStone = v[i];
                        targetIdx = i;
                    }
                }

                if (isHorizontal) {
                    remain -= h[targetIdx];
                    for (int i = 0; i < N; i++) {
                        if (map[i][targetIdx]) {
                            map[i][targetIdx] = false;
                            v[i]--;
                        }
                    }
                } else {
                    remain -= v[targetIdx];
                    for (int i = 0; i < N; i++) {
                        if (map[targetIdx][i]) {
                            map[targetIdx][i] = false;
                            h[i]--;
                        }
                    }
                }
                cnt++;
            }


            bw.write(String.valueOf(cnt));
            bw.flush();
//            bw.close();
        }
    }

    static class Stone {
        private final int x;
        private final int y;

        public Stone(int x, int y) {
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