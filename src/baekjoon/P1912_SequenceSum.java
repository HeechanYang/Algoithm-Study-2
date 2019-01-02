package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 *
 */
public class P1912_SequenceSum {
    private static int N;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(br.readLine());
            int[] arr = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            int sum = arr[0];
            int max = sum;

            for (int i = 1; i < N; i++) {
                int num = arr[i];
                if (sum < 0) {
                    sum = num;
                } else {
                    sum += num;
                }

                max = Math.max(max,sum);
            }
            System.out.println(max);
        }
    }

}
