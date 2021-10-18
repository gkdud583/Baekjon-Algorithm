import java.io.*;
import java.util.*;


  

public class baekjoon_2468 {


    
    static final int MAX = 987654321;
    static final int dy[] = {-1,1,0,0};
    static final int dx[] = {0,0,-1,1};
    static int map[][];
    public static void main(String[]args) throws Exception{
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        map = new int[N][N];

        int min=MAX, max=0;
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                map[i][j] = sc.nextInt();
                min = Math.min(min, map[i][j]);
                max = Math.max(max, map[i][j]);
            }
        }
        System.out.println(solve(N,min,max));

    }
    static int solve(int N, int min, int max){
        int ans = 0;
        for(int i=min-1; i<max; i++){
            int cnt = 0;
            boolean check[][] = new boolean[N][N];
            for(int j=0; j<N; j++){
                for(int k=0; k<N; k++){
                    if(map[j][k] > i && !check[j][k]){
                        check[j][k] = true;
                        dfs(N,j,k,i,check);
                        cnt++;
                    }
                }
            }
            ans = Math.max(ans, cnt);
            
        }
        return ans;
    }
    static void dfs(int N, int y, int x, int h, boolean check[][]){
        for(int i=0; i<4; i++){
            int ny = y + dy[i];
            int nx = x + dx[i];

            if(ny < 0 || nx < 0 || ny >= N || nx >= N || map[ny][nx] <= h || check[ny][nx])
                continue;
            check[ny][nx] = true;

            dfs(N,ny,nx,h,check);


        }
    }
}