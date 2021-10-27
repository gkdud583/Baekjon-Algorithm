import java.io.*;
import java.util.*;


  

public class baekjoon_1103 {

    static final int DY[] = {-1,1,0,0};
    static final int DX[] = {0,0,-1,1};
    static int max = 0;
    static int count[][];
    static int map[][];
    public static void main(String[]args) throws Exception{
        Scanner sc = new Scanner(System.in);

        
        String input[] = sc.nextLine().split(" ");

        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        map = new int[N][M];
        count = new int[N][M];

        for(int i=0; i<N; i++){
            input = sc.nextLine().split("");
            for(int j=0; j<M; j++){
                if(input[j].equals("H")){
                    map[i][j] = 0;
                }else
                    map[i][j] = Integer.parseInt(input[j]);
            }
        }

        
        boolean check[][] = new boolean[N][M];
        check[0][0] = true;
        dfs(N,M,0,0,0,check);
        System.out.println(max);



    }
    static boolean dfs(int N, int M, int y, int x, int cnt, boolean check[][]){

        for(int i=0; i<4; i++){
            
            int ny = y + DY[i] * map[y][x];
            int nx = x + DX[i] * map[y][x];

            
            if(ny < 0 || nx < 0 || ny >= N || nx >= M || map[ny][nx] == 0)
            {   
                max = Math.max(max,cnt+1);
                continue;
            }
            if(check[ny][nx]){
                max = -1;
                return false;
            }
            if(count[ny][nx] >= cnt+1)
                continue;
            
            count[ny][nx] = cnt+1;
            check[ny][nx] = true;
            
            max = Math.max(max,cnt+1);

            if(!dfs(N, M, ny, nx, cnt+1, check))
                return false;
            check[ny][nx] = false;
        }
        return true;
    }
}