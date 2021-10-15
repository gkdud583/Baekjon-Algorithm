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
public class baekjoon_16946 {


    static StringBuffer sb;
    static int count[] = new int[1000*1000+1];
    static final int dy[] = {-1,1,0,0};
    static final int dx[] = {0,0,-1,1};


    static int map[][];
    public static void main(String[]args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
        String input[] = br.readLine().split(" ");
        
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        map = new int[N][M];

        for(int i=0; i<N; i++){
            input = br.readLine().split("");
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(input[j]);
            }
        }

        sb = new StringBuffer();
        solve(N,M);

    }
    static void solve(int N, int M){
        

        //빈곳 묶음 만들기
        int index = 1;
        int bundle[][] = new int[N][M];
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(map[i][j] == 0 && bundle[i][j] == 0){
                    bfs(i, j, index++, N, M, bundle);
                } 
            }
        }
        
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(map[i][j] == 0)
                    sb.append(0);
                else{
                    int cnt = 1;
                    // boolean check[] = new boolean[index];
                    Set<Integer> set = new HashSet<>();
                    for(int k=0; k<4; k++){
                        int y = i + dy[k];
                        int x = j + dx[k];
        
                        if(outOfArray(y,x,N,M) || map[y][x] == 1 || set.contains(bundle[y][x]))
                            continue;
                        set.add(bundle[y][x]);
                        // check[bundle[y][x]] = true;
                        cnt += count[bundle[y][x]];
        
                    }
                    sb.append(cnt % 10);
                }
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
    static boolean outOfArray(int y, int x, int N, int M){
        if(y < 0 || x < 0 || y >= N || x >= M)
            return true;
        return false;
    }
    static void bfs(int y, int x, int index, int N, int M, int bundle[][]){
        Queue<Location> queue = new LinkedList<>();

        queue.add(new Location(y,x));

        bundle[y][x] = index;


        int cnt = 1;

        while(!queue.isEmpty()){
            Location cur = queue.poll();

            for(int i=0; i<4; i++){
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];

                if(outOfArray(ny, nx, N, M) || bundle[ny][nx] != 0 || map[ny][nx] == 1)
                    continue;
                cnt++;
                bundle[ny][nx] = index;
                queue.add(new Location(ny,nx));

            }
        }
        count[index] = cnt;

    }
}
