import java.util.*;
import java.lang.Math;

public class ProgrammersLandMoving {
    static class Pair {
        int x,y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Tuple {
        int g1, g2, dist;

        Tuple(int g1, int g2, int dist) {
            this.g1 = g1;
            this.g2 = g2;
            this.dist = dist;
        }
    }

    static int[][] mp = new int[300][300];
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static int[] parent;

    public static void bfs(int[][] land, int a, int b, int val, int h) {
        int N = land.length;
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(a,b));

        while(!q.isEmpty()) {
            Pair p = q.poll();
            int x = p.x, y = p.y;
            for(int k=0; k<4; k++) {
                int nx = x+dx[k];
                int ny = y+dy[k];

                if(nx<0 || nx>=N || ny<0 || ny>=N) continue;
                if(mp[nx][ny]==0 && Math.abs(land[nx][ny] - land[x][y]) <= h) {
                    q.offer(new Pair(nx,ny));
                    mp[nx][ny]=val;
                }
            }
        }
    }

    public static int findParent(int index) {
        if(index == parent[index])
            return index;
        return parent[index] = findParent(parent[index]);
    }

    public static void union(int a, int b) {
        int pa = findParent(a);
        int pb = findParent(b);

        if(pa < pb) parent[pb] = pa;
        else parent[pa] = pb;
    }

    public static void main(String[] args) {
        int[][] land = {
//				{1,4,8,10},
//				{5,5,5,5},
//				{10,10,10,10},
//				{10,10,10,20}
                {10,11,10,11},
                {2,21,20,10},
                {1,20,21,11},
                {2,1,2,1}
        };
//		int height = 3;
        int height = 1;

        int length = land.length;
        for(int i=0; i<length; i++) {
            for(int j=0; j<length; j++)
                mp[i][j]=0;
        }

        int tt = 1;
        for(int i=0; i<length; i++) {
            for(int j=0; j<length; j++) {
                if(mp[i][j] == 0) {
                    mp[i][j] = tt;
                    bfs(land, i, j, tt++, height);
                }
            }
        }

        parent = new int[tt];
        for(int i=0; i<tt; i++) parent[i] = i;

//        for(int i=0; i<length; i++) {
//            for(int j=0; j<length; j++)
//                System.out.print(mp[i][j]);
//            System.out.println();
//        }

        ArrayList<Tuple> list = new ArrayList<>();
        for(int i=0; i<length; i++) {
            for(int j=0; j<length; j++) {
                for(int k=0; k<4; k++) {
                    int ni = i+dx[k];
                    int nj = j+dy[k];

                    if(ni<0 || ni>=length || nj<0 || nj>=length) continue;
                    if(mp[ni][nj] != mp[i][j]) {
                        list.add(new Tuple(mp[i][j], mp[ni][nj], Math.abs(land[i][j] - land[ni][nj])));
                    }
                }
            }
        }

//        for(Tuple t: list) {
//        	System.out.println(t.g1 + " " + t.g2 + " " + t.dist);
//        }

        list.sort(new Comparator<Tuple>() {
            @Override
            public int compare(Tuple a, Tuple b) {
                if (a.dist < b.dist) return -1;
                else if (a.dist > b.dist) return 1;
                else {
                    if (a.g1 < b.g1) return -1;
                    else return 1;
                }
            }
        });

        int count=0, answer=0;
        for(Tuple t: list) {
            if(findParent(t.g1) != findParent(t.g2)) {
                union(t.g1, t.g2);
                answer += t.dist;
                if(++count == tt) break;
            }
        }
        System.out.println(answer);
    }
}
