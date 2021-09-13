import java.util.*;

public class CodingTest2021_L_2 {

    public static void main(String[] args) {
        int[][] jobs = {
                {1,5,2,3},
                {2,2,3,2},
                {3,1,3,3},
                {5,2,1,5},
                {7,1,1,1},
                {9,1,1,1},
                {10,2,2,9}

//                {1,2,1,5},
//                {2,1,2,100},
//                {3,2,1,5},
//                {5,2,1,5}

//                {0,2,3,1},
//                {5,3,3,1},
//                {10,2,4,1}

//                {0,5,1,1},
//                {2,4,3,3},
//                {3,4,4,5},
//                {5,2,3,2}
        };

        int time = jobs[0][0] + jobs[0][1];
        int now_job = jobs[0][2];

        boolean[] check = new boolean[jobs.length];
        check[0] = true;

        List<Integer> done_job = new ArrayList<>();
        done_job.add(now_job);

        while(true) {
            boolean flag = true;
            for(int i=0; i<jobs.length; i++) {
                if (!check[i]) {
                    flag = false;
                    break;
                }
            }
            if(flag) break;

            Set<Integer> possible_job = new HashSet<>();        // 다음으로 처리가 가능한 잡 세트
            Map<Integer, Integer> prior = new HashMap<>();      // 키=업무 분류 번호, 값=중요도 합

            boolean same_job = false;
            for(int i=0; i<jobs.length; i++) {
                if(check[i]) continue;
                if(jobs[i][0] <= time) {
                    possible_job.add(i);
                    if(prior.get(jobs[i][2]) != null)
                        prior.put(jobs[i][2], prior.get(jobs[i][2]) + jobs[i][3]);
                    else
                        prior.put(jobs[i][2], jobs[i][3]);

                    if(now_job == jobs[i][2]) {same_job = true;}
                }
            }
            if(!possible_job.isEmpty()) {
                if(!same_job) {     // 같은 잡이 없을 때
                    int mx = 0, new_job = 101;
                    for(Integer p: prior.keySet()) mx = Math.max(mx, prior.get(p));
                    for(Integer p: prior.keySet()) {
                        if(mx == prior.get(p) && new_job > p)
                            new_job = p;
                    }
                    now_job = new_job;
                    done_job.add(now_job);
                }       // 같은 잡이 있는 경우 바로 여기로
                for(Integer j: possible_job) {
                    if(now_job == jobs[j][2]) {
                        time += jobs[j][0];
                        check[j] = true;
                    }
                }
            } else {
                time++;
            }
        }
        System.out.println(done_job);
    }
}
