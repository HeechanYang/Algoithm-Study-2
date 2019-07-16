package googlecodejam.Round1;

import java.io.*;
import java.util.StringTokenizer;

public class Problem3 {
    private static int N, K;
    private static int[] C, D;

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int T = Integer.parseInt(br.readLine());

            StringBuilder sb = new StringBuilder();

            for (int t = 1; t <= T; t++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                N = Integer.parseInt(st.nextToken());
                K = Integer.parseInt(st.nextToken());

                C = new int[N];
                D = new int[N];

                int cnt = 0;

                st = new StringTokenizer(br.readLine());
                for (int i = 0; i < N; i++) {
                    C[i] = Integer.parseInt(st.nextToken());
                }

                st = new StringTokenizer(br.readLine());
                for (int i = 0; i < N; i++) {
                    D[i] = Integer.parseInt(st.nextToken());
                }

                for (int i = 0; i < N; i++) {
                    int maxC = -1;
                    int maxD = -1;
                    for (int j = i; j < N; j++) {
                        maxC = maxC < C[j] ? C[j] : maxC;
                        maxD = maxD < D[j] ? D[j] : maxD;

                        int diff = Math.abs(maxC - maxD);
                        if (diff <= K) {
                            cnt++;
                        }
                    }
                }

                sb.append("Case #").append(t).append(": ");
                sb.append(cnt).append('\n');
            }
            bw.write(sb.toString());
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
