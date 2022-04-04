import java.util.*;

public class ProgrammersLine2022_5 {

    public static void main(String[] args) {
//        long[] abilities = {
//                2,8,3,6,1,9,1,9
//        };
//        int k = 2;

        long[] abilities = {
                700000000,600000000,800000000,900000000,1000000000
        };
        int k = 1;

        List<Long> list = new ArrayList<>();
        for (long ability : abilities) list.add(ability);
        list.sort(Collections.reverseOrder());

        boolean isOdd = false;      // 짝수면 false, 홀수면 true
        if(list.size() % 2 != 0) {  // 홀수면 강제로 1개 더 추가 -> 짝수로 맞추기
            list.add(0L);
            isOdd = true;
        }

        long answer = 0;
        for(int i=0; i<list.size(); i += 2) {
            long p1 = list.get(i);
            long p2 = list.get(i+1);

            if(p1 == p2) {
                answer += p1;
            } else {
                if(i == list.size()-2) {    // 마지막
                    if(isOdd) {             // 홀수일 때
                        k--;
                        answer += p1;
                    } else {                // 짝수일 때
                        if(k>0) {
                            k--;
                            answer += p1;
                        } else {
                            answer += p2;
                        }
                    }
                } else {                    // 마지막이 아닌 경우
                    if(isOdd) {             // 홀수일 때
                        if(k>1) {
                            k--;
                            answer += p1;
                        } else {
                            answer += p2;
                        }
                    } else {                // 짝수일 때
                        if(k>0) {
                            k--;
                            answer += p1;
                        } else {
                            answer += p2;
                        }
                    }
                }
            }
        }
        System.out.println("answer = " + answer);
    }
}
