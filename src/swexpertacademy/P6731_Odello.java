package swexpertacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWefzFeq5P8DFAUh&categoryId=AWefzFeq5P8DFAUh&categoryType=CODE
 * <p>
 * 6731. 홍익이의 오델로 게임
 */

public class P6731_Odello {
    private static int T, N;
    private static boolean[][] board;
    private static boolean[][] checked;
    private static List<boolean[][]> cache;

    public static void main(String[] args) throws IOException {
        try (final BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int T = Integer.parseInt(br.readLine());

            for (int i = 1; i <= T; i++) {
                N = Integer.parseInt(br.readLine());
                board = new boolean[N][N];
                checked = new boolean[N][N];
                cache = new ArrayList<>();

                for (int j = 0; j < N; j++) {
                    StringBuilder sb = new StringBuilder(br.readLine());
                    for (int k = 0; k < N; k++) {
                        char c = sb.charAt(k);
                        board[j][k] = c == 'B';
                    }
                }

                // K 번째 요소 출력
                System.out.printf("#%d %d\n", i, solution(board, checked, 0));
            }
        }
    }

    public static int solution(boolean[][] b, boolean[][] c, int cnt) {
        for (boolean[][] temp : cache) {
            if (equals(temp, c)) {
                return Integer.MAX_VALUE;
            }
        }
        cache.add(c);
        boolean hasChanged = false;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (b[i][j] && !c[i][j]) {
                    hasChanged = true;

                    boolean[][] tempB = deepCopy(b);
                    boolean[][] tempC = deepCopy(c);

                    for (int a = 0; a < N; a++) {
                        tempB[a][j] = !tempB[a][j];
                        tempB[i][a] = !tempB[i][a];
                    }
                    tempB[i][j] = !tempB[i][j];
                    tempC[i][j] = true;
                    int tempResult = solution(tempB, tempC, cnt + 1);
                    if (min > tempResult) {
                        min = tempResult;
                    }
                }
            }
        }

        if (!hasChanged) {
            return cnt;
        } else {
            return min;
        }
    }

    public static boolean[][] deepCopy(boolean[][] original) {
        if (original == null) {
            return null;
        }

        final boolean[][] result = new boolean[original.length][];
        for (int i = 0; i < original.length; i++) {
            result[i] = Arrays.copyOf(original[i], original[i].length);
            // For Java versions prior to Java 6 use the next:
            // System.arraycopy(original[i], 0, result[i], 0, original[i].length);
        }
        return result;
    }

    private static boolean equals(boolean[][] b1, boolean[][] b2) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (b1[i][j] != b2[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}