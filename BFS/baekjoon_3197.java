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
    static final int dy[] = {-1,1,0,0};
    static final int dx[] = {0,0,-1,1};
    static Queue<Location> queue = new LinkedList<>();
    static boolean blockedRoadArr[][];
    static boolean check[][];
    static int mark[][];
    static Queue<Location> waterList = new LinkedList<>();
    static Location swan2;

    static int map[][];

    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);

        String input[] = sc.nextLine().split(" ");
        int R = Integer.parseInt(input[0]);
        int C = Integer.parseInt(input[1]);

        map = new int[R][C];
        blockedRoadArr = new boolean[R][C];
        mark = new int[R][C];
        check = new boolean[R][C];

        ArrayList<Location> swanList = new ArrayList<>();
        for(int i=0; i<R; i++){
            input = sc.nextLine().split("");
            for(int j=0; j<C; j++){
                if(input[j].equals(".")){
                    map[i][j] = 0;
                    waterList.add(new Location(i,j));
                }else if(input[j].equals("L")){
                    map[i][j] = 2;
                    waterList.add(new Location(i,j));
                    swanList.add(new Location(i,j));
                }else{
                    map[i][j] = 1;
                }
            }
        }
        Location swan1 = swanList.get(0);
        swan2 = swanList.get(1);

        queue.add(new Location(swan1.y, swan1.x));

        check[swan1.y][swan1.x] = true;

        if(isPossible(R,C)){
            System.out.println(0);
        }else{
            System.out.println(solve(R,C));
        }

    }
    static boolean isPossible(int R, int C){



        while(!queue.isEmpty()){
            Location cur = queue.poll();

            for(int i=0; i<4; i++){
                int y = cur.y + dy[i];
                int x = cur.x + dx[i];

                if(outOfArray(y, x, R, C) || check[y][x])
                    continue;
            
                
                if(y == swan2.y && x == swan2.x){
                    return true;
                }

                check[y][x] = true;
                
                if(map[y][x] == 1){
                    blockedRoadArr[y][x] = true;
                    continue;
                }


                
                queue.add(new Location(y,x));


            }

        }
        return false;
    }
    static boolean outOfArray(int y, int x, int R, int C){
        if(y < 0 || x < 0 || y >= R || x >= C)
            return true;
        return false;
    }
    static int solve(int R, int C){
        for(int i=1;; i++){
            meltGlacier(R, C);
            if(isPossible(R,C))
                return i;
        }

    }
    
    static void meltGlacier(int R, int C){
        for(int i=0, size=waterList.size(); i<size; i++){
            Location g = waterList.poll();
            for(int j=0; j<4; j++){
                int y = g.y + dy[j];
                int x = g.x + dx[j];

                if(outOfArray(y,x,R,C) || map[y][x] != 1)
                    continue;

                map[y][x] = 0;
                waterList.add(new Location(y, x));

                if(blockedRoadArr[y][x]){
                    blockedRoadArr[y][x] = false;
                    queue.add(new Location(y,x));
                }
                   
                
            }

        }
    }
    
}