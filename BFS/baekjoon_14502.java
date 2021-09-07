import java.util.*;

class Location{
    int y;
    int x;
    Location(int y, int x){
        this.y = y;
        this.x = x;
    }
}


class baekjoon_14502{
    
    static final int SIZE = 3; //세울수 있는 벽의 크기
    static int MAX = 0;
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);  

        int N = sc.nextInt();
        int M = sc.nextInt();

        int map[][] = new int[N][M];
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                map[i][j] = sc.nextInt();
            }
        }

        boolean check[][] = new boolean[N][M];
        solve(0,check,N,M,map);

        System.out.println(MAX);
    }
    static void solve(int i, boolean check[][], int N, int M,int map[][]){
        if(i >= SIZE){

            int count = getSafeZone(N,M,map);
            MAX = Math.max(MAX, count);
            return;
        }
        
        for(int j=0; j<N; j++){
            for(int k=0; k<M; k++){

                if(!check[j][k] && map[j][k] == 0){
                    int tempMap[][] = copyMap(N,M,map);
                    tempMap[j][k] = 1;
                    boolean tempCheck[][] = copyCheck(N,M,check);
                    tempCheck[j][k] = true;
                    solve(i+1, tempCheck, N, M, tempMap);
                }
            }
        }
    }
    static boolean[][] copyCheck(int N, int M, boolean check[][]){
        boolean tempCheck[][] = new boolean[N][M];

        for(int i=0; i<N; i++){
            tempCheck[i] = check[i].clone();
        }
        return tempCheck;
    }
    static int[][] copyMap(int N, int M, int map[][]){
        int tempMap[][] = new int[N][M];
        for(int i=0; i<N; i++){
            tempMap[i] = map[i].clone();
        }
        return tempMap;

    }
    static int getSafeZone(int N, int M, int map[][]){
        Queue<Location> queue = new LinkedList<>();
        boolean check[][] = new boolean[N][M];

        int count = 0;
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(map[i][j] == 2){
                    queue.add(new Location(i,j));
                    check[i][j] = true;
                    
                }else if(map[i][j] == 0)
                    count++;
            }
        }

        int dy[] = {-1,1,0,0};
        int dx[] = {0,0,-1,1};

        while(!queue.isEmpty()){
            Location loc = queue.poll();
            for(int i=0; i<4; i++){
                int y = loc.y + dy[i];
                int x = loc.x + dx[i];

                if(y < 0 || x < 0 || y >= N || x >= M || check[y][x] || map[y][x] > 0){
                    continue;
                }
                check[y][x] = true;
                queue.add(new Location(y,x));
                count--;

            }
        }
        return count;

    }
    
    
}

