package baekjoon.step.sort;

import java.io.*;
import java.util.*;

public class P1427_SortNumbersDesc {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringBuilder sb = new StringBuilder(20000);

            int N = Integer.parseInt(br.readLine());
            Set<String> words = new HashSet<>();

            for (int i = 0; i < N; i++) {
                words.add(br.readLine());
            }

            List<String> wordList = new ArrayList<>(words);

            Collections.sort(wordList, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    if (o1.length() != o2.length()) {
                        return o1.length() - o2.length();
                    } else {
                        for (int i = 0; i < o1.length(); i++) {
                            if (o1.charAt(i) != o2.charAt(i)) {
                                return o1.charAt(i) - o2.charAt(i);
                            }
                        }
                    }
                    return 0;
                }
            });

            for (String word : wordList) {
                sb.append(word).append('\n');
            }

            bw.write(sb.toString());
            bw.flush();
        }
    }
}
