package swexpertacademy;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class P1258_FindMatrix {

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            StringBuilder sb = new StringBuilder();

            int T = Integer.parseInt(br.readLine());

            for (int t = 1; t <= T; t++) {
                int N = Integer.parseInt(br.readLine());

                boolean[][] board = new boolean[N][N];
                boolean[][] visited = new boolean[N][N];

                StringTokenizer st;
                for (int n = 0; n < N; n++) {
                    st = new StringTokenizer(br.readLine());
                    for (int m = 0; m < N; m++) {
                        board[n][m] = Integer.parseInt(st.nextToken()) != 0;
                    }
                }

                List<Matrix> matrices = new ArrayList<>();

                for (int y = 0; y < N; y++) {
                    for (int x = 0; x < N; x++) {
                        if (board[y][x] && !visited[y][x]) {
                            // 매트릭스 찾음!
                            int offsetX = x, offsetY;
                            int width = 0;
                            int height = 0;

                            for (offsetY = y; offsetY < N && board[offsetY][offsetX]; offsetY++) {
                                for (; offsetX < N && board[offsetY][offsetX]; offsetX++) {
                                    visited[offsetY][offsetX] = true;
                                }
                                width = offsetX - x;
                                offsetX = x;
                            }
                            height = offsetY - y;

                            matrices.add(new Matrix(height, width));
                        }
                    }
                }

                matrices.sort(new Comparator<Matrix>() {
                    @Override
                    public int compare(Matrix o1, Matrix o2) {

                        if (o1.getSize() == o2.getSize()) {
                            return o2.getHeight() - o1.getHeight();
                        } else {
                            return o1.getSize() - o2.getSize();
                        }
                    }
                });
                sb.append('#').append(t).append(' ').append(matrices.size()).append(' ');

                for (Matrix m : matrices) {
                    sb.append(m.getWidth()).append(' ').append(m.getHeight()).append(' ');
                }

                sb.append('\n');
            }

            bw.write(sb.toString());
            bw.flush();
        }
    }

    static class Matrix {
        private final int width;
        private final int height;
        private final int size;

        Matrix(int width, int height) {
            this.width = width;
            this.height = height;
            this.size = width * height;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }

        public int getSize() {
            return size;
        }
    }
}
