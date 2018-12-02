package swexpertacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWIeV9sKkcoDFAVH
 * <p>
 * 4013. [모의 SW 역량테스트] 특이한 자석
 */


public class P4013_SpecialMagnet {
    public static final int DIRECTION_CLOCK = 1;
    public static final int DIRECTION_REVERSE_CLOCK = -1;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int T = Integer.parseInt(br.readLine());

            for (int i = 0; i < T; i++) {
                int K = Integer.parseInt(br.readLine());

                // 4개의 톱니바퀴 생성
                Gear[] gears = new Gear[4];

                // 입력 값에 따라 각 톱니바퀴의 톱니 값 지정
                for (int j = 0; j < 4; j++) {
                    gears[j] = new Gear();

                    StringTokenizer st = new StringTokenizer(br.readLine());
                    for (int m = 0; m < 8; m++) {
                        gears[j].getMagnets()[m] = Integer.parseInt(st.nextToken());
                    }
                }

                // 매 회 회전을 처리
                for (int j = 0; j < K; j++) {
                    StringTokenizer st = new StringTokenizer(br.readLine());
                    int gearNum = Integer.parseInt(st.nextToken()) - 1;
                    int direction = Integer.parseInt(st.nextToken());

                    rotateGears(gears, gearNum, gearNum, direction);
                }

                // 결과값 출력
                System.out.printf("#%d %d\n", i + 1, getGearsScore(gears));
            }
        }
    }

    // 톱니들의 연쇄적 회전을 처리
    public static void rotateGears(Gear[] gears, int firstGearNum, int gearNum, int direction) {
        int beforeGearNum = gearNum - 1;
        int nextGearNum = gearNum + 1;

        // 해당 톱니바퀴가 회전의 시작이라면 양 옆의 톱니바퀴를 처리
        if (firstGearNum == gearNum) {
            if (gearNum > 0) {                  // left
                if (gears[beforeGearNum].getRight() != gears[gearNum].getLeft()) {
                    rotateGears(gears, firstGearNum, beforeGearNum, -direction);
                }
            }
            if (gearNum < 3) {                  //right
                if (gears[gearNum].getRight() != gears[nextGearNum].getLeft()) {
                    rotateGears(gears, firstGearNum, nextGearNum, -direction);
                }
            }
        } else if (firstGearNum > gearNum) {    // 왼쪽으로 퍼지는 회전이라면 왼쪽 톱니바퀴 처리
            if (gearNum > 0) {                  // left
                if (gears[beforeGearNum].getRight() != gears[gearNum].getLeft()) {
                    rotateGears(gears, firstGearNum, beforeGearNum, -direction);
                }
            }
        } else {                                // 오른쪽으로 퍼지는 회전이라면 오른쪽 톱니바퀴 처리
            if (gearNum < 3) {                  // right
                if (gears[gearNum].getRight() != gears[nextGearNum].getLeft()) {
                    rotateGears(gears, firstGearNum, nextGearNum, -direction);
                }
            }
        }

        // 마지막엔 해당 톱니바퀴를 처리
        gears[gearNum].rotate(direction);
    }

    // 톱니바퀴들의 점수 합을 구함
    public static int getGearsScore(Gear[] gears) {
        int result = 0;
        for (int i = 0; i < gears.length; i++) {
            if (gears[i].getMagnets()[0] == 1) {
                result += Math.pow(2, i);
            }
        }
        return result;
    }

    static class Gear {
        public static final int INDEX_RIGHT = 2;
        public static final int INDEX_LEFT = 6;
        public static final int GEAR_SIZE = 8;

        private int[] magnets = new int[8];

        public int[] getMagnets() {
            return magnets;
        }

        public void setMagnets(int[] magnets) {
            this.magnets = magnets;
        }

        public int getRight() {
            return this.magnets[INDEX_RIGHT];
        }

        public int getLeft() {
            return this.magnets[INDEX_LEFT];
        }

        // 주어진 방향으로 톱니바퀴회전
        public void rotate(int direction) {
            switch (direction) {
                case DIRECTION_CLOCK:
                    int temp = this.magnets[GEAR_SIZE - 1];
                    System.arraycopy(this.magnets, 0, this.magnets, 1, GEAR_SIZE - 1);
                    this.magnets[0] = temp;
                    break;
                case DIRECTION_REVERSE_CLOCK:
                    temp = this.magnets[0];
                    System.arraycopy(this.magnets, 1, this.magnets, 0, GEAR_SIZE - 1);
                    this.magnets[GEAR_SIZE - 1] = temp;
                    break;
            }
        }
    }
}
