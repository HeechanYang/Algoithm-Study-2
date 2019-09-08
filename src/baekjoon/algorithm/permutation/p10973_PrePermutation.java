package baekjoon.algorithm.permutation;

import java.io.*;

public class p10973_PrePermutation {

    private static int N;
    private static char[] target;
    private static char[] arr;
    private static boolean[] visits;
    private static StringBuilder sb;
    private static boolean isEnd;
    private static int idx;
    private static int targetIdx;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            N = Integer.parseInt(br.readLine());

            target = new char[N];
            arr = new char[N];
            visits = new boolean[N];
            sb = new StringBuilder();

            String targetStr = br.readLine();
            for (int i = 0; i < N; i++) {
                target[i] = targetStr.charAt(i * 2);
            }

            targetIdx = -1;
            permutation(0);

            visits = new boolean[N];
            targetIdx--;
            idx = 0;
            isEnd = false;
            permutation(0);

            if (targetIdx == 0) {
                sb.append("-1");
            } else {
                for (int i = 0; i < N; i++) {
                    sb.append(arr[i]).append(' ');
                }
            }

            bw.write(sb.toString());
            bw.flush();
        }
    }

    private static void permutation(int depth) {
        if (depth == N) {
            if (idx == (targetIdx) || isSameArray(arr, target)) {
                targetIdx = idx;
                isEnd = true;
            } else {
                idx++;
            }

            return;
        }

        for (int i = 1; i <= N; i++) {
            if (!visits[i - 1]) {
                visits[i - 1] = true;
                arr[depth] = (char) (i + '0');
                permutation(depth + 1);
                if (isEnd) {
                    return;
                }
                visits[i - 1] = false;
            }
        }
    }

    private static boolean isSameArray(char[] str1, char[] str2) {
        for (int i = 0; i < N; i++) {
            if (str1[i] != str2[i]) {
                return false;
            }
        }
        return true;
    }
}
