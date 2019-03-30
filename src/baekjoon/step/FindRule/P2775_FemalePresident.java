package baekjoon.step.FindRule;

import java.io.*;

public class P2775_FemalePresident {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int T = Integer.parseInt(br.readLine());

            StringBuilder sb = new StringBuilder();

            for (int t = 0; t < T; t++) {
                int k = Integer.parseInt(br.readLine());
                int n = Integer.parseInt(br.readLine());

                int[][] apartment = new int[k + 1][n];


                for (int i = 0; i < n; i++) {
                    apartment[0][i] = i + 1;
                }

                for (int i = 1; i <= k; i++) {
                    apartment[i][0] = 1;

                    for (int j = 1; j < n; j++) {
                        apartment[i][j] = apartment[i][j - 1] + apartment[i - 1][j];
                    }
                }

                sb.append(apartment[k][n - 1]).append('\n');
            }

            bw.write(sb.toString());
            bw.flush();
        }
    }
}