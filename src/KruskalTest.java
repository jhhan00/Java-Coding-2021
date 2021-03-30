import java.util.*;

public class KruskalTest {
    static class Tuple {
        int p1, p2, dist;

        Tuple(int p1, int p2, int dist) {
            this.p1 = p1;
            this.p2 = p2;
            this.dist = dist;
        }
    }

    static int[] parent;

    public static int find_parent(int id) {
        if(id == parent[id]) return id;
        else return parent[id] = find_parent(parent[id]);
    }

    public static void union(int a, int b) {
        int parentA = find_parent(a);
        int parentB = find_parent(b);

        if(parentA < parentB) parent[parentB] = parentA;
        else parent[parentA] = parentB;
    }

    public static void main(String[] args) {
        int[][] connection = {
                {1, 2, 3},
                {2, 3, 1},
                {2, 4, 4},
                {4, 5, 2},
                {3, 5, 4},
                {1, 4, 5},
                {1, 5, 6},
                {3, 4, 7},
        };
        int N = 5;

        parent = new int[N+1];
        for(int i=1; i<=N; i++) parent[i] = i;

        List<Tuple> list = new ArrayList<>();
        for (int[] con : connection) {
            Tuple t = new Tuple(con[0], con[1], con[2]);
            list.add(t);
        }

        list.sort((t1, t2) -> {
            if(t1.dist < t2.dist) return -1;
            else if(t1.dist > t2.dist) return 1;
            else {
                if(t1.p1 < t2.p1) return -1;
                else return 1;
            }
        });

        int count = 0;
        List<Tuple> finalList = new ArrayList<>();
        for(Tuple t: list) {
            if(find_parent(t.p1) != find_parent(t.p2)) {
                union(t.p1, t.p2);
                count += t.dist;
                finalList.add(t);
            }
        }

        for(Tuple t: finalList)
            System.out.println(t.p1 + " " + t.p2 + " " + t.dist);
        System.out.println(count);
    }
}
