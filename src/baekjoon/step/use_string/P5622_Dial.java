package baekjoon.step.use_string;

import java.io.*;

public class P5622_Dial {

    private static final int NUMBER_OF_ALPHABET = 26;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int[] alphabetLocs = new int[NUMBER_OF_ALPHABET];
            alphabetLocs[0] = alphabetLocs[1] = alphabetLocs[2] = 2;
            alphabetLocs[3] = alphabetLocs[4] = alphabetLocs[5] = 3;
            alphabetLocs[6] = alphabetLocs[7] = alphabetLocs[8] = 4;
            alphabetLocs[9] = alphabetLocs[10] = alphabetLocs[11] = 5;
            alphabetLocs[12] = alphabetLocs[13] = alphabetLocs[14] = 6;
            alphabetLocs[15] = alphabetLocs[16] = alphabetLocs[17] = alphabetLocs[18] = 7;
            alphabetLocs[19] = alphabetLocs[20] = alphabetLocs[21] = 8;
            alphabetLocs[22] = alphabetLocs[23] = alphabetLocs[24] = alphabetLocs[25] = 9;

            char[] word = br.readLine().toCharArray();

            int result = word.length;

            for (char c : word) {
                result += alphabetLocs[c - 'A'];
            }

            bw.write(String.valueOf(result));
            bw.flush();
        }
    }
}
