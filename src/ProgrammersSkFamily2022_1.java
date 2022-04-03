import java.util.*;

public class ProgrammersSkFamily2022_1 {

    public static Set<String> hs;

    public static void makeSubSet(String good, int len) {
        int ix = 0;
        while(ix+len <= good.length()) {
            String sub = good.substring(ix, ix+len);
            hs.add(sub);
            ix++;
        }
    }

    public static String isOnlySubSet(String[] goods, String good) {
        StringBuilder sb = new StringBuilder();
        for(String sub: hs) {
            boolean flag = true;
            for(String g: goods) {
                if(g.equals(good)) continue;
                if(g.contains(sub)) {
                    flag = false;
                    break;
                }
            }
            if(flag) {
                sb.append(sub);
                sb.append(" ");
            }
        }
        return sb.toString().trim();
    }

    public static void main(String[] args) {
//        String[] goods = {
//                "pencil", "cilicon", "contrabase", "picturelist"
//        };

        String[] goods = {
                "abcdeabcd", "cdabe", "abce", "bcdeab"
        };

        hs = new TreeSet<>();
        List<String> list = new ArrayList<>();
        for(String good: goods) {
            boolean flag = false;
            for(int i=1; i<=good.length(); i++) {
                hs.clear();
                makeSubSet(good, i);

                String ans = isOnlySubSet(goods, good);
                if(!ans.equals("")) {
                    list.add(ans);
                    flag = true;
                    break;
                }
            }
            if(!flag) list.add("None");
        }
        System.out.println("list = " + list);
    }
}
