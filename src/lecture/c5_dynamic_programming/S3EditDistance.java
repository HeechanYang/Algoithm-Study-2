package lecture.c5_dynamic_programming;

public class S3EditDistance {
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        String S = "strong", T = "stoneee";

        int m = S.length(), n = T.length();
        int[][] E = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            E[m][0] = i;
        }
        for (int j = 0; j <= n; j++) {
            E[0][n] = j;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int alpha = S.charAt(i-1) != T.charAt(j-1) ? 1 : 0;
                E[i][j] = min(E[i][j - 1] + 1, E[i - 1][j] + 1, E[i - 1][j - 1] + alpha);
            }
        }

        System.out.println("최소 편집 거리 : " + E[m][n]);
    }

    public static int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }
}
