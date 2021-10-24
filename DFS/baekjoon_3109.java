import java.io.*;
import java.util.*;


  

public class baekjoon_3109 {

    static int DY[] = {-1,0,1};
    static int DX[] = {1,1,1};
    static int max = 0;
    static boolean map[][];
    public static void main(String[]args) throws Exception{
        Scanner sc = new Scanner(System.in);

        String input[] = sc.nextLine().split(" ");
        int R = Integer.parseInt(input[0]);
        int C = Integer.parseInt(input[1]);

        map = new boolean[R][C];

        for(int i=0; i<R; i++){
            input = sc.nextLine().split("");
            for(int j=0; j<C; j++){
                if(input[j].equals("."))
                    map[i][j] = true;
                else
                    map[i][j] = false;
            }
        }

        solve(R,C);
        System.out.println(max);

    }
    static void solve(int R, int C){
        boolean check[][] = new boolean[R][C];

        for(int i=0; i<R; i++){
            check[i][0] = true;
            dfs(i,0,R,C,check);
        }
    }
    static boolean dfs(int y, int x, int R, int C, boolean check[][]){
        if(x == C-1){
            //끝점에 도달
            max++; 
            return true;
        }
        for(int i=0; i<3; i++){
            int ny = y + DY[i];
            int nx = x + DX[i];

            if(ny < 0 || nx < 0 || ny >= R || nx >= C || !map[ny][nx] || check[ny][nx])
                continue;
            check[ny][nx] = true;
            if(dfs(ny,nx,R,C,check))
                return true;

            
        }
        return false;
    }
   
}