package baekjoon.step.FindRule;

import java.io.*;
import java.util.StringTokenizer;

public class P10250_ACMHotel {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int T = Integer.parseInt(br.readLine());

            StringBuilder sb = new StringBuilder();

            for (int t = 0; t < T; t++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                int H = Integer.parseInt(st.nextToken());
                int W = Integer.parseInt(st.nextToken());
                int N = Integer.parseInt(st.nextToken());

                int ho = (N - 1) / H + 1;
                int floor = ((N - 1) % H + 1) * 100;

                sb.append(floor + ho).append('\n');
            }

            bw.write(sb.toString());
            bw.flush();
        }
    }
}