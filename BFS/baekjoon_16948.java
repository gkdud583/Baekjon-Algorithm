
import java.io.*;
import java.util.*;



class Move{
    int y;
    int x;
    int c;
    Move(int y, int x, int c){
        this.y = y;
        this.x = x;
        this.c = c;
    }
    
}
public class baekjoon_16948 {

    static int sy;
    static int sx;
    static int ey;
    static int ex;
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        sy = sc.nextInt();
        sx = sc.nextInt();
        ey = sc.nextInt();
        ex = sc.nextInt();

        System.out.println(bfs(N));

    }
    static int bfs(int N){
        Queue<Move> queue = new LinkedList<>();
        queue.add(new Move(sy, sx, 0));

        int check[][] = new int[N][N];

        for(int i=0; i<N; i++)
            Arrays.fill(check[i], -1);
        check[sy][sx] = 0;

        int dy[] = {-2,-2,0,0,2,2};
        int dx[] = {-1,1,-2,2,-1,1};
        while(!queue.isEmpty()){
            Move cur = queue.poll();

            for(int i=0; i<6; i++){
                int y = cur.y + dy[i];
                int x = cur.x + dx[i];

                if(y < 0 || x < 0 || y >= N || x >= N || check[y][x] != -1)
                    continue;
                if(y == ey && x == ex)
                    return cur.c+1;
                check[y][x] = cur.c + 1;
                queue.add(new Move(y,x,cur.c+1));
            }
        }
        return -1;
    }
    
}