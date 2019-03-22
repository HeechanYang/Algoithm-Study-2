package baekjoon.step;

import java.io.*;

public class P1065_HanNumber {

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringBuilder sb = new StringBuilder();

            int N = Integer.parseInt(br.readLine());
            int cnt = 0;

            for (int i = 1; i <= N; i++) {
                if (isHanNumber(i)) {
                    cnt++;
                }
            }

            bw.write(String.valueOf(cnt));
            bw.flush();
        }
    }

    private static boolean isHanNumber(int num) {
        int cur = 0;
        int gap = 0;
        boolean isGapInit = false;

        int before = num % 10;
        num /= 10;

        while (num > 0) {
            cur = num % 10;

            if (!isGapInit) {
                gap = cur - before;
                isGapInit = true;
            }

            if (cur - before != gap) {
                return false;
            }

            num /= 10;
            before = cur;
        }

        return true;
    }
}
