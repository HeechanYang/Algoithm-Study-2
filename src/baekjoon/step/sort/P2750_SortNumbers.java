package baekjoon.step.sort;

import java.io.*;
import java.util.Arrays;

public class P2750_SortNumbers {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringBuilder sb = new StringBuilder(5000000);

            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[N];

            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(br.readLine());
            }

            Arrays.sort(arr);

            for(int num : arr){
                sb.append(num).append('\n');
            }

            bw.write(sb.toString());
            bw.flush();
        }
    }
}
