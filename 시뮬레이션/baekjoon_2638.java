import java.util.*;


class Location{
    int y;
    int x;
    Location(int y,int x){
        this.y = y;
        this.x = x;

    }
}
class baekjoon_2638{
    static Queue<Location> CHEESE_LOCATION = new LinkedList<>();
    static int checkCheese(boolean check[][],int map[][]){
        int r = check.length;
        int c = check[0].length;
        int dy[] = {-1,1,0,0};
        int dx[] = {0,0,-1,1};
        int meltedCheese = 0;

        for(int i=0, size = CHEESE_LOCATION.size(); i<size; i++){
            Location cur = CHEESE_LOCATION.poll();
            int count = 0;
            for(int j=0; j<4; j++){
                int y = cur.y + dy[j];
                int x = cur.x + dx[j];

                if(y < 0 || x < 0 || y >= r || x >= c)  
                    continue;

                if(check[y][x])
                    count++;
                
            }
            if(count >= 2){
                //2면 이상이 공기와 닿아있으면 녹음.
                map[cur.y][cur.x] = 0;
                meltedCheese++;

            }
            if(count < 2){
                CHEESE_LOCATION.add(cur);
            }
        }
        return meltedCheese;
    }
    static void visitEmpty(int map[][],boolean check[][]){
        int dy[] = {-1,1,0,0};
        int dx[] = {0,0,-1,1};
        int r = map.length;
        int c = map[0].length;
        Queue<Location> queue = new LinkedList<>();
        queue.add(new Location(0,0));

        while(!queue.isEmpty()){
            Location loc = queue.poll();
            
            for(int i=0; i<4; i++){
                int y = loc.y + dy[i];
                int x = loc.x + dx[i];

                if(y < 0 || x < 0 || y >= r || x >= c || check[y][x] || map[y][x] == 1){
                    continue;
                }
                check[y][x] = true;
                queue.add(new Location(y,x));
            }
        }
    }
    static int meltCheese(int map[][]){
        int r = map.length;
        int c = map[0].length;
        int h = 0;
        
        while(true){
            boolean check[][] = new boolean[r][c];
            check[0][0] = true;
            visitEmpty(map,check);
            int meltedCheese = checkCheese(check,map);
            
            if(meltedCheese == 0)
                break;
            h++;
        }   
        return h;
    }
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        int m = sc.nextInt();

        int map[][] = new int[n][m];

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                map[i][j] = sc.nextInt();
                if(map[i][j] == 1){
                    CHEESE_LOCATION.add(new Location(i,j));
                }
            }
        }

        System.out.println(meltCheese(map));
    }
        
}
