package algorithmjobs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ProblemD {
    private static int[][] board;
    private static int N, M;
    private static List<Set<Integer>> pathList;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int T = Integer.parseInt(br.readLine());
            for (int i = 0; i < T; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                N = Integer.parseInt(st.nextToken());
                M = Integer.parseInt(st.nextToken());

                board = new int[M][N];

                pathList = new ArrayList<>();

                for (int j = 0; j < M; j++) {
                    st = new StringTokenizer(br.readLine());
                    for (int k = 0; k < N; k++) {
                        board[j][k] = Integer.parseInt(st.nextToken());
                    }
                }

                dfs(new HashSet<>(), 0, 0);

                int max = 0;

                for (Set<Integer> set : pathList) {
                    int sum = 0;
                    for (int n : set) {
                        sum += n;
                    }
                    if (max < sum) {
                        max = sum;
                    }
                }

                max = pathList.size() != 0 ? max : -1;

                System.out.printf("#%d %d %d\n", i + 1, pathList.size(), max);
            }
        }
    }

    public static void dfs(Set<Integer> set, int x, int y) {
        if (x == N - 1 && y == M - 1) {
            set.add(board[y][x]);
            pathList.add(set);
        } else {
            if (!set.contains(board[y][x])) {
                set.add(board[y][x]);
                if (x + 1 < N) {
                    dfs(new HashSet<>(set), x + 1, y);
                }
                if (y + 1 < M) {
                    dfs(new HashSet<>(set), x, y + 1);
                }
            }
        }
    }
}