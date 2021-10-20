import java.io.*;
import java.util.*;


public class baekjoon_1937 {
    static final int dy[] = {-1,1,0,0};
    static final int dx[] = {0,0,-1,1};
    static int map[][];
    static int count[][];
    public static void main(String[]args) throws Exception{
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        map = new int[N][N];
        count = new int[N][N];

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++)
                map[i][j] = sc.nextInt();
        }

        System.out.println(solve(N));

        // for(int i=0; i<N; i++){
        //     for(int j=0; j<N; j++){
        //         System.out.print(count[i][j]+" ");
        //     }
        //     System.out.println();
        // }


    }
    static int solve(int N){
        int max = 0;
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(count[i][j] == 0){
                    max = Math.max(max, dfs(i,j,N,count));
                }else{
                    max = Math.max(max, count[i][j]);

                }
            }
        }
        return max;
    }
    static int dfs(int y, int x, int N, int count[][]){
        int max = 0;
        for(int i=0; i<4; i++){
            int ny = y + dy[i];
            int nx = x + dx[i];

            if(ny < 0 || nx < 0 || ny >= N || nx >= N || map[ny][nx] <= map[y][x])
                continue;
            if(count[ny][nx] != 0)
                max = Math.max(max, count[ny][nx]);
            else{
                max = Math.max(max,dfs(ny,nx,N,count));
            }
        }
        return count[y][x] = max + 1;

    }
}