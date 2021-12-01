
import java.util.*;
import java.io.*;



public class baekjoon_21736 {
    static final int DY[] = {-1,1,0,0};
    static final int DX[] = {0,0,-1,1};
    static int map[][];
    static int ans = 0;
    public static void main(String[]args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input[] = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        map = new int[N][M];

        int fy = 0, fx = 0;
        for(int i=0; i<N; i++){
            input = br.readLine().split("");
            for(int j=0; j<M; j++){
                if(input[j].equals("O")){
                    map[i][j] = 0;
                }else if(input[j].equals("X")){
                    map[i][j] = -1;
                }else if(input[j].equals("I")){
                    map[i][j] = 0;
                    fy = i;
                    fx = j;

                }
                else if(input[j].equals("P")){
                    map[i][j] = 2;
                }
            }
        }
        boolean check[][] = new boolean[N][M];
        check[fy][fx] = true;
        dfs(N,M,fy,fx,check);
        System.out.println(ans == 0 ? "TT" : ans);

    }
    static void dfs(int N, int M, int y ,int x, boolean check[][]){
        for(int i=0; i<4; i++){
            int ny = y + DY[i];
            int nx = x + DX[i];

            if(ny < 0 || nx < 0 || ny >= N || nx >= M || map[ny][nx] == -1 || check[ny][nx])
                continue;

            check[ny][nx] = true;

            if(map[ny][nx] == 2){
                ans++;
            }
            dfs(N,M,ny,nx,check);
        }
    }
}