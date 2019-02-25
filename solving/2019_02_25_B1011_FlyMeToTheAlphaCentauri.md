# 2019.02.25.(월)
## [[백준온라인] 1011. FlyMeToTheAlphaCentauri](https://www.acmicpc.net/problem/1011)

## 풀이

* 먼저 y - x를 하여 총 거리 dist를 구한 후,
* 최대로 증가할 수 있는 이동거리를 구한다.
    * 1 + 2 + ... + (n-1) + n + (n-1) + ... + 2 + 1 = n^2
    * 그러므로, `n^2 < dist`를 만족하는 n을 구해주면 된다. ( `n = (int)Math.sqrt(dist); cnt += n * n` )
* 여기서 중요한 점은 이 우주선은 1 ~ n의 모든 이동거리를 얼마든지 반복할 수 있다.
    * 작동을 최소한으로 하기 위해 남은 거리 `dist - n^2`에서 n만큼 최대한 이동한다. (`cnt += (int)dist/n`)
    * 그래도 거리가 남아 더 이동해야한다면 1~n 광년 중 한 번을 이동하면 된다. (`cnt++`)

1. 먼저 y - x를 하여 총 거리 dist를 구한다.
    - 1 + 2 + ... + (n-1) + n + (n-1) + ... + 2 + 1 = n^2
    - 그러므로, `n^2 < dist`를 만족하는 n을 구해주면 된다.
```java
int dist = y - x;
```

2. 최대로 증가할 수 있는 이동거리를 구한다.
```java
int max = (int) Math.sqrt(dist);
cnt += max * 2 - 1;
dist -= max * max;
```
3. 작동을 최소한으로 하기 위해 남은 거리 `dist - n^2`에서 n만큼 최대한 이동한다.
```java
cnt += (int) dist / max;
```

4. 그래도 거리가 남아 더 이동해야한다면 1~n 광년 중 한 번을 이동하면 된다.
```java
if (dist % max != 0) {
    cnt++;
}
```

53. 전체 코드
```java
import java.io.*;
import java.util.StringTokenizer;

public class P1011_FlyMeToTheAlphaCentauri {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringBuilder sb = new StringBuilder();

            int T = Integer.parseInt(br.readLine());

            for (int i = 0; i < T; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int dist = y - x;

                // 해결

                int cnt = 0;

                int max = (int) Math.sqrt(dist);
                cnt += max * 2 - 1;
                dist -= max * max;

                cnt += (int) dist / max;

                if (dist % max != 0) {
                    cnt++;
                }

                sb.append(cnt).append('\n');
            }

            bw.write(sb.toString());
            bw.flush();
//            bw.close();
        }
    }
}
```