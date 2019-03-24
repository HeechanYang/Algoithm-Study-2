package baekjoon.step;

import java.io.*;

public class P10039_AverageScore {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int scoreSum = 0;
            for (int i = 0; i < 5; i++) {
                int score = Integer.parseInt(br.readLine());
                scoreSum += score >= 40 ? score : 40;
            }
            bw.write(String.valueOf(scoreSum / 5));
            bw.flush();
        }
    }
}
