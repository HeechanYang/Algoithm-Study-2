# 2019.02.26.(화)
## [[백준온라인] 1012. OrganicCabbage](https://www.acmicpc.net/problem/1012)

## 풀이

1. A와 B가 움직이는 경로의 좌표를 배열에 삽입한다.
```java
st = new StringTokenizer(br.readLine());
for (int i = 1; i < M; i++) {
    int command = Integer.parseInt(st.nextToken());
    pathA[i] = move(pathA[i - 1], command);
}

st = new StringTokenizer(br.readLine());
for (int i = 1; i < M; i++) {
    int command = Integer.parseInt(st.nextToken());
    pathB[i] = move(pathB[i - 1], command);
}
```

2. 각 path마다 영향받는 BC의 리스트를 조회한다.
```java
private static List<BC> getBCListAt(Coord coord) {
    List<BC> bcList = new ArrayList<>();
    for (BC bc : bcs) {
        int distance = getDistance(bc.getCoord(), coord);
        if (bc.getCoverage() >= distance) {
            bcList.add(bc);
        }
    }

    return bcList;
}
```
3. 이 때 performance가 좋은 BC순으로 삽입한다.
```java
Arrays.sort(bcs);
```
4. 이제 다시 A와 B 각각의 path를 돌아가며 BC 리스트 중 0 번째 BC를 가져온다.
```java
for (int i = 0; i < M; i++) {
    Coord coordA = pathA[i];
    Coord coordB = pathB[i];

    List<BC> bcListA = getBCListAt(coordA);
    List<BC> bcListB = getBCListAt(coordB);

    int cntA = bcListA.size();
    int cntB = bcListB.size();

    BC bcA = null;
    BC bcB = null;

    int a = 0;
    int b = 0;

    if (cntA > 0) {
        bcA = bcListA.get(0);
        a = bcA.getPerformance();
    }
    if (cntB > 0) {
        bcB = bcListB.get(0);
        b = bcB.getPerformance();
    }
...
}
```
5. aBC와 bBC가 같지 않다면 충돌이 나지 않은 것으로 그대로 더해주면 된다.
    
6. 만약 aBC와 bBC가 같다면 충돌이 난 것으로, 각각 다음 BC를 가져오고, 그 둘 중 더 큰 것을 적용하면 된다.
    
7. 만약 둘 다 다음 BC가 없다면 나눠가진다.
```java
// 충돌이 났을 경우
if (bcA != null && bcA == bcB) {
    if (cntA == 1 && cntB == 1) { // 서로 하나씩밖에 없으면
        result += bcA.getPerformance();
    } else if (cntA > 1 && cntB == 1) {
        result += bcListA.get(1).getPerformance() + bcB.getPerformance();
    } else if (cntA == 1 && cntB > 1) {
        result += bcA.getPerformance() + bcListB.get(1).getPerformance();
    } else {
        BC tempA = bcListA.get(1);
        BC tempB = bcListB.get(1);
        result += bcA.getPerformance() + Math.max(tempA.getPerformance(), tempB.getPerformance());
    }
} else {
    result += a + b;
}
```

8. 전체 코드

