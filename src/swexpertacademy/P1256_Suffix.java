package swexpertacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15StKqAQkCFAYD&categoryId=AV15StKqAQkCFAYD&categoryType=CODE&&&
 * <p>
 * 1256. [S/W 문제해결 응용] 5. 접미사
 */

public class P1256_Suffix {
    public static int T, N;
    public static StringBuilder sentence;
    public static String[] suffixArr;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            T = Integer.parseInt(br.readLine());

            // Loop T times
            for (int t = 1; t <= T; t++) {
                N = Integer.parseInt(br.readLine());
                sentence = new StringBuilder(br.readLine());

                suffixArr = new String[sentence.length()];
                for (int i = 0; i < sentence.length(); i++) {
                    suffixArr[i] = sentence.substring(i);
                }

                Arrays.sort(suffixArr);

                // Print result
                System.out.printf("#%d %s\n", t, suffixArr[N - 1]);
            }
        }
    }
}