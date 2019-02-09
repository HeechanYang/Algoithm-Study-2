package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1004
 * 1004. 어린 왕자
 * <p>
 * 각 행성계는 맞닿거나 서로 교차하지 않으므로 각 행성계의 관계는 다음 중 하나이다.
 * - 포함하거나
 * - 포함되거나
 * - 전혀 관련 없거나
 * <p>
 * 그러므로 다음과 같은 원들의 수를 찾으면 된다
 * - '출발지'와 '도착지 모두를 포함하는 원은 제외한다.
 * - '출발지'를 포함하는 원의 수를 센다. => A
 * - '도착지'를 포함하는 원의 수를 센다. => B
 * - 답은 A + B
 * <p>
 * 포함하는지 여부는 '행성계의 중심부터 목표점의 거리'와 '행성계의 반지름'을 비교하면 된다.
 */

/*
2
-5 1 12 1
7
1 1 8
-3 -1 1
2 2 2
5 5 1
-4 5 1
12 1 1
12 1 2
-5 1 5 1
1
0 0 2
 */

public class P1004_YoungPrince {

    private static int T, N;
    private static int sx, sy, ex, ey;
    private static Planet[] planets;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringBuilder sb = new StringBuilder();

            T = Integer.parseInt(br.readLine());

            for (int i = 0; i < T; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                sx = Integer.parseInt(st.nextToken());
                sy = Integer.parseInt(st.nextToken());
                ex = Integer.parseInt(st.nextToken());
                ey = Integer.parseInt(st.nextToken());

                N = Integer.parseInt(br.readLine());
                planets = new Planet[N];

                for (int j = 0; j < N; j++) {
                    st = new StringTokenizer(br.readLine());
                    int x = Integer.parseInt(st.nextToken());
                    int y = Integer.parseInt(st.nextToken());
                    int r = Integer.parseInt(st.nextToken());

                    planets[j] = new Planet(x, y, r);
                }

                sb.append(solution()).append('\n');
            }

            bw.write(sb.toString());
            bw.flush();
        }
    }

    private static int solution() {
        int A = 0, B = 0;
        for (Planet p : planets) {
            final boolean containA = isContained(sx, sy, p);
            final boolean containB = isContained(ex, ey, p);

            if (containA && !containB) {
                A++;
            } else if (!containA && containB) {
                B++;
            }
        }

        return A + B;
    }

    private static boolean isContained(int x, int y, Planet p) {
        double distance = getDistance(x, y, p.getX(), p.getY());

        return distance < p.radius;
    }

    private static double getDistance(int x1, int y1, int x2, int y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    static class Planet {
        private final int x;
        private final int y;
        private final int radius;

        public Planet(int x, int y, int radius) {
            this.x = x;
            this.y = y;
            this.radius = radius;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getRadius() {
            return radius;
        }
    }
}
