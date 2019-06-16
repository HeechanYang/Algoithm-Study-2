package lecture.c5_dynamic_programming;

public class S2OptimalOrder {
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        // 4개의 행렬 10x5, 5x20, 20x4, 4x30
        int[] C = {10, 5, 20, 4, 30};
        int N = C.length - 1;
        int[][] S = new int[N + 1][N + 1];
        int[][] T = new int[N + 1][N + 1];

        // 대각선 0으로 초기화
        for (int i = 0; i < N; i++) {
            S[i][i] = T[i][i] = 0;
        }

        // 대각선 반복
        for (int d = 1; d <= N - 1; d++) {
            for (int i = 1; i <= N - d; i++) {
                int j = d + i;
                S[i][j] = INF;

                for (int k = i; k <= j - 1; k++) {
                    int temp = S[i][k] + S[k + 1][j] + C[i - 1] * C[k] * C[j];
                    if (temp < S[i][j]) {
                        S[i][j] = temp;
                        T[i][j] = k;
                    }
                }
            }
        }

        System.out.println(S[1][N]);

        optimalOrder(T, 1, N);
    }

    private static void optimalOrder(int[][] T, int i, int j) {
        if (i == j) {
            System.out.print("M" + i);
        } else {
            System.out.print("(");
            optimalOrder(T, i, T[i][j]);
            optimalOrder(T, T[i][j] + 1, j);
            System.out.print(")");
        }
    }
}
