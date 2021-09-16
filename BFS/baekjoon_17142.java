import java.util.*;

class Location{
    int y;
    int x;
    int t;
    Location(int y, int x, int t){
        this.y = y;
        this.x = x;
        this.t = t;
    }

}
class baekjoon_17142{
    static final int MAX = 987654321;
    static int min = MAX;
    static Location combination[];
    static ArrayList<Location> virusList = new ArrayList<>();
    static int zeroCount = 0;
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);  

        int N = sc.nextInt();
        int M = sc.nextInt();

        int map[][] = new int[N][N];
        combination = new Location[M];

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                map[i][j] = sc.nextInt();
                if(map[i][j] == 2){
                    virusList.add(new Location(i,j,0));
                }else if(map[i][j] == 0)
                    zeroCount++;
            }
        }

        if(zeroCount == 0)
            System.out.println(0);
        else{
            getCombination(0, 0, N, M, map);
            System.out.println(min == MAX ? -1 : min);
        }

    }

    static void getCombination(int start, int index, int N, int M, int map[][]){
        
        if(index >= M){
            bfs(N, M, map);
            return;
        }

        for(int i=start; i<virusList.size(); i++){

            Location cur = virusList.get(i);
            combination[index] = cur;
            getCombination(i+1, index+1, N, M, map);
        }
    }
    static void bfs(int N, int M, int map[][]){
        
        int dy[] = {-1,1,0,0};
        int dx[] = {0,0,-1,1};
        Queue<Location> tempCombination = new LinkedList<>();
        boolean check[][] = new boolean[N][N];


        

        for(int i=0; i<M; i++){
            Location loc = combination[i];
            check[loc.y][loc.x] = true;
            tempCombination.add(new Location(loc.y, loc.x,0));
        }
        
        int tempZeroCount = zeroCount;
        while(!tempCombination.isEmpty()){
            Location cur = tempCombination.poll();
            for(int j=0; j<4; j++){
                int y = cur.y + dy[j];
                int x = cur.x + dx[j];

                //0빈칸 1벽 2비활성바이러스 -1활성바이러스
                if(y < 0 || x < 0 || y >= N || x >= N || check[y][x] || map[y][x] == 1)
                    continue;

                check[y][x] = true;
                tempCombination.add(new Location(y,x,cur.t+1));

                if(map[y][x] == 0){
                    tempZeroCount--;
                }
                
                if(tempZeroCount == 0){
                    min = Math.min(min, cur.t+1);
                    return;
                    
                }

            }
            
        }
    }
}