package swexpertacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWgqsAlKr9sDFAW0
 * <p>
 * 6782. 현주가 좋아하는 제곱근 놀이
 */

public class P6782_RootGame {
    private static int T;

    public static void main(String[] args) throws IOException {
        try (final BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int T = Integer.parseInt(br.readLine());
            StringBuilder sb = new StringBuilder();

            for (int i = 1; i <= T; i++) {
                long n = Long.parseLong(br.readLine());

                // K 번째 요소 출력
                sb.append("#").append(i).append(" ").append(solution(n)).append("\n");
            }
            System.out.println(sb);
        }
    }

    public static long solution(long N) {
        long cnt = 0;
        while (N != 2) {
            long sqrt = (long) Math.sqrt(N);
            if (sqrt * sqrt == N) {
                cnt++;
                N = sqrt;
            } else {
                long tempL = sqrt + 1;
                cnt += tempL * tempL - N;
                N = tempL * tempL;
            }
        }
        return cnt;
    }
}