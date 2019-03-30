package baekjoon.step.use_string;

import java.io.*;
import java.util.StringTokenizer;

public class P6064_KaingCalendar {

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            StringBuilder sb = new StringBuilder();

            int T = Integer.parseInt(br.readLine());

            for (int t = 0; t < T; t++) {

                StringTokenizer st = new StringTokenizer(br.readLine());
                int M = Integer.parseInt(st.nextToken());
                int N = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                int cnt = x % (M + 1);
                int tempY = x;

                for (int i = 0; i < N; i++) {
                    int ty = tempY % N == 0 ? N : tempY % N;
                    if (ty == y) {
                        break;
                    }

                    tempY = ty + M;
                    cnt += M;
                }

                sb.append(cnt > lcm(M, N) ? "-1" : cnt).append('\n');
            }

            bw.write(sb.toString());
            bw.flush();
        }
    }

    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    private static int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }
}
