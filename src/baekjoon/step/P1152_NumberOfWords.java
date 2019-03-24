package baekjoon.step;

import java.io.*;
import java.util.StringTokenizer;

public class P1152_NumberOfWords {
    public static void main(String[] args) throws IOException {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))){
            StringTokenizer st = new StringTokenizer(br.readLine());
            bw.write(String.valueOf(st.countTokens()));
            bw.flush();
        }
    }
}
