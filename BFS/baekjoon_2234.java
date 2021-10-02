import java.io.*;
import java.util.*;

class Location{
    int y;
    int x;
    Location(int y, int x){
        this.y = y;
        this.x = x;

    }
}

class Wall{
    int y;
    int x;
    int d;

    Wall(int y, int x, int d){
        this.y = y;
        this.x = x;
        this.d = d;
    }
}
class Main
{
    static ArrayList<Wall> list = new ArrayList<>();
    static StringBuffer map[][];
    public static void main(String[]args) throws IOException{
    
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        map = new StringBuffer[M][N];
        for(int i=0; i<M; i++){
            for(int j=0; j<N; j++){
                int num = sc.nextInt();
                map[i][j] = new StringBuffer(getBinaryString(num));
                for(int k=0; k<map[i][j].length(); k++){
                    if(map[i][j].charAt(k) == '1'){
                        list.add(new Wall(i,j,k));

                    }
                }
            }
        }
        solve(M,N);
    }
    static String getBinaryString(int num){
        StringBuffer s = new StringBuffer(Integer.toBinaryString(num));

        while(s.length() < 4){
            s.insert(0, '0');
        }

        return s.toString();

    }
    static void solve(int M, int N){
        boolean check[][] = new boolean[M][N];
        
        int max = 0, count = 0;
        for(int i=0; i<M; i++){
            for(int j=0; j<N; j++){
                if(!check[i][j]){
                    count++;
                    max = Math.max(max, bfs(i,j,M,N,check));
                }
            }
        }
        System.out.println(count);
        System.out.println(max);

        int combinedMax = 0;
        //0남 1동 2북 3서
        for(int i=0; i<list.size(); i++){
            Wall w = list.get(i);

            StringBuffer sb = map[w.y][w.x];
            
            sb.setCharAt(w.d, '0');

            check = new boolean[M][N];
            combinedMax = Math.max(combinedMax, bfs(w.y, w.x, M, N, check));

            sb.setCharAt(w.d, '1');
        }
        System.out.println(combinedMax);


    }
    static int bfs(int y, int x, int M, int N,boolean check[][]){
        Queue<Location> queue = new LinkedList<>();
        queue.add(new Location(y,x));

        check[y][x] = true;

        int dy[] = {1,0,-1,0};
        int dx[] = {0,1,0,-1};

        int size = 1;

        while(!queue.isEmpty()){
            Location cur = queue.poll();
            StringBuffer sb = map[cur.y][cur.x];

            for(int i=0; i<4; i++){
                if(sb.charAt(i) == '0'){
                    int ny = cur.y + dy[i];
                    int nx = cur.x + dx[i];

                    if(ny < 0 || nx < 0 || ny >= M || nx >= N || check[ny][nx])
                        continue;
                    size++;
                    check[ny][nx] = true;
                    queue.add(new Location(ny, nx));

                }
            }
        }
        return size;
        


    }
}