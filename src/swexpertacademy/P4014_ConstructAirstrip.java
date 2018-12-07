package swexpertacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWIeW7FakkUDFAVH&
 * <p>
 * 4014. [모의 SW 역량테스트] 활주로 건설
 */

public class P4014_ConstructAirstrip {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int T = Integer.parseInt(br.readLine());

            // Loop T times
            for (int i = 0; i < T; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                int N = Integer.parseInt(st.nextToken());
                int X = Integer.parseInt(st.nextToken());

                int[][] land = new int[N][N];

                for (int j = 0; j < N; j++) {
                    String s = br.readLine();
                    st = new StringTokenizer(s);
                    for (int k = 0; k < N; k++) {
                        land[j][k] = Integer.parseInt(st.nextToken());
                    }
                }


                // Print result
                System.out.printf("#%d %d\n", (i + 1), solution(land, X));
            }
        }
    }

    private static int solution(int[][] land, int X) {
        int N = land.length;

        int[][] landCompareX = new int[N][N - 1];
        int[][] landCompareY = new int[N][N - 1];

        for (int i = 0; i < N; i++) {
            String compareXStr = "";
            String compareYStr = "";
            for (int j = 0; j < N - 1; j++) {
                int thisHeight = land[i][j];
                int thisRowHeight = land[j][i];
                int nextHeight = land[i][j + 1];
                int nextRowHeight = land[j + 1][i];

                //높아지면 +, 낮아지면 -
                landCompareX[i][j] = nextHeight - thisHeight;
                landCompareY[i][j] = nextRowHeight - thisRowHeight;
                compareXStr += landCompareX[i][j] + " ";
                compareYStr += landCompareY[i][j] + " ";
            }
            System.out.println(compareXStr);
            System.out.println(compareYStr);
            System.out.println();
        }

        int cnt = 0;

        for (int i = 0; i < N; i++) {
            System.out.println("====="+i+"=====");
            // 가로 활주로
            if (isEnable(X, N, landCompareX[i])) {
                cnt++;
            }
            // 세로 활주로
            if (isEnable(X, N, landCompareY[i])) {
                cnt++;
            }
        }

        return cnt;
    }

    private static boolean isEnable(int X, int N, int[] lineCompare) {
        int to = 0;

        for (int j = 0; j < N - 1; j++) {
            int value = lineCompare[j];

            if (Math.abs(value) > 1) {
                System.out.println("Math.abs(value) > 1");
                return false;
            } else if (value == 1) {    // 높아지는 곳 (왼쪽 X 칸 체크)
                if (j < X - 1) {
                    System.out.println("j < X - 1");
                    return false;
                } else if (!isContinuousValueDuring(lineCompare, value, j - X, X)) {
                    System.out.println("!isContinuousValueDuring(lineCompare, value, j - X, X)");
                    return false;
                } else if (j < to + X - 1) {
                    System.out.println("j < to + X - 1");
                    return false;
                }
            } else if (value == -1) { // 낮아지는 곳 (오른쪽 X 칸 체크)
                if (j > N - X - 1) {
                    System.out.println("j > N - X - 1");
                    return false;
                } else if (!isContinuousValueDuring(lineCompare, value, j, X)) {
                    System.out.println("!isContinuousValueDuring(lineCompare, value, j, X)");
                    return false;
                }
                to = j + X;
            }
        }
        System.out.println("true");
        return true;
    }

    private static boolean isContinuousValueDuring(int[] lineCompare, int value, int startIdx, int X) {
        for (int i = startIdx + 1; i < startIdx + X; i++) {
            if (lineCompare[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
