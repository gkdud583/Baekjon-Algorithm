import java.util.*;


class Location{
    int y;
    int x;
    Location(int y,int x){
        this.y = y;
        this.x = x;
    }
}
class baekjoon_2636{
    static int CHEESE_COUNT = 0;
    static int MELT_CHEESE_COUNT[] = new int[10001];
    static Queue<Location> queue = new LinkedList<>();

    static void meltCheese(int [][]map){
        int dy[] = {1,-1,0,0};
        int dx[] = {0,0,-1,1};
        int r = map.length;
        int c = map[0].length;

        int tempMap[][] = new int[r][c];
        for(int i=0; i<r; i++)
            tempMap[i] = (int[])map[i].clone();
        boolean check[][] = new boolean[r][c];

        queue.add(new Location(0,0));

        int meltCount = 0;
        while(!queue.isEmpty()){
            Location cur = queue.poll();
            for(int i=0; i<4; i++){
                int y = cur.y + dy[i];
                int x = cur.x + dx[i];
                if(y < 0 || x < 0 || y >= r || x >= c)
                    continue;
                if(!check[y][x]){
                    check[y][x] = true;
                    if(map[y][x] == 1){
                        //치즈가 있음.
                        tempMap[y][x] = 0;
                        meltCount++;
                        
                    }else{
                        queue.add(new Location(y,x));
                    }
                }
                
            }
        }
        for(int i=0; i<r; i++){
            map[i] = (int[])tempMap[i].clone();
        }
        CHEESE_COUNT -= meltCount;
    }
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        int r = sc.nextInt();
        int c = sc.nextInt();


        int map[][] = new int[r][c];
        
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                map[i][j] = sc.nextInt();
                if(map[i][j] == 1){
                    CHEESE_COUNT++;
                }
            }
        }
        int h = 0;
        MELT_CHEESE_COUNT[h] = CHEESE_COUNT;

        while(CHEESE_COUNT > 0){
            meltCheese(map);
            MELT_CHEESE_COUNT[++h] = CHEESE_COUNT;
        }
        System.out.println(h);
        System.out.println(MELT_CHEESE_COUNT[h-1]);

    }
        
}



