
import java.util.*;
import java.io.*;


public class baekjoon_15559 {
    static final int DY[] = {-1,1,0,0};
    static final int DX[] = {0,0,-1,1};
    static int map[][];
    public static void main(String[]args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input[] = br.readLine().split(" ");

        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        map = new int[N][M];

        for(int i=0; i<N; i++){
            input = br.readLine().split("");
            for(int j=0; j<M; j++){
                String v = input[j];

                //NSWE
                if(v.equals("N")){
                    map[i][j] = 0;
                }else if(v.equals("S")){
                    map[i][j] = 1;
                }else if(v.equals("W")){
                    map[i][j] = 2;
                }else
                    map[i][j] = 3;
            }
        }
        System.out.println(solve(N,M));



    }
    static int solve(int N, int M){

        int index = 1;
        int sum = 0;
        int check[][] = new int[N][M];

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(check[i][j] == 0){
                    check[i][j] = index;
                    boolean ret = dfs(i, j, index,check);
                    index++;
                    if(!ret)
                        sum += 1;

                }
            }
        }
        return sum;
    }
    static boolean dfs(int y, int x, int index, int check[][]){
        int ny = y + DY[map[y][x]];
        int nx = x + DX[map[y][x]];

        if(check[ny][nx] != 0 && check[ny][nx] != index)
            return true;
        if(check[ny][nx] == 0){
            check[ny][nx] = index;
            if(dfs(ny, nx, index, check))
                return true;
        }
        return false;

    }
    
}


