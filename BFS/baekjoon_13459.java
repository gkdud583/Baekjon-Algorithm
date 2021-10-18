import java.io.*;
import java.util.*;


  
class Move{
    int ry;
    int rx;
    int by;
    int bx;
    int cnt;
    Move(int ry, int rx, int by, int bx, int cnt){
        this.ry = ry;
        this.rx = rx;
        this.by = by;
        this.bx = bx;
        this.cnt = cnt;
    }
}
public class baekjoon_13459 {
    static int map[][];
    static Move start = new Move(0,0,0,0,0);

    public static void main(String[]args) throws Exception{
        Scanner sc = new Scanner(System.in);

        String input[] = sc.nextLine().split(" ");

        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        map = new int[N][M];

        for(int i=0; i<N; i++){
            input = sc.nextLine().split("");
            for(int j=0; j<M; j++){
                if(input[j].equals("."))
                    map[i][j] = 0;
                else if(input[j].equals("#"))
                    map[i][j] = 1;
                else if(input[j].equals("O"))
                    map[i][j] = 2;
                else if(input[j].equals("R"))
                {
                    start.ry = i;
                    start.rx = j;
                    map[i][j] = 0;
                }else if(input[j].equals("B")){
                    start.by = i;
                    start.bx = j;
                    map[i][j] = 0;

                }

            }
        }
        System.out.println(bfs(N,M));
    }
    static int bfs(int N, int M){
        Queue<Move> queue = new LinkedList<>();
        queue.add(start);
        boolean check[][][][] = new boolean[N][M][N][M];
        check[start.ry][start.rx][start.by][start.bx] = true;

        while(!queue.isEmpty()){
            Move cur = queue.poll();
            if(cur.cnt+1 <= 10){
                if(moveTop(cur.ry, cur.rx, cur.by, cur.bx, cur.cnt, check, queue))
                    return 1;
                if(moveBottom(N, cur.ry, cur.rx, cur.by, cur.bx, cur.cnt, check, queue))
                    return 1;
                if(moveLeft(cur.ry, cur.rx, cur.by, cur.bx, cur.cnt, check, queue))
                    return 1;
                if(moveRight(M, cur.ry, cur.rx, cur.by, cur.bx, cur.cnt, check, queue))
                    return 1;
            }

        }
        return 0;

    }
    static int getTopLocation(int y, int x, int my, int mx){
        
        for(int i=y-1; i>=0; i--){
            if(map[i][x] == 1 || (my >=0 && map[my][mx] != 2 && i == my && x == mx))
                break;
            if(map[i][x] == 2)
                return i;
            y = i;
        }
        return y;
    }
    static boolean moveTop(int ry, int rx, int by, int bx, int cnt, boolean check[][][][], Queue<Move> queue){

        if(ry < by){
            ry = getTopLocation(ry,rx,-1,-1);
            by = getTopLocation(by,bx,ry,rx);

            if(map[ry][rx] == 2 && map[by][bx] != 2)
                return true;
            if(map[ry][rx] == 2 || map[by][bx] == 2)
                return false;

            if(check[ry][rx][by][bx])
                return false;
            check[ry][rx][by][bx] = true;
            queue.add(new Move(ry,rx,by,bx,cnt+1));
            
        }else{
            by = getTopLocation(by,bx,-1,-1);
            ry = getTopLocation(ry,rx,by,bx);

            if(map[ry][rx] == 2 && map[by][bx] != 2)
                return true;
            if(map[ry][rx] == 2 || map[by][bx] == 2)
                return false;

            if(check[ry][rx][by][bx])
                return false;
            check[ry][rx][by][bx] = true;
            queue.add(new Move(ry,rx,by,bx,cnt+1));
            
        }
        return false;
    } 
    static int getBottomLocation(int y, int x, int N, int my, int mx){
        for(int i=y+1; i<N; i++){
            if(map[i][x] == 1 || (my >=0 && map[my][mx] != 2 && i == my && x == mx))
                break;
            if(map[i][x] == 2)
                return i;
            y = i;
        }
        return y;
    }
    static boolean moveBottom(int N, int ry, int rx, int by, int bx, int cnt, boolean check[][][][], Queue<Move> queue){
        if(ry > by){
            ry = getBottomLocation(ry, rx, N, -1, -1);
            by = getBottomLocation(by, bx, N, ry, rx);
            
            if(map[ry][rx] == 2 && map[by][bx] != 2)
                return true;
            if(map[ry][rx] == 2 || map[by][bx] == 2)
                return false;

            if(check[ry][rx][by][bx])
                return false;
            check[ry][rx][by][bx] = true;
            queue.add(new Move(ry,rx,by,bx,cnt+1));
        }else{
            by = getBottomLocation(by, bx, N, -1, -1);
            ry = getBottomLocation(ry, rx, N, by, bx);

            if(map[ry][rx] == 2 && map[by][bx] != 2)
                return true;
            if(map[ry][rx] == 2 || map[by][bx] == 2)
                return false;

            if(check[ry][rx][by][bx])
                return false;
            check[ry][rx][by][bx] = true;
            queue.add(new Move(ry,rx,by,bx,cnt+1));
        }
        return false;
    }

