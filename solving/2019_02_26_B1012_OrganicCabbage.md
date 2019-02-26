# 2019.02.26.(화)
## [[백준온라인] 1012. OrganicCabbage](https://www.acmicpc.net/problem/1012)

## 풀이

* 처음엔 bfs방식으로 각 area를 구하여 카운팅했지만 시간초과 뜸
* 그 다음엔 dfs방식으로 구하여 해결

1. bfs로 구하는 방식(시간초과)
```java
private static void makeArea(int x, int y) {
    Queue<Coord> queue = new LinkedList<>();
    queue.add(new Coord(x, y));

    while (!queue.isEmpty()) {
        Coord thisCoord = queue.poll();
        x = thisCoord.getX();
        y = thisCoord.getY();
        land[x][y] = false;

        if (x > 0 && land[x - 1][y]) {
            queue.add(new Coord(x - 1, y));
        }
        if (x < M - 1 && land[x + 1][y]) {
            queue.add(new Coord(x + 1, y));
        }
        if (y > 0 && land[x][y - 1]) {
            queue.add(new Coord(x, y - 1));
        }
        if (y < N - 1 && land[x][y + 1]) {
            queue.add(new Coord(x, y + 1));
        }

    }
}
```

2. dfs로 구하는 방식(시간초과)
```java
private static void makeArea(int x, int y) {
    land[x][y] = false;
    if (x > 0 && land[x - 1][y]) {
        makeArea(x - 1, y);
    }
    if (x < M - 1 && land[x + 1][y]) {
        makeArea(x + 1, y);
    }
    if (y > 0 && land[x][y - 1]) {
        makeArea(x, y - 1);
    }
    if (y < N - 1 && land[x][y + 1]) {
        makeArea(x, y + 1);
    }
}
```

3. 전체 코드
```java
import java.io.*;
import java.util.StringTokenizer;

public class P1012_OrganicCabbage {
    private static int M, N, K;
    private static boolean[][] land;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringBuilder sb = new StringBuilder();

            int T = Integer.parseInt(br.readLine());

            for (int i = 0; i < T; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                M = Integer.parseInt(st.nextToken());
                N = Integer.parseInt(st.nextToken());
                K = Integer.parseInt(st.nextToken());

                land = new boolean[M][N];

                int x, y;
                for (int j = 0; j < K; j++) {
                    st = new StringTokenizer(br.readLine());
                    x = Integer.parseInt(st.nextToken());
                    y = Integer.parseInt(st.nextToken());
                    land[x][y] = true;
                }

                int areaCnt = 0;
                for (int m = 0; m < M; m++) {
                    for (int n = 0; n < N; n++) {
                        if (land[m][n]) {
                            makeArea(m, n);
                            areaCnt++;
                        }
                    }
                }

                sb.append(areaCnt).append('\n');
            }

            bw.write(sb.toString());
            bw.flush();
//            bw.close();
        }
    }

    private static void makeArea(int x, int y) {
        land[x][y] = false;

        if (x > 0 && land[x - 1][y]) {
            makeArea(x - 1, y);
        }
        if (x < M - 1 && land[x + 1][y]) {
            makeArea(x + 1, y);
        }
        if (y > 0 && land[x][y - 1]) {
            makeArea(x, y - 1);
        }
        if (y < N - 1 && land[x][y + 1]) {
            makeArea(x, y + 1);
        }
    }
}
```