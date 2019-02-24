package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1009
 * 1009. 분산처리
 * <p>
 * 그냥 a^b의 일의 자리 숫자를 구하면 된다.
 * - 좀 더 간단히 하여 (a의 일의 자리 숫자)^b의 일의 자리 숫자를 구하면 된다.
 * - 또한, 다음을 보면 각 제곱수의 일의 자리 숫자는 못해도 4 번에 한 번은 반복되는 것을 볼 수 있다.
 * - 결국 구해야할 것은 `(a의 일의 자리 숫자)^(b % 4)의 일의 자리 숫자`인 것이다
 * <p>
 * 각 수의 제곱수의 일의 자리 숫자는 다음과 같다.
 * 0 - 0, 0, 0, 0, 0, ...
 * 1 - 1, 1, 1, 1, 1, ...
 * 2 - 2, 4, 8, 6, 2, ...
 * 3 - 3, 9, 7, 1, 3, ...
 * 4 - 4, 6, 4, 6, 4, ...
 * 5 - 5, 5, 5, 5, 5, ...
 * 6 - 6, 6, 6, 6, 6, ...
 * 7 - 7, 9, 3, 1, 7, ...
 * 8 - 8, 4, 2, 6, 8, ...
 * 9 - 9, 1, 9, 1, 9, ...
 */

public class P1009_DistributedProcessing {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringBuilder sb = new StringBuilder();

            int T = Integer.parseInt(br.readLine());

            for (int i = 0; i < T; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken()) % 10;
                // [0,3]이 아닌 [1,4]를 구해야한다
                int b = (Integer.parseInt(st.nextToken()) + 3) % 4 + 1;

                int result = 1;
                for (int j = 0; j < b; j++) {
                    result *= a;
                }

                // [0,9]가 아닌 [1,10]을 구해야한다
                result = (result + 9) % 10 + 1;

                sb.append(result).append('\n');
            }

            bw.write(sb.toString());
            bw.flush();
//            bw.close();
        }
    }
}
