import java.util.*;


class baekjoon_14503{
    //0북,1동,2남,3서
    
    static int forward_x[] = {-1,0,1,0};
    static int forward_y[] = {0,-1,0,1};
    static int backward_x[] = {0,-1,0,1};
    static int backward_y[] = {1,0,-1,0};
    static int count = 0;
    static void dfs(int y,int x,int d,boolean check[][],int map[][]){
        //현재 장소를 청소
        int n = check.length;
        int m = check[0].length;
     
        if(!check[y][x]){
            check[y][x] = true;
            count++; 
        }
        
        boolean isTrue = false;
        //인근 방향 탐색
        for(int i=0; i<4; i++){
            //0북 1동 2남 3서
            int move_y = y + forward_y[d];
            int move_x = x + forward_x[d];
         
            //Out of index check
            if(move_y >= n || move_x >= m || move_y < 0 || move_x < 0)
                continue;
            //벽이거나 청소를 이미 했다면 다른 방향 탐색
            if(map[move_y][move_x] == 1 || check[move_y][move_x]){
                if(d == 0)
                    d = 3;
                else
                    d -= 1;
                continue;
            }
                
            isTrue = true;
            if(d == 0)
                d = 3;
            else
                d -= 1;
            dfs(move_y,move_x,d,check,map);
            break;
            

        }
        if(!isTrue){
            //후진이 가능한지 확인
            int move_y = y + backward_y[d];
            int move_x = x + backward_x[d];
            if(move_y >= n || move_x >= m || move_y < 0 || move_x < 0 || map[move_y][move_x] == 1)
                return;
            if(map[move_y][move_x] == 0)
                dfs(move_y,move_x,d,check,map);
        }
    }
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int cur_y = sc.nextInt();
        int cur_x = sc.nextInt();
        int cur_direction = sc.nextInt();

        int map[][] = new int[n][m];
        boolean check[][] = new boolean[n][m];
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                map[i][j] = sc.nextInt();
            }
        }
        dfs(cur_y,cur_x,cur_direction,check,map);
        System.out.println(count);

    }
}