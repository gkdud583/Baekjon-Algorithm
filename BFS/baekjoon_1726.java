import java.io.*;
import java.util.*;


class Move{
    int y;
    int x;
    int d;
    int c;
    Move(int y, int x, int d, int c){
        this.y = y;
        this.x = x;
        this.d = d;
        this.c = c;
    }
}
class Main
{
    static Move start;
    static Move end;
    static int map[][];
    public static void main(String[]args) throws IOException{
    
        Scanner sc = new Scanner(System.in);

        int M = sc.nextInt();
        int N = sc.nextInt();


        map = new int[M][N];

        for(int i=0; i<M; i++){
            for(int j=0; j<N; j++){
                map[i][j] = sc.nextInt();
            }
        }

        start = new Move(sc.nextInt()-1, sc.nextInt()-1, sc.nextInt(), 0);
        end = new Move(sc.nextInt()-1, sc.nextInt()-1, sc.nextInt(), 0);

        System.out.println(bfs(M,N));

    }
    static int bfs(int M, int N){
        Queue<Move> queue = new LinkedList<>();
        boolean check[][][] = new boolean[M][N][5];

        queue.add(new Move(start.y, start.x, start.d, 0));
        check[start.y][start.x][start.d] = true;

        //1동 2서 3남 4북
        int left[] = {0,4,3,1,2};
        int right[] = {0,3,4,2,1};


        int dy[] = {0,0,0,1,-1};
        int dx[] = {0,1,-1,0,0};
        while(!queue.isEmpty()){
            Move cur = queue.poll();

           
            //명령1
            for(int i=1; i<=3; i++){
                int y = cur.y + (dy[cur.d] * i);
                int x = cur.x + (dx[cur.d] * i);

                if(outOfArray(y,x,M,N) || map[y][x] == 1) 
                    break;
                if(check[y][x][cur.d])
                    continue;
                if(isEnd(y,x,cur.d))
                    return cur.c+1;
                queue.add(new Move(y,x,cur.d,cur.c+1));
                check[y][x][cur.d] = true;
            }

            //명령2
            if(!check[cur.y][cur.x][left[cur.d]]){
                if(isEnd(cur.y, cur.x, left[cur.d]))
                    return cur.c+1;
            
                check[cur.y][cur.x][left[cur.d]] = true;
                queue.add(new Move(cur.y, cur.x, left[cur.d], cur.c+1));
            }

            if(!check[cur.y][cur.x][right[cur.d]])
            {
                if(isEnd(cur.y, cur.x, right[cur.d]))
                    return cur.c+1;
                check[cur.y][cur.x][right[cur.d]] = true;
                queue.add(new Move(cur.y, cur.x, right[cur.d], cur.c+1));


            }


        }
        return 0;
    }
    static boolean outOfArray(int y, int x, int M, int N){
        if(y < 0 || x < 0 || y >= M || x >= N)
            return true;
        return false;
    }
    static boolean isEnd(int y, int x, int d){
        if(y == end.y && x == end.x && d == end.d)
            return true;
        return false;
    }
}