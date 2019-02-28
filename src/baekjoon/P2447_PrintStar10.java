package baekjoon;

import java.io.*;

/**
 * https://www.acmicpc.net/problem/2447
 * 2447. 별찍기 - 10
 */

public class P2447_PrintStar10 {

    private static char[][] board;
    private static int exp;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            StringBuilder sb = new StringBuilder();

            int N = Integer.parseInt(br.readLine());
            board = new char[N][N];

            for (exp = 0; Math.pow(3, exp) != N; exp++) ;

            board[0][0] = '*';

            recursive(1);

            for (int i = 0; i < N; i++) {
                sb.append(board[i]).append('\n');
            }

            // 해결
            bw.write(sb.toString());
            bw.flush();
        }
    }

    private static void recursive(int level) {
        int len = (int) Math.pow(3, level);
        int beforeLen = len / 3;

        if (level <= exp) {

            for (int i = 0; i < len; i += beforeLen) {
                for (int j = 0; j < len; j += beforeLen) {

                    if (i != beforeLen || j != beforeLen) {

                        for (int x = 0; x < beforeLen; x++) {
                            for (int y = 0; y < beforeLen; y++) {
                                board[j + y][i + x] = board[y][x];
                            }
                        }
                    } else {

                        for (int x = 0; x < beforeLen; x++) {
                            for (int y = 0; y < beforeLen; y++) {
                                board[j + y][i + x] = ' ';
                            }
                        }
                    }
                }
            }

            recursive(level + 1);
        }
    }
}