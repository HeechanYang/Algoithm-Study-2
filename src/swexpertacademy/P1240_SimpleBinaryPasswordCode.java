package swexpertacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15FZuqAL4CFAYD&categoryId=AV15FZuqAL4CFAYD&categoryType=CODE
 * <p>
 * 1240. [S/W 문제해결 응용] 1일차 - 단순 2진 암호코드
 */

public class P1240_SimpleBinaryPasswordCode {
    public static int T, N, M;
    public static final int CODE_LENGTH = 7;
    public static final int PASSWD_LENGTH = 8;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            T = Integer.parseInt(br.readLine());

            // Loop T times
            for (int i = 0; i < T; i++) {
                StringTokenizer st = new StringTokenizer((br.readLine()));
                N = Integer.parseInt(st.nextToken());
                M = Integer.parseInt(st.nextToken());

                boolean[][] passwordCodes = new boolean[N][M * 4];

                // Init password codes
                for (int n = 0; n < N; n++) {
                    StringBuilder sb = new StringBuilder(Integer.toBinaryString(Integer.parseInt(br.readLine(), 16)));
                    for (int n2 = 0; n2 < M; n2++) {
                        if (sb.charAt(n2) == '1') {
                            passwordCodes[n][n2] = true;
                        }
                    }
                }


                // Print result
                System.out.printf("#%d %d\n", (i + 1), solution(passwordCodes));
            }
        }
    }

    private static int solution(boolean[][] passwordCodes) {
        int passwdLine = getPasswdCodeStartLine(passwordCodes);
        boolean[] passwd = passwordCodes[passwdLine];
        int lastIdx = getLastOne(passwd);
        int startIdx = lastIdx - (CODE_LENGTH * PASSWD_LENGTH - 1);

        boolean[] realPasswd = Arrays.copyOfRange(passwd, startIdx, lastIdx + 1);
        int[] passwdInt = new int[PASSWD_LENGTH];

        for (int i = 0; i < PASSWD_LENGTH; i++) {
            boolean[] thisCode = Arrays.copyOfRange(realPasswd, i * 7 + 1, i * 7 + 6);
            passwdInt[i] = getCode(thisCode);
        }

        if (isValidPasswd(passwdInt)) {
            return sumOfPasswd(passwdInt);
        } else {
            return 0;
        }
    }

    private static int sumOfPasswd(int[] passwd) {
        int sum = 0;
        for (int num : passwd) {
            sum += num;
        }
        return sum;
    }

    private static boolean isValidPasswd(int[] passwd) {
        int sum = passwd[PASSWD_LENGTH - 1];
        for (int i = 0; i < PASSWD_LENGTH - 1; i++) {
            if (i % 2 == 0) {
                sum += passwd[i] * 3;
            } else {
                sum += passwd[i];
            }
        }
        return sum % 10 == 0;
    }

    private static int getLastOne(boolean[] passwd) {
        for (int i = M - 1; i >= 0; i--) {
            if (passwd[i]) {
                return i;
            }
        }
        return 0;
    }

    private static int getPasswdCodeStartLine(boolean[][] passwordCodes) {
        int result = 0;
        int cnt = 0;

        for (int i = 1; i < N; i++) {
            boolean[] beforeLine = passwordCodes[i - 1];
            boolean[] thisLine = passwordCodes[i];

            boolean isSame = true;
            boolean isPasswdLine = false;

            for (int j = 0; j < M; j++) {
                if (!(beforeLine[j] == thisLine[j])) {
                    isSame = false;
                    cnt = 0;
                    break;
                }
                isPasswdLine = isPasswdLine || thisLine[j];
            }
            if (isPasswdLine && isSame) {
                cnt++;
                if (cnt == 4) {
                    return i - 4;
                }
            }
        }

        return -1;
    }

    private static int getCode(boolean[] word) {
        boolean b1 = word[0];
        boolean b2 = word[1];
        boolean b3 = word[2];
        boolean b4 = word[3];
        boolean b5 = word[4];

        if (!b1 && !b2 && b3 && b4 && !b5) return 0;
        else if (!b1 && b2 && b3 && !b4 && !b5) return 1;
        else if (!b1 && b2 && !b3 && !b4 && b5) return 2;
        else if (b1 && b2 && b3 && b4 && !b5) return 3;
        else if (b1 && !b2 && !b3 && !b4 && b5) return 4;
        else if (b1 && b2 && !b3 && !b4 && !b5) return 5;
        else if (b1 && !b2 && b3 && b4 && b5) return 6;
        else if (b1 && b2 && b3 && !b4 && b5) return 7;
        else if (b1 && b2 && !b3 && b4 && b5) return 8;
        else if (!b1 && !b2 && b3 && !b4 && b5) return 9;
        else return -1;

    }
}
