package baekjoon.step.use_string;

import java.io.*;
import java.util.StringTokenizer;

public class P2908_Sangsoo {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            int changedA = 0;
            int changedB = 0;

            int multi = 100;

            for (int i = 0; i < 3; i++) {
                int a = A % 10;
                int b = B % 10;

                changedA+=a*multi;
                changedB+=b*multi;

                A /= 10;
                B /= 10;
                multi/=10;
            }

            bw.write(String.valueOf(Math.max(changedA, changedB)));
            bw.flush();
        }
    }
}
