package baekjoon;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(br.readLine());

            int result = -1;

            for (int i = 0, a = n / 5; i < 3 && a>=0; i++, a--) {
                int temp = n - a * 5;
                if (temp % 3 == 0) {
                    result = a + temp / 3;
                    break;
                }
            }

            bw.write(String.valueOf(result));
            bw.flush();
        }
    }
}