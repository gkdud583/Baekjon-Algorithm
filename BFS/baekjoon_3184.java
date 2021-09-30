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

    static String map[][];
    static int sheepCount = 0;
    static int wolfCount = 0;
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
                if(map[i][j].equals("o"))
                    sheepCount++;
                if(map[i][j].equals("v"))
                    wolfCount++;
            }
        }
        solve(R,C);
        System.out.println(sheepCount+" "+wolfCount);



    }
    static void solve(int R, int C){
        boolean check[][] = new boolean[R][C];
        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                if(!map[i][j].equals("#") && !check[i][j]){
                    bfs(i,j,R,C,check);
                }
            }
        }

    }
    static void bfs(int y, int x, int R, int C, boolean check[][]){
        Queue<Location> queue = new LinkedList<>();
        queue.add(new Location(y,x));
        check[y][x] = true;

        int tempSheepCount = 0, tempWolfCount = 0;
        if(map[y][x].equals("o")){
            tempSheepCount++;
        }else if(map[y][x].equals("v")){
            tempWolfCount++;
        }

        int dy[] = {-1,1,0,0};
        int dx[] = {0,0,-1,1};
        
        while(!queue.isEmpty()){
            Location cur = queue.poll();
            for(int i=0; i<4; i++){
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];

                if(ny < 0 || nx < 0 || ny >= R || nx >= C || map[ny][nx].equals("#") || check[ny][nx])
                    continue;
                if(map[ny][nx].equals("o")){
                    tempSheepCount++;
                }else if(map[ny][nx].equals("v")){
                    tempWolfCount++;
                }
                queue.add(new Location(ny,nx));
                check[ny][nx] = true;
            }
        }
        if(tempSheepCount > tempWolfCount)
            wolfCount -= tempWolfCount;
        else
            sheepCount -= tempSheepCount;
    }
}