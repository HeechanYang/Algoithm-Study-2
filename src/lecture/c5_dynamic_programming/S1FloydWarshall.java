package lecture.c5_dynamic_programming;

public class S1FloydWarshall {
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        int N = 10;
        int[][] matrix = new int[N][N];
        int[][] paths = new int[N][N];

        // 전부 INF로 초기화
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                matrix[i][j] = INF;
                paths[i][j] = j;
            }
        }

        // 주어진 값 초기화
        // ....

        // 전부 INF로 초기화
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(matrix[i][k] + matrix[k][j] < matrix[i][j]){
                        matrix[i][j] = matrix[i][k] + matrix[k][j];
                        paths[i][j] = paths[i][k];
                    }
                }
            }
        }
    }
}
