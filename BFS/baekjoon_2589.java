import java.util.*;

class Location{
    int y;
    int x;
    int dist;
    Location(int y, int x, int dist){
        this.y = y;
        this.x = x;
        this.dist = dist;
    }
}

class baekjoon_2589{
    static int dy[] = {-1,1,0,0};
    static int dx[] = {0,0,-1,1};
    static int max = 0;

    
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);  

        String input[] = sc.nextLine().split(" ");
        int R = Integer.parseInt(input[0]);
        int C = Integer.parseInt(input[1]);

        String map[][] = new String[R][C];


        for(int i=0; i<R; i++){
            input = sc.nextLine().split("");
            for(int j=0; j<C; j++){
                map[i][j] = input[j];

                

            }
        }

        solve(R,C,map);
        System.out.println(max);


    }
    static void solve(int R, int C, String map[][]){
        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                if(map[i][j].equals("L")){
                    bfs(i,j,R,C,map);

                }
            }
        }

    }
    static void bfs(int start_y, int start_x, int R, int C, String map[][]){
        Queue<Location> queue = new LinkedList<>();
        boolean check[][] = new boolean[R][C];

        queue.add(new Location(start_y,start_x,0));
        check[start_y][start_x] = true;

        while(!queue.isEmpty()){
            Location cur = queue.poll();

            for(int i=0; i<4; i++){
                int y = cur.y + dy[i];
                int x = cur.x + dx[i];

                if(y < 0 || x < 0 || y >= R || x >= C || check[y][x] || map[y][x].equals("W")){
                    continue;
                }

                max = Math.max(max, cur.dist + 1);
                queue.add(new Location(y,x,cur.dist + 1));
                check[y][x] = true;
            }
        }
        
    }
}