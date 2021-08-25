import java.util.*;

class Location{
    int y;
    int x;
    Location(int y, int x){
        this.y = y;
        this.x = x;

    }
}
class baekjoon_2933{
    
    static String map[][];
    static int turn = 0;
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);    
        String input[] = sc.nextLine().split(" ");
        int R = Integer.parseInt(input[0]);
        int C = Integer.parseInt(input[1]);

        int height[] = new int[R];
        for(int i=0,j=R-1; i<R; i++){
            height[i] = j--;
        }
        map = new String[R][C];
    
        for(int i=0; i<R; i++){
            input = sc.nextLine().split("");
            for(int j=0; j<C; j++){
                map[i][j] = input[j];
            }
        }

        int N = sc.nextInt();
        for(int i=0; i<N; i++){

            int H = height[sc.nextInt() - 1];
            int r = H;
            int c = throwStick(R,C,H,map);
            dropCluster(r,c,R,C,map);

        }

        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
       
        
    }
    static int throwStick(int R,int C,int H,String map[][]){
        int ret = 0;
        if(turn == 0){

            for(int i=0; i<C; i++){
                if(map[H][i].equals("x")){
                    map[H][i] = ".";
                    ret = i;
                    break;
                }
               
            }
            turn = 1;
        }else{
            for(int i=C-1; i>=0; i--){
                if(map[H][i].equals("x")){
                    map[H][i] = ".";
                    ret = i;
                    break;
                }
               
            }
            turn = 0;
        }
        return ret;
    }

    static void dropCluster(int r,int c,int R,int C,String map[][]){

        int dy[] = {-1,1,0,0};
        int dx[] = {0,0,-1,1};
        boolean check[][] = new boolean[R][C];
        for(int i=0; i<4; i++){
            int y = r + dy[i];
            int x = c + dx[i];

            if(y < 0 || x < 0 || y >= R || x >= C || check[y][x] || map[y][x].equals("."))
                continue;

            if(bfs(y,x,R,C,map,check))
                break;
        }
       
    }
    static boolean bfs(int y, int x,int R,int C,String map[][],boolean check[][]){
      
        Queue<Location> queue = new LinkedList<>();
        int dy[] = {-1,1,0,0};
        int dx[] = {0,0,-1,1};

        ArrayList<Location> clusterList = new ArrayList<>();
        clusterList.add(new Location(y,x));
        queue.add(new Location(y,x));
        check[y][x] = true;
        while(!queue.isEmpty()){
            Location cur = queue.poll();

            for(int i=0; i<4; i++){
                y = cur.y + dy[i];
                x = cur.x + dx[i];

                if(y < 0 || x < 0 || y >= R || x >= C || check[y][x] || map[y][x].equals(".")){
                    continue;
                }

                check[y][x] = true;
                clusterList.add(new Location(y,x));
                queue.add(new Location(y,x));
            }
        }
        return checkClusterDrop(R,C,map,clusterList);

    }

    static boolean checkClusterDrop(int R,int C,String map[][],ArrayList<Location> clusterList){

        String temp[][] = new String[R][C];

        for(int i=0; i<R; i++)
            temp[i] = map[i].clone();
        Collections.sort(clusterList, new Comparator<Location>(){
            @Override
            public int compare(Location o1, Location o2) {
                if(o1.y > o2.y)
                    return -1;
                else if(o1.y < o2.y){
                    return 1;
                }
                else{
                    if(o1.x > o2.x)
                        return -1;
                    else if(o1.x < o2.x)
                        return 1;
                    else
                        return 0;
                }
            }
        });
        boolean isPossible = false;
        while(true){
            //한칸씩 내려보면서 확인
            boolean isBreak = false;

            for(int i=0; i<clusterList.size(); i++){
                Location cur = clusterList.get(i);
                int y = cur.y + 1;
                
                if(y >= R || temp[y][cur.x].equals("x")){
                    isBreak = true;
                    break;
                }
                

                temp[y][cur.x] = "x";
                temp[cur.y][cur.x] = ".";
                cur.y = y;
            }
            if(isBreak)
                break;
            else{
                isPossible = true;
                for(int i=0; i<R; i++)
                    map[i] = temp[i].clone();
            }
        }

        return isPossible;
    }
}
