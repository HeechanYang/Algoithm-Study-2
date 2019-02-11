package baekjoon;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 1007. 벡터 매칭
 * <p>
 * -
 */

/*
2
4
5 5
5 -5
-5 5
-5 -5
2
-100000 -100000
100000 100000
 */

/*
0.000000000000
282842.712474619038
 */

public class p1007_VetctorMatching {
    private static int T, N;
    private static List<Dot> dots;

    private static int sumX;
    private static int sumY;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringBuilder resultString = new StringBuilder();

            T = Integer.parseInt(br.readLine());

            for (int i = 0; i < T; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                N = Integer.parseInt(st.nextToken());

                dots = new ArrayList<>(20 + 1);

                sumX = 0;
                sumY = 0;

                for (int j = 0; j < N; j++) {
                    st = new StringTokenizer(br.readLine());
                    long x = Long.parseLong(st.nextToken());
                    long y = Long.parseLong(st.nextToken());
                    Dot dot = new Dot(x, y);

                    dots.add(dot);

                    sumX += x;
                    sumY += y;
                }

                count = 0;

                long result = solution(0, 0, 0, 0);
                double resultLength = result != 0 ? Math.sqrt(result) : result;
                resultString.append(resultLength).append('\n');
            }

            bw.write(resultString.toString());
            bw.flush();
        }
    }

    private static int count;

    private static long solution(int cnt, int position, long sx, long sy) {
        count++;

        if (cnt == N / 2) {
            return (sumX - sx - sx) * (sumX - sx - sx) + (sumY - sy - sy) * (sumY - sy - sy);
        } else {
            long result = Long.MAX_VALUE;
            cnt++;

            while (position < N) {
                result = Math.min(result
                        , solution(cnt, position + 1, sx + dots.get(position).x, sy + dots.get(position).y));
                position++;
            }
            return result;
        }
    }

    private

    static class Dot {
        private final long x;
        private final long y;

        public Dot(long x, long y) {
            this.x = x;
            this.y = y;
        }

        public long getX() {
            return x;
        }

        public long getY() {
            return y;
        }
    }
}