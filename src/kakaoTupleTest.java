import java.util.*;

public class kakaoTupleTest {
    public static void main(String[] args) {
        String s = "{{4,2,3},{3},{2,3,4,1},{2,3}}";
        HashMap<Integer, List<Integer>> hm = new HashMap<>();

        s = s.substring(2, s.length()-2);
        s = s.replace("},{", "#");
        String[] sp = s.split("#");

        for(String t: sp) {
            String[] tt = t.split(",");
            List<Integer> list = new ArrayList<>();
            for (String value : tt)
                list.add(Integer.parseInt(value));
            hm.put(tt.length, list);
        }
        System.out.println(hm);
        List<Integer> intList = new ArrayList<>();
        for(int i: hm.keySet()) {
            for(int j: hm.get(i))
                if(!intList.contains(j))
                    intList.add(j);
        }
        System.out.println(intList);
        int[] answer = intList.stream().mapToInt(i->i).toArray();
        for (int j : answer)
            System.out.println(j);
    }
}
