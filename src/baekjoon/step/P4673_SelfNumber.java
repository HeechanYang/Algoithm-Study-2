package baekjoon.step;

import java.io.*;

public class P4673_SelfNumber {

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringBuilder sb = new StringBuilder();

            boolean[] visited = new boolean[10001];
            int cnt = 0;

            for (int i = 1; i <= 10000; i++) {
                int temp = d(i);
                if (temp <= 10000) {
                    visited[temp] = true;
                }
            }

            for (int i = 1; i <= 10000; i++) {
                if (!visited[i]) {
                    sb.append(i).append('\n');
                }
            }
            bw.write(sb.toString());
            bw.flush();
        }
    }

    private static int d(int num) {
        int result = num;

        while (num > 0) {
            result += num % 10;
            num /= 10;
        }
        return result;
    }
}
