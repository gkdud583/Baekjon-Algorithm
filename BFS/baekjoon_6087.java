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
    static final int MAX = 987654321;
    static int map[][];
    static ArrayList<Location> list = new ArrayList<>();

    public static void main(String[]args) throws IOException{
    
        Scanner sc = new Scanner(System.in);

        String input[] = sc.nextLine().split(" ");
        int W = Integer.parseInt(input[0]);
        int H = Integer.parseInt(input[1]);

      
        map = new int[H][W];
        for(int i=0; i<H; i++){
            input = sc.nextLine().split("");
            for(int j=0; j<W; j++){
                if(input[j].equals("."))
                    map[i][j] = 0;
                else if(input[j].equals("*"))
                    map[i][j] = 1;
                else if(input[j].equals("C")){
                    map[i][j] = 2;
                    list.add(new Location(i,j));

                }
            }
        }

        System.out.println(solve(H,W));

    }
    static int solve(int H, int W){
        Location start = list.get(0);
        Location end = list.get(1);

        Queue<Move> queue = new LinkedList<>();

        for(int i=0; i<4; i++)
            queue.add(new Move(start.y, start.x, i, 0));

        int moveCheck[][][] = new int[H][W][4];
        int mirrorCheck[][][][] = new int[H][W][4][2];
        for(int i=0; i<H; i++){
            for(int j=0; j<W; j++)
                Arrays.fill(moveCheck[i][j], MAX);
        }

        for(int i=0; i<H; i++){
            for(int j=0; j<W; j++){
                for(int k=0; k<4; k++){
                    Arrays.fill(mirrorCheck[i][j][k], MAX);

                }
            }
        }

        
        int dy[] = {0,0,1,-1};
        int dx[] = {1,-1,0,0};

        int dl[] = {2,3,0,1};
        int dr[] = {3,2,1,0};


        while(!queue.isEmpty()){
            Move cur = queue.poll();
            
            int y = cur.y + dy[cur.d];
            int x = cur.x + dx[cur.d];

            
            
            if(!outOfArray(y,x,H,W) && map[y][x] != 1){
                if(moveCheck[y][x][cur.d] >= cur.c){
                    moveCheck[y][x][cur.d] = cur.c;
                    queue.add(new Move(y,x,cur.d,cur.c));
                }
                
                if(mirrorCheck[y][x][cur.d][0] >= cur.c + 1){
                    mirrorCheck[y][x][cur.d][0] = cur.c + 1;
                    queue.add(new Move(y,x,dl[cur.d],cur.c+1));
                }

                if(mirrorCheck[y][x][cur.d][1] >= cur.c + 1){
                    mirrorCheck[y][x][cur.d][1] = cur.c + 1;
                    queue.add(new Move(y,x,dr[cur.d],cur.c+1));
                }
            }
            
        }
        int min = MAX;
        for(int i=0; i<4; i++){
            min = Math.min(min, moveCheck[end.y][end.x][i]);
        }
        return min;


    }
    static boolean outOfArray(int y, int x,int H, int W){
        if(y < 0 || x < 0 || y >= H || x >= W)
            return true;
        return false;
    }
}