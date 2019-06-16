package lecture.c5_dynamic_programming;

public class S4Knapsack {
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        int C = 10;
        int[] weights = {5, 4, 6, 3}, values = {10, 40, 30, 50};
        int N = weights.length;
        int[][] K = new int[N + 1][C + 1];

        // 첫 행, 열 초기화
        for (int i = 0; i <= N; i++)
            K[i][0] = 0;
        for (int i = 0; i <= C; i++)
            K[0][i] = 0;

        for (int i = 1; i <= N; i++) {
            for (int w = 1; w <= C; w++) {
                if (weights[i - 1] > w) {       // 물건 i의 무게가 임시 배낭의 용량을 초과하면
                    K[i][w] = K[i - 1][w];      // K[i][w] == K[i-1][w]
                } else {                        // 물건 i를 담을 경우와 담지 않을 경우 비교
                    int notInclude = K[i - 1][w];
                    int include = K[i - 1][w - weights[i - 1]] + values[i - 1];
                    K[i][w] = Math.max(include, notInclude);
                }
            }
        }

        System.out.println("최대 가치 : " + K[N][C]);
    }
}
