package facebook_hackercup;

import java.io.*;

public class Problem2 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int T = Integer.parseInt(br.readLine());
            StringBuilder sb = new StringBuilder();

            for (int i = 1; i <= T; i++) {
                sb.append(String.format("Case #%d: %c\n", i, isPossible(br.readLine())));
            }

            bw.write(sb.toString());
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static char isPossible(String s) {
        char[] arr = s.substring(1).toCharArray();

        int bCount = 0;
        int emptyCount = 0;

        for (char c : arr) {
            if (c == 'B') {
                bCount++;
            } else {
                emptyCount++;
            }
        }

        if (emptyCount == 0) {
            return 'N';
        } else if (bCount == 1 && emptyCount == 1) {
            return 'Y';
        } else if (bCount > 1) {
            return 'Y';
        } else {
            return 'N';
        }
    }
}
