# 2019.02.24.(일)
## [[백준온라인] 1009. 분산 처리](https://www.acmicpc.net/problem/1009)

## 풀이

* 그냥 a^b의 일의 자리 숫자를 구하면 된다.
* - 좀 더 간단히 하여 (a의 일의 자리 숫자)^b의 일의 자리 숫자를 구하면 된다.
* - 또한, 다음을 보면 각 제곱수의 일의 자리 숫자는 못해도 4 번에 한 번은 반복되는 것을 볼 수 있다.
* - 결국 구해야할 것은 `(a의 일의 자리 숫자)^(b % 4)의 일의 자리 숫자`인 것이다

* 각 수의 제곱수의 일의 자리 숫자는 다음과 같다.
    * 0 - 0, 0, 0, 0, 0, ...
    * 1 - 1, 1, 1, 1, 1, ...
    * 2 - 2, 4, 8, 6, 2, ...
    * 3 - 3, 9, 7, 1, 3, ...
    * 4 - 4, 6, 4, 6, 4, ...
    * 5 - 5, 5, 5, 5, 5, ...
    * 6 - 6, 6, 6, 6, 6, ...
    * 7 - 7, 9, 3, 1, 7, ...
    * 8 - 8, 4, 2, 6, 8, ...
    * 9 - 9, 1, 9, 1, 9, ...
   

1. 각 a, b를 입력받을 때 바로 a%10과 b%4를 대입한다.
    - 주의해야 할 점은 b가 4의 배수일 때 0이 아닌 4를 대입해야 한다.
```java
int a = Integer.parseInt(st.nextToken()) % 10;
// [0,3]이 아닌 [1,4]를 구해야한다
int b = (Integer.parseInt(st.nextToken()) + 3) % 4 + 1;
``` 

2. 그 다음 a^b의 1의 자리를 구한다.
    - 이 때도 a^b가 10의 배수일 때 0이 아닌 10을 대입하도록 한다.
```java
// [0,9]가 아닌 [1,10]을 구해야한다
int result = ((int)Math.pow(a,b) + 9) % 10 + 1;
```

3. 전체 코드
```java
import java.io.*;
import java.util.StringTokenizer;

public class P1009_DistributedProcessing {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringBuilder sb = new StringBuilder();

            int T = Integer.parseInt(br.readLine());

            for (int i = 0; i < T; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken()) % 10;
                // [0,3]이 아닌 [1,4]를 구해야한다
                int b = (Integer.parseInt(st.nextToken()) + 3) % 4 + 1;

                // [0,9]가 아닌 [1,10]을 구해야한다
                int result = ((int)Math.pow(a,b) + 9) % 10 + 1;

                sb.append(result).append('\n');
            }

            bw.write(sb.toString());
            bw.flush();
//            bw.close();
        }
    }
}
```