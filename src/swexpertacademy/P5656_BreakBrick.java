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

public class P4014_ConstructAirstrip_FAIL {
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
            for (int j = 0; j < N - 1; j++) {
                int thisHeight = land[i][j];
                int thisRowHeight = land[j][i];
                int nextHeight = land[i][j + 1];
                int nextRowHeight = land[j + 1][i];

                //높아지면 +, 낮아지면 -
                landCompareX[i][j] = nextHeight - thisHeight;
                landCompareY[i][j] = nextRowHeight - thisRowHeight;
            }
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            // i번째 가로 활주로 체크
            if (isEnable(X, N, landCompareX[i])) {
                cnt++;
            }
            // i번째 세로 활주로 체크
            if (isEnable(X, N, landCompareY[i])) {
                cnt++;
            }
        }

        return cnt;
    }

    // 해당 라인에 활주로 건설 가능 여부를 반환하는 메서드
    private static boolean isEnable(int X, int N, int[] lineCompare) {
        int to = 0;

        for (int j = 0; j < N - 1; j++) {
            // j번 칸과 j+1번 칸사이의 높이차
            int value = lineCompare[j];

            if (Math.abs(value) > 1) {          // 높이차가 1보다 크면 false
                System.out.println("default");
                return false;
            } else if (value == 1) {    // 높아지는 곳
                if (j < X - 1) {                // 높아지는데 왼쪽에 경사로를 놓 공간이 없다면 false
                    System.out.println("11111");
                    return false;
                } else if (!isContinuousValueDuring(lineCompare, value, j - X, X)) {    // 높아지고 land를 초과하지 않는데 왼쪽이 X칸만큼 평평하지 않다면 false
                    System.out.println("11111");
                    return false;
                } else if (j < to + X - 1) {    // 높아지는데 왼쪽의 경사로에 의해 경사로를 놓을 공간이 없다면 false
                    System.out.println("11111");
                    return false;
                }
            } else if (value == -1) {   // 낮아지는 곳
                if (j > N - X - 1) {            // 낮아지는데 오른 쪽에 경사로를 놓으면 land를 초과한다면 false
                    System.out.println("-1-1-1-1");
                    return false;
                } else if (!isContinuousValueDuring(lineCompare, value, j, X)) {        // 낮아지고 land를 초과하지 않는데 오른쪽이 X칸만큼 평평하지 않다면 false
                    System.out.println("-1-1-1-1");
                    return false;
                }

                // to번째 칸까지 경사로가 차지
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
