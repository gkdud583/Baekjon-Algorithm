import java.io.*;
import java.util.*;

class Location{
    int y;
    int x;
    int breakCount;
    int totalCount;
    Location(int y, int x, int breakCount, int totalCount){
        this.y = y;
        this.x = x;
        this.breakCount = breakCount;
        this.totalCount = totalCount;

    }
}
class Main
{
    static final int MAX = 987654321;
    static int map[][];
    public static void main(String[]args) throws IOException{
        Scanner sc = new Scanner(System.in);

        String input[] = sc.nextLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        int K = Integer.parseInt(input[2]);

        map = new int[N+1][M+1];
        for(int i=1; i<=N; i++){
            input = sc.nextLine().split("");
            for(int j=0; j<M; j++){
                map[i][j+1] = Integer.parseInt(input[j]);
            }
        }

        System.out.println(bfs(N,M,K));


    }
    static int bfs(int N, int M, int K){
        int count[][][] = new int[N+1][M+1][K+1];
        for(int i=1; i<=N; i++){
            for(int j=1; j<=M; j++)
                Arrays.fill(count[i][j], MAX);
        }
        count[1][1][0] = 1;
        Queue<Location> queue = new LinkedList<>();
        queue.add(new Location(1,1,0,1));

        int dy[] = {-1,1,0,0};
        int dx[] = {0,0,-1,1};
        while(!queue.isEmpty()){
            Location cur = queue.poll();
            for(int i=0; i<4; i++){
                int y = cur.y + dy[i];
                int x = cur.x + dx[i];

                if(y < 1 || x < 1 || y > N || x > M)
                    continue;

                if(map[y][x] == 1 && cur.breakCount+1 <= K){
                    if(count[y][x][cur.breakCount+1] > cur.totalCount + 1){
                        count[y][x][cur.breakCount+1] = cur.totalCount + 1;
                        queue.add(new Location(y,x,cur.breakCount+1,cur.totalCount+1));
                    }
                }else if(map[y][x] == 0){
                    if(count[y][x][cur.breakCount] > cur.totalCount + 1){
                        count[y][x][cur.breakCount] = cur.totalCount + 1;
                        queue.add(new Location(y,x,cur.breakCount,cur.totalCount+1));
                    }
                }
            }
        }
        int min = MAX;
        for(int i=0; i<=K; i++)
            min = Math.min(min, count[N][M][i]);
        return min == MAX ? -1 : min;
    }
}