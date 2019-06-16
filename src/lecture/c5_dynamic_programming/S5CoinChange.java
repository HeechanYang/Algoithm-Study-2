package lecture.c5_dynamic_programming;

public class S5CoinChange {
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        int N = 110;
        int[] coins = {80, 50, 10};
        int K = coins.length;
        int[] C = new int[N + 1];

        // 첫 행 초기화
        C[0] = 0;
        for (int i = 1; i <= N; i++)
            C[i] = INF;

        for (int i = 1; i <= N; i++) {          // 거스름돈을 1원씩 증가시킴
            for (int j = 0; j < K; j++) {       // 각 동전들을 계산해봄
                int d = coins[j];
                if (d <= i && C[i] > C[i - d] + 1) {
                    C[i] = C[i - d] + 1;
                }
            }
        }

        System.out.println("동전 수 : " + C[N]);
    }
}