    static int getLeftLocation(int y, int x, int my, int mx){
        for(int i=x-1; i>=0; i--){
            if(map[y][i] == 1 || (mx >=0 && map[my][mx] != 2 && y == my && i == mx))
                break;
            if(map[y][i] == 2)
                return i;
            x = i;
        }
        return x;
    }
    static boolean moveLeft(int ry, int rx, int by, int bx, int cnt, boolean check[][][][], Queue<Move> queue){
        if(rx < bx){
            rx = getLeftLocation(ry, rx, -1, -1);
            bx = getLeftLocation(by, bx, ry, rx);

            if(map[ry][rx] == 2 && map[by][bx] != 2)
                return true;
            if(map[ry][rx] == 2 || map[by][bx] == 2)
                return false;

            if(check[ry][rx][by][bx])
                return false;
            check[ry][rx][by][bx] = true;
            queue.add(new Move(ry,rx,by,bx,cnt+1));

            
        }else{  
            bx = getLeftLocation(by, bx, -1, -1);
            rx = getLeftLocation(ry, rx, by, bx);

            if(map[ry][rx] == 2 && map[by][bx] != 2)
                return true;
            if(map[ry][rx] == 2 || map[by][bx] == 2)
                return false;

            if(check[ry][rx][by][bx])
                return false;
            check[ry][rx][by][bx] = true;
            queue.add(new Move(ry,rx,by,bx,cnt+1));
        }
        return false;
    }
    
    static int getRightLocation(int y, int x, int M, int my, int mx){
        for(int i=x+1; i<M; i++){
            if(map[y][i] == 1 || (mx >= 0 && map[my][mx] != 2 && i == mx && y == my))
                break;
            if(map[y][i] == 2)
                return i;
            x = i;
        }
        return x;
    }
    static boolean moveRight(int M, int ry, int rx, int by, int bx, int cnt, boolean check[][][][], Queue<Move> queue){
        if(rx > bx){
            rx = getRightLocation(ry, rx, M, -1, -1);
            bx = getRightLocation(by, bx, M, ry, rx);

            if(map[ry][rx] == 2 && map[by][bx] != 2)
                return true;

            if(map[ry][rx] == 2 || map[by][bx] == 2)
                return false;

            if(check[ry][rx][by][bx])
                return false;
            check[ry][rx][by][bx] = true;
            queue.add(new Move(ry,rx,by,bx,cnt+1));
        }else{

            bx = getRightLocation(by, bx, M, -1, -1);
            rx = getRightLocation(ry, rx, M, by, bx);

            if(map[ry][rx] == 2 && map[by][bx] != 2)
                return true;
            if(map[ry][rx] == 2 || map[by][bx] == 2)
                return false;


            if(check[ry][rx][by][bx])
                return false;
            check[ry][rx][by][bx] = true;
            queue.add(new Move(ry,rx,by,bx,cnt+1));
        }
        return false;
    }
   
}

