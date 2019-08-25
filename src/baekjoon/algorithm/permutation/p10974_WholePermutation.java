package baekjoon.algorithm.permutation;

import java.io.*;

public class p10974_WholePermutation {

    private static int N;
    private static char[] arr;
    private static boolean[] visits;
    private static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            N = Integer.parseInt(br.readLine());

            sb = new StringBuilder(2 * N * N * N);
            arr = new char[N];
            visits = new boolean[N];

            permutation(0);

            bw.write(sb.toString());
            bw.flush();
        }
    }

    private static void permutation(int depth) {
        if (depth == N) {
            for (int i = 0; i < N; i++) {
                sb.append(arr[i]).append(' ');
            }
            sb.append('\n');
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (!visits[i - 1]) {
                visits[i - 1] = true;
                arr[depth] = (char) (i + '0');
                permutation(depth + 1);
                visits[i - 1] = false;
            }
        }
    }
}
