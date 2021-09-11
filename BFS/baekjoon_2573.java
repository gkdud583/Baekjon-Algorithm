import java.util.*;

class Location{
    int y;
    int x;
    Location(int y, int x){
        this.y = y;
        this.x = x;
    }
}

class baekjoon_2573{
    static int dy[] = {-1,1,0,0};
    static int dx[] = {0,0,-1,1};


    
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);  

        int N = sc.nextInt();
        int M = sc.nextInt();

        int map[][] = new int[N][M];

        for(int i=0; i<N; i++){
            for(int j=0;j<M; j++){
                map[i][j] = sc.nextInt();
            }
        }

        System.out.println(solve(N,M,map));


    }
    static int solve(int N, int M, int map[][]){
        
        int year = 0;
        while(true){
            
            boolean check[][] = new boolean[N][M];
            int count = 0;
            for(int i=0; i<N; i++){
                for(int j=0; j<M; j++){
                    if(!check[i][j] && map[i][j] > 0){
                        count++;
                        ArrayList<Location> list = bfs(i,j,N,M,map,check);
                        meltGlacier(list, N, M, map);
                    }
                }
            }
            if(count >= 2)
                return year;
            if(count == 0)
                return 0;
            year++;         
          
            
        }
    }

    static boolean isOutOfArray(int y, int x,int N, int M){
        if(y < 0 || y >= N || x < 0 || x >= M)
            return true;
        return false;
    }
    static void meltGlacier(ArrayList<Location> list, int N, int M, int map[][]){
        int tempMap[][] = coppyMap(N, M, map);

        for(int i=0; i<list.size(); i++){
            Location cur = list.get(i);
            if(cur.y == 1 && cur.x == 3){
                int t = 3;
            }
            int count = 0;

            for(int j=0; j<4; j++){
                int y = cur.y + dy[j];
                int x = cur.x + dx[j];
                
                //상하좌우에 물의 개수를 구함.
                if(isOutOfArray(y,x,N,M) || map[y][x] > 0)
                    continue;
                count++;
            }
            tempMap[cur.y][cur.x] -= count;
            if(tempMap[cur.y][cur.x] < 0)
                tempMap[cur.y][cur.x] = 0;
        }
        for(int i=0; i<N; i++){
            map[i] = tempMap[i].clone();
        }
    }
    static ArrayList<Location> bfs(int start_y, int start_x, int N, int M, int map[][],boolean check[][]){
        ArrayList<Location> list = new ArrayList<>();
        Queue<Location> queue = new LinkedList<>();

        queue.add(new Location(start_y, start_x));
        list.add(new Location(start_y, start_x));

        check[start_y][start_x] = true;

        

        while(!queue.isEmpty()){

            Location cur = queue.poll();

            //빙하의 높이가 줄어든다.

            for(int i=0; i<4; i++){
                int y = cur.y + dy[i];
                int x = cur.x + dx[i];

                if(isOutOfArray(y,x,N,M) || check[y][x] || map[y][x] == 0)
                    continue;
                
                queue.add(new Location(y,x));
                list.add(new Location(y,x));
                check[y][x] = true;
            }
           
        }
        return list;
    }
    static int[][] coppyMap(int N, int M, int map[][]){
        int tempMap[][] = new int[N][M];

        for(int i=0; i<N; i++)
            tempMap[i] = map[i].clone();
        
        return tempMap;
    }
}
