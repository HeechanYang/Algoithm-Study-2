package codeforces;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Problem5 {
    private static int N, M;
    private static int[] intArr;
    private static int[][] segmentArr;

    private static int D, Q;
    private static List<Integer> segmentList;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            StringBuilder sb = new StringBuilder();

            StringTokenizer st = new StringTokenizer(br.readLine());

            segmentList = new ArrayList<>();

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            intArr = new int[N];
            segmentArr = new int[M][2];

            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {
                intArr[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                segmentArr[i][0] = Integer.parseInt(st.nextToken());
                segmentArr[i][1] = Integer.parseInt(st.nextToken());
            }

            solution();

            sb.append(0).append('\n')
                    .append(0).append('\n');

            for (int i : segmentList) {
                sb.append(i).append(' ');
            }

            bw.write(sb.toString());
            bw.flush();
            bw.close();
        }
    }

    private static void solution() {

    }

    private static void subSegment(int l, int r) {
        for (int i = l; i <= r; i++) {
            intArr[i]--;
        }
    }
}