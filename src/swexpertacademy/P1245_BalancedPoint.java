package swexpertacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15MeBKAOgCFAYD&categoryId=AV15MeBKAOgCFAYD&categoryType=CODE&&&
 * <p>
 * 1245. [S/W 문제해결 응용] 2일차 - 균형점
 */

public class P1245_BalancedPoint {
    public static int T, N;
    public static int[] xArr;
    public static int[] weightArr;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            T = Integer.parseInt(br.readLine());

            // Loop T times
            for (int i = 1; i <= T; i++) {
                N = Integer.parseInt(br.readLine());

                xArr = new int[N];
                weightArr = new int[N];

                StringTokenizer st = new StringTokenizer(br.readLine());
                // Init x and weight
                for (int n = 0; n < N; n++) {
                    xArr[n] = Integer.parseInt(st.nextToken());
                }
                for (int n = 0; n < N; n++) {
                    weightArr[n] = Integer.parseInt(st.nextToken());
                }

                // Print result
                System.out.printf("#%d %s\n", i, solution());
            }
        }
    }

    private static String solution() {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < N - 1; i++) {
            double leftX = xArr[i];
            double rightX = xArr[i + 1];

            double tempX;
            double power;

            do {
                tempX = (leftX + rightX) / 2;
                power = getPower(tempX);
                if (power < 0) {
                    leftX = tempX;
                } else {
                    rightX = tempX;
                }
            } while (power == 0 || rightX - leftX >= 0.000000000001);
            result.append(String.format("%.10f ", tempX));
        }

        return result.toString();
    }

    private static double getPower(double x) {
        double result = 0;
        for (int i = 0; i < N; i++) {
            double power = (weightArr[i] / Math.pow(x - xArr[i], 2));
            if(x > xArr[i]){
                result -= power;
            }else{
                result += power;
            }
        }
        return result;
    }
}