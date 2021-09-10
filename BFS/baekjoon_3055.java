import java.util.*;

class Location{
    int y;
    int x;
    int t;
    char map[][];
    ArrayList<Water> waterList;
    Location(int y,int x,int t,char map[][],ArrayList<Water> waterList){
        this.y = y;
        this.x = x;
        this.t = t;
        this.map = map;
        this.waterList = waterList;
    }
}

class Water{
    int y;
    int x;

    Water(int y, int x){
        this.y = y;
        this.x = x;
    }
}
class baekjoon_3055{
    
    static int dy[] = {-1,1,0,0};
    static int dx[] = {0,0,-1,1};

    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);  

        String input[] = sc.nextLine().split(" ");
        int R = Integer.parseInt(input[0]);
        int C = Integer.parseInt(input[1]);

        int hedgehog_y = 0, hedgehog_x = 0;
        int cave_y = 0, cave_x = 0;
        char map[][] = new char[R][C];

        ArrayList<Water> waterList = new ArrayList<>();
        for(int i=0; i<R; i++){
            String line = sc.nextLine();
            for(int j=0; j<C; j++){
                map[i][j] = line.charAt(j);
                if(map[i][j] == 'S'){
                    hedgehog_y = i;
                    hedgehog_x = j;
                }else if(map[i][j] == 'D'){
                    cave_y = i;
                    cave_x = j;
                }else if(map[i][j] == '*'){
                    waterList.add(new Water(i,j));
                }
            }
        }
        solve(hedgehog_y,hedgehog_x,cave_y,cave_x,R,C,map,waterList);
    }
    static void solve(int hedgehog_y, int hedgehog_x, int cave_y, int cave_x, int R, int C, char map[][],ArrayList<Water> waterList){
        boolean check[][] = new boolean[R][C];
        Queue<Location> queue = new LinkedList<>();
        queue.add(new Location(hedgehog_y,hedgehog_x,0,map,waterList));
        check[hedgehog_y][hedgehog_x] = true;


        while(!queue.isEmpty()){
            Location cur = queue.poll();

        

            //고슴도치 이동
            for(int i=0; i<4; i++){
                int y = cur.y + dy[i];
                int x = cur.x + dx[i];

                if(y == cave_y && x == cave_x){
                    System.out.println(cur.t + 1);
                    return;
                }

         
                char tempMap[][] = coppyMap(R, C, cur.map);
                ArrayList<Water> tempWaterList = coppyWaterList(cur.waterList);
                
                if(isHedgehogPossible(y,x,R,C,tempMap) && !check[y][x]){

                    tempMap[cur.y][cur.x] = '.';
                    tempMap[y][x] = 'S';
                    check[y][x] = true;
                    queue.add(new Location(y,x,cur.t + 1,tempMap,tempWaterList));

                    //물 이동
                    for(int j=0,size=tempWaterList.size(); j<size; j++){
                        Water water = tempWaterList.get(j);

                        for(int k=0; k<4; k++){
                            int water_y = water.y + dy[k];
                            int water_x = water.x + dx[k];

                            if(isWaterPossible(water_y,water_x,R,C,tempMap)){
                                
                                tempMap[water_y][water_x] = '*';
                                tempWaterList.add(new Water(water_y,water_x));
                            }

                        }
                    }

                }
            }
        }
        System.out.println("KAKTUS");
    }

    static ArrayList<Water> coppyWaterList(ArrayList<Water> waterList){
        ArrayList<Water> temp = new ArrayList<>();
        for(int i=0; i<waterList.size(); i++){
            temp.add(waterList.get(i));
        }
        return temp;
    }
    static char[][] coppyMap(int R, int C, char map[][]){
        
        char tempMap[][] = new char[R][C];
        for(int i=0; i<R; i++)
            tempMap[i] = map[i].clone();
        return tempMap;
    }
    static boolean checkOutOfArrange(int y, int x, int R, int C){
        if(y < 0 || x < 0 || y >= R || x >= C)
            return true;
        return false;
    }
    static boolean isWaterPossible(int y, int x, int R,int C, char map[][]){
        if(!checkOutOfArrange(y,x,R,C) && (map[y][x] == '.' || map[y][x] == 'S')){
            return true;
        }
        return false;
    }
    static boolean isHedgehogPossible(int y, int x, int R, int C, char map[][]){
        if(!checkOutOfArrange(y,x,R,C) && map[y][x] == '.'){
            for(int i=0; i<4; i++){
                int around_y = y + dy[i];
                int around_x = x + dx[i];

                if(checkOutOfArrange(around_y,around_x,R,C))
                    continue;
                if(map[around_y][around_x] == '*'){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}

