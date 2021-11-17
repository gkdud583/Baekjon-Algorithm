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
class Main{ 
    static final int DY[] = {-1,1,0,0};
    static final int DX[] = {0,0,-1,1};
    static int map[][];
    public static void main(String[]args) throws Exception{
        Scanner sc = new Scanner(System.in);

        String input[] = sc.nextLine().split(" ");

        int N = Integer.parseInt(input[0]);
        int M = 10;
        int K = Integer.parseInt(input[1]);

        map = new int[N][M];

        for(int i=0; i<N; i++){
            input = sc.nextLine().split("");
            for(int j=0; j<M; j++)
                map[i][j] = Integer.parseInt(input[j]);
        }

        solve(N, M, K);

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++)
                System.out.print(map[i][j]);
            System.out.println();
        }

    }
    static void solve(int N, int M, int K){
        while(true){
            if(!findRegion(N, M, K))
                break;
            dropCell(N, M);
        }
    }
    static boolean findRegion(int N, int M, int K){
        boolean check[][] = new boolean[N][M];

        int tempMap[][] = new int[N][M];

        //copy map - > tempMap
        copyMap(N, M, map, tempMap);

        boolean ret = false;
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(!check[i][j] && map[i][j] != 0){
                    check[i][j] = true;
                    HashSet<Location> region = new HashSet<>();
                    region.add(new Location(i,j));
                    int cnt = dfs(N, M, i, j, map[i][j], check, region) + 1;
                    if(cnt >= K){
                        ret = true;
                        //동시에 발생해야하므로 변화사항은 tempMap에
                        removeRegion(tempMap, region);
                    }
                }
            }
        }

        //copy tempMap - > map
        copyMap(N, M, tempMap, map);
        return ret;

    }
    static void copyMap(int N, int M, int src[][], int dst[][]){
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++)
                dst[i][j] = src[i][j];
        }
    }
    static int dfs(int N, int M, int y, int x, int c, boolean check[][], HashSet<Location> region){
        int count = 0;
        for(int i=0; i<4; i++){
            int ny = y + DY[i];
            int nx = x + DX[i];

            if(ny < 0 || nx < 0 || ny >= N || nx >= M || check[ny][nx] || map[ny][nx] != c)
                continue;
            check[ny][nx] = true;
            region.add(new Location(ny,nx));
            count += dfs(N, M, ny, nx, c, check, region) + 1;

        }
        return count;
    }
    static void removeRegion(int tempMap[][], HashSet<Location> region){
        for(Location loc : region){
            tempMap[loc.y][loc.x] = 0;
        }
    }
    static void dropCell(int N, int M){
        for(int i=M-1; i>=0; i--){ //열
            for(int j=N-1; j>=0; j--) //행
            {
                if(map[j][i] != 0){
                    for(int k=j+1; k<N; k++){
                        if(map[k][i] != 0)
                            break;
                        map[k][i] = map[k-1][i];
                        map[k-1][i] = 0;
                    }
                }
            }
        }
    }
}