

import java.util.*;

class Move{
    int y;
    int x;
    int h_count;
    int t_count;

    Move(int y, int x, int h_count, int t_count){
        this.y = y;
        this.x = x;
        this.h_count = h_count;
        this.t_count = t_count;
    }
}


class Main{
    static final int MAX = 987654321;
    
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);

        int K = sc.nextInt();
        int W = sc.nextInt();
        int H = sc.nextInt();

        int map[][] = new int[H][W];

        for(int i=0; i<H; i++){
            for(int j=0; j<W; j++){
                map[i][j] = sc.nextInt();
            }
        }

        int dp[][][] = new int[H][W][K+1];
        for(int i=0; i<H; i++){
            for(int j=0; j<W; j++){
                Arrays.fill(dp[i][j], MAX);
            }
        }
        bfs(H,W,K,map,dp);
        int ans = MAX;
        for(int i=0; i<=K; i++){
            ans = Math.min(ans,dp[H-1][W-1][i]);
        }
        System.out.println(ans == MAX ? -1 : ans);
      

    }
    static void bfs(int H, int W, int K, int[][]map,int dp[][][]){
        int dy[] = {-2,-2,-1,-1,1,1,2,2,-1,1,0,0};
        int dx[] = {-1,1,-2,2,-2,2,-1,1,0,0,-1,1};
        Queue<Move> queue = new LinkedList<>();

        dp[0][0][0] = 0;

        queue.add(new Move(0,0,0,0));


        while(!queue.isEmpty()){
            Move cur = queue.poll();

            //말이 갈수 있는 위치
            if(cur.h_count < K){
                for(int i=0; i<8; i++){
                    int y = cur.y + dy[i];
                    int x = cur.x + dx[i];

                    if(isOutOfArray(y,x,H,W) || map[y][x] == 1 || dp[y][x][cur.h_count+1] <= cur.t_count+1)
                        continue;
                    dp[y][x][cur.h_count+1] = cur.t_count + 1;
                    queue.add(new Move(y,x,cur.h_count+1,cur.t_count+1));
                    
                }
            }
            //인접
            for(int i=8; i<12; i++){
                int y = cur.y + dy[i];
                int x = cur.x + dx[i];


                if(isOutOfArray(y, x, H, W)  || map[y][x] == 1 || dp[y][x][cur.h_count] <= cur.t_count+1)
                    continue;
             
                dp[y][x][cur.h_count] = cur.t_count+1;
                queue.add(new Move(y, x, cur.h_count, cur.t_count+1));

            }
            
        }
    }
    static boolean isOutOfArray(int y, int x, int H, int W){
        if(y < 0 || x < 0 || y >= H || x >= W)
            return true;
        return false;
    }
    
}

