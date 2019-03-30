package baekjoon.step.FindRule;

import java.io.*;

public class P1193_FindFraction {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int N = Integer.parseInt(br.readLine());

            int result = 0;
            int temp = 0;

            while (result < N) {
                result += ++temp;
            }

            String s = "";

            int mod = N - (temp - 1) * temp / 2;

            int a, b;

            if (temp % 2 == 1) {
                a = temp + 1 - mod;
                b = mod;
            } else {
                a = mod;
                b = temp + 1 - mod;
            }

            bw.write(a + "/" + b);
            bw.flush();
        }
    }
}