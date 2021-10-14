
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
class Log{
    int y;
    int x;
    int d;
    int cnt;
    int map[][];
    Log(int y, int x, int d, int cnt){
        this.y = y;
        this.x = x;
        this.d = d;
        this.cnt = cnt;
    }
}
public class baekjoon_1938 {

    static int map[][];
    static ArrayList<Location> startList = new ArrayList<>();
    static ArrayList<Location> endList = new ArrayList<>();
    static int endDir = 0;

    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);

        int N = Integer.parseInt(sc.nextLine());

        map = new int[N][N];



        for(int i=0; i<N; i++){
            String input[] = sc.nextLine().split("");
            for(int j=0; j<N; j++){
                if(input[j].equals("B")){
                    startList.add(new Location(i,j));
                    map[i][j] = 0;
                }else if(input[j].equals("E")){
                    endList.add(new Location(i,j));
                    map[i][j] = 0;

                }else{
                    map[i][j] = Integer.parseInt(input[j]);
                }
            }
        }

        endDir = endList.get(0).y < endList.get(1).y ? 1 : 0;

        System.out.println(solve(N));



    }
    static int solve(int N){

        Queue<Log> queue = new LinkedList<>();
        //d가 0이면 가로 1이면 세로
        int d = startList.get(0).y < startList.get(1).y ? 1 : 0;
        queue.add(new Log(startList.get(0).y, startList.get(0).x,d,0));
        boolean check[][][] = new boolean[N][N][2];
        check[startList.get(0).y][startList.get(0).x][d] = true;
        while(!queue.isEmpty()){
            Log cur = queue.poll();

            if(cur.d == 0){
                //가로
                //위로 이동
                if(!outOfArray(cur.y-1, cur.x, N) && 
                map[cur.y-1][cur.x] == 0 && map[cur.y-1][cur.x+1] == 0 && map[cur.y-1][cur.x+2] == 0){
                    if(!check[cur.y-1][cur.x][0]){
                        if(isEnd(cur.y-1, cur.x, 0))
                            return cur.cnt+1;
                        check[cur.y-1][cur.x][0] = true;
                        queue.add(new Log(cur.y-1, cur.x, 0, cur.cnt+1));
                        
                    }
                }
                
                //아래로이동
                if(!outOfArray(cur.y+1, cur.x, N) &&
                map[cur.y+1][cur.x] == 0 && map[cur.y+1][cur.x+1] == 0 && map[cur.y+1][cur.x+2] == 0){
                    if(!check[cur.y+1][cur.x][0]){
                        if(isEnd(cur.y+1, cur.x, 0))
                            return cur.cnt+1;
                        check[cur.y+1][cur.x][0] = true;
                        queue.add(new Log(cur.y+1, cur.x, 0, cur.cnt+1));

                    }
                }
                //왼쪽으로 이동
                if(!outOfArray(cur.y, cur.x-1, N) && map[cur.y][cur.x-1] == 0){
                    if(!check[cur.y][cur.x-1][0]){
                        if(isEnd(cur.y, cur.x-1, 0))
                            return cur.cnt+1;
                        check[cur.y][cur.x-1][0] =true;
                        queue.add(new Log(cur.y, cur.x-1, 0, cur.cnt+1));
                    }
                }
                //오른쪽으로 이동
                if(!outOfArray(cur.y, cur.x+3, N) && map[cur.y][cur.x+3] == 0){
                    if(!check[cur.y][cur.x+1][0]){
                        if(isEnd(cur.y, cur.x+1, 0))
                            return cur.cnt+1;
                        check[cur.y][cur.x+1][0] =true;
                        queue.add(new Log(cur.y, cur.x+1, 0, cur.cnt+1));
                    }
                }
                //회전
                if(!outOfArray(cur.y-1, cur.x+1, N) && !outOfArray(cur.y+1, cur.x+1, N) && check(cur.y, cur.x, cur.d, N)){
                    if(isEnd(cur.y-1, cur.x+1, 1))
                        return cur.cnt+1;
                    check[cur.y-1][cur.x+1][1] = true;
                    queue.add(new Log(cur.y-1, cur.x+1, 1, cur.cnt+1));
                }



            }else{
                //세로

                //위로 이동
                if(!outOfArray(cur.y-1,cur.x,N) && map[cur.y-1][cur.x] == 0){
                    if(!check[cur.y-1][cur.x][1]){
                        if(isEnd(cur.y-1, cur.x, 1))
                            return cur.cnt+1;

                        check[cur.y-1][cur.x][1] = true;
                        queue.add(new Log(cur.y-1, cur.x, 1, cur.cnt+1));
                    }
                }
                //아래로 이동
                if(!outOfArray(cur.y+3, cur.x, N) && map[cur.y+3][cur.x] == 0){
                    if(!check[cur.y+1][cur.x][1]){
                        if(isEnd(cur.y+1, cur.x, 1))
                            return cur.cnt+1;
                        check[cur.y+1][cur.x][1] = true;
                        queue.add(new Log(cur.y+1, cur.x, 1, cur.cnt+1));
                    }
                }
                //왼쪽으로 이동
                if(!outOfArray(cur.y, cur.x-1, N) && 
                map[cur.y][cur.x-1] == 0 && map[cur.y+1][cur.x-1] == 0 && map[cur.y+2][cur.x-1] == 0){
                    if(!check[cur.y][cur.x-1][1]){
                        if(isEnd(cur.y, cur.x-1, 1))
                            return cur.cnt+1;
                        check[cur.y][cur.x-1][1] = true;
                        queue.add(new Log(cur.y, cur.x-1, 1, cur.cnt+1));
                    }
                    
                }
                //오른쪽으로 이동
                if(!outOfArray(cur.y, cur.x+1, N) && 
                map[cur.y][cur.x+1] == 0 && map[cur.y+1][cur.x+1] == 0 && map[cur.y+2][cur.x+1] == 0){
                    if(!check[cur.y][cur.x+1][1]){
                        if(isEnd(cur.y, cur.x+1, 1))
                            return cur.cnt+1;
                        check[cur.y][cur.x+1][1] = true;
                        queue.add(new Log(cur.y, cur.x+1, 1, cur.cnt+1));
                    }
                    
                }
                //회전
                if(!outOfArray(cur.y+1, cur.x-1, N) && !outOfArray(cur.y+1, cur.x+1, N) && check(cur.y, cur.x, cur.d, N)){
                    if(!check[cur.y+1][cur.x-1][0]){
                        if(isEnd(cur.y+1, cur.x-1, 0))
                            return cur.cnt+1;
                        check[cur.y+1][cur.x-1][0] = true;
                        queue.add(new Log(cur.y+1, cur.x-1, 0, cur.cnt+1));
                    }
                }

            }
        }
        return 0;
    }
    static boolean outOfArray(int y, int x, int N){
        if(y < 0 || x < 0 || y >= N || x >= N)
            return true;
        return false;
    }
    static boolean isEnd(int y, int x, int d){
        if(y == endList.get(0).y && x == endList.get(0).x && d == endDir)
            return true;
        return false;
    }
    static boolean check(int y, int x, int d, int N){
        int rowDY[] = {-1,-1,-1,1,1,1};
        int rowDX[] = {0,1,2,0,1,2};
        int colDY[] = {0,1,2,0,1,2};
        int colDX[] = {-1,-1,-1,1,1,1};

        if(d == 0){
            for(int i=0; i<6; i++){
                int ny = y + rowDY[i];
                int nx = x + rowDX[i];

                if(outOfArray(ny, nx, N))
                    continue;
                if(map[ny][nx] == 1)
                    return false;
            }
            return true;
        }else{
            for(int i=0; i<6; i++){
                int ny = y + colDY[i];
                int nx = x + colDX[i];

                if(outOfArray(ny, nx, N))
                    continue;
                if(map[ny][nx] == 1)
                    return false;
            }
            return true;
        }
    }
}