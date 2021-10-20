import java.io.*;
import java.util.*;


  

public class baekjoon_2583 {
    static final int dy[] = {-1,1,0,0};
    static final int dx[] = {0,0,-1,1};
    static ArrayList<Integer> list = new ArrayList<>();
    static boolean map[][];
    static int cnt = 0;
    public static void main(String[]args) throws Exception{
        Scanner sc = new Scanner(System.in);

        int M = sc.nextInt();
        int N = sc.nextInt();
        int K = sc.nextInt();

        map = new boolean[M][N];

        for(int i=0; i<K; i++){
            int lbx = sc.nextInt();
            int lby = sc.nextInt();
            int rtx = sc.nextInt();
            int rty = sc.nextInt();

            for(int j=lby; j<rty; j++){
                for(int k=lbx; k<rtx; k++){
                    map[j][k] = true;
                }
            }

        }
        solve(M,N);

        Collections.sort(list);
        System.out.println(list.size());

        for(int i=0; i<list.size(); i++){
            System.out.print(list.get(i)+" ");
        }

        

    }
    static void solve(int M, int N){
        
        for(int i=0; i<M; i++){
            for(int j=0; j<N; j++){
                if(!map[i][j]){
                    map[i][j] = true;
                    cnt = 1;
                    dfs(i,j,M,N);
                    list.add(cnt);

                }
            }
        }
    }
    static void dfs(int y, int x, int M, int N){
        for(int i=0; i<4; i++){
            int ny = y + dy[i];
            int nx = x + dx[i];

            if(ny < 0 || nx < 0 || ny >= M || nx >= N || map[ny][nx])
                continue;
            map[ny][nx] = true;
            cnt++;
            dfs(ny,nx,M,N);
        }
    }
}