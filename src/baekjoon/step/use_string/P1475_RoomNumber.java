package baekjoon.step.use_string;

import java.io.*;

public class P1475_RoomNumber {

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringBuilder sb = new StringBuilder();

            char[] roomNumber = br.readLine().toCharArray();

            int[] numberCounts = new int[9];

            for (char c : roomNumber) {
                if (c == '9') c = '6';

                numberCounts[c - '0']++;
            }

            numberCounts[6] = (numberCounts[6] + 1) / 2;

            int max = 0;
            for (int cnt : numberCounts) {
                max = Math.max(max, cnt);
            }

            bw.write(String.valueOf(max));
            bw.flush();
        }
    }
}
