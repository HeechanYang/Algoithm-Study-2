package swexpertacademy;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class P2819_AdditionOfNumbers {
    private static final int BOARD_SIZE = 4;
    private static final int MAX_NUM = 9999999;
    private static final int MOVE_CNT = 6;
    private static int[] dx = new int[]{0, 1, 0, -1};
    private static int[] dy = new int[]{1, 0, -1, 0};

    private static int[][] board = new int[BOARD_SIZE][BOARD_SIZE];
    private static Set<Integer> hashSet = new HashSet<>();

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int T = Integer.parseInt(br.readLine());
            StringBuilder sb = new StringBuilder();

            for (int t = 1; t <= T; t++) {
                hashSet.clear();

                for (int i = 0; i < BOARD_SIZE; i++) {
                    StringTokenizer st = new StringTokenizer(br.readLine());
                    for (int j = 0; j < BOARD_SIZE; j++) {
                        board[i][j] = Integer.parseInt(st.nextToken());
                    }
                }

                for (int i = 0; i < BOARD_SIZE; i++) {
                    for (int j = 0; j < BOARD_SIZE; j++) {
                        recursive(j, i, 0, 0);
                    }
                }

                sb.append(String.format("#%d %d\n", t, hashSet.size()));
            }

            bw.write(sb.toString());
            bw.flush();
        }
    }

    private static void recursive(int x, int y, int depth, int num) {
        num *= 10;
        num += board[y][x];

        if (depth == MOVE_CNT) {
            hashSet.add(num);
            return;
        }

        depth++;

        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if (isInBound(nextX, nextY)) {
                recursive(nextX, nextY, depth, num);
            }
        }

    }

    private static boolean isInBound(int x, int y) {
        return x >= 0 && x < BOARD_SIZE && y >= 0 && y < BOARD_SIZE;
    }
}
