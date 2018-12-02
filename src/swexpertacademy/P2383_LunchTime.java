package swexpertacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5-BEE6AK0DFAVl&
 * <p>
 * 2383. [모의 SW 역량테스트] 점심 식사시간
 */

public class P2383_LunchTime {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int T = Integer.parseInt(br.readLine());

            // Loop T times
            for (int i = 0; i < T; i++) {
                min = Integer.MAX_VALUE;
                int N = Integer.parseInt(br.readLine());

                List<Coord> students = new ArrayList<>();
                List<Coord> exits = new ArrayList<>();

                // Init lists
                for (int n = 0; n < N; n++) {
                    StringTokenizer st = new StringTokenizer(br.readLine());
                    for (int n2 = 0; n2 < N; n2++) {
                        int value = Integer.parseInt(st.nextToken());
                        Coord thisCoord = new Coord(n2, n, value);


                        if (value == 1) { //학생
                            students.add(thisCoord);
                        } else if (value > 1) { //출입구
                            exits.add(thisCoord);
                        }
                    }
                }

                List<Integer> stair1 = new ArrayList<>();
                List<Integer> stair2 = new ArrayList<>();
                solution(stair1, stair2, students, exits);

                // Print result
                System.out.printf("#%d %d\n", (i + 1), min);
            }
        }
    }

    private static int min = Integer.MAX_VALUE;

    private static void solution(List<Integer> stair1, List<Integer> stair2, List<Coord> students, List<Coord> exits) {
        if (students.size() == 0) {

            List<Integer> tempStair1 = new ArrayList<>(stair1);
            List<Integer> tempStair2 = new ArrayList<>(stair2);

            int stairTime1 = processStair(tempStair1, exits.get(0).getValue());
            int stairTime2 = processStair(tempStair2, exits.get(1).getValue());

            int totalTime = stairTime1 > stairTime2 ? stairTime1 : stairTime2;

            if (totalTime < min) {
                min = totalTime;
            }
        } else {
            stair1.add(getDistanceBetween(students.get(0), exits.get(0)));
            solution(stair1, stair2, students.subList(1, students.size()), exits);

            stair1.remove(stair1.size() - 1);

            stair2.add(getDistanceBetween(students.get(0), exits.get(1)));
            solution(stair1, stair2, students.subList(1, students.size()), exits);

            stair2.remove(stair2.size() - 1);
        }
    }

    private static int processStair(List<Integer> stairWaitingList, int value) {
        if(stairWaitingList.size()==0){
            return 0;
        }

        int time = 0;
        Collections.sort(stairWaitingList);

        int[] lifes = new int[stairWaitingList.size()];
        Arrays.fill(lifes, value);

        while (lifes[lifes.length - 1] > 0) {
            time++;
            int cnt = 0;
            for (int i = 0; i < stairWaitingList.size(); i++) {
                if (cnt < 3
                        && stairWaitingList.get(i) < time
                        && lifes[i] > 0) {
                    cnt++;
                    lifes[i]--;
                }
            }
        }

        return ++time;
    }

    private static int getDistanceBetween(Coord coord1, Coord coord2) {
        return Math.abs(coord1.getX() - coord2.getX()) + Math.abs(coord1.getY() - coord2.getY());
    }

    static class Coord {
        private int x;
        private int y;
        private int value;

        public Coord(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
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

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}
