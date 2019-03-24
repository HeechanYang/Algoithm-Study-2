package baekjoon.step;

import java.io.*;
import java.util.StringTokenizer;

public class P2920_Scale {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int first = Integer.parseInt(st.nextToken());
            if (first == 1) {
                for (int i = 2; i <= 8; i++) {
                    if (Integer.parseInt(st.nextToken()) != i) {
                        System.out.println("mixed");
                        return;
                    }
                }
                System.out.println("ascending");
            } else if (first == 8) {
                for (int i = 7; i >= 1; i--) {
                    if (Integer.parseInt(st.nextToken()) != i) {
                        System.out.println("mixed");
                        return;
                    }
                }
                System.out.println("descending");
            } else {
                System.out.println("mixed");
            }
        }
    }
}
