package baekjoon.step.use_string;

import java.io.*;

public class P2941_CroatiaAlphabet {

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int result = 0;

            char[] word = br.readLine().toCharArray();

            for (int i = 0; i < word.length; i++) {
                if (word[i] == 'c') {
                    if (i < word.length - 1 && (word[i + 1] == '=' || word[i + 1] == '-')) {
                        i++;
                    }
                } else if (word[i] == 'd') {
                    if (i < word.length - 1 && (word[i + 1] == '-')) {
                        i++;
                    } else if (i < word.length - 2 && (word[i + 1] == 'z') && (word[i + 2] == '=')) {
                        i+=2;
                    }
                } else if (word[i] == 'l') {
                    if (i < word.length - 1 && (word[i + 1] == 'j')) {
                        i++;
                    }
                } else if (word[i] == 'n') {
                    if (i < word.length - 1 && (word[i + 1] == 'j')) {
                        i++;
                    }
                } else if (word[i] == 's') {
                    if (i < word.length - 1 && (word[i + 1] == '=')) {
                        i++;
                    }
                } else if (word[i] == 'z') {
                    if (i < word.length - 1 && (word[i + 1] == '=')) {
                        i++;
                    }
                }
                result++;
            }

            bw.write(String.valueOf(result));
            bw.flush();
        }
    }
}
