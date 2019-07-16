package baekjoon.algorithm.bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class P1260_DFSAndBFS {
    private static List<Integer>[] lists;
    private static boolean[] isVisited;
    private static StringBuilder sb;

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            sb = new StringBuilder();
//            HashMap

            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken()) - 1;

            isVisited = new boolean[N];

            lists = new LinkedList[N];
            for (int i = 0; i < N; i++) {
                lists[i] = new LinkedList<>();
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int x1 = Integer.parseInt(st.nextToken()) - 1;
                int x2 = Integer.parseInt(st.nextToken()) - 1;
                lists[x1].add(x2);
                lists[x2].add(x1);
            }
            for (int i = 0; i < N; i++) {
                Collections.sort(lists[i]);
            }

            dfs(V);
            sb.append('\n');

            isVisited = new boolean[N];
            bfs(V);

            bw.write(sb.toString());
            bw.flush();
        } catch (Exception e) {
        }
    }

    private static void bfs(int v) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(v);
        isVisited[v] = true;

        while (!queue.isEmpty()) {
            int x = queue.poll();
            sb.append(x + 1).append(' ');
            for (int vv : lists[x]) {
                if (!isVisited[vv]) {
                    isVisited[vv] = true;
                    queue.offer(vv);
                }
            }
        }
    }

    private static void dfs(int v) {
        isVisited[v] = true;
        sb.append(v + 1).append(' ');
        for (int vv : lists[v]) {
            if (!isVisited[vv]) {
                dfs(vv);
            }
        }
    }
}
