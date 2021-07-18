import java.util.*;



class Location{
    int y;
    int x;
    int s; //이동 초 
    
    Location(int y,int x,int s){
        this.y = y;
        this.x = x;
        this.s = s;
    }
}
class comparator implements Comparator<Location>{
    @Override
    public int compare(Location a,Location b){
        if(a.s > b.s)
            return 1;
        else if(a.s < b.s)
            return -1;
        else if(a.s == b.s){
            if(a.y > b.y)
                return 1;
            else if(a.y < b.y)
                return -1;
            else if(a.y == b.y){
                if(a.x > b.x)
                    return 1;
                else if(a.x < b.x)
                    return -1;
                else
                    return 0;

            }

        }
        return 0;
        
    }
}
class baekjoon_16236{
    static int dy[] = {-1,0,0,1};
    static int dx[] = {0,-1,1,0};
    static int MAX = 987654321;
    static int shark_y;
    static int shark_x;
    static int shark_size = 2;
    static int answer = 0;
    static boolean checkOutOfIndex(int y,int x,int n){
        if(y < 0 || x < 0 || y >= n || x >= n)
            return true;
        return false;
    }
    static Location bfs(int y,int x,int map[][]){
        PriorityQueue<Location> pq = new PriorityQueue<>(new comparator());
        int n = map.length;
        pq.add(new Location(y,x,1));
        boolean check[][] = new boolean[n][n];
        check[y][x] = true;
        while(!pq.isEmpty()){
            Location cur = pq.poll();
            if(map[cur.y][cur.x] != 0 && map[cur.y][cur.x] < shark_size){
                return cur;
            }
            
            for(int i=0; i<4; i++){
                int move_y = cur.y + dy[i];
                int move_x = cur.x + dx[i];
               
                if(checkOutOfIndex(move_y,move_x,n))
                    continue;
                if(check[move_y][move_x] || map[move_y][move_x] > shark_size)
                    continue;
               
                check[move_y][move_x] = true;
                pq.add(new Location(move_y,move_x,cur.s+1));
            
            }
        }
        return new Location(0,0,MAX);
    }
    
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int map[][] = new int[n][n];
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                map[i][j] = sc.nextInt();
                if(map[i][j] == 9)
                {
                    //아기 상어의 위치
                    shark_y = i;
                    shark_x = j;
                    map[i][j] = 0;
                }
            }
        }

        ArrayList<Location> locationList = new ArrayList<>();
        int count = 0;
        while(true){
            locationList.clear();
           
            for(int i=0; i<4; i++){
                int y = shark_y + dy[i];
                int x = shark_x + dx[i];
                if(checkOutOfIndex(y,x,n)){
                    continue;
                }
                if(map[y][x] > shark_size)
                    continue;
                Location loc  = bfs(y,x,map);
                if(loc.s >= MAX)
                    continue;
                locationList.add(loc);
            }
            Collections.sort(locationList,new comparator());
            if(locationList.size() == 0){
                break;
            }
            Location moveLoc = locationList.get(0);
            count++;
            if(shark_size == count){
                shark_size++;
                count = 0;
            }
            shark_y = moveLoc.y;
            shark_x = moveLoc.x;
            answer += moveLoc.s;
            map[shark_y][shark_x] = 0;

        }
        System.out.println(answer);
       

    }
}

