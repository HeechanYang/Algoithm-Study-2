package swexpertacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15OZ4qAPICFAYD&categoryId=AV15OZ4qAPICFAYD&categoryType=CODE
 * <p>
 * 1247. [S/W 문제해결 응용] 2일차 - 최적 경로
 */

public class P1247_OptimalPath {
    public static int T, N;
    public static int startX, startY, endX, endY;
    public static int[] toStartDistance;
    public static int[] toEndDistance;
    public static int[][] distanceMatrix;
    public static int[] xArr;
    public static int[] yArr;
    public static boolean[] isVisited;
    private static int min;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            T = Integer.parseInt(br.readLine());

            // Loop T times
            for (int i = 1; i <= T; i++) {
                N = Integer.parseInt(br.readLine());

                xArr = new int[N];
                yArr = new int[N];

                toStartDistance = new int[N];
                toEndDistance = new int[N];
                distanceMatrix = new int[N][N];
                isVisited = new boolean[N];
                min = Integer.MAX_VALUE;

                StringTokenizer st = new StringTokenizer(br.readLine());

                startX = Integer.parseInt(st.nextToken());
                startY = Integer.parseInt(st.nextToken());
                endX = Integer.parseInt(st.nextToken());
                endY = Integer.parseInt(st.nextToken());
                for (int n = 0; n < N; n++) {
                    xArr[n] = Integer.parseInt(st.nextToken());
                    yArr[n] = Integer.parseInt(st.nextToken());
                }
                setDistance();

                // Print result
                System.out.printf("#%d %s\n", i, solution());
            }
        }
    }

    private static int solution() {
        for (int i = 0; i < N; i++) {
            isVisited[i] = true;
            bruteForce(i, toStartDistance[i], 2);
            isVisited[i] = false;
        }

        return min;
    }


    private static void bruteForce(int idx, int distance, int level) {
        if (level == N) {
            for (int i = 0; i < N; i++) {
                if (!isVisited[i]) {
                    min = Math.min(min, distance + distanceMatrix[idx][i] + toEndDistance[i]);
                }
            }
        } else {
            for (int i = 0; i < N; i++) {
                if (!isVisited[i]) {
                    isVisited[i] = true;
                    int tempDistance = distance + distanceMatrix[i][idx];
                    if (tempDistance < min) {
                        bruteForce(i, tempDistance, level + 1);
                    }
                    isVisited[i] = false;
                }
            }
        }
    }

    private static void setDistance() {
        for (int i = 0; i < N; i++) {
            toStartDistance[i] = getDistance(startX, startY, xArr[i], yArr[i]);
            toEndDistance[i] = getDistance(endX, endY, xArr[i], yArr[i]);

            for (int j = i + 1; j < N; j++) {
                distanceMatrix[i][j] = distanceMatrix[j][i] = getDistance(xArr[i], yArr[i], xArr[j], yArr[j]);
            }
        }
    }

    private static int getDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}