import java.util.*;

class Location{
    int y;
    int x;
    int r;

    Location(int y, int x,int r){
        this.y = y;
        this.x = x;
        this.r = r;
    }
}
class baekjoon_20057{
    
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);  
        
        int N = sc.nextInt();

        int map[][] = new int[N][N];


        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++)
                map[i][j] = sc.nextInt();
        }
        ArrayList<Location> direction[] = new ArrayList[4];
        for(int i=0; i<4; i++)
            direction[i] = new ArrayList<>();

        //왼쪽
        direction[0].add(new Location(0,-1,-1));
        direction[0].add(new Location(-1,-1,10));
        direction[0].add(new Location(-1,0,7));
        direction[0].add(new Location(-1,1,1));
        direction[0].add(new Location(0,-2,5));
        direction[0].add(new Location(1,0,7));
        direction[0].add(new Location(2,0,2));
        direction[0].add(new Location(1,-1,10));
        direction[0].add(new Location(1,1,1));
        direction[0].add(new Location(-2,0,2));

        //아래
        direction[1].add(new Location(1,0,-1));
        direction[1].add(new Location(-1,-1,1));
        direction[1].add(new Location(-1,1,1));
        direction[1].add(new Location(0,-1,7));
        direction[1].add(new Location(0,-2,2));
        direction[1].add(new Location(0,1,7));
        direction[1].add(new Location(0,2,2));
        direction[1].add(new Location(1,-1,10));
        direction[1].add(new Location(1,1,10));
        direction[1].add(new Location(2,0,5));

        //오른쪽
        direction[2].add(new Location(0,1,-1));
        direction[2].add(new Location(-1,-1,1));
        direction[2].add(new Location(-1,0,7));
        direction[2].add(new Location(-1,1,10));
        direction[2].add(new Location(-2,0,2));
        direction[2].add(new Location(0,2,5));
        direction[2].add(new Location(1,0,7));
        direction[2].add(new Location(1,1,10));
        direction[2].add(new Location(1,-1,1));
        direction[2].add(new Location(2,0,2));

        //위
        direction[3].add(new Location(-1,0,-1));
        direction[3].add(new Location(-2,0,5));
        direction[3].add(new Location(-1,-1,10));
        direction[3].add(new Location(-1,1,10));
        direction[3].add(new Location(0,-2,2));
        direction[3].add(new Location(0,-1,7));
        direction[3].add(new Location(0,1,7));
        direction[3].add(new Location(0,2,2));
        direction[3].add(new Location(1,-1,1));
        direction[3].add(new Location(1,1,1));





    
        System.out.println(getSandAmount(N,direction,map));
        
    }

    static int getSandAmount(int N,ArrayList<Location> direction[],int map[][]){
        int sandAmount = 0;
        //토네이도

        int y = N / 2;
        int x = N / 2;
        int l_b_size = 1;
        int r_t_size = 2;
        for(int i=0; i<N/2; i++){
            
            
            //왼쪽
            for(int l=0; l<l_b_size; l++){
               
                x = x - 1;
                sandAmount += moveSand(y,x,0,direction,map);
            }
            //아래
            for(int b=0; b<l_b_size; b++){
                y = y + 1;
                sandAmount += moveSand(y,x,1,direction,map);

            }
            //오른쪽
            for(int r=0; r<r_t_size; r++){
                x = x + 1;
                sandAmount += moveSand(y,x,2,direction,map);

            }
            //위
            for(int t=0; t<r_t_size; t++){
                y = y - 1;
                sandAmount += moveSand(y,x,3,direction,map);

            }
        
            l_b_size += 2;
            r_t_size += 2;

        }

        //왼쪽 이동
        for(int i=0; i<N-1; i++){
            x = x - 1;
            sandAmount += moveSand(y,x,0,direction,map);

        }
        return sandAmount;
    }
    static int moveSand(int y,int x, int d,ArrayList<Location> direction[],int map[][]){
        //y,x - > 현재 y의 위치
        int ret = 0;
        int N = map.length;
        ArrayList<Location> list = direction[d];
        
        Location a_loc = list.get(0);

        int remain = map[y][x];
        for(int i=1; i<list.size(); i++){
            Location cur = list.get(i);
            int move_y = y + cur.y;
            int move_x = x + cur.x;
            int amount = (int)(map[y][x] * (cur.r / 100.0));

           
            remain -= amount;

            if(move_y < 0 || move_x < 0 || move_y >= N || move_x >= N){
                ret += amount;
  
            }else
                map[move_y][move_x] += amount;
        }
        if(y + a_loc.y < 0 || y + a_loc.y >= N || x + a_loc.x < 0 || x + a_loc.x >= N )
            ret += remain;
        else
            map[y + a_loc.y][x + a_loc.x] += remain;

        map[y][x] = 0;
        return ret;
    }
    
}

