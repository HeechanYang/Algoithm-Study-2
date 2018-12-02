package swexpertacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXRUN9KfZ8DFAUo&
 * <p>
 * 5658. [모의 SW 역량테스트] 보물상자 비밀번호
 */

public class P5658_TreasureBoxPassword {
    public static void main(String[] args) throws IOException {
        try (final BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int T = Integer.parseInt(br.readLine());

            for (int i = 1; i <= T; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int N = Integer.parseInt(st.nextToken());
                int K = Integer.parseInt(st.nextToken());

                String passWordStr = br.readLine();

                Set<String> passwordSet = getPasswordSet(passWordStr);

                // 각 Set을 Int형으로 List에 저장
                List<Integer> passwordLongList = new ArrayList<>();
                for (String password : passwordSet) {
                    passwordLongList.add(Integer.parseInt(password, 16));
                }

                // 내림차순 정렬
                passwordLongList.sort(Collections.reverseOrder());

                // K 번째 요소 출력
                System.out.printf("#%d %d\n", i, passwordLongList.get(K - 1));
            }
        }
    }

    // 비밀번호 Set을 추출하는 함수
    private static Set<String> getPasswordSet(String passWordStr) {
        int passwordLen = passWordStr.length();
        StringBuilder passwordSb = new StringBuilder(passWordStr);

        Set<String> passwordSet = new HashSet<>();

        // 한 칸씩 돌림
        for (int i = 0; i < passwordLen; i++) {
            char firstChar = passwordSb.charAt(0);
            passwordSb.append(firstChar);
            passwordSb.deleteCharAt(0);

            // 새로운 문자열을 4등분한 후 Set에 저장
            for (int j = 0; j < 4; j++) {
                int startIdx = j * passwordLen / 4;
                int endIdx = (j + 1) * passwordLen / 4;
                String subStr = passwordSb.substring(startIdx, endIdx);
                passwordSet.add(subStr);
            }

        }

        return passwordSet;
    }
}