```java
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class P5644_WirelessCharging {
    public static final int DONT_MOVE = 0;
    public static final int MOVE_UP = 1;
    public static final int MOVE_RIGHT = 2;
    public static final int MOVE_DOWN = 3;
    public static final int MOVE_LEFT = 4;

    private static BC[] bcs;
    private static int A;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringBuilder sb = new StringBuilder();

            int T = Integer.parseInt(br.readLine());

            for (int t = 0; t < T; t++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                int M = Integer.parseInt(st.nextToken()) + 1;

                Coord[] pathA = new Coord[M];
                Coord[] pathB = new Coord[M];

                A = Integer.parseInt(st.nextToken());
                bcs = new BC[A];

                pathA[0] = new Coord(1, 1);
                pathB[0] = new Coord(10, 10);

                st = new StringTokenizer(br.readLine());
                for (int i = 1; i < M; i++) {
                    int command = Integer.parseInt(st.nextToken());
                    pathA[i] = move(pathA[i - 1], command);
                }

                st = new StringTokenizer(br.readLine());
                for (int i = 1; i < M; i++) {
                    int command = Integer.parseInt(st.nextToken());
                    pathB[i] = move(pathB[i - 1], command);
                }

                for (int i = 0; i < A; i++) {
                    st = new StringTokenizer(br.readLine());
                    int x = Integer.parseInt(st.nextToken());
                    int y = Integer.parseInt(st.nextToken());
                    int symbol = Integer.parseInt(st.nextToken());
                    int p = Integer.parseInt(st.nextToken());
                    bcs[i] = new BC(new Coord(x, y), symbol, p);
                }

                Arrays.sort(bcs);

                int result = 0;

                for (int i = 0; i < M; i++) {
                    Coord coordA = pathA[i];
                    Coord coordB = pathB[i];

                    List<BC> bcListA = getBCListAt(coordA);
                    List<BC> bcListB = getBCListAt(coordB);

                    int cntA = bcListA.size();
                    int cntB = bcListB.size();

                    BC bcA = null;
                    BC bcB = null;

                    int a = 0;
                    int b = 0;

                    if (cntA > 0) {
                        bcA = bcListA.get(0);
                        a = bcA.getPerformance();
                    }
                    if (cntB > 0) {
                        bcB = bcListB.get(0);
                        b = bcB.getPerformance();
                    }

                    // 충돌이 났을 경우
                    if (bcA != null && bcA == bcB) {
                        if (cntA == 1 && cntB == 1) { // 서로 하나씩밖에 없으면
                            result += bcA.getPerformance();
                        } else if (cntA > 1 && cntB == 1) {
                            result += bcListA.get(1).getPerformance() + bcB.getPerformance();
                        } else if (cntA == 1 && cntB > 1) {
                            result += bcA.getPerformance() + bcListB.get(1).getPerformance();
                        } else {
                            BC tempA = bcListA.get(1);
                            BC tempB = bcListB.get(1);
                            result += bcA.getPerformance() + Math.max(tempA.getPerformance(), tempB.getPerformance());
                        }
                    } else {
                        result += a + b;
                    }

                }
                sb.append(result).append('\n');
            }
            bw.write(sb.toString());
            bw.flush();
        }
    }

    private static List<BC> getBCListAt(Coord coord) {
        List<BC> bcList = new ArrayList<>();
        for (BC bc : bcs) {
            int distance = getDistance(bc.getCoord(), coord);
            if (bc.getCoverage() >= distance) {
                bcList.add(bc);
            }
        }

        return bcList;
    }

    private static int getDistance(Coord a, Coord b) {
        return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());
    }

    private static Coord move(Coord coord, int command) {
        int x = coord.getX();
        int y = coord.getY();

        switch (command) {
            case DONT_MOVE:
                break;
            case MOVE_UP:
                y--;
                break;
            case MOVE_RIGHT:
                x++;
                break;
            case MOVE_DOWN:
                y++;
                break;
            case MOVE_LEFT:
                x--;
                break;
        }

        return new Coord(x, y);
    }

    static class BC implements Comparable {
        private final Coord coord;
        private final int coverage;
        private final int performance;

        BC(Coord coord, int coverage, int performance) {
            this.coord = coord;
            this.coverage = coverage;
            this.performance = performance;
        }

        public Coord getCoord() {
            return coord;
        }

        public int getCoverage() {
            return coverage;
        }

        public int getPerformance() {
            return performance;
        }

        @Override
        public int compareTo(Object o) {
            return ((BC) o).getPerformance() - performance;
        }
    }

    static class Coord {
        private final int x;
        private final int y;

        Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}
```

