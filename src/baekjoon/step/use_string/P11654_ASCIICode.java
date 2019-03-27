package baekjoon.step.use_string;

import java.io.*;

public class P11654_ASCIICode {

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringBuilder sb = new StringBuilder();

            int N = br.readLine().charAt(0);

            bw.write(String.valueOf(N));
            bw.flush();
        }
    }
}
