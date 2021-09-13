import java.util.*;


class Location{
    int y;
    int x;
    Location(int y, int x){
        this.y = y;
        this.x = x;
    }
}
class baekjoon_2146{
    static int dy[] = {-1,1,0,0};
    static int dx[] = {0,0,-1,1};
    static int min = 987654321;

    
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);  

        int N = sc.nextInt();

        int map[][] = new int[N][N];
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                map[i][j] = sc.nextInt();
            }
        }

        solve(N,map);
    }
    static void solve(int N, int map[][]){
        boolean check[][] = new boolean[N][N];
        int index = 1;
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(!check[i][j] && map[i][j] != 0){
                    findIL(index++,i,j,N,map,check);
                }
            }
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(map[i][j] != 0)
                    getLen(i,j,N, map);
            }
        }

        System.out.println(min);
    }
    static void findIL(int index, int start_y, int start_x, int N, int map[][],boolean check[][]){
        Queue<Location> queue = new LinkedList<>();
        queue.add(new Location(start_y, start_x));
        check[start_y][start_x] = true;
        map[start_y][start_x] = index;


        while(!queue.isEmpty()){
            Location cur = queue.poll();

            for(int i=0; i<4; i++){
                int y = cur.y + dy[i];
                int x = cur.x + dx[i];

                if(y < 0 || x < 0 || y >= N || x >= N || check[y][x] || map[y][x] == 0){
                    continue;
                }

                map[y][x] = index;
                queue.add(new Location(y,x));
                check[y][x] = true;
            }
        }


    }
    static void getLen(int start_y, int start_x, int N,int map[][]){
        Queue<Location> queue = new LinkedList<>();
        boolean check[][] = new boolean[N][N];
        int count[][] = new int[N][N];

        
        queue.add(new Location(start_y, start_x));
        check[start_y][start_x] = true;
        int index = map[start_y][start_x];


        while(!queue.isEmpty()){
            Location cur = queue.poll();
            
            for(int i=0; i<4; i++){
                int y = cur.y + dy[i];
                int x = cur.x + dx[i];

                if(y < 0 || x < 0 || y >= N || x >= N || check[y][x] || map[y][x] == index)
                    continue;

                check[y][x] = true;
                count[y][x] = count[cur.y][cur.x] + 1;

                if(map[y][x] == 0){
                    queue.add(new Location(y,x));
                    
                    
                }else
                    min = Math.min(min, count[y][x]-1);

               

            }
        }

    }
}