import java.io.*;
import java.util.*;


  

public class baekjoon_1405 {


    //동서남북
    static final int DY [] = {0,0,-1,1};
    static final int DX [] = {1,-1,0,0};
    static double simpleProbability;
    static double percentage[] = new double[4];
    static int w;
    static int s;
    static int n;
    static int e;
    public static void main(String[]args) throws Exception{
        Scanner sc = new Scanner(System.in);


        int N = sc.nextInt();

        w = s = -1 * N;
        n = e = N;

        
        for(int i=0; i<4; i++){
            percentage[i] = sc.nextInt() / 100.0;
        }

        boolean check[][] = new boolean[N*2+1][N*2+1];
        check[N][N] = true;
        dfs(N,N,0,N,1,check);
        System.out.println(simpleProbability);
    }
    static void dfs(int y, int x, int i, int N, double p, boolean[][] check){
        if(i >= N){
            simpleProbability += p;
            return;
        }
        for(int j=0; j<4; j++){
            int ny = y + DY[j];
            int nx = x + DX[j];

            

            if(check[ny][nx])
                continue;
            else{
                check[ny][nx] = true;
                dfs(ny, nx, i+1, N, p*percentage[j], check);
                check[ny][nx] = false;
            }
        }
    }


}