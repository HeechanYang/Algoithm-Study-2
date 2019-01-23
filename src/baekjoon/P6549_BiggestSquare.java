package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/6549
 */
public class P6549_BiggestSquare {
    private static int N;
    private static int[] heightArr;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            heightArr = new int[N];

            for (int i = 0; i < N; i++) {
                heightArr[i] = Integer.parseInt(st.nextToken());
            }

            StringBuilder sb = new StringBuilder();

            for (int result : solution()) {
                sb.append(result).append(" ");
            }

            System.out.println(sb);
        }
    }

    public static int[] solution() {
        int[] resultArr = new int[N];
        Deque<Integer> tempDeque = new LinkedList<>();
        Map<Integer, Integer> heightIdxMaps = new HashMap<>();
        int curMin = Integer.MAX_VALUE;

        for (int i = N - 1; i >= 0; i--) {
            int thisHeight = heightArr[i];

            // 현재 타워에 부딪히는 레이저들 조회
            int removeCnt = 0;
            for (int laserHeight : tempDeque) {
                if (laserHeight < thisHeight) {
                    int idxOfSendToThisTop = heightIdxMaps.remove(laserHeight);
                    resultArr[idxOfSendToThisTop] = i + 1;
                    removeCnt++;
                } else {
                    // 더 높은 레이저들은 패쓰
                    // 이거 안해주면 거의 O(N^2)이라 TimeOver 뜸.
                    break;
                }
            }

            for (int j = 0; j < removeCnt; j++) {
                tempDeque.removeFirst();
            }

            // 이렇게하면 남은 레이저들은 정렬된 상태로 들어가있음
            heightIdxMaps.put(thisHeight, i);
            tempDeque.addFirst(thisHeight);
        }

        return resultArr;
    }

}
