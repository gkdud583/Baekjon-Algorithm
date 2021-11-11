import java.util.*;



class Main{
    //0은 위 1은 아래 2는 왼쪽 3은 오른쪽
    static final int DY[] = {-1,1,0,0};
    static final int DX[] = {0,0,-1,1};
    static int map[][];
    static boolean isExit[][]; //해당 지점을 거치면 경계밖으로 나갈수 있는지 확인
    static int cnt = 0;
    public static void main(String[]args) throws Exception{
        Scanner sc = new Scanner(System.in);

        String input[] = sc.nextLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        map = new int[N][M];
        isExit = new boolean[N][M];

        for(int i=0; i<N; i++){
            input = sc.nextLine().split("");
            for(int j=0; j<M; j++){
                char c = input[j].charAt(0);
                if(c == 'U'){
                    map[i][j] = 0;
                }
                else if(c == 'D'){
                    map[i][j] = 1;
                }
                else if(c == 'L'){
                    map[i][j] = 2;
                }
                else if(c == 'R'){
                    map[i][j] = 3;
                }
             
            }
        }

        System.out.println(solve(N,M));

    }
    static int solve(int N, int M){
        boolean check[][] = new boolean[N][M];
        int ans = 0;
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(!check[i][j]){
                    cnt = 1;
                    check[i][j] = true;
                    
                    if(dfs(i,j,check,N,M)){
                        isExit[i][j] = true;
                        ans += cnt;
                    }
                }

            }
        }
        return ans;
    }
    static boolean dfs(int y, int x, boolean check[][], int N, int M){
        
        int ny = y + DY[map[y][x]];
        int nx = x + DX[map[y][x]];

        if(ny < 0 || nx < 0 || ny >= N || nx >= M){
            isExit[y][x] = true;
            return true;
        }

        //이미 방문한곳이라면 탈출할수 있는지 확인
        if(check[ny][nx]){
            if(isExit[ny][nx]){
                isExit[y][x] = true;
                return true;
            }
            return false;
        }
        
        cnt++;
        check[ny][nx] = true;
        if(!dfs(ny,nx,check,N,M)) //탈출 불가능
            return false;
        return isExit[y][x] = true; //탈출 가능
        
    }
}