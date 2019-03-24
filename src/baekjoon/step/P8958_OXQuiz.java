package baekjoon.step;

import java.io.*;

public class P8958_OXQuiz {
    public static char CHAR_O = 'O';
    public static char CHAR_X = 'X';

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int T = Integer.parseInt(br.readLine());
            StringBuilder sb = new StringBuilder();

            for (int t = 0; t < T; t++) {
                String str = br.readLine();

                int score = 0;
                int combo = 0;

                for (int i = 0; i < str.length(); i++) {
                    char c = str.charAt(i);

                    if (c == CHAR_O) {
                        combo++;
                        score += combo;
                    } else {
                        combo = 0;
                    }
                }

                sb.append(score).append('\n');
            }
            bw.write(sb.toString());
            bw.flush();
        }
    }
}
