import java.util.*;
import java.io.*;

class Location{
    int y;
    int x;
    Location(int y, int x){
        this.y = y;
        this.x = x;
    }
}
class baekjoon_16988{
    static final int DY[] = {-1,1,0,0};
    static final int DX[] = {0,0,-1,1};
    static int max = 0;
    static int totalCount = 0;
    static int count = 0;
    static int map[][];
    static ArrayList<Location> enemyLocationList = new ArrayList<>();
    public static void main(String[]args) throws Exception{
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        map = new int[N][M];

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                map[i][j] = sc.nextInt();
                if(map[i][j] == 2)
                    enemyLocationList.add(new Location(i,j));
            }
        }
        solve(N,M,0,-1,0);
        System.out.println(max);
    }
    static void solve(int N, int M, int y, int x, int cnt){
        if(cnt >= 2){

            totalCount = 0;
            getEnemy(N, M);
            return;
        }
        for(int i=y; i<N; i++){
            for(int j=0; j<M; j++){
                if(i == y && j <= x)
                    continue;
                if(map[i][j] != 0)
                    continue;
                map[i][j] = 1;
                solve(N,M,i,j,cnt+1);
                map[i][j] = 0;
                
            }
        }
    }
    static void getEnemy(int N, int M){
        boolean check[][] = new boolean[N][M];
        for(int i=0; i<enemyLocationList.size(); i++){
            Location loc = enemyLocationList.get(i);
            if(!check[loc.y][loc.x]){
                check[loc.y][loc.x] = true;
                count = 1;
                if(!dfs(N,M,loc.y,loc.x,check))
                    totalCount += count;
            }
        }
        max = Math.max(max, totalCount);
    }
    static boolean dfs(int N, int M, int y, int x, boolean check[][]){

        boolean isAnySpace = false;
        for(int i=0; i<4; i++){
            int ny = y + DY[i];
            int nx = x + DX[i];

            if(ny < 0 || nx < 0 || ny >= N || nx >= M || map[ny][nx] == 1 || check[ny][nx])
                continue;
            if(map[ny][nx] == 0){
                isAnySpace = true;
                continue;
            }
            count++;
            check[ny][nx] = true;
            if(dfs(N,M,ny,nx,check))
                isAnySpace = true;
        }
        return isAnySpace;
    }
}