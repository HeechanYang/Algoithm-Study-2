package lecture.c10_publickey_encrypt;

public class ExtendedEuclidean {
    public static void main(String[] args) {
        // as + bt = c
        // gcd(1914, 899) = 29, s = 8, t = -17 = (-17) + 29 = 12
        Tuple t = extendedEuclideanGCD(1914, 899);
        int x = t.getS() > 0 ? t.getS() : t.getS() + t.getGcd();
        int y = t.getT() > 0 ? t.getT() : t.getT() + t.getGcd();
        System.out.printf("gcd: %d, x: %d, y: %d\n", t.getGcd(), x, y);
    }

    private static Tuple extendedEuclideanGCD(int a, int b) {
        Tuple d1 = Tuple.createFactory(a, 1, 0, 0);
        Tuple d2 = Tuple.createFactory(b, 0, 1, /*내림*/ a / b);
        return extendedEuclideanGCD(d1, d2);
    }

    // ax + by = c 에서 gcd(a, b), x, y의 값을 구하기 위한 '확장 유클리드 함수'
    // a, b가 양의 정수이기 위해선 c가 gcd(a, b)의 배수여야 함
    private static Tuple extendedEuclideanGCD(Tuple d1, Tuple d2) {
        while (d1.getGcd() % d2.getGcd() != 0) {
            int newS = d1.getS() - d2.getS() * d2.getQ();
            int newT = d1.getT() - d2.getT() * d2.getQ();
            int newGcd = d1.getGcd() - d2.getGcd() * d2.getQ();
            int newQ = /*내림*/ d2.getGcd() / newGcd;
            Tuple newTuple = Tuple.createFactory(newGcd, newS, newT, newQ);

            d1 = d2;
            d2 = newTuple;
        }
        return d2;
    }

    // a * s ≡ 1 (mod p)
    // -> as + pt = 1
    public static int getReverse(int a, int p){
        Tuple tuple = extendedEuclideanGCD(a, p);

        return tuple.getS();
    }

    static class Tuple {
        private final int gcd;
        private final int s;
        private final int t;
        private final int q;

        private Tuple(int gcd, int s, int t, int q) {
            this.gcd = gcd;
            this.s = s;
            this.t = t;
            this.q = q;
        }

        public static Tuple createFactory(int gcd, int s, int t, int q) {
            return new Tuple(gcd, s, t, q);
        }

        public int getGcd() {
            return gcd;
        }

        public int getS() {
            return s;
        }

        public int getT() {
            return t;
        }

        public int getQ() {
            return q;
        }
    }
}