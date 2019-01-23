package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/6549
 */
public class P6549_BiggestSquare_DevideAndConquer {
    private static int N;
    private static int[] heightArr;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))
        ) {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            StringBuilder sb = new StringBuilder();
            while (true) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                N = Integer.parseInt(st.nextToken());
                if (N == 0) {
                    bw.write(sb.toString());
                    bw.flush();
                    bw.close();
                    break;
                }
                heightArr = new int[N];

                for (int i = 0; i < N; i++) {
                    heightArr[i] = Integer.parseInt(st.nextToken());
                }

                sb.append(solution(0, N - 1)).append('\n');
            }
        }
    }

    private static long solution(int start, int end) {
        if (start == end) {
            return heightArr[start];
        } else {
            int mid = (start + end) / 2;
            long maxArea = Math.max(solution(start, mid), solution(mid + 1, end));

            int l = mid, r = mid + 1;
            long lessMidHeight = Math.min(heightArr[l], heightArr[r]);
            maxArea = Math.max(maxArea, lessMidHeight * 2);

            while (start < l || r < end) {
                if (l == start) {
                    r++;
                    lessMidHeight = Math.min(lessMidHeight, heightArr[r]);
                } else if (r == end) {
                    l--;
                    lessMidHeight = Math.min(lessMidHeight, heightArr[l]);
                } else if (heightArr[l - 1] >= heightArr[r + 1]) {
                    l--;
                    lessMidHeight = Math.min(lessMidHeight, heightArr[l]);
                } else {
                    r++;
                    lessMidHeight = Math.min(lessMidHeight, heightArr[r]);
                }
                maxArea = Math.max(maxArea, lessMidHeight * (r - l + 1));
            }
            return maxArea;
        }
    }
}