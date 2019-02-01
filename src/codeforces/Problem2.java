package codeforces;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem2 {
    private static int N;
    private static int A, B;
    private static int[] divisorArr;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            StringBuilder sb = new StringBuilder();

            N = Integer.parseInt(br.readLine());
            divisorArr = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());


            for (int i = 0; i < N; i++) {
                divisorArr[i] = Integer.parseInt(st.nextToken());
            }

            solution();

            bw.write(A + " " + B);
            bw.flush();
            bw.close();
        }
    }

    private static void solution() {
        Arrays.sort(divisorArr);

        A = divisorArr[N - 1];

        B = 0;

        for (int i = 0; i < N - 1; i++) {
            int thisDivisor = divisorArr[i];
            int nextDivisor = divisorArr[i + 1];

            // 다음 수와 같다면 공약수라는 뜻!
            if (thisDivisor == nextDivisor) {
                B = thisDivisor;
                continue;
            }

            // A의 약수가 아니라면 B의 약수!
            if (A % thisDivisor != 0) {
                B = thisDivisor;
            }
        }
    }
}