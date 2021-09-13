import java.util.*;
public class CodingTest2021_L_1 {

    public static void main(String[] args) {
        /**
         * student의 길이: 1~100
         * student의 원소: 0 or 1
         * 1 <= k <= 100
         */
//        int[] student = {0,1,0,0};
//        int k = 1;
        int[] student = {0,1,0,0,1,1,0};
        int k = 2;
//        int[] student = {0,1,0};
//        int k = 2;

        int answer = 0;
        Set<String> set = new LinkedHashSet<>();
        for(int i=0; i<student.length; i++) {
            int cnt = 0;
            StringBuilder sb = new StringBuilder();
            for(int j=i; j<student.length; j++) {
                sb.append(student[j]);
                if(student[j] == 1) cnt++;
                if(cnt == k) set.add(sb.toString());
            }
        }
        System.out.println(set);
        answer = set.size();
        System.out.println("answer = " + answer);
    }
}
