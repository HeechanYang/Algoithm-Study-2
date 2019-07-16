package googlecodejam.qualification;

import java.io.*;

/**
 * Someone just won the Code Jam lottery, and we owe them N jamcoins! However, when we tried to print out an oversized
 * check, we encountered a problem. The value of N, which is an integer, includes at least one digit that is a 4... and
 * the 4 key on the keyboard of our oversized check printer is broken.
 * <p>
 * Fortunately, we have a workaround: we will send our winner two checks for positive integer amounts A and B, such that
 * neither A nor B contains any digit that is a 4, and A + B = N. Please help us find any pair of values A and B that
 * satisfy these conditions.
 */

public class Attempt1 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int T = Integer.parseInt(br.readLine());

            StringBuilder sb = new StringBuilder();

            for (int t = 1; t <= T; t++) {
                char[] N = br.readLine().toCharArray();

                int length = N.length;

                for (int i = length - 1; i >= 0; i--) {
                    if (N[i] != '0') {
                        N[i] -= 1;
                        break;
                    } else {
                        N[i] = '9';
                    }
                }

                char[] num1 = new char[length];
                char[] num2 = new char[length];

                for (int i = 0; i < length; i++) {
                    if (N[i] != '4') {
                        switch (N[i]) {
                            case '5':
                                num1[i] = '2';
                                num2[i] = '3';
                                break;
                            case '6':
                                num1[i] = '1';
                                num2[i] = '5';
                                break;
                            case '7':
                                num1[i] = '1';
                                num2[i] = '6';
                                break;
                            case '8':
                                num1[i] = '1';
                                num2[i] = '7';
                                break;
                            case '9':
                                num1[i] = '1';
                                num2[i] = '8';
                                break;
                            default:
                                num1[i] = (char) ((N[i] + '0') / 2);
                                num2[i] = (char) ((N[i] + '0') / 2.0 + 0.5);
                                break;
                        }
                    } else {
                        num1[i] = '2';
                        num2[i] = '2';
                    }
                }

                int zeroCnt1 = 0;
                int zeroCnt2 = 0;

                for (int i = 0; i < length; i++) {
                    if (num1[i] == '0') {
                        zeroCnt1++;
                    } else {
                        break;
                    }
                }

                for (int i = 0; i < length; i++) {
                    if (num2[i] == '0') {
                        zeroCnt2++;
                    } else {
                        break;
                    }
                }

                char[] num1Final;
                if (zeroCnt1 == length) {
                    num1Final = new char[]{'1'};
                } else {
                    num1Final = new char[length - zeroCnt1];
                    System.arraycopy(num1, zeroCnt1, num1Final, 0, length - zeroCnt1);
                    num1Final[length - zeroCnt1 - 1] += 1;
                }

                char[] num2Final = new char[length - zeroCnt2];
                System.arraycopy(num2, zeroCnt2, num2Final, 0, length - zeroCnt2);

                sb.append("Case #").append(t).append(": ");
                sb.append(num1Final).append(' ').append(num2Final).append('\n');
            }


            bw.write(sb.toString());
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
