package baekjoon.step.use_string;

import java.io.*;

public class P1157_StudyingWord {

    private static final int NUMBER_OF_ALPHABET = 26;
    private static final char CHAR_A = 65;
    private static final char CHAR_Z = 90;
    private static final char CHAR_a = 97;
    private static final char CHAR_z = 122;


    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int[] alphabetCounts = new int[NUMBER_OF_ALPHABET];

            char[] word = br.readLine().toCharArray();

            for (char c : word) {
                if (CHAR_A <= c && c <= CHAR_Z) {
                    alphabetCounts[c - CHAR_A]++;
                }
                if (CHAR_a <= c && c <= CHAR_z) {
                    alphabetCounts[c - CHAR_a]++;
                }
            }

            int max = -1;
            char c = ' ';
            boolean duplicated = false;
            for (int i = 0; i < NUMBER_OF_ALPHABET; i++) {
                if (alphabetCounts[i] > max) {
                    duplicated = false;

                    c = (char) (i + CHAR_A);

                    max = alphabetCounts[i];
                } else if (alphabetCounts[i] == max) {
                    duplicated = true;
                }
            }

            if (duplicated) {
                bw.write('?');
            } else {
                bw.write(String.valueOf(c));
            }
            bw.flush();
        }
    }
}
