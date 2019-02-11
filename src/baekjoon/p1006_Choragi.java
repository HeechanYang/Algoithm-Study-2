package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 1006. 습격자 초라기
 * <p>
 * - 먼저 인접한 구역과 합칠 수 없는 구역부터 구하고, 한 소대씩 집어넣은 다음.
 * - 합칠 수 있는 인접 구역이 하나 뿐인 구역들에도 한 소대 씩 집어넣고,
 * - 남은 구역들은 인접 합칠 수 있는 인접 구역이 두 개 이상인 곳.
 */

/*
4
6 5
1 5 1 1 1 1
1 1 1 1 5 1
12 5
1 1 1 1 1 5 1 1 1 1 1 5
1 1 5 1 1 5 1 1 5 1 1 5
3 5
1 4 1
1 4 1
4 5
2 4 1 1
3 1 4 3
 */

/*
8
16
3
4
 */

public class p1006_Choragi {
    private static final int NOT_CACHED = -1;

    private static int T, N, W;
    private static int[] line1;
    private static int[] line2;

    private static int[][][] cache;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringBuilder resultString = new StringBuilder();

            T = Integer.parseInt(br.readLine());

            for (int i = 1; i <= T; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                N = Integer.parseInt(st.nextToken());
                W = Integer.parseInt(st.nextToken());

                line1 = new int[N];
                line2 = new int[N];
                cache = new int[N][4][4];

                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    line1[j] = Integer.parseInt(st.nextToken());
                }

                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    line2[j] = Integer.parseInt(st.nextToken());
                }

                resultString.append(solution(0, 0)).append('\n');
            }

            bw.write(resultString.toString());
            bw.flush();
        }
    }

    private static int solution(int l1x, int l2x) {

        return 0;
    }
}