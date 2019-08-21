package programmers;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://programmers.co.kr/learn/courses/30/lessons/1829?language=java
 *
 * 카카오 프렌즈 컬러링북
 */

public class KakaoFriendsColoringBook {
    private static final int[] dx = new int[]{0, 0, 1, -1};
    private static final int[] dy = new int[]{1, -1, 0, 0};

    private boolean[][] visit;

    public int[] solution(int m, int n, int[][] picture) {
        visit = new boolean[m][n];

        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visit[i][j]) {
                    int size = bfs(picture, j, i);
                    if (picture[i][j] != 0) {
                        numberOfArea++;
                        maxSizeOfOneArea = Math.max(maxSizeOfOneArea, size);
                    }
                }
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }

    private int bfs(int[][] picture, int x, int y) {
        int size = 1;
        visit[y][x] = true;
        Queue<Coord> queue = new LinkedList<>();
        Coord temp = new Coord(x, y);
        queue.add(temp);

        while (!queue.isEmpty()) {
            temp = queue.poll();

            for (int i = 0; i < 4; i++) {
                int tx = temp.x + dx[i];
                int ty = temp.y + dy[i];
                if (tx < 0 || ty < 0 || tx >= visit[0].length || ty >= visit.length)
                    continue;
                if (picture[y][x] != picture[ty][tx])
                    continue;

                if (!visit[ty][tx]) {
                    size++;
                    visit[ty][tx] = true;
                    queue.add(new Coord(tx, ty));
                }
            }
        }

        return size;
    }

    class Coord {
        private int x;
        private int y;

        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        KakaoFriendsColoringBook k = new KakaoFriendsColoringBook();
        int[][] ex1 = new int[][]{{1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}};

        int[] result = k.solution(6, 4, ex1);

        System.out.println(result[0] + ", " + result[1]);
    }
}