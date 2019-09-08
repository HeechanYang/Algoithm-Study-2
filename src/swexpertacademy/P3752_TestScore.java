package swexpertacademy;

import java.io.*;
import java.util.StringTokenizer;

public class P3752_TestScore {

    private static int[] arr;
    private static boolean[] visits;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            StringBuilder sb = new StringBuilder();

            int T = Integer.parseInt(br.readLine());

            for (int t = 1; t <= T; t++) {

                int N = Integer.parseInt(br.readLine());
                arr = new int[N];
                visits = new boolean[100 * N];
                visits[0] = true;

                int max = 0;

                StringTokenizer st = new StringTokenizer(br.readLine());

                for (int i = 0; i < N; i++) {
                    int num = Integer.parseInt(st.nextToken());
                    arr[i] = num;
                    max += num;

                    for (int j = max; j >= 0; j--) {
                        if (visits[j]) {
                            visits[j + num] = true;
                        }
                    }
                }

                sb.append('#').append(t).append(" ").append(count()).append('\n');
            }

            bw.write(sb.toString());
            bw.flush();
        }
    }

    private static int count() {
        int cnt = 0;
        for (boolean b : visits) {
            if (b) {
                cnt++;
            }
        }
        return cnt;
    }
}
