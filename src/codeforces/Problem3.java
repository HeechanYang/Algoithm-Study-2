package codeforces;

import java.io.*;

public class Problem3 {
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

    private static int solution() {
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
        return minPosition;
    }
}