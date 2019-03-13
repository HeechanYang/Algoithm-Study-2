package baekjoon;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/2504
 * 2504. 괄호의 값
 */

public class P10972_NextSequence {

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            StringBuilder sb = new StringBuilder();

            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[N];

            boolean can = false;

            int mostIdx = 0;
            int newMostIdx = 0;

            StringTokenizer st = new StringTokenizer(br.readLine());

            arr[0] = Integer.parseInt(st.nextToken());
            for (int i = 1; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                if (arr[i] > arr[i - 1]) {
                    mostIdx = i - 1;
                    newMostIdx = i;
                    can = true;
                } else {
                    if (arr[mostIdx] < arr[i] && arr[i] < arr[newMostIdx]) {
                        newMostIdx = i;
                    }
                }
            }

            if (!can) {
                bw.write(String.valueOf(-1));
            } else {
                //swap
                int temp = arr[mostIdx];
                arr[mostIdx] = arr[newMostIdx];
                arr[newMostIdx] = temp;

                int[] tempArr = Arrays.copyOfRange(arr, mostIdx + 1, arr.length);

                Arrays.sort(tempArr);

                for (int i = 0; i <= mostIdx; i++) {
                    sb.append(arr[i]).append(' ');
                }

                for (int i = 0; i < tempArr.length; i++) {
                    sb.append(tempArr[i]).append(' ');
                }

                bw.write(sb.toString());
            }
            bw.flush();
        }
    }

}