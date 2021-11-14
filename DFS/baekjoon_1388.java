import java.util.*;



class Main{
    static final int DY[] = {-1,1,0,0};
    static final int DX[] = {0,0,-1,1};
    static char map[][];
    public static void main(String[]args) throws Exception{
        Scanner sc = new Scanner(System.in);


        String input[] = sc.nextLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        
        map = new char[N][M];

        for(int i=0; i<N; i++){
            input = sc.nextLine().split("");
            for(int j=0; j<M; j++){                
                map[i][j] = input[j].charAt(0);
            }
        }

        System.out.println(solve(N,M));
        


    }
    static int solve(int N, int M){
        int cnt = 0;
        boolean check[][] = new boolean[N][M];
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(!check[i][j]){
                    check[i][j] = true;
                    cnt++;
                    dfs(N, M, i, j, map[i][j], check);
                }
            }
        }
        return cnt;
    }
    static void dfs(int N, int M, int y, int x, char c, boolean check[][]){
        
        
        //만약 두 개의 ‘-’가 인접해 있고, 같은 행에 있다면, 두 개는 같은 나무 판자이고, 
        //두 개의 ‘|’가 인접해 있고, 같은 열에 있다면, 두 개는 같은 나무 판자이다.

        if(c == '|'){
            for(int i=0; i<2; i++){
                int ny = y + DY[i];
                int nx = x + DX[i];

                if(isOutOfArray(ny, nx, N, M) || check[ny][nx] || map[ny][nx] != c)
                    continue;
                check[ny][nx] = true;
                dfs(N,M,ny,nx,c,check);
            }
        }else{
            for(int i=2; i<4; i++){
                int ny = y + DY[i];
                int nx = x + DX[i];

                if(isOutOfArray(ny, nx, N, M) || check[ny][nx] || map[ny][nx] != c)
                    continue;
                check[ny][nx] = true;
                dfs(N,M,ny,nx,c,check);

            }
        }
    }
    static boolean isOutOfArray(int y, int x, int N, int M){
        if(y < 0 || x < 0 || y >= N || x >= M)
            return true;
        return false;
    }
}