package baekjoon.step.sort;

import java.io.*;
import java.util.*;

public class P2108_Statistics {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringBuilder sb = new StringBuilder(20);

            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[N];
            Map<Integer, Integer> map = new HashMap<>();

            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(br.readLine());
            }

            Arrays.sort(arr);

            int sum = 0;
            for (int num : arr) {
                sum += num;
                map.put(num, map.get(num) != null ? map.get(num) + 1 : 1);
            }

            int average;
            if (sum > 0) {
                average = (int) ((double) sum / (double) N + 0.5);
            } else {
                average = (int) ((double) sum / (double) N - 0.5);
            }

            int max = Integer.MIN_VALUE;
            for (int val : map.values()) {
                if (max < val) {
                    max = val;
                }
            }

            List<Integer> mostPopularNumbers = new ArrayList<>();
            for (int key : map.keySet()) {
                if (map.get(key) == max) {
                    mostPopularNumbers.add(key);
                }
            }

            Collections.sort(mostPopularNumbers);

            sb.append(average).append('\n');
            sb.append(arr[N / 2]).append('\n');
            if (mostPopularNumbers.size() == 1) {
                sb.append(mostPopularNumbers.get(0)).append('\n');
            } else {
                sb.append(mostPopularNumbers.get(1)).append('\n');
            }
            sb.append(arr[N - 1] - arr[0]);

            bw.write(sb.toString());
            bw.flush();
        }
    }
}
