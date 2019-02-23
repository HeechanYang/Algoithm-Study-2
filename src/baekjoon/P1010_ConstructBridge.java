package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 1010. 다리 짓기
 * <p>
 * - 계산을 좀 더 용이하게 하기 위해 N에 작은 수를, M에 큰 수를 대입한다.
 * - (현재 위치, 남은 개수)으로 메모이제이션을 하여 중복 계산을 줄인다.
 */

public class P1010_ConstructBridge {
    private static int N, M;
    private static long result;
    private static long[][] cache;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int t = Integer.parseInt(br.readLine());
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < t; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                N = Integer.parseInt(st.nextToken());
                M = Integer.parseInt(st.nextToken());

                // N에 작은 숫자를 대입하고
                // M에 큰 숫자를 대입한다
                int temp = Math.max(N, M);
                N = Math.min(N, M);
                M = temp;

                cache = new long[M][N];
                for (int m = 0; m < M; m++) {
                    for (int n = 0; n < N; n++) {
                        cache[m][n] = -1;
                    }
                }

                sb.append(solution(0, N - 1)).append('\n');
            }

            bw.write(sb.toString());
            bw.flush();
//            bw.close();
        }
    }

    private static long solution(int position, int remain) {
        if (cache[position][remain] == -1) {
            if (remain == 0) {
                int result = M - position;
                cache[position][remain] = result > 0 ? result : 0;
            } else {
                long result = 0;
                for (int i = position + 1; i + remain <= M; i++) {
                    result += solution(i, remain - 1);
                }
                cache[position][remain] = result;
            }
        }

        return cache[position][remain];
    }
}
