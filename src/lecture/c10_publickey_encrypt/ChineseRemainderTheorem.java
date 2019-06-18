package lecture.c10_publickey_encrypt;

import java.util.ArrayList;
import java.util.List;

/**
 * 중국인의 나머지 정리 (CRT : Chinese Remainder Theorem)
 * - 연립 합동식의 유일한 해를 찾는 정리
 */
public class ChineseRemainderTheorem {

    public static void main(String[] args) {
        // x ≡ 2 (mod 3)
        // x ≡ 3 (mod 5)
        // x ≡ 2 (mod 7)
        Congruence c1 = new Congruence(3, 2);
        Congruence c2 = new Congruence(5, 3);
        Congruence c3 = new Congruence(7, 2);

        System.out.println("x: " + chineseRemainderTheorem(List.of(c1, c2, c3)));
    }

    private static int chineseRemainderTheorem(List<Congruence> congruenceList) {
        // m 초기화
        int m = 1;
        for (Congruence c : congruenceList) {
            m *= c.getModular();
        }

        // nList 초기화
        List<Integer> nList = new ArrayList<>();
        for (Congruence c : congruenceList) {
            int n = m / c.getModular();
            nList.add(n);
        }

        // sList(역원) 초기화
        List<Integer> sList = new ArrayList<>();
        for (int i = 0; i < congruenceList.size(); i++) {
            int modular = congruenceList.get(i).getModular();
            // ?
            int newN = nList.get(i) % modular;
            int s = ExtendedEuclidean.getReverse(newN, modular);
            s = s > 0 ? s : s + modular;
            sList.add(s);
        }

        // x 계산
        int sum = 0;
        for (int i = 0; i < congruenceList.size(); i++) {
            int a = congruenceList.get(i).getRemainder();
            int n = nList.get(i);
            int s = sList.get(i);
            sum += a * n * s;
        }

        return sum % m;
    }

    private static class Congruence {
        private final int modular;
        private final int remainder;

        public Congruence(int modular, int remainder) {
            this.modular = modular;
            this.remainder = remainder;
        }

        public int getModular() {
            return modular;
        }

        public int getRemainder() {
            return remainder;
        }
    }
}