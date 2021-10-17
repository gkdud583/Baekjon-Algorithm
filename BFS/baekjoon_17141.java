import java.io.*;
import java.util.*;

class Location{
    int y;
    int x;
    Location(int y, int x){
        this.y = y;
        this.x = x;
    }


  
}
public class baekjoon_17141 {

    static final int MAX = 987654321;
    static int min = MAX;
    static int emptyCnt = 0;
    static int map[][];
    static ArrayList<Location> virusPossibleList = new ArrayList<>();
    public static void main(String[]args) throws Exception{
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        map = new int[N][N];
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                map[i][j] = sc.nextInt();
                if(map[i][j] != 1){
                    emptyCnt++;
                    if(map[i][j] == 2){
                        virusPossibleList.add(new Location(i,j));
                    }
    
                }
            }
        }
        solve(0,-1,0,M,N,new HashSet<>());
        System.out.println(min == MAX ? -1 : min);
    }
    static void solve(int sy, int sx, int index, int size, int N, Set<Location> select){
        if(index >= size){
            min = Math.min(min, bfs(N, select));
            return;
        }
        for(int i=sy; i<N; i++){
            for(int j=0; j<N; j++){
                if(i == sy && j < sx)
                    continue;
                if(map[i][j] != 2)
                    continue;
                Location loc = new Location(i,j);
                select.add(loc);
                solve(i, j+1, index+1, size, N, select);
                select.remove(loc);

            }
        }
        

    }
    static int bfs(int N, Set<Location> select){

        Queue<Location> queue = new LinkedList<>();
        boolean check[][] = new boolean[N][N];

        for(Location loc : select){
            queue.add(new Location(loc.y, loc.x));
            check[loc.y][loc.x] = true;
        }
        

        int tempEmptyCnt = emptyCnt - queue.size();
        int t = 0;

        int dy[] = {-1,1,0,0};
        int dx[] = {0,0,-1,1};
        while(!queue.isEmpty()){
            int virusCnt = 0;
            for(int i=0, size=queue.size(); i<size; i++){
                Location cur = queue.poll();
                for(int j=0; j<4; j++){
                    int y = cur.y + dy[j];
                    int x = cur.x + dx[j];
                    if(y < 0 || x < 0 || y >= N || x >= N || check[y][x] || map[y][x] == 1)
                        continue;
                    virusCnt++;
                    queue.add(new Location(y,x));
                    check[y][x] = true;
                    tempEmptyCnt--;
                }
            }
            if(virusCnt == 0)
                break;
            t++;
            if(tempEmptyCnt == 0)
                break;
        }
        return tempEmptyCnt == 0 ? t : MAX;
    }
}