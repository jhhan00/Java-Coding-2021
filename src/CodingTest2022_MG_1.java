import java.util.*;
public class CodingTest2022_MG_1 {

    /**
     * 주식 문제였음
     * 1차원 int 배열이 주어짐 - 배열 안의 값들은 그날의 주식 (4 <= 배열 길이 <= 50)
     * 싸게 사서 비싸게 팔기 -> 최대의 수익을 내기
     * 최대 2번 살 수 있음
     * 하루에 최대 1번 살 수 있음
     * 하루에 최대 1번 팔 수 있음
     * 동시에 사고 팔기 안 됨
     * 이익이 안나면 return 0
     */
    public static void main(String[] args) {
        // 배열 길이가 최대 50인 것이 크나큰 함정이었네... ㅜㅜ
        int[] stock = {
                //1,2,3,1,2,4
                //4,4,3,2,1
                //1,2,3,4
                1,10,5,12,7
        };

        int mx = -1;
        for(int a=0;a<stock.length; a++) {
            for(int b=a+1; b<stock.length; b++) {
                for(int c=b+1; c<stock.length; c++) {
                    for(int d=c+1; d<stock.length; d++) {
                        int val1 = (stock[b]-stock[a]) + (stock[d]-stock[c]);
                        mx = Math.max(mx, val1);
                        int val2 = (stock[c]-stock[a]) + (stock[d]-stock[b]);
                        mx = Math.max(mx, val2);
                        int val3 = (stock[d]-stock[a]) + (stock[c]-stock[b]);
                        mx = Math.max(mx, val3);
                    }
                }
            }
        }

        mx = Math.max(0,mx);
        System.out.println("mx = " + mx);
    }
}
