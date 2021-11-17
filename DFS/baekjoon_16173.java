import java.util.*;
import java.io.*;


class Main{ 
    static final int DY[] = {1,0};
    static final int DX[] = {0,1};
    static int map[][];
    public static void main(String[]args) throws Exception{
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        map = new int[N][N];

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++)
                map[i][j] = sc.nextInt();
        }

        boolean check[][] = new boolean[N][N];
        check[0][0] = true;
        System.out.println(dfs(N, 0, 0 ,check) == true ? "HaruHaru" : "Hing");
    }
    static boolean dfs(int N, int y, int x, boolean check[][]){
        for(int j=0; j<2; j++){
            int ny = y + DY[j] * map[y][x];
            int nx = x + DX[j] * map[y][x];

            if(ny < 0 || nx < 0 || ny >= N || nx >= N || check[ny][nx])
                continue;
            if(map[ny][nx] == -1)
                return true;
            check[ny][nx] = true;
            if(dfs(N, ny, nx, check))
                return true;
        }
        return false;
    }
}