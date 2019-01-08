package swexpertacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15StKqAQkCFAYD&categoryId=AV15StKqAQkCFAYD&categoryType=CODE&&&
 * <p>
 * 1256. [S/W 문제해결 응용] 5. 접미사
 */

public class P1256_Suffix {
    public static int T, N;
    public static StringBuilder sentence;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            T = Integer.parseInt(br.readLine());

            // Loop T times
            for (int t = 1; t <= T; t++) {
                N = Integer.parseInt(br.readLine());
                sentence = new StringBuilder(br.readLine());
                PriorityQueue<String> pq = new PriorityQueue<>();
                for (int i = 0; i < sentence.length(); i++) {
                    pq.add(sentence.substring(i));
                }
                for (int i = 0; i < N - 1; i++, pq.poll()) ;

                // Print result
                System.out.printf("#%d %s\n", t, pq.poll());
            }
        }
    }
}