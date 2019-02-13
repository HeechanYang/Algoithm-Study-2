# 2019.02.06.(수)
## [[백준온라인] 1005. ACM Craft](https://www.acmicpc.net/problem/1005)

## 풀이

- 두 점 A, B를 잇는 벡터는 임의의 점 C를 이용해 AC + CB (AC - BC)와 같이 표현할 수 있다.
  1. 임의의 점 C를 (0, 0)으로 둔다
  2. N개의 점이 있다면, 각각의 두 점을 잇는 벡터들을 표현하려면, 점 C와 잇는 벡터는 아래와 같아야 한다.
   - N/2개의 점(A)은 A (C->A)
   - N/2개의 점(B)는 -B (B->A)
  3. 그렇다면 두 점 A, B를 잇는 벡터는 결국 A - B로 나타낼 수가 있게 된다.
- 결국, 각 점을 A1부터 An까지로 나타낸다면, 각 두 점을 잇는 벡터들의 합은
  - (A1 + A2 + ... + An) - 2 * (A1 + A2 + ... + A(n / 2))로 나타낼 수 있다.
- 여기서 어떤 점들이 A1 ~ A(n/2)에 속하게 될지 알기 위해서 각 점을 전부 집어넣어 본다.
  - 경우의 수는 n개중 n/2를 순서없이 고르는 것이니 -> (n)C(n/2)
  - n은 최대 20이니 최대 (20 * 19 * ... * 11) / (10 * 9 * ... * 1) = 184,756 회

1. 각 점을 담을 Dot Class 생성
```java
static class Dot {
    private final long x;
    private final long y;

    public Dot(long x, long y) {
        this.x = x;
        this.y = y;
    }

    public long getX() {
        return x;
    }

    public long getY() {
        return y;
    }
}
``` 

2. 각 경우의 수를 계산하기 위해 재귀를 돌려 최솟값을 반환할 solution() 메서드 구현
```java
private static long solution(int cnt, int position, long sx, long sy) {
    if (cnt == N / 2) {
        return (sumX - sx - sx) * (sumX - sx - sx) + (sumY - sy - sy) * (sumY - sy - sy);
    } else {
        long result = Long.MAX_VALUE;
        cnt++;

        while (position < N) {
            result = Math.min(result
                    , solution(cnt, position + 1, sx + dots.get(position).x, sy + dots.get(position).y));
            position++;
        }
        return result;
    }
}
```

3. 전체 코드
```java
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class p1007_VetctorMatching {
    private static int T, N;
    private static List<Dot> dots;

    private static int sumX;
    private static int sumY;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringBuilder resultString = new StringBuilder();

            T = Integer.parseInt(br.readLine());

            for (int i = 0; i < T; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                N = Integer.parseInt(st.nextToken());

                dots = new ArrayList<>(20 + 1);

                sumX = 0;
                sumY = 0;

                for (int j = 0; j < N; j++) {
                    st = new StringTokenizer(br.readLine());
                    long x = Long.parseLong(st.nextToken());
                    long y = Long.parseLong(st.nextToken());
                    Dot dot = new Dot(x, y);

                    dots.add(dot);

                    sumX += x;
                    sumY += y;
                }

                long result = solution(0, 0, 0, 0);
                double resultLength = result != 0 ? Math.sqrt(result) : result;
                resultString.append(resultLength).append('\n');
            }

            bw.write(resultString.toString());
            bw.flush();
        }
    }

    private static long solution(int cnt, int position, long sx, long sy) {
        if (cnt == N / 2) {
            return (sumX - sx - sx) * (sumX - sx - sx) + (sumY - sy - sy) * (sumY - sy - sy);
        } else {
            long result = Long.MAX_VALUE;
            cnt++;

            while (position < N) {
                result = Math.min(result
                        , solution(cnt, position + 1, sx + dots.get(position).getX(), sy + dots.get(position).getY()));
                position++;
            }
            return result;
        }
    }

    static class Dot {
        private final long x;
        private final long y;

        public Dot(long x, long y) {
            this.x = x;
            this.y = y;
        }

        public long getX() {
            return x;
        }

        public long getY() {
            return y;
        }
    }
}
```