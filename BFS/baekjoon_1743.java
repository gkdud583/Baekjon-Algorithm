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

class Main
{
    static boolean map[][];
    static ArrayList<Location> trashLocList = new ArrayList<>();
    public static void main(String[]args) throws IOException{
        Scanner sc = new Scanner(System.in);
        
        int N = sc.nextInt();
        int M = sc.nextInt();
        int K = sc.nextInt();

        map = new boolean[N][M];
        for(int i=0; i<K; i++){
            int y = sc.nextInt() - 1;
            int x = sc.nextInt() - 1;
            trashLocList.add(new Location(y,x));
            map[y][x] = true;
        }

        solve(N,M);

    }
    static void solve(int N, int M){
        int max = 0;
        boolean check[][] = new boolean[N][M];
        for(int i=0; i<trashLocList.size(); i++){
            Location cur = trashLocList.get(i);

            if(!check[cur.y][cur.x]){
                max = Math.max(max, bfs(cur.y, cur.x, N, M, check));
            }
        }
        System.out.println(max);
    }
    static int bfs(int y, int x, int N, int M, boolean check[][]){
        Queue<Location> queue = new LinkedList<>();
        queue.add(new Location(y,x));
        check[y][x] = true;

        int count = 1;
        int dy[] = {-1,1,0,0};
        int dx[] = {0,0,-1,1};
        while(!queue.isEmpty()){
            Location cur = queue.poll();
            for(int i=0; i<4; i++){
                int moveY = cur.y + dy[i];
                int moveX = cur.x + dx[i];

                if(moveX < 0 || moveX >= M || moveY < 0 || moveY >= N || !map[moveY][moveX] || check[moveY][moveX])
                    continue;
                check[moveY][moveX] = true;
                queue.add(new Location(moveY, moveX));
                count++;
            }
        }
        return count;

    }
}
