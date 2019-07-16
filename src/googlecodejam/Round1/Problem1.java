package googlecodejam.Round1;

import java.io.*;
import java.util.StringTokenizer;

public class Problem1 {
    private static int P, Q;

    private static int DIR_EAST = 0;
    private static int DIR_WEST = 1;
    private static int DIR_SOUTH = 2;
    private static int DIR_NORTH = 3;

    private static int[] offsetX = {1, -1, 0, 0};
    private static int[] offsetY = {0, 0, -1, 1};
    private static Person[] people;

    private static int max, maxX, maxY;

    private static int[][] map;

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int T = Integer.parseInt(br.readLine());

            StringBuilder sb = new StringBuilder();

            for (int t = 1; t <= T; t++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                P = Integer.parseInt(st.nextToken());
                Q = Integer.parseInt(st.nextToken());

                people = new Person[P];
                map = new int[Q + 1][Q + 1];

                max = 0;
                maxX = 0;
                maxY = 0;

                for (int p = 0; p < P; p++) {
                    st = new StringTokenizer(br.readLine());

                    int x = Integer.parseInt(st.nextToken());
                    int y = Integer.parseInt(st.nextToken());
                    char dirChar = st.nextToken().charAt(0);
                    int dir = 0;
                    switch (dirChar) {
                        case 'E':
                            dir = DIR_EAST;
                            break;
                        case 'W':
                            dir = DIR_WEST;
                            break;
                        case 'S':
                            dir = DIR_SOUTH;
                            break;
                        case 'N':
                            dir = DIR_NORTH;
                            break;
                    }

                    people[p] = new Person(x, y, dir);
                }

                travel();

                sb.append("Case #").append(t).append(": ");
                sb.append(maxX).append(' ').append(maxY).append('\n');
            }
            bw.write(sb.toString());
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void travel() {
        for (Person person : people) {
            int x = person.x;
            int y = person.y;

            if (person.direction == DIR_NORTH) {
                for (int i = person.y + 1; i <= Q; i++) {
                    for (int j = 0; j <= Q; j++) {
                        map[i][j]++;
                    }
                }
            } else if (person.direction == DIR_SOUTH) {
                for (int i = person.y - 1; i >= 0; i--) {
                    for (int j = 0; j <= Q; j++) {
                        map[i][j]++;
                    }
                }
            } else if (person.direction == DIR_WEST) {
                for (int i = person.x - 1; i >= 0; i--) {
                    for (int j = 0; j <= Q; j++) {
                        map[j][i]++;
                    }
                }
            } else if (person.direction == DIR_EAST) {
                for (int i = person.x + 1; i <= Q; i++) {
                    for (int j = 0; j <= Q; j++) {
                        map[j][i]++;
                    }
                }
            }

            for (int i = 0; i <= Q; i++) {
                for (int j = 0; j <= Q; j++) {
                    if (max < map[j][i]) {
                        max = map[j][i];
                        maxX = i;
                        maxY = j;
                    }
                }
            }
        }
    }

    static class Person {
        private int x;
        private int y;
        private int direction;

        public Person(int x, int y, int direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }
    }
}
