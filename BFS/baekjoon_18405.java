
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

public class baekjoon_18405 {

    static int map[][];
    static Queue<Location> virus[];
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int K = sc.nextInt();

        map = new int[N][N];
        virus = new Queue[K+1];
        
        for(int i=0; i<=K; i++){
            virus[i] = new LinkedList<>();
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                map[i][j] = sc.nextInt();
                if(map[i][j] != 0){
                    virus[map[i][j]].add(new Location(i,j));
                }
            }
        }
        int S = sc.nextInt();

        solve(N,K,S);
        System.out.println(map[sc.nextInt()-1][sc.nextInt()-1]);

    }
    static void solve(int N, int K,int max){
        int dy[] = {-1,1,0,0};
        int dx[] = {0,0,-1,1};
        int s = 0;
        while(s < max){
            boolean isBreak = false;
            for(int i=1; i<=K; i++){
                for(int j=0,size=virus[i].size(); j<size; j++){
                    Location cur = virus[i].poll();
                    for(int k=0; k<4; k++){
                        int y = cur.y + dy[k];
                        int x = cur.x + dx[k];

                        if(y < 0 || x < 0 || y >= N || x >= N || map[y][x] != 0)
                            continue;
                        
                        if(!isBreak)
                            isBreak = true;
                        map[y][x] = i;
                        virus[i].add(new Location(y,x));

                    }
                }
            }
            s++;
            if(!isBreak)
                break;
        }
    }
}