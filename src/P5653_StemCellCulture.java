import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXRJ8EKe48DFAUo
 * <p>
 * 5653. [모의 SW 역량테스트] 줄기세포배양
 */

public class P5653_StemCellCulture {

    public static void main(String[] args) throws IOException {
        try (final BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int T = Integer.parseInt(br.readLine());

            for (int i = 0; i < T; i++) {
                StringTokenizer tokenizer = new StringTokenizer(br.readLine());

                int N = Integer.parseInt(tokenizer.nextToken());
                int M = Integer.parseInt(tokenizer.nextToken());
                int K = Integer.parseInt(tokenizer.nextToken());
                int xLength = M + 2 * K;
                int yLength = N + 2 * K;

                int[][] plate = new int[yLength][xLength];
                int[][] timeAttack = new int[yLength][xLength];

                int startXPoint = xLength / 2 - M / 2;
                int startYPoint = yLength / 2 - N / 2;

                for (int j = 0; j < N; j++) {
                    StringTokenizer tokenizer2 = new StringTokenizer(br.readLine());
                    for (int k = 0; k < M; k++) {
                        int value = Integer.parseInt(tokenizer2.nextToken());
                        plate[startYPoint + j][startXPoint + k] = value;
                        timeAttack[startYPoint + j][startXPoint + k] = value * 2;
                    }
                }

                System.out.printf("#%d %d\n", i + 1, solution(plate, timeAttack, K));
            }
        }
    }

    private static int solution(int[][] plate, int[][] timeAttack, int K) {
        int xLength = plate[0].length;
        int yLength = plate.length;

        for (int i = 0; i < K; i++) {
            // time attack!
            for (int j = 0; j < yLength; j++) {
                for (int k = 0; k < xLength; k++) {
                    timeAttack[j][k]--;
                }
            }

            // process!
            for (int j = 0; j < yLength; j++) {
                for (int k = 0; k < xLength; k++) {
                    int thisValue = plate[j][k];
                    if (thisValue != 0 && timeAttack[j][k] == (thisValue - 1) ) {
                        int[] xArr = new int[]{k - 1, k + 1, k, k};
                        int[] yArr = new int[]{j, j, j - 1, j + 1};
                        for (int a = 0; a < 4; a++) {
                            int targetX = xArr[a];
                            int targetY = yArr[a];
                            int targetPlate = plate[targetY][targetX];
                            int targetTimeAttack = timeAttack[targetY][targetX];
                            // 아직 초기화 되어있지 않으면 '증식'
                            // '같은 시간'에 초기화 되었지만, 해당 세포의 값보다 세포값이 작다면 '덮어씀'
                            if (targetPlate == 0
                                    || ((targetTimeAttack == targetPlate * 2) && (targetPlate < thisValue))) {
                                plate[targetY][targetX] = thisValue;
                                timeAttack[targetY][targetX] = thisValue * 2;
                            }
                        }
                    }
                }
            }
        }

        int cnt = 0;
        for (int j = 0; j < yLength; j++) {
            for (int k = 0; k < xLength; k++) {
                if (timeAttack[j][k] > 0 && plate[j][k] != 0)
                    cnt++;
            }
        }

        return cnt;
    }

    private static void printArr(int[][] arr) {
        System.out.println();
        for (int[] anArr : arr) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.printf("%3d", anArr[j]);
            }
            System.out.println();
        }
    }
}
