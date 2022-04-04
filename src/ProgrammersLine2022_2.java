import java.util.*;

public class ProgrammersLine2022_2 {

    public static List<Character> list;
    public static boolean[] check;
    public static int answer;

    public static int solve2(String[] sentences, String combi) {
        int score = 0;
        for(String sentence: sentences) {
            boolean flag = true;
            String s1 = sentence.toLowerCase();
            int cap = 0;
            for(int i=0; i<s1.length(); i++) {
                if(s1.charAt(i) == ' ') continue;
                boolean bool = s1.charAt(i) != sentence.charAt(i);
                int l1 = -1, l2 = 0;
                l1 = combi.indexOf(s1.charAt(i));
                if(bool) {
                    l2 = combi.indexOf('S');
                }

                if(l1>=0 && l2>=0) {
                    if(bool) cap += 1;
                } else {
                    flag = false;
                    break;
                }
            }
            if(flag) {
                score += sentence.length();
                score += cap;
            }
        }
        return score;
    }

    public static void solve(String[] sentences, int n, int ix, StringBuilder sb) {
        if(sb.length() == n) {
            String res = sb.toString();
            int ans1 = solve2(sentences, res);
            if(answer < ans1) answer = ans1;
            return;
        }
        for(int i=ix; i<list.size(); i++) {
            if(check[i]) continue;
            check[i] = true;
            sb.append(list.get(i));
            solve(sentences, n, i+1, sb);
            sb.deleteCharAt(sb.length()-1);
            check[i] = false;
        }
    }

    public static void main(String[] args) {
        String[] sentences = {
                "line in line",
                "LINE",
                "in lion"
        };
        int n = 5;

//        String[] sentences = {
//                "ABcD",
//                "bdbc",
//                "a",
//                "Line neWs"
//        };
//        int n = 7;

        Set<Character> alpha = new HashSet<>();
        for(String sentence: sentences) {
            String s1 = sentence.toLowerCase();
            for(int i=0; i<s1.length(); i++) {
                if(s1.charAt(i) == ' ') continue;
                if(s1.charAt(i) != sentence.charAt(i)) {
                    alpha.add('S');
                }
                alpha.add(s1.charAt(i));
            }
        }

        list = new ArrayList<>(alpha);
        check = new boolean[list.size()];

        answer = -1;
        solve(sentences, n, 0, new StringBuilder());
        if(answer == -1) answer = 0;
        System.out.println("answer = " + answer);
    }
}
