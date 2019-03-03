package baekjoon;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * https://www.acmicpc.net/problem/4355
 * 4355. 서로소
 * <p>
 * 걍 n - (n의 약수의 개수) -
 */

public class P4355_Seoroso {

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringBuilder sb = new StringBuilder();

            while (true) {
                int N = Integer.parseInt(br.readLine());
                int result = 1;

                List<Soinsoo> soinsoos = new ArrayList<>();

                if (N == 0) {
                    break;
                }

                int temp = 2;
                while (true) {
                    if (N == 1) {
                        break;
                    } else if (temp > Math.sqrt(N)) {
                        soinsoos.add(new Soinsoo(N, 1));
                        break;
                    } else if (N % temp == 0) {
                        int pow = 0;
                        while (N % temp == 0) {
                            N /= temp;
                            pow++;
                        }
                        soinsoos.add(new Soinsoo(temp, pow));
                    }

                    temp++;
                }

                for(Soinsoo s : soinsoos){
                    result*=(s.number-1)*Math.pow(s.number,s.pow-1);
                }

                sb.append(result).append('\n');
            }

            bw.write(sb.toString());
            bw.flush();
        }
    }

    static class Soinsoo {
        private final int number;
        private final int pow;

        public Soinsoo(int number, int pow) {
            this.number = number;
            this.pow = pow;
        }
    }
}
