import java.util.*;

class baekjoon_16929{
    static final int DY[] = {-1,1,0,0};
    static final int DX[] = {0,0,-1,1};
    static int map[][];
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);

        String input[] = sc.nextLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        map = new int[N][M];
        for(int i=0; i<N; i++){
            input = sc.nextLine().split("");
            for(int j=0; j<M; j++){
                map[i][j] = input[j].charAt(0)-'A';
            }
        }
        System.out.println(solve(N,M) == false ? "No" : "Yes");
    }
    static boolean solve(int N, int M){
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                boolean check[][] = new boolean[N][M];
                check[i][j] = true;
                if(dfs(i,j,i,j,1,map[i][j],N,M,check))
                    return true;
            }
        }
        return false;
    }
    static boolean dfs(int fy, int fx, int y, int x, int cnt, int color, int N, int M, boolean check[][]){
        
        for(int i=0; i<4; i++){
            int ny = y + DY[i];
            int nx = x + DX[i];

            if(ny < 0 || nx < 0 || ny >= N || nx >= M ||  map[ny][nx] != color)
                continue;
            if(fy == ny && fx == nx && cnt >= 4){
                return true;
            }
            if(check[ny][nx])
                continue;
            check[ny][nx] = true;
            if(dfs(fy,fx,ny,nx,cnt+1,color,N,M,check))
                return true;
        }
        return false;
    }
    
}