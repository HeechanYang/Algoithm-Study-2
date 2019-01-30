package codeforces;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Problem4 {
    private static char[] COLORS = new char[]{'R', 'G', 'B'};

    private static int N;
    private static int minChangeCnt;
    private static char[] lampArr;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            StringBuilder sb = new StringBuilder();

            N = Integer.parseInt(br.readLine());

            minChangeCnt = Integer.MAX_VALUE;

            lampArr = new char[N];
            String lampStr = br.readLine();

            for (int i = 0; i < N; i++) {
                lampArr[i] = lampStr.charAt(i);
            }

            solution();

            sb.append(minChangeCnt).append('\n').append(charArr2String(lampArr));

            bw.write(sb.toString());
            bw.flush();
            bw.close();
        }
    }

    private static void solution() {
        List<Integer> haveToChangePositionList = new ArrayList<>();

        for (int i = 1; i < N; i++) {
            char beforeLamp = lampArr[i-1];
            char thisLamp = lampArr[i];

            if (thisLamp == beforeLamp) {
                haveToChangePositionList.add(i);
                i++;
            }
        }
        minChangeCnt = haveToChangePositionList.size();

        for (int haveToChangePosition : haveToChangePositionList) {
            lampArr[haveToChangePosition] = newColor(haveToChangePosition);
        }
    }

    private static char newColor(int i) {
        char beforeColor = lampArr[i-1];
        char afterColor = 0;
        if (i < N - 1) {
            afterColor = lampArr[i+1];
        }

        for (char color : COLORS) {
            if (color != beforeColor) {
                if (i < N - 1 && color != afterColor) {
                    return color;
                } else if (i >= N - 1) {
                    return color;
                }
            }
        }

        //ERROR!
        return 0;
    }

    public static StringBuilder charArr2String(char[] charArr){
        StringBuilder sb = new StringBuilder();
        for (char c : charArr) {
            sb.append(c);
        }
        return sb;
    }
}