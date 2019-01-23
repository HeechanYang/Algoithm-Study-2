package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/6549
 */
public class P6549_BiggestSquare {

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringBuilder sb = new StringBuilder();
            while (true) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int N = Integer.parseInt(st.nextToken());

                if (N == 0) {
                    System.out.println(sb);
                    break;
                } else {
                    int[] heightArr = new int[N];
                    long maxHeight = 0;

                    for (int i = 0; i < N; i++) {
                        heightArr[i] = Integer.parseInt(st.nextToken());
                        maxHeight = Math.max(maxHeight, heightArr[i]);
                    }

                    long maxArea = 0;

                    for (int i = 0; i < N; i++) {
                        long thisHeight = heightArr[i];
                        long tempMaxArea = N * thisHeight;

                        if (tempMaxArea < maxHeight
                                || tempMaxArea < maxArea) {
                            continue;
                        }

                        int l, r;
                        //왼쪽으로
                        for (l = i; l > -1 && (heightArr[l] >= thisHeight); l--) ;
                        // 오른쪽으로
                        for (r = i; r < N && (heightArr[r] >= thisHeight); r++) ;

                        int width = r - l - 1;
                        long thisArea = width * thisHeight;

                        maxArea = Math.max(maxArea, thisArea);
                    }

                    sb.append(maxArea).append('\n');
                }
            }
        }
    }
}