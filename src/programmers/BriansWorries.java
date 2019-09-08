package programmers;

import java.util.ArrayList;
import java.util.List;

public class BriansWorries {
    public static void main(String[] args) {
        BriansWorries b = new BriansWorries();

        System.out.println(b.solution("HaEaLaLaObWORLDb"));
        System.out.println(b.solution("SpIpGpOpNpGJqOqA"));
        System.out.println(b.solution("AxAxAxAoBoBoB"));
    }

    private Rule[] rules;
    private List<String> wordList;
    private boolean[] visits;

    public String solution(String sentence) {
        rules = new Rule['z' + 1];
        for (int i = 'a'; i <= 'z'; i++) {
            rules[i] = new Rule((char) i, 0);
        }
        wordList = new ArrayList<>();
        visits = new boolean['z' + 1];

        boolean ruleStart = false;
        char tempC = ' ';
        for (int i = 0; i < sentence.length(); i++) {
            char c = sentence.charAt(i);
            if (isSpecialSymbol(c)) {
                rules[c].addIndex(i);
            }
        }

        for (Rule rule : rules) {
            if (rule.cnt == 1 || rule.cnt == 3
                    || (rule.cnt == 2 && rule.indices.get(0) - rule.indices.get(1) > 2)) {
                rule.type = RuleType.RULE1;
            } else {
                rule.type = RuleType.RULE2;
            }
        }
        return getOriginSentence(wordList);
    }

    private int addWord(String sentence, char delim, int startIdx) {
        StringBuilder sb = new StringBuilder(100);
        int len = 0;
        int specialCnt = 1;


        for (int i = startIdx + 1; i < sentence.length(); i++) {
            char c = sentence.charAt(i);
            if (isSpecialSymbol(c)) {

            }
        }

        return len;
    }

    private String getOriginSentence(List<String> words) {
        StringBuilder sb = new StringBuilder(100);
        for (String word : words) {
            sb.append(word).append(' ');
        }
        return sb.toString();
    }

    private boolean isSpecialSymbol(char c) {
        return 'a' <= c && c <= 'z';
    }

    class Rule {
        private RuleType type;
        private char symbol;
        private int cnt;
        private List<Integer> indices;

        public Rule(char symbol, int cnt) {
            this.symbol = symbol;
            this.cnt = cnt;
            this.indices = new ArrayList<>();
        }

        public void addIndex(int idx) {
            this.indices.add(idx);
        }
    }

    enum RuleType {
        RULE1, RULE2
    }
}
