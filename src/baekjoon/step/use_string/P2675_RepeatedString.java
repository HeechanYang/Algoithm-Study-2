package baekjoon.step.use_string;

import java.io.*;
import java.util.StringTokenizer;

public class P2675_RepeatedString {

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            StringBuilder sb = new StringBuilder();

            int T = Integer.parseInt(br.readLine());

            for (int t = 0; t < T; t++) {

                StringTokenizer st = new StringTokenizer(br.readLine());
                int R = Integer.parseInt(st.nextToken());
                char[] word = st.nextToken().toCharArray();

                for (char c : word) {
                    for (int i = 0; i < R; i++) {
                        sb.append(c);
                    }
                }
                sb.append('\n');
            }

            bw.write(sb.toString());
            bw.flush();
        }
    }
}
