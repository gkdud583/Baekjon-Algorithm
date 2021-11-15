import java.util.*;
import java.io.*;


class Main{ 
    static final int DY[] = {-1,1,0,0};
    static final int DX[] = {0,0,-1,1};
    static boolean map[][];
    public static void main(String[]args) throws Exception{

        Scanner sc = new Scanner(System.in);

    
        int T = Integer.parseInt(sc.nextLine());


        
        

        StringBuffer sb = new StringBuffer();
        for(int i=0; i<T; i++){
            String input[] = sc.nextLine().split(" ");
            int H = Integer.parseInt(input[0]);
            int W = Integer.parseInt(input[1]);
    
            map = new boolean[H][W];
            for(int j=0; j<H; j++){
                input = sc.nextLine().split("");
                for(int k=0; k<W; k++){
                    if(input[k].equals("#"))
                        map[j][k] = true;
                }
            }
            sb.append(solve(H,W)).append("\n");
        }
        System.out.println(sb);
        
    }
    static int solve(int H, int W){
        int cnt = 0;
        boolean check[][] = new boolean[H][W];

        for(int i=0; i<H; i++){
            for(int j=0; j<W; j++){
                if(!check[i][j] && map[i][j]){
                    check[i][j] = true;
                    cnt++;
                    dfs(i,j,check,H,W);
                }
            }
        }

        return cnt;
    }
    static void dfs(int y, int x, boolean check[][], int H, int W){
        for(int i=0; i<4; i++){
            int ny = y + DY[i];
            int nx = x + DX[i];

            if(ny < 0 || nx < 0 || ny >= H || nx >= W || !map[ny][nx] || check[ny][nx])
                continue;

            check[ny][nx] = true;
            dfs(ny, nx, check, H, W);

        }
    }
}