package swexpertacademy;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class P1259_MetalStick {

    private static Bolt[] bolts;
    private static Queue<Bolt> queue;
    private static Stack<Bolt> stack;
    private static int maxLength;
    private static int N;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            StringBuilder sb = new StringBuilder();

            int T = Integer.parseInt(br.readLine());

            for (int t = 1; t <= T; t++) {
                maxLength = 0;
                stack = new Stack<>();

                N = Integer.parseInt(br.readLine());

                bolts = new Bolt[N];
                visited = new boolean[N];

                StringTokenizer st;

                st = new StringTokenizer(br.readLine());
                for (int n = 0; n < N; n++) {

                    int male = Integer.parseInt(st.nextToken());
                    int female = Integer.parseInt(st.nextToken());

                    bolts[n] = new Bolt(male, female);
                }

                for (int i = 0; i < N; i++) {
                    dfs(i);
                }

                sb.append('#').append(t).append(' ');
                for (Bolt bolt : queue) {
                    sb.append(bolt.getMale()).append(' ').append(bolt.getFemale()).append(' ');
                }
                sb.append('\n');
            }

            bw.write(sb.toString());
            bw.flush();
        }
    }

    private static void dfs(int idx) {
        boolean isEnd = true;
        int female = bolts[idx].getFemale();
        visited[idx] = true;
        stack.push(bolts[idx]);

        for (int i = 0; i < N; i++) {
            if (!visited[i] && female == bolts[i].getMale()) {
                isEnd = false;
                dfs(i);
            }
        }

        if (isEnd) {
            int len = stack.size();
            if (maxLength < len) {
                maxLength = len;
                queue = new LinkedList<>();
                queue.addAll(stack);
            }
        }

        visited[idx] = false;
        stack.pop();
    }

    static class Bolt {
        private final int male;
        private final int female;

        Bolt(int male, int female) {
            this.male = male;
            this.female = female;
        }

        public int getMale() {
            return male;
        }

        public int getFemale() {
            return female;
        }
    }

}