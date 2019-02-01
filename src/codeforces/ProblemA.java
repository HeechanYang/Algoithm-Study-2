package codeforces;

import java.io.*;
import java.util.StringTokenizer;

/**
 * https://codeforces.com/contest/1108/problem/A
 * A. Two distinct points
 *
 * 그냥 두 선분(A, B)에서 A에 속하는 점 중 하나(a), B에 속하는 점 중 하나(b)를 출력하는데
 * 단, a와 b가 같으면 안된다.
 *
 * [해결방법]
 * 걍 이중포문으로 서로 다른거 나올때까지 돌리면 됨.
 */

public class ProblemA {
    private static int T;
    private static int A, B;
    private static int l1, r1, l2, r2;
    private static int[] heightArr;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            StringBuilder sb = new StringBuilder();

            T = Integer.parseInt(br.readLine());

            for (int i = 1; i <= T; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                l1 = Integer.parseInt(st.nextToken());
                r1 = Integer.parseInt(st.nextToken());
                l2 = Integer.parseInt(st.nextToken());
                r2 = Integer.parseInt(st.nextToken());

                solution(l1, r1, l2, r2);

                sb.append(A).append(' ').append(B).append('\n');
            }

            bw.write(sb.toString());
            bw.flush();
            bw.close();
        }
    }

    private static void solution(int l1, int r1, int l2, int r2) {
        boolean flag = false;

        for (int i = l1; i <= r1; i++) {
            A = i;
            for (int j = l2; j <= r2; j++) {
                B = j;
                if (A != B) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                break;
            }
        }
    }
}