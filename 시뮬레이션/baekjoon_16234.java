import java.util.*;

class Location{
    int y;
    int x;
    Location(int y,int x){
        this.y = y;
        this.x = x;
    }

}
class baekjoon_16234{
    static int bfs(ArrayList<Location> unionLocations,int y,int x,int l, int r,boolean check[][],int map[][]){
        //0은 위,1은 아래, 2는 왼쪽, 3은 오른쪽
        int dy[] = {-1,1,0,0};
        int dx[] = {0,0,-1,1};

        int n = map.length;

        Queue<Location> queue = new LinkedList<>();
        
        queue.add(new Location(y,x));
        unionLocations.add(new Location(y,x));
        int sum = map[y][x];
        while(!queue.isEmpty()){
            Location cur = queue.poll();
            for(int i=0; i<4; i++){
                int move_y = cur.y + dy[i];
                int move_x = cur.x + dx[i];
                if(move_y < 0 || move_x < 0 || move_y >= n || move_x >= n || check[move_y][move_x])
                    continue;
                int d = Math.abs(map[cur.y][cur.x] - map[move_y][move_x]);
                if(d >= l && d <= r){
                    check[move_y][move_x] = true;
                    queue.add(new Location(move_y,move_x));
                    unionLocations.add(new Location(move_y,move_x));
                    sum += map[move_y][move_x];
    
                }
            }
        }
        return sum / unionLocations.size();
    }
    static int getMovementDay(int n, int l, int r,int map[][]){
        int day = 0;
        int avg = 0;
        ArrayList<Location> unionLocations = new ArrayList<>();
        int tempMap[][] = new int[n][n];
        for(int i=0; i<n; i++){
            tempMap[i] = (int[])map[i].clone();
        }

        for(;;day++){
            boolean check[][] = new boolean[n][n];
            boolean isUnion = false;
            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    unionLocations.clear();
                    if(!check[i][j]){
                        check[i][j] = true;
                        avg = bfs(unionLocations,i,j,l,r,check,map);
                        if(unionLocations.size() <= 1)
                            continue;
                        isUnion = true;
                        for(int k=0; k<unionLocations.size(); k++){
                            Location cur = unionLocations.get(k);
                            tempMap[cur.y][cur.x] = avg;
                        }
                    }

                }
            }
            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++)
                    map[i][j] = tempMap[i][j];
            }
            if(!isUnion)
                break;
        }
        return day;

    }
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int l = sc.nextInt();
        int r = sc.nextInt();

        int map[][] = new int[n][n];
        
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                map[i][j] = sc.nextInt();
            }
        }

        
        System.out.println(getMovementDay(n,l,r,map));

        
       
    }
}




