package baekjoon.step.sort;

import java.io.*;

public class P10989_SortNumbers3 {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringBuilder sb = new StringBuilder(10);

            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[10001];

            for (int i = 0; i < N; i++) {
                int num = Integer.parseInt(br.readLine());
                arr[num]++;
            }

            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[i]; j++) {
                    sb.append(i).append('\n');
                }
            }

            bw.write(sb.toString());
            bw.flush();
        }
    }
}
