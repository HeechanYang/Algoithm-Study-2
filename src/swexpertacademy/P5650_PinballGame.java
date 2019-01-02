package swexpertacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXRF8s6ezEDFAUo
 * <p>
 * 5650. [모의 SW 역량테스트] 핀볼 게임
 */

public class P5650_PinballGame {
    public static boolean isEnd = false;
    public static int maxScore = 0;
    public static Coord[] DIRECTION_TYPE = new Coord[]{new Coord(0, -1), new Coord(0, 1), new Coord(-1, 0), new Coord(1, 0)};

    public static void main(String[] args) throws IOException {
        try (final BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int T = Integer.parseInt(br.readLine());

            // Each Test cases
            for (int t = 1; t <= T; t++) {
                int N = Integer.parseInt(br.readLine());

                maxScore = 0;


                // Init board
                int[][] board = new int[N][];
                for (int i = 0; i < N; i++) {
                    board[i] = new int[N];
                    StringTokenizer st = new StringTokenizer(br.readLine());
                    for (int j = 0; j < N; j++) {
                        board[i][j] = Integer.parseInt(st.nextToken());
                    }
                }

                // Brute force
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        for (int k = 0; k < 4; k++) {
                            // swexpertacademy.Ball 생성
                            Ball ball = new Ball(j, i, DIRECTION_TYPE[k].getX(), DIRECTION_TYPE[k].getY());

                            //경로 체크를 위한 Stack
                            Stack<Path> paths = new Stack<>();

                            while (!isEnd) {
                                isEnd = false;

//                                swexpertacademy.PinballObject.setBall(board,ball, );
                            }

                            if (ball.getScore()>maxScore){
                                maxScore = ball.getScore();
                            }
                        }
                    }
                }


                // Print result
//                System.out.printf("#%d %d\n", t, solution(board));
            }
        }
    }
    static class Path {
        private int x;
        private int y;
        private int directionType;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Path path = (Path) o;
            return x == path.x &&
                    y == path.y &&
                    directionType == path.directionType;
        }

        @Override
        public int hashCode() {

            return Objects.hash(x, y, directionType);
        }
    }

    static class Coord {
        private int x;
        private int y;

        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }

    static class Ball {
        public static final int REFLECTION_X = 1;
        public static final int REFLECTION_Y = 2;
        public static final int REFLECTION_DIAGONAL = 3;
        public static final int REFLECTION_REVERSE_DIAGONAL = 4;


        public static final int DIR_UP = 11;
        public static final int DIR_DOWN = 12;
        public static final int DIR_LEFT = 13;
        public static final int DIR_RIGHT = 14;

        private int startX;
        private int startY;
        private int x;
        private int y;
        private int score;
        private int directionX;
        private int directionY;

        public Ball(int startX, int startY, int directionX, int directionY) {
            this.startX = startX;
            this.startY = startY;
            this.x = startX;
            this.y = startY;
            this.score = 0;
            this.directionX = directionX;
            this.directionY = directionY;
        }

        public int getStartX() {
            return startX;
        }

        public void setStartX(int startX) {
            this.startX = startX;
        }

        public int getStartY() {
            return startY;
        }

        public void setStartY(int startY) {
            this.startY = startY;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getDirectionX() {
            return directionX;
        }


        public int getDirectionY() {
            return directionY;
        }

        public void setDirection(int directionType) {
            int temp;
            switch (directionType) {
                case REFLECTION_X:
                    this.directionX *= -1;
                    break;
                case REFLECTION_Y:
                    this.directionY *= -1;
                    break;
                case REFLECTION_DIAGONAL:
                    temp = this.directionX;
                    this.directionX = this.directionY;
                    this.directionY = temp;
                    break;
                case REFLECTION_REVERSE_DIAGONAL:
                    temp = this.directionX;
                    this.directionX = -this.directionY;
                    this.directionY = -temp;
                    break;
            }
        }

        public void move() {
            this.x += directionX;
            this.y += directionY;
        }

        public int getDirection() {
            if (this.x == 1) {
                return DIR_RIGHT;
            } else if (this.x == -1) {
                return DIR_LEFT;
            } else if (this.y == 1) {
                return DIR_DOWN;
            } else {
                return DIR_UP;
            }
        }

        public int getScore() {
            return score;
        }

        public void addScore() {
            this.score++;
        }
    }

    static class PinballObject {
        public static final int OBJECT_TYPE_BLACK_WHOLE = -1;
        public static final int OBJECT_TYPE_0 = 0;
        public static final int OBJECT_TYPE_1 = 1;
        public static final int OBJECT_TYPE_2 = 2;
        public static final int OBJECT_TYPE_3 = 3;
        public static final int OBJECT_TYPE_4 = 4;
        public static final int OBJECT_TYPE_5 = 5;


        public static void setBall(int[][] board, Ball ball, int objectType, int thisX, int thisY) {
            switch (objectType) {
                case OBJECT_TYPE_1:
                    ball.addScore();
                    switch (ball.getDirection()) {
                        case Ball.DIR_UP:
                            ball.setDirection(Ball.REFLECTION_Y);
                            break;
                        case Ball.DIR_RIGHT:
                            ball.setDirection(Ball.REFLECTION_X);
                            break;
                        case Ball.DIR_LEFT:
                        case Ball.DIR_DOWN:
                            ball.setDirection(Ball.REFLECTION_DIAGONAL);
                            break;
                    }
                    break;
                case OBJECT_TYPE_2:
                    ball.addScore();
                    switch (ball.getDirection()) {
                        case Ball.DIR_DOWN:
                            ball.setDirection(Ball.REFLECTION_Y);
                            break;
                        case Ball.DIR_RIGHT:
                            ball.setDirection(Ball.REFLECTION_X);
                            break;
                        case Ball.DIR_UP:
                        case Ball.DIR_LEFT:
                            ball.setDirection(Ball.REFLECTION_REVERSE_DIAGONAL);
                            break;
                    }

                    break;
                case OBJECT_TYPE_3:
                    ball.addScore();
                    switch (ball.getDirection()) {
                        case Ball.DIR_DOWN:
                            ball.setDirection(Ball.REFLECTION_Y);
                            break;
                        case Ball.DIR_LEFT:
                            ball.setDirection(Ball.REFLECTION_X);
                            break;
                        case Ball.DIR_UP:
                        case Ball.DIR_RIGHT:
                            ball.setDirection(Ball.REFLECTION_DIAGONAL);
                            break;
                    }

                    break;
                case OBJECT_TYPE_4:
                    ball.addScore();
                    switch (ball.getDirection()) {
                        case Ball.DIR_UP:
                            ball.setDirection(Ball.REFLECTION_Y);
                            break;
                        case Ball.DIR_LEFT:
                            ball.setDirection(Ball.REFLECTION_X);
                            break;
                        case Ball.DIR_DOWN:
                        case Ball.DIR_RIGHT:
                            ball.setDirection(Ball.REFLECTION_REVERSE_DIAGONAL);
                            break;
                    }
                    break;
                case OBJECT_TYPE_5:
                    P5650_PinballGame.maxScore++;
                    switch (ball.getDirection()) {
                        case Ball.DIR_UP:
                        case Ball.DIR_DOWN:
                            ball.setDirection(Ball.REFLECTION_Y);
                            break;
                        case Ball.DIR_RIGHT:
                        case Ball.DIR_LEFT:
                            ball.setDirection(Ball.REFLECTION_X);
                            break;
                    }
                    break;
            }

            // 웜홀
            if (6 <= objectType && objectType <= 10) {
                Coord anotherWormhole = getAnotherWormholeCoord(board, objectType, thisX, thisY);

                ball.setX(anotherWormhole.getX());
                ball.setY(anotherWormhole.getY());

            } else if (objectType == -1) {        // 블랙홀
                P5650_PinballGame.isEnd = true;
            }

        }

        public static Coord getAnotherWormholeCoord(int[][] board, int objectType, int thisX, int thisY) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] == objectType && !(thisY == i && thisX == j)) {
                        return new Coord(j, i);
                    }
                }
            }
            return null;
        }
    }
}
