import java.util.*;

class Location{
    int y;
    int x;
    int type;
    Location(int y,int x,int type){
        this.y = y;
        this.x = x;
        this.type = type;
    }

}
class baekjoon_15683{
    static int CMP = 0;
    static int BlindSPOT = 0;
    static int MIN = 0;
    static ArrayList<Location> CCTV_LOCATIONS = new ArrayList<>();
    static HashMap<Integer,ArrayList<String>> CCTV_DIRECTIONS = new HashMap<>();
    
    static int[][] checkSpace(int y,int x,int map[][],String direction){
        //0은 위, 1은 아래, 2는 왼쪽,3은 오른쪽
        int r = map.length;
        int c = map[0].length;
        int tempMap[][] = new int[r][c];
        for(int i=0; i<r; i++)
            tempMap[i] = (int[]) map[i].clone();
        String d[] = direction.split("");
        for(int i=0; i<d.length; i++){
            try{
                int parseDirection = Integer.parseInt(d[i]);
                if(parseDirection == 0){
                    //위 가리킴
                    for(int j=y-1; j>=0; j--){
                        if(map[j][x] == 6){
                            //벽에 부딪힘
                            break;
                        }
                        else if(map[j][x] == 0){
                            //빈방이면 가능
                            CMP--;
                            tempMap[j][x] = 9;
    
                        }
    
                    }
    
                }else if(parseDirection == 1){
                    //아래 가리킴
                    for(int j=y+1; j<r; j++){
                        if(map[j][x] == 6){
                            //벽에 부딪힘
                            break;
                        }
                        else if(map[j][x] == 0){
                            //빈방이면 가능
                            CMP--;
                            tempMap[j][x] = 9;
    
                        }
    
                    }
    
                }else if(parseDirection == 2){
                    //왼쪽 가리킴
                    for(int j=x-1; j>=0; j--){
                        if(map[y][j] == 6){
                            //벽에 부딪힘
                            break;
                        }
                        else if(map[y][j] == 0){
                            //빈방이면 가능
                            CMP--;
                            tempMap[y][j] = 9;
    
                        }
    
                    }
    
                }else if(parseDirection == 3){
                    //오른쪽 가리킴
                    for(int j=x+1; j<c; j++){
                        if(map[y][j] == 6){
                            //벽에 부딪힘
                            break;
                        }
                        else if(map[y][j] == 0){
                            //빈방이면 가능
                            CMP--;
                            tempMap[y][j] = 9;
    
                        }
    
                    }
    
                }
            }catch(NumberFormatException e){

            }
            
    
        }
        return tempMap;
    }
    
    static void dfs(int i,int remain,int map[][]){
        if(i >= CCTV_LOCATIONS.size()){
            MIN = Math.min(MIN,remain);
            return;
        }
        Location cur = CCTV_LOCATIONS.get(i);
        for(int j=0; j<CCTV_DIRECTIONS.get(cur.type).size(); j++){
            CMP = remain;
            int [][] tempMap = checkSpace(cur.y,cur.x,map,CCTV_DIRECTIONS.get(cur.type).get(j));
            dfs(i+1,CMP,tempMap);

        }
    }
    public static void main(String[]args){

        Scanner sc = new Scanner(System.in);
        int r = sc.nextInt();
        int c = sc.nextInt();

        int map[][] = new int[r][c];
        
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                map[i][j] = sc.nextInt();
                if(map[i][j] == 0)
                    BlindSPOT++;
                else if(map[i][j] >= 1 && map[i][j] <= 5){
                    CCTV_LOCATIONS.add(new Location(i,j,map[i][j]));
                }
            }
        }
        CCTV_DIRECTIONS.put(1, new ArrayList<>(List.of("0", "1", "2","3")));
        CCTV_DIRECTIONS.put(2, new ArrayList<>(List.of("01", "23")));
        CCTV_DIRECTIONS.put(3, new ArrayList<>(List.of("03", "13", "21","20")));
        CCTV_DIRECTIONS.put(4, new ArrayList<>(List.of("203", "031", "213","021")));
        CCTV_DIRECTIONS.put(5, new ArrayList<>(List.of("0123")));
        
        MIN = BlindSPOT;
        CMP = BlindSPOT;

        dfs(0,BlindSPOT,map);
        System.out.println(MIN);


    }
}