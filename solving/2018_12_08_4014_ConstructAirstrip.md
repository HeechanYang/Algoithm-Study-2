# 2018.12.08.(토)
## [4014. [모의 SW 역량테스트] 활주로 건설](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWIeW7FakkUDFAVH&)

1. 각 줄에서 활주로 조건을 만족하지 못하는 경우의 수는 다음과 같다.
    - 높이가 1 높아지는데, 왼쪽에 경사로를 놓으면 땅을 넘어가거나, 다른 경사로와 겹칠 경우.
    - 높이가 1 낮아지는데, 오른쪽에 경사로를 놓으면 땅을 넘어가거나, 오른쪽으로 X칸만큼 평평하지 않은 경우.
    - 높이차가 2이상 나는 경우.

2. 따라서 각 라인을 (1)의 기준으로 체크해주면 됨.

3. 세로줄을 검사하기위해 90도 회전

```java
// 세로줄을 검사하기위해 90도 회전
int[][] rotatedLand = rotateLand(land);
```

4. 각 라인에 대해 활주로 건설이 가능한지 검사하는 메서드 생성

```java
private static boolean isEnable(int X, int N, int[] line) {
    int to = -1;

    // 앞에서부터 차례대로 검사
    for (int i = 0; i < N - 1; i++) {
        int thisValue = line[i];
        int nextValue = line[i + 1];
        int sub = nextValue - thisValue;

        switch (sub) {
            case 0: // 평평한 경우
                break;
            case 1: // 높이가 1 높아지는 경우. (왼쪽에 경사로를 놓아야함)
                // to까지는 경사로를 놓을 수 없기 때문에 false. 
                // (land를 넘어가거나, 이미 그 칸에 경사로가 있거나)
                if ((i - to) < X) {
                    return false;
                }

                // i까지 경사로를 놓았으니 to값 변경
                to = i;
                break;
            case -1: // 높이가 1 낮아지는 경우. (오른쪽에 경사로를 놓아야함)
                // land를 넘어가기 때문에 false.
                if (N - 1 < i + X) {
                    return false;
                }
                // 오른쪽으로 X칸만큼 평평한지 체크
                for (int j = i + 1; j < i + X; j++) {
                    int thisValueTemp = line[j];
                    int nextValueTemp = line[j + 1];
                    int subTemp = thisValueTemp - nextValueTemp;
                    // 평평하지 않으면 false
                    if (subTemp != 0) {
                        return false;
                    }
                }

                // (i + X)까지 경사로를 놓았으니 to값 변경
                to = i + X;
                i = to - 1;
                break;
            default: // 높이차가 2이상인 경우 false
                return false;
        }
    }

    return true;
}
```

5. 각 라인 검사
```java
int cnt = 0;

//가로세로 각 줄 체크
for (int i = 0; i < N; i++) {
    // 가로 i번째 줄 체크
    if (isEnable(X, N, land[i])) cnt++;
    // 세로 i번째 줄 체크
    if (isEnable(X, N, rotatedLand[i])) cnt++;
}
```

6. 결과값 출력

```java
// 결과값 출력
System.out.printf("#%d %d\n", (i + 1), solution(land, X));
```

# 전체 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWIeW7FakkUDFAVH&
 * <p>
 * 4014. [모의 SW 역량테스트] 활주로 건설
 */

public class P4014_ConstructAirstrip {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int T = Integer.parseInt(br.readLine());

            // Loop T times
            for (int i = 0; i < T; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                int N = Integer.parseInt(st.nextToken());
                int X = Integer.parseInt(st.nextToken());

                int[][] land = new int[N][N];

                for (int j = 0; j < N; j++) {
                    String s = br.readLine();
                    st = new StringTokenizer(s);
                    for (int k = 0; k < N; k++) {
                        land[j][k] = Integer.parseInt(st.nextToken());
                    }
                }

                // Print result
                System.out.printf("#%d %d\n", (i + 1), solution(land, X));
            }
        }
    }

    private static int solution(int[][] land, int X) {
        int N = land.length;

        int cnt = 0;

        // 세로줄을 검사하기위해 90도 회전
        int[][] rotatedLand = rotateLand(land);

        //가로세로 각 줄 체크
        for (int i = 0; i < N; i++) {
            // 가로 i번째 줄 체크
            if (isEnable(X, N, land[i])) cnt++;
            // 세로 i번째 줄 체크
            if (isEnable(X, N, rotatedLand[i])) cnt++;
        }

        return cnt;
    }

    // 해당 라인에 활주로 건설 가능 여부를 반환하는 메서드
    private static boolean isEnable(int X, int N, int[] line) {
        int to = -1;

        // 앞에서부터 차례대로 검사
        for (int i = 0; i < N - 1; i++) {
            int thisValue = line[i];
            int nextValue = line[i + 1];
            int sub = nextValue - thisValue;

            switch (sub) {
                case 0: // 평평한 경우
                    break;
                case 1: // 높이가 1 높아지는 경우. (왼쪽에 경사로를 놓아야함)
                    // to까지는 경사로를 놓을 수 없기 때문에 false.
                    // (land를 넘어가거나, 이미 그 칸에 경사로가 있거나)
                    if ((i - to) < X) {
                        return false;
                    }

                    // i까지 경사로를 놓았으니 to값 변경
                    to = i;
                    break;
                case -1: // 높이가 1 낮아지는 경우. (오른쪽에 경사로를 놓아야함)
                    // land를 넘어가기 때문에 false.
                    if (N - 1 < i + X) {
                        return false;
                    }
                    // 오른쪽으로 X칸만큼 평평한지 체크
                    for (int j = i + 1; j < i + X; j++) {
                        int thisValueTemp = line[j];
                        int nextValueTemp = line[j + 1];
                        int subTemp = thisValueTemp - nextValueTemp;
                        // 평평하지 않으면 false
                        if (subTemp != 0) {
                            return false;
                        }
                    }

                    // (i + X)까지 경사로를 놓았으니 to값 변경
                    to = i + X;
                    i = to - 1;
                    break;
                default: // 높이차가 2이상인 경우 false
                    return false;
            }
        }

        return true;
    }

    //2차원 배열 회전
    public static int[][] rotateLand(int[][] land) {
        int lenX = land[0].length;
        int lenY = land.length;

        int[][] rotatedLand = new int[lenX][lenY];

        for (int i = 0; i < lenX; i++) {
            for (int j = 0; j < lenY; j++) {
                rotatedLand[i][j] = land[j][lenX - i - 1];
            }
        }

        return rotatedLand;
    }
}
```