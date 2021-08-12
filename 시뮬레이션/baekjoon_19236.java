import java.util.*;


class Shark{
    int x;
    int y;
    int d;

    Shark(int x, int y, int d){
        this.x = x;
        this.y = y;
        this.d = d;
    }
}
class Fish{
    int x; //행
    int y; //열

    int i; //번호
    int d; //방향

    Fish(int x, int y, int i, int d){
        this.x = x;
        this.y = y;
        this.i = i;
        this.d = d;
    }
}

class baekjoon_19236{
    static int MAX = 0;


    static void copyMap(Fish mapCopy[][], Fish map[][]){
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                Fish f = map[i][j];
                if(f == null)
                    mapCopy[i][j] = null;
                else{
                    mapCopy[i][j] = new Fish(f.x,f.y,f.i,f.d);
                }
            }
        }
    }
    static void copyFishLocation(Fish fishLocationCopy[],Fish fishLocation[]){

        for(int i=1; i<=16; i++){
            Fish f = fishLocation[i];
            if(f == null){
                fishLocationCopy[i] = null;
            }else
                fishLocationCopy[i] = new Fish(f.x,f.y,f.i,f.d);

        }

    }
 
    static void dfs(int sum, Shark shark, Fish fishLocation[], Fish map[][]){
        int dx[] = {0,-1,-1,0,1,1,1,0,-1};
        int dy[] = {0,0,-1,-1,-1,0,1,1,1};

        int x = shark.x;
        int y = shark.y;

        int cmp = sum;
        moveFish(shark, fishLocation, map);

        while(true){

            cmp = sum;
            x += dx[shark.d];
            y += dy[shark.d];

            Fish fishLocationCopy[] = new Fish[17];
            Fish mapCopy[][] = new Fish[4][4];

            copyFishLocation(fishLocationCopy,fishLocation);
            copyMap(mapCopy,map);
            


            if(x >= 0 && y >= 0 && x < 4 && y < 4){
                if(mapCopy[x][y] != null){
                    Fish eating = mapCopy[x][y];
                    fishLocationCopy[eating.i] = null;
                    mapCopy[x][y] = null;

                    cmp += eating.i;
                    

                    dfs(cmp, new Shark(x,y,eating.d), fishLocationCopy, mapCopy);
                }
            }else
                break;
        }
        MAX = Math.max(MAX, cmp);
    }
   
    static void moveFish(Shark shark, Fish fishLocation[], Fish map[][] ){
        int dx[] = {0,-1,-1,0,1,1,1,0,-1};
        int dy[] = {0,0,-1,-1,-1,0,1,1,1};
        for(int i=1; i<=16; i++){
            Fish cur = fishLocation[i];

            if(cur == null)
                continue;

            //물고기 방향 정함
            int d = cur.d;

            do{
                int x = cur.x + dx[d];
                int y = cur.y + dy[d];
                if(x >= 0 && y >= 0 && x < 4 && y < 4){
                    if(map[x][y] != null || (map[x][y] == null && !(shark.x == x && shark.y == y))){
                        
                        if(map[x][y] != null){
                            Fish b = map[x][y];
                            b.x = cur.x;
                            b.y = cur.y;
                            fishLocation[b.i] = b;
                            map[b.x][b.y]  = b;
                        }else{
                            map[cur.x][cur.y] = null;
                        }
                        cur.d = d;
                        cur.x = x;
                        cur.y = y;

                        map[x][y] = cur;
                        fishLocation[cur.i] = cur;
                      
                        break;
                    }
                }

                d = d + 1 > 8 ? 1 : d + 1;
            }while(d != cur.d);
        }
    }
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        
        Fish fishLocation[] = new Fish[17];
        Fish map[][] = new Fish[4][4];
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){

                int index = sc.nextInt(); //물고기번호
                int d = sc.nextInt(); //방향
                map[i][j] = new Fish(i,j,index,d);
                fishLocation[index] = new Fish(i,j,index,d);
            }
        }

      

        Fish eating = map[0][0];
        map[0][0] = null;
        fishLocation[eating.i] = null;
        Shark shark = new Shark(0,0,eating.d);

        dfs(eating.i, shark, fishLocation, map);
        System.out.println(MAX);

    }
    

}


