package algorithmjobs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ProblemC {
    private static final int DIRECTION_CLOCK = 1;
    private static final int DIRECTION_REVERSE = 2;

    private static int[][] board;
    private static boolean[][] visit;
    private static int N, M, X, Y;
    private static int K, L;
    private static int[] rotationArr;
    private static Operation[] operationArr;
    private static int[][] DIRECTION = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            // 입력 시작
            int T = Integer.parseInt(br.readLine());
            for (int i = 0; i < T; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                N = Integer.parseInt(st.nextToken());
                M = Integer.parseInt(st.nextToken());
                X = Integer.parseInt(st.nextToken()) - 1;
                Y = Integer.parseInt(st.nextToken()) - 1;

                board = new int[M][N];
                visit = new boolean[M][N];

                for (int j = 0; j < M; j++) {
                    st = new StringTokenizer(br.readLine());
                    for (int k = 0; k < N; k++) {
                        board[j][k] = Integer.parseInt(st.nextToken());
                    }
                }

                K = Integer.parseInt(br.readLine());
                rotationArr = new int[K];

                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < K; j++) {
                    rotationArr[j] = Integer.parseInt(st.nextToken());
                }

                L = Integer.parseInt(br.readLine());
                operationArr = new Operation[L];

                for (int j = 0; j < L; j++) {
                    st = new StringTokenizer(br.readLine());
                    char direction = st.nextToken().charAt(0);
                    int rotateDirection = Integer.parseInt(st.nextToken());
                    int rotateCount = Integer.parseInt(st.nextToken());

                    operationArr[j] = new Operation(direction, rotateDirection, rotateCount);
                }
                // 입력 끝

                System.out.printf("#%d %d %d %d\n", i + 1, solution(), X + 1, Y + 1);
            }
        }
    }

    private static int solution() {
        int score = board[Y][X];
        visit[Y][X] = true;
        int curRotateLocation = 0;

        for (Operation operation : operationArr) {
            // 방향 설정
            int curDirectionIdx = 0;
            switch (operation.direction) {
                case 'E':
                    curDirectionIdx = 0;
                    break;
                case 'W':
                    curDirectionIdx = 1;
                    break;
                case 'S':
                    curDirectionIdx = 2;
                    break;
                case 'N':
                    curDirectionIdx = 3;
                    break;
            }
            int[] curDirection = DIRECTION[curDirectionIdx];

            // 돌림판 돌려서 이동거리 설정
            int rotateCount = operation.rotateCount
                    * (operation.rotateDirection == DIRECTION_CLOCK ? -1 : 1);
            curRotateLocation += rotateCount;
            while (curRotateLocation < 0) {
                curRotateLocation += K;
            }
            curRotateLocation %= K;
            int distance = rotationArr[curRotateLocation];

            // 이동하면서 Score 계산
            for (int i = 0; i < distance; i++) {
                int nextX = X + curDirection[0];
                int nextY = Y + curDirection[1];

                // 이동할 수 있다면
                if (!(nextX < 0 || nextY < 0 || nextX >= N || nextY >= M)) {
                    // 장애물이 아니라면
                    if (board[nextY][nextX] != -1) {
                        // 이동
                        X = nextX;
                        Y = nextY;

                        if (!visit[Y][X]) {
                            visit[Y][X] = true;
                            score += board[Y][X];
                        }
                    }
                }
            }
        }

        return score;
    }

    static class Operation {
        private final char direction;
        private final int rotateDirection;
        private final int rotateCount;

        public Operation(char direction, int rotateDirection, int rotateCount) {
            this.direction = direction;
            this.rotateDirection = rotateDirection;
            this.rotateCount = rotateCount;
        }
    }
}