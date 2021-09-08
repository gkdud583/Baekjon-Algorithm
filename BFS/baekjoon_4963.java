import java.util.*;

class Location{
    int y;
    int x;
    Location(int y, int x){
        this.y = y;
        this.x = x;
    }
}


class baekjoon_4963{
    
    
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);  

        StringBuffer sb = new StringBuffer();
        while(true){
            int w = sc.nextInt();
            int h = sc.nextInt();

            if(w == 0 && h == 0)
                break;

            int map[][] = new int[h][w];

            for(int i=0; i<h; i++){
                for(int j=0; j<w; j++){
                    map[i][j] = sc.nextInt();
                }
            }

            int count = 0;
            boolean check[][] = new boolean[h][w];

            for(int i=0; i<h; i++){
                for(int j=0; j<w; j++){
                    if(!check[i][j] && map[i][j] == 1){
                        bfs(i,j,h,w,check,map);
                        count++;
                    }
                }
            }
            sb.append(count+"\n");



        }
        System.out.println(sb);

    }
    static void bfs(int i, int j, int h, int w, boolean check[][], int map[][]){
        Queue<Location> queue = new LinkedList<>();
        queue.add(new Location(i,j));
        check[i][j] = true;

        int dy[] = {-1,1,0,0,-1,1,-1,1};
        int dx[] = {0,0,-1,1,-1,-1,1,1};

        while(!queue.isEmpty()){
            Location loc = queue.poll();

            for(int k=0; k<8; k++){
                int y = loc.y + dy[k];
                int x = loc.x + dx[k];

                if(y < 0 || x < 0 || y >= h || x >= w || check[y][x] || map[y][x] == 0)
                    continue;
            
                check[y][x] = true;
                queue.add(new Location(y,x));

            }

            
        }
    }
    
    
}

