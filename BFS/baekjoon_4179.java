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
class Move{
    int y;
    int x;
    int count;
    Move(int y, int x, int count){
        this.y = y;
        this.x = x;
        this.count = count;
    }
}
class Main
{
    static Location start;
    static String map[][];
    static Queue<Location> fireList = new LinkedList<>();
    static final int dy[] = {-1,1,0,0};
    static final int dx[] = {0,0,-1,1};

    public static void main(String[]args) throws IOException{
        Scanner sc = new Scanner(System.in);
        
        String input[] = sc.nextLine().split(" ");
        int R = Integer.parseInt(input[0]);
        int C = Integer.parseInt(input[1]);

        map = new String[R][C];
        for(int i=0; i<R; i++){
            input = sc.nextLine().split("");
            for(int j=0; j<C; j++){
                map[i][j] = input[j];
                if(map[i][j].equals("F"))
                    fireList.add(new Location(i,j));
                if(map[i][j].equals("J")){
                    start = new Location(i,j);
                }
            }
        }
        bfs(R,C);
    }
    static void bfs(int R, int C){
        Queue<Move> queue = new LinkedList<>();
        boolean check[][] = new boolean[R][C];
        check[start.y][start.x] = true;
        queue.add(new Move(start.y, start.x, 0));


        while(!queue.isEmpty()){
            for(int i=0,size=queue.size(); i<size; i++){
                Move cur = queue.poll();
                for(int j=0; j<4; j++){
                    int y = cur.y + dy[j];
                    int x = cur.x + dx[j];

                    if(y == 1 && x == 0){
                        int t = 3;
                    }
                    if(OutOfIndex(y,x,R,C))
                    {
                        System.out.println(cur.count + 1);
                        return;
                    }
                    if(map[y][x].equals("#") || map[y][x].equals("F") || check[y][x] || AroundFire(y,x,R,C,map))
                        continue;
                    
                    check[y][x] = true;
                    queue.add(new Move(y,x,cur.count+1));
                }
            }
            //불이동
            for(int i=0,size=fireList.size(); i<size; i++){
                Location fire = fireList.poll();

                for(int j=0; j<4; j++){
                    int y = fire.y + dy[j];
                    int x = fire.x + dx[j];
                    
                    if(OutOfIndex(y,x,R,C) || map[y][x].equals("F") || map[y][x].equals("#"))
                        continue;
                    map[y][x] = "F";
                    fireList.add(new Location(y,x));
                }
            }
            
        }
        System.out.println("IMPOSSIBLE");
    }
    static boolean OutOfIndex(int y, int x, int R, int C){
        if(y < 0 || x < 0 || y >= R || x >= C)
            return true;
        return false;
    }
    static boolean AroundFire(int y, int x, int R, int C, String map[][]){
        for(int i=0; i<4; i++){
            int ny = y + dy[i];
            int nx = x + dx[i];

            if(OutOfIndex(ny,nx,R,C))
                continue;
            if(map[ny][nx].equals("F"))
                return true;
        }
        return false;

    }
}