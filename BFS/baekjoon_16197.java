
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

class Coin{
    int fy;
    int fx;
    int sy;
    int sx;
    int cnt;

    Coin(int fy, int fx, int sy, int sx, int cnt){
        this.fy = fy;
        this.fx = fx;
        this.sy = sy;
        this.sx = sx;
        this.cnt = cnt;
    }
}
public class baekjoon_16197 {

    static int map[][];
    static int min = 987654321;
    static Location FC;
    static Location SC;
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);

        String input[] = sc.nextLine().split(" ");

        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        map = new int[N][M];
        ArrayList<Location> coin = new ArrayList<>();
        for(int i=0; i<N; i++){
            input = sc.nextLine().split("");
            for(int j=0; j<M; j++){
                if(input[j].equals(".")){
                    map[i][j] = 0;
                }else if(input[j].equals("o")){
                    map[i][j] = 0;
                    coin.add(new Location(i,j));
                }else{
                    map[i][j] = 1;
                }
            }
        }
        FC = coin.get(0);
        SC = coin.get(1);

        bfs(N, M);
        System.out.println(min == 987654321 ? -1 : min);


    }
    static void bfs(int N, int M){
        Queue<Coin> queue = new LinkedList<>();
        queue.add(new Coin(FC.y, FC.x, SC.y, SC.x, 0));

        boolean check[][][][] = new boolean[N][M][N][M];
        check[FC.y][FC.x][SC.y][SC.x] = true;

        int dy[] = {-1,1,0,0};
        int dx[] = {0,0,-1,1};

        while(!queue.isEmpty()){
            Coin cur = queue.poll();
            if(cur.cnt + 1 <= 10){
                for(int i=0; i<4; i++){
                    int nfy = cur.fy + dy[i];
                    int nfx = cur.fx + dx[i];
                    int nsy = cur.sy + dy[i];
                    int nsx = cur.sx + dx[i];

                    
                    //0은 벽임
                    //1은 동전이거나 빈곳이어서 움직임
                    //2는 나가 떨어짐
                    int ret1 = move(nfy, nfx, N, M);
                    int ret2 = move(nsy, nsx, N, M);

                    if(ret1 == 0){
                        nfy = cur.fy;
                        nfx = cur.fx;
                    }
                    if(ret2 == 0){
                        nsy = cur.sy;
                        nsx = cur.sx;
                    }

                

                    if(ret1 == 2 && ret2 == 2)
                        continue;
                    if((ret1 == 2 && ret2 != 2) || (ret2 == 2 && ret1 != 2)){
                        min = Math.min(min, cur.cnt + 1);
                        continue;
                    }
                    if(check[nfy][nfx][nsy][nsx])
                        continue;
                    check[nfy][nfx][nsy][nsx] = true;
                    queue.add(new Coin(nfy, nfx, nsy, nsx, cur.cnt + 1));

                    
                }
            }
        }
    }

    static int move(int y, int x, int N, int M){
        if(y < 0 || x < 0 || y >= N || x >= M)
            return 2;
        else if(map[y][x] == 1)
            return 0;
        return 1;
    }


    
}
