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
    static final int SIZE = 5;
    static int count = 0;
    static String map[][];
    public static void main(String[]args) throws IOException{
    
        Scanner sc = new Scanner(System.in);
        map = new String[5][5];

        for(int i=0; i<5; i++){
            String input[] = sc.nextLine().split("");
            for(int j=0; j<5; j++){
                map[i][j] = input[j];
            }
        }

        perm(0, 0, -1, 7, 0, new HashMap<String,Boolean>());
        System.out.println(count);

    }
    static void perm(int i, int sy, int sx, int size, int yCount, HashMap<String,Boolean> p){
        
        if(i >= size){
        
            if(bfs(p))
                count++;
            return;

        }
        for(int j=sy; j<SIZE; j++){
            for(int k=0; k<SIZE; k++){
                if(j == sy && k <= sx)
                    continue;
                if(map[j][k].equals("Y") && yCount+1 > 3)
                    continue;
                StringBuffer sb = new StringBuffer();
                sb.append(j).append(k);
                p.put(sb.toString(), false);
                perm(i+1, j, k, size, map[j][k].equals("Y") ? yCount+1 : yCount, p);
                p.remove(sb.toString());
            }
        }
        
    }
  
    static boolean bfs(HashMap<String,Boolean> p){
        Queue<Location> queue = new LinkedList<>();
        Iterator<Map.Entry<String, Boolean>> iter = p.entrySet().iterator();
        String startLoc[] = iter.next().getKey().split("");
        int sy = Integer.parseInt(startLoc[0]);
        int sx = Integer.parseInt(startLoc[1]);
        queue.add(new Location(sy,sx));

        boolean check[][] = new boolean[SIZE][SIZE];
        check[sy][sx] = true;
        
        int cnt = 1;

        int dy[] = {-1,1,0,0};
        int dx[] = {0,0,-1,1};

        while(!queue.isEmpty()){
            Location cur = queue.poll();

    
            for(int i=0; i<4; i++){
                int y = cur.y + dy[i];
                int x = cur.x + dx[i];

                if(y < 0 || x < 0 || y >= SIZE || x >= SIZE || check[y][x] || !p.containsKey(String.valueOf(y)+x))
                    continue;
                check[y][x] = true;
                queue.add(new Location(y,x));
                cnt++;
                
            }
        }
        return cnt == 7 ? true : false;
    }
}

