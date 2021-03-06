package baekjoon;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1026
 * 1026. 보물
 * <p>
 * <p>
 * 그냥 한 배열은 내림차순 정렬, 한 배열은 오름차순 정렬해서 각각 곱한 뒤 더하면 됨
 */

public class P1026_Treasure {
    private static int N;
    private static int[] A, B;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int N = Integer.parseInt(br.readLine());
            A = new int[N];
            B = new int[N];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                A[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                B[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(A);
            Arrays.sort(B);

            int result = 0;
            for (int i = 0; i < N; i++) {
                result += A[i] * B[N - i - 1];
            }

            bw.write(String.valueOf(result));
            bw.flush();
//            bw.close();
        }
    }
}