package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/6549
 */
public class P6549_BiggestSquare {
    private static int N;
    private static int[] heightArr;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                N = Integer.parseInt(st.nextToken());
                if (N == 0) {
                    return;
                }
                heightArr = new int[N];

                for (int i = 0; i < N; i++) {
                    heightArr[i] = Integer.parseInt(st.nextToken());
                }

                long maxArea = 0;

                for (int i = 0; i < N; i++) {
                    int thisHeight = heightArr[i];
                    long thisArea = 0;

                    //왼쪽으로
                    for (int j = i; j >= 0; j--) {
                        int tempHeight = heightArr[j];
                        if (tempHeight >= thisHeight) {
                            thisArea += thisHeight;
                        } else {
                            break;
                        }
                    }
                    // 오른쪽으로
                    for (int j = i + 1; j < N; j++) {
                        int tempHeight = heightArr[j];
                        if (tempHeight >= thisHeight) {
                            thisArea += thisHeight;
                        } else {
                            break;
                        }
                    }

                    maxArea = Math.max(maxArea, thisArea);
                }

                System.out.println(maxArea);
            }
        }
    }
}
