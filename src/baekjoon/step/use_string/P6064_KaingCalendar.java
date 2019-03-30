package baekjoon.step.use_string;

import java.io.*;
import java.util.StringTokenizer;

public class P6064_KaingCalendar {

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            StringBuilder sb = new StringBuilder();

            int T = Integer.parseInt(br.readLine());

            for (int t = 0; t < T; t++) {

                StringTokenizer st = new StringTokenizer(br.readLine());
                int M = Integer.parseInt(st.nextToken());
                int N = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                if (x < 1 || x > M || y < 1 || y > M) {
                    sb.append(-1);
                } else {

                }

                sb.append('\n');
            }

            bw.write(sb.toString());
            bw.flush();
        }
    }
}
