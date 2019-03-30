package baekjoon.step.FindRule;

import java.io.*;

public class P2292_HouseOfBee {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int N = Integer.parseInt(br.readLine());
            int n;
            for (n = 1; n * (n + 1) / 2 * 6 + 1 < N; n++) ;

            n++;

            if (N == 1) {
                n = 1;
            }

            bw.write(String.valueOf(n));
            bw.flush();
        }
    }
}
