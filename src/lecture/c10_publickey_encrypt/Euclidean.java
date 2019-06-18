package lecture.c10_publickey_encrypt;

public class Euclidean {
    public static void main(String[] args) {
        // gcd(14, 49) = 7
        System.out.println(euclideanGCD(49, 14));
    }

    private static int euclideanGCD(int a, int b) {
        return b == 0 ? a : euclideanGCD(b, a % b);
    }
}
