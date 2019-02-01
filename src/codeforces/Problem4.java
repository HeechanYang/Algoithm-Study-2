package codeforces;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * https://codeforces.com/contest/1108/problem/D
 * D. Diverse Garland
 *
 * C번과 비슷한 문제이지만 풀이방법은 다름
 * 각 램프의 색깔은 R, G, B중 하나.
 * 각 색깔이 연속으로 있으면 안됨
 * - ex) RGBGBGBGBGB
 * - ex) GBRGRGRGRG
 * - ex) GRBRBRBRBRB
 *
 * C번의 핵심 전략은 다음과 같았음.
 * - 램프 색깔 패턴의 모든 조합을 구하고 해당 패턴들과 램프의 개수를 이용해 정답셋을 구해놓는다.
 * 하지만 D번에서는 수 많은 정답셋이 나올 수 있기 때문에 같은 방법을 사용하기 어려움.
 *
 * [해결방법]
 * - 그냥 쭉 루프돌면서 연속으로 존재하는 색깔의 램프(바뀌어야하는 램프)의 인덱스를 구해놓는다.
 *   - 주의해야할 것은 연속이 시작된 이후 짝수번째만 바꾸어야함
 *     - 만약 RRR, RRRR과 같이 3번 이상 연속으로 나오면 R(R)R, R(R)R(R)과 같이 짝수 번째만!
 * - 다시 루프를 돌면서 해당 인덱스의 램프들의 색깔을 변경하는데, 변경할 때 주변의 램프들의 색깔과 다른 색을 칠한다
 */

public class Problem4 {
    private static char[] COLORS = new char[]{'R', 'G', 'B'};

    private static int N;
    private static int minChangeCnt;
    private static char[] lampArr;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            StringBuilder sb = new StringBuilder();

            N = Integer.parseInt(br.readLine());

            minChangeCnt = Integer.MAX_VALUE;

            lampArr = new char[N];
            String lampStr = br.readLine();

            for (int i = 0; i < N; i++) {
                lampArr[i] = lampStr.charAt(i);
            }

            solution();

            sb.append(minChangeCnt).append('\n').append(charArr2String(lampArr));

            bw.write(sb.toString());
            bw.flush();
            bw.close();
        }
    }

    private static void solution() {
        List<Integer> haveToChangePositionList = new ArrayList<>();

        for (int i = 1; i < N; i++) {
            char beforeLamp = lampArr[i-1];
            char thisLamp = lampArr[i];

            if (thisLamp == beforeLamp) {
                haveToChangePositionList.add(i);
                i++;
            }
        }
        minChangeCnt = haveToChangePositionList.size();

        for (int haveToChangePosition : haveToChangePositionList) {
            lampArr[haveToChangePosition] = newColor(haveToChangePosition);
        }
    }

    private static char newColor(int i) {
        char beforeColor = lampArr[i-1];
        char afterColor = 0;
        if (i < N - 1) {
            afterColor = lampArr[i+1];
        }

        for (char color : COLORS) {
            if (color != beforeColor) {
                if (i < N - 1 && color != afterColor) {
                    return color;
                } else if (i >= N - 1) {
                    return color;
                }
            }
        }

        //ERROR!
        return 0;
    }

    public static StringBuilder charArr2String(char[] charArr){
        StringBuilder sb = new StringBuilder();
        for (char c : charArr) {
            sb.append(c);
        }
        return sb;
    }
}