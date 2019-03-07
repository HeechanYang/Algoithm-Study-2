package baekjoon;

import java.io.*;
import java.util.Stack;

/**
 * https://www.acmicpc.net/problem/2504
 * 2504. 괄호의 값
 */

public class P2504_ValueOfBracket {
    static final char OPEN_2 = '(';
    static final char OPEN_3 = '[';
    static final char CLOSE_2 = ')';
    static final char CLOSE_3 = ']';
    static final int OPEN_2_INT = -1;
    static final int OPEN_3_INT = -2;
    static final int CLOSE_2_INT = -3;
    static final int CLOSE_3_INT = -4;

    private static String str;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            str = br.readLine();

            bw.write(String.valueOf(solution()));
            bw.flush();
        }
    }

    private static int solution() {
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            switch (c) {
                case OPEN_2:
                    stack.push(OPEN_2_INT);
                    break;
                case OPEN_3:
                    stack.push(OPEN_3_INT);
                    break;
                case CLOSE_2:
                    int sum = 0;
                    int pop;

                    if (stack.size() == 0) return 0;
                    while ((pop = stack.pop()) != OPEN_2_INT) {
                        if (pop == OPEN_3_INT || pop == CLOSE_3_INT) {
                            return 0;
                        } else {
                            sum += pop;
                        }

                        if (stack.size() == 0) break;
                    }
                    if (sum == 0) {
                        sum = 1;
                    }
                    stack.push(sum * 2);
                    break;
                case CLOSE_3:
                    sum = 0;

                    if (stack.size() == 0) return 0;
                    while ((pop = stack.pop()) != OPEN_3_INT) {
                        if (pop == OPEN_2_INT || pop == CLOSE_2_INT) {
                            return 0;
                        } else {
                            sum += pop;
                        }
                        if (stack.size() == 0) break;
                    }
                    if (sum == 0) {
                        sum = 1;
                    }
                    stack.push(sum * 3);
                    break;
            }
        }

        int result = 0;
        for (int num : stack) {
            if (num < 0) {
                return 0;
            }
            result += num;
        }
        return result;
    }
}