import java.util.*;



class baekjoon_14716{
    static final int DY[] = {-1,1,0,0,-1,-1,1,1};
    static final int DX[] = {0,0,-1,1,-1,1,-1,1};
    static int map[][];
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);

        int M = sc.nextInt();
        int N = sc.nextInt();

        map = new int[M][N];
        for(int i=0; i<M; i++){
            for(int j=0; j<N; j++)
                map[i][j] = sc.nextInt();
        }

        System.out.println(solve(M,N));

    }
    static int solve(int M, int N){
        boolean check[][] = new boolean[M][N];

        int cnt = 0;
        for(int i=0; i<M; i++){
            for(int j=0; j<N; j++){
                if(!check[i][j] && map[i][j] == 1){
                    check[i][j] = true;
                    dfs(i,j,M,N,check);
                    cnt++;
                }
            }
        }
        return cnt;
    }
    static void dfs(int y, int x, int M, int N, boolean check[][]){
        for(int i=0; i<8; i++){
            int ny = y + DY[i];
            int nx = x + DX[i];

            if(ny < 0 || nx < 0 || ny >= M || nx >= N || map[ny][nx] == 0 || check[ny][nx])
                continue;
            check[ny][nx] = true;
            dfs(ny,nx,M,N,check);
        }
    }
}