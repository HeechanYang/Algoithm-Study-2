package swexpertacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15JEKKAM8CFAYD&categoryId=AV15JEKKAM8CFAYD&categoryType=CODE
 * <p>
 * 1242. [S/W 문제해결 응용] 1일차 - 암호코드 스캔
 */

public class P1242_PasswordCodeScan_FAIL {
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
                M = Integer.parseInt(st.nextToken()) * 4;

                Set<String> passwdSet = new HashSet<>();

                int sum = 0;

                // Init password codes
                for (int n = 0; n < N; n++) {
                    String readLine = br.readLine();
                    StringBuilder sb = new StringBuilder(readLine);

                    String tempPasswd;
                    boolean isAllZero = true;

                    for (int n2 = 0; n2 < M; n2 += 4) {
                        String thisCode = String.valueOf(sb.charAt(n2 / 4));

                        StringBuilder sb1 = new StringBuilder(Integer.toBinaryString(Integer.parseInt(thisCode, 16)));
                        if (!sb1.toString().equals("0")) {
                            isAllZero = false;
                        }
                    }

                    if (!isAllZero && !passwdSet.contains(readLine)) {
                        Set<String> tempSet = new HashSet<>(passwdSet);
                        for (String s : tempSet) {
                            for (int a = 0; a < readLine.length(); a++) {
                                char a1 = s.charAt(a);
                                char a2 = sb.charAt(a);
                                if (a1 == a2) {
                                    sb.replace(a, a + 1, "0");
                                }
                            }
                            passwdSet.add(sb.toString());
                        }
                        passwdSet.add(readLine);
                    }


                }

                for (String s : passwdSet) {
                    int num = solution(s);
//                    System.out.println("[" + s + "]");
//                    System.out.println();
                    sum += num;
                }


                // Print result
                System.out.printf("#%d %d\n", (i + 1), sum);
            }
        }
    }

    private static boolean[] strToBooleanArray(String passwdStr) {
        StringBuilder sb = new StringBuilder(passwdStr);
        boolean[] passwd = new boolean[sb.length() * 4];

        String tempPasswd;
        boolean isAllZero = true;

        for (int n2 = 0; n2 < M; n2 += 4) {
            String thisCode = String.valueOf(sb.charAt(n2 / 4));

            StringBuilder sb1 = new StringBuilder(Integer.toBinaryString(Integer.parseInt(thisCode, 16)));
            for (int j = sb1.length(); j < 4; j++) {
                sb1.insert(0, '0');
            }

            for (int j = 0; j < 4; j++) {
                passwd[n2 + j] = sb1.charAt(j) == '1';
            }
        }
        return passwd;
    }

    private static int solution(String passwdStr) {
        boolean[] passwd = strToBooleanArray(passwdStr);

        int firstOne = getFirstOneIdx(passwd);
        int lastIdx = getLastOneIdx(passwd);

        int bitSize = (lastIdx - firstOne) / (CODE_LENGTH * PASSWD_LENGTH) + 1;

        int startIdx = lastIdx - (CODE_LENGTH * PASSWD_LENGTH) * bitSize + 1;

        if (startIdx < 0) {
            return 0;
        }

        boolean[] realPasswd = Arrays.copyOfRange(passwd, startIdx, lastIdx + 1);

        //압축!
        passwd = compactPasswd(realPasswd, bitSize);

        // 압축 실패 시 return 0;
        if (passwd == null) {
            return 0;
        }

//        System.out.println("########################## " + bitSize);
        for (boolean b : passwd) {
//            System.out.print((b ? 1 : 0) + " ");
        }
//        System.out.println();

        int[] passwdInt = new int[PASSWD_LENGTH];

        for (int i = 0; i < PASSWD_LENGTH; i++) {
            boolean[] thisCode = Arrays.copyOfRange(passwd, i * 7 + 1, i * 7 + 6);
            passwdInt[i] = getCode(thisCode);
//            System.out.print(passwdInt[i] + " ");
            if (passwdInt[i] == -1) {
//                System.out.println("FALSE");
                return 0;
            }
        }
//        System.out.println();


        if (isValidPasswd(passwdInt)) {
            return sumOfPasswd(passwdInt);
        } else {
            return 0;
        }
    }

    private static boolean[] compactPasswd(boolean[] realPasswd, int bitSize) {
        boolean[] compactedPasswd = new boolean[PASSWD_LENGTH * CODE_LENGTH];
        for (int i = 0; i < PASSWD_LENGTH * CODE_LENGTH * bitSize; i += bitSize) {
            boolean thisCode = realPasswd[i];
            boolean isValid = true;

            for (int j = 1; j < bitSize; j++) {
                if (realPasswd[i + j] != thisCode) {
                    isValid = false;
                    break;
                }
            }

            if (isValid) {
                compactedPasswd[i / bitSize] = thisCode;
            } else {
                return null;
            }
        }

        return compactedPasswd;
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

    private static int getFirstOneIdx(boolean[] passwd) {
        for (int i = 0; i < M; i++) {
            if (passwd[i]) {
                return i;
            }
        }
        return 0;
    }

    private static int getLastOneIdx(boolean[] passwd) {
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
