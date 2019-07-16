package baekjoon.step.sort;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class P1181_SortWords {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringBuilder sb = new StringBuilder(10);

            int N = Integer.parseInt(br.readLine());
            List<Integer> numbers = new ArrayList<>();

            for (; N > 0; N /= 10) {
                numbers.add(N % 10);
            }

            Collections.sort(numbers, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2-o1;
                }
            });

            for (int num : numbers) {
                sb.append(num);
            }

            bw.write(sb.toString());
            bw.flush();
        }
    }
}
