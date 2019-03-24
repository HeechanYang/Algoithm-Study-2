package baekjoon.step;

import java.io.*;

public class P2577_NumberOfNumber {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int A = Integer.parseInt(br.readLine());
            int B = Integer.parseInt(br.readLine());
            int C = Integer.parseInt(br.readLine());
            int N = A * B * C;

            int[] counts = new int[10];

            while (N > 0) {
                counts[N % 10]++;
                N /= 10;
            }

            for (int cnt : counts) {
                bw.write(cnt + "\n");
            }

            bw.flush();
        }
    }
}
