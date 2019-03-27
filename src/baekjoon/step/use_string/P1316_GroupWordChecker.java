package baekjoon.step.use_string;

import java.io.*;

public class P1316_GroupWordChecker {

    private static final int NUMBER_OF_ALPHABET = 26;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int cnt = 0;

            int T = Integer.parseInt(br.readLine());

            for (int t = 0; t < T; t++) {
                boolean isGroupWord = true;

                boolean[] visited = new boolean[NUMBER_OF_ALPHABET];
                char[] word = br.readLine().toCharArray();

                char lastChar = ' ';

                for (char c : word) {
                    if (lastChar != c && visited[c - 'a']) {
                        isGroupWord = false;
                        break;
                    } else {
                        lastChar = c;
                        visited[c - 'a'] = true;
                    }
                }

                if (isGroupWord) {
                    cnt++;
                }
            }

            bw.write(String.valueOf(cnt));
            bw.flush();
        }
    }
}
