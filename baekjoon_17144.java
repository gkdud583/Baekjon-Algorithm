import java.util.*;

class Location{
    int y;
    int x;
    Location(int y,int x){
        this.y = y;
        this.x = x;
    }

}
class baekjoon_17144{
    static ArrayList<Location> FINE_DUST_LOCATIONS = new ArrayList<>();

    static void findFineDustLocation(int map[][]){
        FINE_DUST_LOCATIONS.clear();
        int r = map.length;
        int c = map[0].length;

        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                if(map[i][j] > 0){
                    FINE_DUST_LOCATIONS.add(new Location(i,j));
                }
            }
        }
    }
    static int getFineDustSum(int map[][]){
        int sum = 0;
        for(int i=0; i<map.length; i++){
            for(int j=0; j<map[0].length; j++)
            {
                if(map[i][j] != -1)
                    sum += map[i][j];
            }
               
        }
        return sum;
    }
    static void actuateCleaner(Location cleanerLocation[],int map[][]){
       
        int r = map.length;
        int c = map[0].length;
        ArrayList<Integer> topCleaner = new ArrayList<>();
        ArrayList<Integer> bottomCleaner = new ArrayList<>();


        //클리너 윗 부분 
        for(int i=cleanerLocation[0].x+1; i<c; i++){
            topCleaner.add(map[cleanerLocation[0].y][i]);
        }

        for(int i=cleanerLocation[0].y-1; i>=0; i--){
            topCleaner.add(map[i][c-1]);
        }

        for(int i=c-2; i>=cleanerLocation[0].x+1; i--){
            topCleaner.add(map[0][i]);
        }

        for(int i=0; i<=cleanerLocation[0].y-1; i++){
            topCleaner.add(map[i][0]);
        }

        //클리너 아랫부분
        for(int i=cleanerLocation[1].x+1; i<c; i++){
            bottomCleaner.add(map[cleanerLocation[1].y][i]);
        }

        for(int i=cleanerLocation[1].y+1; i<r; i++){
            bottomCleaner.add(map[i][c-1]);
        }

        for(int i=c-2; i>=cleanerLocation[1].x; i--){
            bottomCleaner.add(map[r-1][i]);

        }
        for(int i=r-2; i>=cleanerLocation[1].y+1; i--){
            bottomCleaner.add(map[i][cleanerLocation[1].x]);
        }

        topCleaner.add(0,0);
        topCleaner.remove(topCleaner.size()-1);

        bottomCleaner.add(0,0);
        bottomCleaner.remove(bottomCleaner.size()-1);

         //원복
         //클리너 윗 부분 
         int index = 0;
         for(int i=cleanerLocation[0].x+1; i<c; i++){
            map[cleanerLocation[0].y][i] = topCleaner.get(index++);
        }

        for(int i=cleanerLocation[0].y-1; i>=0; i--){
            map[i][c-1] = topCleaner.get(index++);
        }

        for(int i=c-2; i>=cleanerLocation[0].x; i--){
            map[0][i] = topCleaner.get(index++);
        }

        for(int i=1; i<=cleanerLocation[0].y-1; i++){
            map[i][0] = topCleaner.get(index++);
        }

        //클리너 아랫부분
        index = 0;
        for(int i=cleanerLocation[1].x+1; i<c; i++){
            map[cleanerLocation[1].y][i] = bottomCleaner.get(index++);
        }

        for(int i=cleanerLocation[1].y+1; i<r; i++){
            map[i][c-1] = bottomCleaner.get(index++);
        }

        for(int i=c-2; i>=cleanerLocation[1].x; i--){
            map[r-1][i] = bottomCleaner.get(index++);

        }
        for(int i=r-2; i>=cleanerLocation[1].y+1; i--){
            map[i][cleanerLocation[1].x] = bottomCleaner.get(index++);
        }






    }
    
    static void diffuseFineDust(int map[][],Location cleanerLocation[]){
        int r = map.length;
        int c = map[0].length;

        int dy[] = {-1,1,0,0};
        int dx[] = {0,0,-1,1};

        int tempMap[][] = new int[r][c];

        for(int i=0; i<FINE_DUST_LOCATIONS.size(); i++){
            int d = 0;
            Location cur = FINE_DUST_LOCATIONS.get(i);
            for(int j=0; j<4; j++){
                int y = cur.y + dy[j];
                int x = cur.x + dx[j];
                if(y < 0 || x < 0 || y >= r || x >= c || map[y][x] == -1)
                    continue;
                int diffuseMount = map[cur.y][cur.x] / 5;
                tempMap[y][x] += diffuseMount;
                d++;
            }
            tempMap[cur.y][cur.x] += (map[cur.y][cur.x] - map[cur.y][cur.x] / 5 * d);
        }

        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                if(i == cleanerLocation[0].y && j == cleanerLocation[0].x){
                    continue;
                }else if(i == cleanerLocation[1].y && j == cleanerLocation[1].x)
                    continue;
                map[i][j] = tempMap[i][j];
            }
        }
    }
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        
        int r = sc.nextInt();
        int c = sc.nextInt();
        int t = sc.nextInt();
        
        int map[][] = new int[r][c];

        //0은 위,1은 아래
        Location cleanerLocation[] = new Location[2];
        int cleanerIndex = 0;

        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                map[i][j] = sc.nextInt();
                if(map[i][j] == -1){
                    cleanerLocation[cleanerIndex++] = new Location(i,j);
                }
            }
        }
        for(int i=0; i<t; i++){
            findFineDustLocation(map);
            diffuseFineDust(map,cleanerLocation);
            actuateCleaner(cleanerLocation,map);
        }
        System.out.println(getFineDustSum(map));
        
       
    }
}



