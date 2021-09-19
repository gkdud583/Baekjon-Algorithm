
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
    static int dy[] = {-1,1,0,0};
    static int dx[] = {0,0,-1,1};
    static String map[][];
    static Queue<Location> fireList;
    static boolean fireMap[][];

    static Location start;
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        
        int T = Integer.parseInt(sc.nextLine());
        for(int i=0; i<T; i++){
            String input[] = sc.nextLine().split(" ");
            int W = Integer.parseInt(input[0]);
            int H = Integer.parseInt(input[1]);
            map = new String[H][W];
            fireList = new LinkedList<>();
            fireMap = new boolean[H][W];
            for(int j=0; j<H; j++){
                String line[] = sc.nextLine().split("");    
                for(int k=0; k<W; k++){
                    map[j][k] = line[k];
                    if(map[j][k].equals("@")){
                        start = new Location(j,k);
                    }else if(map[j][k].equals("*")){
                        fireList.add(new Location(j,k));
                        fireMap[j][k] = true;
                    }
                }

            }
            solve(H,W,map);
        }
        
    }
    
    static void solve(int H, int W, String map[][]){
        Queue<Move> queue = new LinkedList<>();
        
        queue.add(new Move(start.y,start.x,0));
        boolean check[][] = new boolean[H][W];
        check[start.y][start.x] = true;
        

        while(!queue.isEmpty()){
            for(int i=0,size=queue.size(); i<size; i++){
                Move cur = queue.poll();
           

                //상근 이동
                for(int j=0; j<4; j++){

                    int y = cur.y + dy[j];
                    int x = cur.x + dx[j];

                    if(y < 0 || x < 0 || y >= H || x >= W){
                        System.out.println(cur.count + 1); 
                        return;
                    }

                    if(checkFire(H,W,y,x) || map[y][x].equals("#") || fireMap[y][x] || check[y][x])
                        continue;

                    check[y][x] = true;
        

                    
                    queue.add(new Move(y,x,cur.count+1));

                    

                }
            }
            //불 이동
            for(int j=0,size=fireList.size(); j<size; j++){
                Location loc = fireList.poll();
                for(int k=0; k<4; k++){
                    int fire_y = loc.y + dy[k];
                    int fire_x = loc.x + dx[k];

                    if(fire_y < 0 || fire_y >= H || fire_x < 0 || fire_x >= W || map[fire_y][fire_x].equals("#") || fireMap[fire_y][fire_x])
                        continue;

                    fireList.add(new Location(fire_y,fire_x));
                    fireMap[fire_y][fire_x] = true;
                    
                }
            }
            


        }
        System.out.println("IMPOSSIBLE");

    }
   
   
   
    static boolean checkFire(int H, int W, int y, int x){
        for(int i=0; i<4; i++){
            int around_y = y + dy[i];
            int around_x = x + dx[i];
            
            if(around_y < 0 || around_y >= H || around_x < 0 || around_x >= W)
                continue;

            if(fireMap[around_y][around_x])
                return true;

        }
        return false;
    }

}

