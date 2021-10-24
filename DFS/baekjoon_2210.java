import java.io.*;
import java.util.*;


  

public class baekjoon_2210 {

    static final int SIZE = 5;
    static final int DY[] = {-1,1,0,0};
    static final int DX[] = {0,0,-1,1};
    static int map[][] = new int[SIZE][SIZE];
    static Set<String> set = new HashSet<>();

    public static void main(String[]args) throws Exception{
        Scanner sc = new Scanner(System.in);

        for(int i=0; i<SIZE; i++){
            for(int j=0; j<SIZE; j++)
                map[i][j] = sc.nextInt();
        }

        solve();
        System.out.println(set.size());
    }
    static void solve(){
        for(int i=0; i<SIZE; i++){
            for(int j=0; j<SIZE; j++){
                StringBuffer sb = new StringBuffer();
                sb.append(map[i][j]);
                dfs(1, i, j, sb);
            }
        }
    }
    static void dfs(int i, int y, int x, StringBuffer sb){
        if(i >= 6){
            if(!set.contains(sb.toString()))
                set.add(sb.toString());
            return;
        }
        for(int j=0; j<4; j++){
            int ny = y + DY[j];
            int nx = x + DX[j];

            if(ny < 0 || nx < 0 || ny >= SIZE || nx >= SIZE)
                continue;
            StringBuffer nsb = new StringBuffer(sb.toString());
            nsb.append(map[ny][nx]);
            dfs(i+1, ny, nx, nsb);
        }
    }
}
​