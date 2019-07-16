package lecture.c10_publickey_encrypt;

import java.util.ArrayList;
import java.util.List;

/**
 * 중국인의 나머지 정리 (CRT : Chinese Remainder Theorem)
 * - 연립 합동식의 유일한 해를 찾는 정리
 * - 매우 큰 수에대한 모듈러 연산 수행 가능
 */
public class ChineseRemainderTheorem {

    public static void main(String[] args) {
        // x ≡ ai (mod mi)
        // ---------------
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
        // m = m1 * m2 * ... * mk
        int m = 1;
        for (Congruence c : congruenceList) {
            int mi = c.getModular();
            m *= mi;
        }

        // nList 초기화
        // ni = m / mi
        // ----------------
        // n1 = m / m1
        // n2 = m / m2
        // ....
        List<Integer> nList = new ArrayList<>();
        for (Congruence c : congruenceList) {
            int mi = c.getModular();
            int ni = m / mi;
            nList.add(ni);
        }

        // sList(역원) 초기화
        // si * ni ≡ 1 (mod mi)  --> si는 ni의 역원
        // ------------------------
        // s1 * 35 ≡ 1 (mod 3)
        // s2 * 21 ≡ 1 (mod 5)
        // s3 * 15 ≡ 1 (mod 7)
        // 확장 유클리드를 이용하여 si를 구하자!
        // a * s ≡ 1 (mod p)
        // -> as + pt = 1
        // si = 확장유클리드.역원(ni, mi)
        List<Integer> sList = new ArrayList<>();
        for (int i = 0; i < congruenceList.size(); i++) {
            int mi = congruenceList.get(i).getModular();
            int ni = nList.get(i);

            int si = ExtendedEuclidean.getReverse(ni, mi);
            si = si > 0 ? si : si + mi;
            sList.add(si);
        }

        // x 계산
        // x = (a1n1s1 + a2n2s2 + ... + aknksk) % m
        int x = 0;
        for (int i = 0; i < congruenceList.size(); i++) {
            int ai = congruenceList.get(i).getRemainder();
            int ni = nList.get(i);
            int si = sList.get(i);
            x += ai * ni * si;
        }

        return x % m;
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