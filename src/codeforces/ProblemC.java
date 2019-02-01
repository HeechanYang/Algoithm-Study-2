package codeforces;

import java.io.*;

/**
 * https://codeforces.com/contest/1108/problem/C
 * C. Nice Garland
 *
 * 일렬로 늘어진 램프의 색깔을 나타내는 배열이 주어지는데,
 * 각 램프의 색깔은 R, G, B중 하나.
 * 각 색깔이 세 번마다 반복되도록 바뀌어야함.
 * - ex) RGBRGBRG
 * - ex) GB
 * - ex) GRBGRBG
 *
 * [해결방법]
 * - 램프 색깔 패턴의 모든 조합을 구하고 해당 패턴들과 램프의 개수를 이용해 정답셋을 구해놓는다.
 * - 각 정답셋과 주어진 현재 램프 색깔을 비교하여 가장 변화가 적은 정답셋을 구하면 됨.
 * - 랩프 색깔 패턴의 조합
 *  - RGB, RBG, BRG, BGR, GRB, GBR 총 6가지
 */

public class ProblemC {
    private static final char R = 'R';
    private static final char G = 'G';
    private static final char B = 'B';

    private static int N;
    private static int minChangeCnt;
    private static int minPosition;

    private static char[] lampArr;
    private static int[] cntArr;
    private static char[][] correctLampsArr;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            StringBuilder sb = new StringBuilder();

            N = Integer.parseInt(br.readLine());
            lampArr = new char[N];
            cntArr = new int[6];
            correctLampsArr = new char[6][N];

            minPosition = -1;
            minChangeCnt = Integer.MAX_VALUE;

            String lampStr = br.readLine();

            for (int i = 0; i < N; i++) {
                lampArr[i] = lampStr.charAt(i);

                switch (i % 3) {
                    case 0:
                        correctLampsArr[0][i] = R;
                        correctLampsArr[1][i] = R;
                        correctLampsArr[2][i] = G;
                        correctLampsArr[3][i] = G;
                        correctLampsArr[4][i] = B;
                        correctLampsArr[5][i] = B;
                        break;
                    case 1:
                        correctLampsArr[0][i] = G;
                        correctLampsArr[1][i] = B;
                        correctLampsArr[2][i] = R;
                        correctLampsArr[3][i] = B;
                        correctLampsArr[4][i] = R;
                        correctLampsArr[5][i] = G;
                        break;
                    case 2:
                        correctLampsArr[0][i] = B;
                        correctLampsArr[1][i] = G;
                        correctLampsArr[2][i] = B;
                        correctLampsArr[3][i] = R;
                        correctLampsArr[4][i] = G;
                        correctLampsArr[5][i] = R;
                        break;
                }
            }

            solution();

            sb.append(minChangeCnt).append('\n');

            for (int i = 0; i < N; i++) {
                sb.append(correctLampsArr[minPosition][i]);
            }

            bw.write(sb.toString());
            bw.flush();
            bw.close();
        }
    }

    private static void solution() {
        for (int i = 0; i < N; i++) {
            char thisLamp = lampArr[i];

            for (int j = 0; j < 6; j++) {
                char thisCorrectLamp = correctLampsArr[j][i];
                if (thisLamp != thisCorrectLamp) {
                    cntArr[j]++;
                }
            }
        }

        for (int i = 0; i < 6; i++) {
            if (minChangeCnt > cntArr[i]) {
                minPosition = i;
                minChangeCnt = cntArr[i];
            }
        }
    }
}