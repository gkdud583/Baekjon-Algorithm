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
    static int map[][];
    public static void main(String[]args) throws IOException{
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        map = new int[N][M];

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                map[i][j] = sc.nextInt();
            }
        }


        solve(N,M);

    }
    static void solve(int N, int M){
        int paintingCount = 0, paintingMaxArea = 0;
        boolean check[][] = new boolean[N][M];
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(map[i][j] == 1 && !check[i][j])
                {
                    paintingCount++;
                    paintingMaxArea = Math.max(paintingMaxArea, bfs(i,j,N,M,check));
                }
            }
        }
        System.out.println(paintingCount);
        System.out.println(paintingMaxArea);
    }
    static int bfs(int y, int x, int N, int M, boolean check[][]){
        Queue<Location> queue = new LinkedList<>();
        queue.add(new Location(y,x));
        check[y][x] = true;

        int dy[] = {-1,1,0,0};
        int dx[] = {0,0,-1,1};
        int count = 1;
        while(!queue.isEmpty()){
            Location cur = queue.poll();

            for(int i=0; i<4; i++){
                int moveY = cur.y + dy[i];
                int moveX = cur.x + dx[i];

                if(moveX < 0 || moveY < 0 || moveX >= M || moveY >= N || map[moveY][moveX] == 0 || check[moveY][moveX])
                    continue;
                
                check[moveY][moveX] = true;
                queue.add(new Location(moveY, moveX));
                count++;

            }

        }
        return count;



    }
}