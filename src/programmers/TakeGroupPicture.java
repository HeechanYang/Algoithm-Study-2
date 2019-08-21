package programmers;

import java.util.ArrayList;
import java.util.List;

public class TakeGroupPicture {
    public static void main(String[] args) {
        TakeGroupPicture t = new TakeGroupPicture();
        t.solution(2, new String[]{"N~F=0", "R~T>2"});
        System.out.println(t.cnt);
        t.solution(2, new String[]{"M~C<2", "C~M>1"});
        System.out.println(t.cnt);
    }

    private static char[] characters = new char[]{'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    private char[] sb;
    private boolean[] visits;
    private List<Condition> conditions;
    private int cnt;

    public int solution(int n, String[] data) {
        sb = new char[characters.length];
        visits = new boolean[characters.length];
        conditions = new ArrayList<>();
        cnt = 0;

        for (String datum : data) {
            conditions.add(new Condition(datum.charAt(0), datum.charAt(2), datum.charAt(3), datum.charAt(4) - '0'));
        }

        combine(0);

        return cnt;
    }

    private void combine(int depth) {
        if (depth == characters.length) {
            if (isValidFormation(sb)) {
                cnt++;
            }
        }
        for (int i = 0; i < characters.length; i++) {
            if (visits[i]) continue;

            sb[depth] = characters[i];
            visits[i] = true;
            combine(depth + 1);
            visits[i] = false;
        }
    }

    private boolean isValidFormation(char[] formation) {
        boolean result = true;
        for (Condition condition : conditions) {
            if (!isValid(formation, condition)) {
                result = false;
                break;
            }
        }

        return result;
    }

    private boolean isValid(char[] formation, Condition condition) {
        int indexA = indexOf(formation, condition.characterA);
        int indexB = indexOf(formation, condition.characterB);

        int distance = Math.abs(indexA - indexB) - 1;

        switch (condition.compare) {
            case '=':
                return distance == condition.distance;
            case '>':
                return distance > condition.distance;
            case '<':
                return distance < condition.distance;
        }

        return false;
    }

    private int indexOf(char[] str, char a) {
        for (int i = 0; i < str.length; i++) {
            if (str[i] == a) return i;
        }

        return -1;
    }

    class Condition {
        private char characterA;
        private char characterB;
        private char compare;
        private int distance;

        public Condition(char characterA, char characterB, char compare, int distance) {
            this.characterA = characterA;
            this.characterB = characterB;
            this.compare = compare;
            this.distance = distance;
        }
    }
}