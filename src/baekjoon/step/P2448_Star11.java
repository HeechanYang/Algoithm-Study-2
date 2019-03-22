package baekjoon.step;

import java.io.*;

public class P2448_Star11 {
    private static char CHAR_STAR = '*';
    private static char CHAR_SPACE = ' ';

    private static char[][] board;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(br.readLine());
            StringBuilder sb = new StringBuilder((2 * n - 1) * n + n);
            board = new char[n][2 * n - 1];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < 2 * n - 1; j++) {
                    board[i][j] = CHAR_SPACE;
                }
            }

            drawStar(0, n - 1, n);

            for (int i = 0; i < n; i++) {
                sb.append(board[i]).append('\n');
            }
            bw.write(sb.toString());
            bw.flush();
        }
    }

    private static void drawStar(int x, int y, int n) {
        if (n == 3) {
            board[x][y] = board[x + 1][y - 1] = board[x + 1][y + 1] = board[x + 2][y - 2]
                    = board[x + 2][y - 1] = board[x + 2][y] = board[x + 2][y + 1] = board[x + 2][y + 2]
                    = CHAR_STAR;
            return;
        }
        drawStar(x, y, n / 2);
        drawStar(x + n / 2, y - n / 2, n / 2);
        drawStar(x + n / 2, y + n / 2, n / 2);
    }
}
