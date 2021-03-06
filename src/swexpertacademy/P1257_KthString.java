package swexpertacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15StKqAQkCFAYD&categoryId=AV15StKqAQkCFAYD&categoryType=CODE&&&
 * <p>
 * 1257. [S/W 문제해결 응용] 6. K번째 문자열
 */

public class P1257_KthString {
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
                int length = sentence.length();

                Set<String> set = new HashSet<>();

                String result;

                if (N > length * (length + 1) / 2) {
                    result = "none";
                } else {
                    int cnt = 0;
                    for (int i = 0; i < length; i++) {
                        for (int j = i + 1; j <= length; j++) {
                            String s = sentence.substring(i, j);
                            set.add(s);
                        }
                    }
                    PriorityQueue<String> pq = new PriorityQueue<>(set);

                    if (pq.size() < N) {
                        result = "none";
                    } else {
                        for (int i = 0; i < N - 1; i++, pq.poll()) ;
                        result = pq.poll();
                    }
                }

                // Print result
                System.out.printf("#%d %s\n", t, result);
            }
        }
    }
}