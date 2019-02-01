package codeforces;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * https://codeforces.com/contest/1108/problem/B
 * B. Divisors of Two Integers
 * <p>
 * 한 양의 정수 배열(arr)이 주어지면, 그 배열 안의 요소들을 약수로 갖는 두 양의 정수(A, B)를 출력하는 문제
 * - 양의 정수 배열은 정렬되어있지 않아도 됨
 * - arr = A의 약수들 + B의 약수들
 * ex) [1,2,4,1,2,3,6]  ->  4(1, 2, 4), 6(1, 2, 3, 6)
 * <p>
 * [해결방법]
 * - 일단 A(or B)는 그 배열의 가장 큰 수이다.
 * - 그렇다면 B(or A)는 그 배열에서 B의 약수들을 뺀 배열에서 가장 큰 수이다.
 * - 뒤에서부터 지우고, 안지워진 첫 수가 B!
 * - 정렬만 하면 됨.
 * <p>
 * Arrays.sort(int[] arr) 사용. DualPivotQuicksort!
 * O(NlogN)
 */

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