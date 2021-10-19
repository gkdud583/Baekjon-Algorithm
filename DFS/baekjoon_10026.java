import java.io.*;
import java.util.*;


  

public class baekjoon_10026 {
    static final int dy[] = {-1,1,0,0};
    static final int dx[] = {0,0,-1,1};
    public static void main(String[]args) throws Exception{
        Scanner sc = new Scanner(System.in);

        int N = Integer.parseInt(sc.nextLine());

        int [][]map = new int[N][N];
        int [][]cbMap = new int[N][N];
        for(int i=0; i<N; i++){
            String input[] = sc.nextLine().split("");
            for(int j=0; j<N; j++){
                if(input[j].equals("R")){
                    map[i][j] = 0;
                    cbMap[i][j] = 0;
                }else if(input[j].equals("G")){
                    map[i][j] = 1;
                    cbMap[i][j] = 0;
                }else if(input[j].equals("B")){
                    map[i][j] = 2;
                    cbMap[i][j] = 1;
                }
            }
        }
        int ans[] = solve(N, map, cbMap);
        System.out.println(ans[0]+" "+ans[1]);
    }
    static int[] solve(int N,int [][]map, int [][]cbMap){
        //적록색약x
        int ret[] = new int[2];
        boolean check[][] = new boolean[N][N];
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(!check[i][j]){
                    check[i][j] = true;
                    dfs(N,i,j,check,map);
                    ret[0]++;
                }
            }
        }

        check = new boolean[N][N];
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(!check[i][j]){
                    check[i][j] = true;
                    dfs(N,i,j,check,cbMap);
                    ret[1]++;
                }
            }
        }

        return ret;

    }
    static void dfs(int N, int y, int x, boolean check[][], int map[][]){
        for(int i=0; i<4; i++){
            int ny = y + dy[i];
            int nx = x + dx[i];

            if(ny < 0 || nx < 0 || ny >= N || nx >= N || map[ny][nx] != map[y][x] || check[ny][nx])
                continue;
            check[ny][nx] = true;
            dfs(N,ny,nx,check,map);
        }
    }
}