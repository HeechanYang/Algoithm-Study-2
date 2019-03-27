package baekjoon.step.use_string;

import java.io.*;

public class P10809_FindAlphabet {
    private static final int NUMBER_OF_ALPHABET = 26;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringBuilder sb = new StringBuilder(100);

            int[] alphabetLocs = new int[NUMBER_OF_ALPHABET];

            for (int i = 0; i < NUMBER_OF_ALPHABET; i++) {
                alphabetLocs[i] = -1;
            }

            char[] word = br.readLine().toCharArray();

            for (int i = 0; i < word.length; i++) {
                if (alphabetLocs[word[i]-'a'] == -1) {
                    alphabetLocs[word[i]-'a'] = i;
                }
            }

            for (int i = 0; i < NUMBER_OF_ALPHABET; i++) {
                sb.append(alphabetLocs[i]).append(' ');
            }

            bw.write(sb.toString());
            bw.flush();
        }
    }
}
