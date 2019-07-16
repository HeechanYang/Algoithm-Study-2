package baekjoon.step.PrimeNumber;

import java.io.*;
import java.util.Arrays;

public class P1978_FindPrimeNumber {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[N];

            boolean[] isPrime = new boolean[1001];
            Arrays.fill(isPrime, true);

//            for(int i=0;)
//
//            StringTokenizer st = new StringTokenizer(br.readLine());
//            for (int i = 0; i < N; i++) {
//                arr[i] = Integer.parseInt(st.nextToken());
//            }

            bw.flush();
        }
    }
}
