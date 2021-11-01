import java.util.*;


class Edge{
    int a;
    int b;
    Edge(int a, int b){
        this.a = Math.min(a,b);
        this.b = Math.max(a,b);
    }
}
class baekjoon_3187{
    static final int DY[] = {-1,1,0,0};
    static final int DX[] = {0,0,-1,1};
    static String map[][];
    static int wolfCnt = 0;
    static int sheepCnt = 0;
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);

        String input[] = sc.nextLine().split(" ");

        int R = Integer.parseInt(input[0]);
        int C = Integer.parseInt(input[1]);

        map = new String[R][C];

        for(int i=0; i<R; i++){
            input = sc.nextLine().split("");
            for(int j=0; j<C; j++){
                map[i][j] = input[j];

                if(map[i][j].equals("v"))
                    wolfCnt++;
                if(map[i][j].equals("k"))
                    sheepCnt++;
            }
        }
        solve(R,C);
        System.out.println(sheepCnt+" "+wolfCnt);
        
    }
    static void solve(int R, int C){
        boolean check[][] = new boolean[R][C];

        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                if(!map[i][j].equals("#") && !check[i][j]){
                    check[i][j] = true;
                    int ans[] = new int[2];
                    if(map[i][j].equals("v"))
                        ans[1]++;
                    else if(map[i][j].equals("k"))
                        ans[0]++;
                    
                    dfs(i,j,R,C,check,ans);
                    if(ans[0] > ans[1]){
                        wolfCnt -= ans[1];
                    }else{
                        sheepCnt -= ans[0];
                    }
                }
            }
        }
    }
    static void dfs(int y, int x, int R, int C, boolean check[][], int ans[]){
        for(int i=0; i<4; i++){
            int ny = y + DY[i];
            int nx = x + DX[i];

            if(ny < 0 || nx < 0 || ny >= R || nx >= C || map[ny][nx].equals("#") || check[ny][nx])
                continue;
            if(map[ny][nx].equals("v"))
                ans[1]++;
            if(map[ny][nx].equals("k"))
                ans[0]++;
            
            check[ny][nx] = true;
            dfs(ny,nx,R,C,check,ans);
        }
    }
}