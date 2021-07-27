import java.util.*;



class Location{
    int y;
    int x;
    Location(int y,int x){
        this.y = y;
        this.x = x;
    }
}
class baekjoon_11559{
    static final int R = 12;
    static final int C = 6; 
    static ArrayList<Location> POPED_PUYO_LIST = new ArrayList<>();
    static void dropPuyo(int map[][]){
        Collections.sort(POPED_PUYO_LIST,new Comparator<Location>(){
            @Override
            public int compare(Location o1, Location o2) {
                return o1.y - o2.y;
            }
        });
        for(int i=0; i<POPED_PUYO_LIST.size(); i++){
           Location cur = POPED_PUYO_LIST.get(i);
           for(int j=cur.y-1; j>=0; j--){
               map[j+1][cur.x] = map[j][cur.x];
               map[j][cur.x] = 0;
           }
       }
    }
    static void burstPuyo(int map[][]){
        ArrayList<Location> list = new ArrayList<>();
        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++)
            {
                if(map[i][j] != 0)
                    list.add(new Location(i,j));
            }
        }
        int dy[] = {-1,1,0,0};
        int dx[] = {0,0,-1,1};

        for(int i=0; i<list.size(); i++){
            Location cur = list.get(i);
            if(map[cur.y][cur.x] != 0){
                ArrayList<Location> popedLocation = new ArrayList<>();
                popedLocation.add(new Location(cur.y,cur.x));

                Queue<Location> queue = new LinkedList<>();
                queue.add(new Location(cur.y,cur.x));

                boolean check[][] = new boolean[R][C];

                check[cur.y][cur.x] = true;
                while(!queue.isEmpty())
                {   
                    Location around = queue.poll();
                    for(int j=0; j<4; j++){
                        int y = around.y + dy[j];
                        int x = around.x + dx[j];
    
                        if(y < 0 || x < 0 || y >= R || x >= C || map[y][x] != map[cur.y][cur.x] || check[y][x]){
                            continue;
                        }
                        check[y][x] = true;
                        queue.add(new Location(y,x));
                        popedLocation.add(new Location(y,x));
                    }
                }
                if(popedLocation.size() >= 4){
                    for(int j=0; j<popedLocation.size(); j++){
                        POPED_PUYO_LIST.add(popedLocation.get(j));
                        map[popedLocation.get(j).y][popedLocation.get(j).x] = 0;
                    }
                }
            }
        }
    }
    static int getChainCount(int map[][]){
        int chainCount = 0;
        while(true){
            POPED_PUYO_LIST.clear();            
            burstPuyo(map);
            if(POPED_PUYO_LIST.size() == 0)
                break;
           
            dropPuyo(map);
            
            chainCount++;
        }
        return chainCount;
    }
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        
        int map[][] =  new int[R][C];
        for(int i=0; i<R; i++){
            String tok[] = sc.nextLine().split("");

            for(int j=0; j<C; j++){
                if(tok[j].equals("."))
                    map[i][j] = 0;
                else if(tok[j].equals("R"))
                    map[i][j] = 1;
                else if(tok[j].equals("G"))
                    map[i][j] = 2;
                else if(tok[j].equals("B"))
                    map[i][j] = 3;
                else if(tok[j].equals("P"))
                    map[i][j] = 4;
                else if(tok[j].equals("Y"))
                    map[i][j] = 5;
            }
        }

        System.out.println(getChainCount(map));
    }
        
}
