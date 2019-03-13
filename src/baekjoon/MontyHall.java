package baekjoon;

import java.util.Random;

public class MontyHall {
    private static final int GOAT = 0;
    private static final int CAR = 1;

    private static final Random rand = new Random();

    public static void main(String[] args) {

        int switchWins = 0;
        int stayWins = 0;

        for (int plays = 0; plays < 100000; plays++) {
            int[] doors = {GOAT, GOAT, GOAT};

            int carDoor = rand.nextInt(3);
            doors[carDoor] = CAR;

            int choice = rand.nextInt(3);

            int shown = getOtherGoatDoor(doors, choice, carDoor);

            if (doors[3 - choice - shown] == CAR) {
                switchWins++;
            } else {
                stayWins++;
            }
        }

        System.out.println("바꿨을 경우 " + switchWins + " 번 승리");
        System.out.println("바꾸지 않았을 경우 " + stayWins + " 번 승리");
    }

    // 사회자가 남은 문 중 오답인 문을 선택
    private static int getOtherGoatDoor(int[] doors, int choice, int carDoor) {
        int shown;
        do {
            shown = rand.nextInt(3);
        } while (doors[shown] == CAR || shown == choice);

        return shown;
    }
}