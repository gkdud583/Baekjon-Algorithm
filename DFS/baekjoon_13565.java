import java.util.*;

class Location{
    int y;
    int x;
    Location(int y, int x){
        this.y = y;
        this.x = x;
    }
}
public class baekjoon_13565{
    static final int DY[] = {-1,1,0,0};
    static final int DX[] = {0,0,-1,1};
    static ArrayList<Location> outerMatter = new ArrayList<>();
    static boolean map[][];
    public static void main(String[]args)
    {
        Scanner sc = new Scanner(System.in);

        String input[] = sc.nextLine().split(" ");

        int M = Integer.parseInt(input[0]);
        int N = Integer.parseInt(input[1]);

        map = new boolean[M][N];

        for(int i=0; i<M; i++){
            input = sc.nextLine().split("");
            for(int j=0; j<N; j++){
                map[i][j] = input[j].equals("0") ? true : false;
                if(i == 0 && map[i][j]){
                    outerMatter.add(new Location(i,j));
                }
            }   
        }

        System.out.print(solve(M,N) == true ? "YES" : "NO");

    }
    static boolean solve(int M, int N){
        boolean check[][] = new boolean[M][N];

        for(int i=0; i<outerMatter.size(); i++){
            Location cur = outerMatter.get(i);
            
            if(!check[cur.y][cur.x]){
                check[cur.y][cur.x] = true;
                if(dfs(M,N,cur.y,cur.x,check))
                    return true;
            }
        }
        return false;
    }
    static boolean dfs(int M, int N, int y, int x, boolean check[][]){
        for(int i=0; i<4; i++){
            int ny = y + DY[i];
            int nx = x + DX[i];

            if(ny < 0 || nx < 0 || ny >= M || nx >= N || !map[ny][nx] || check[ny][nx])
                continue;
            if(ny == M-1)
                return true;

            check[ny][nx] = true;
            if(dfs(M,N,ny,nx,check))
                return true;

        }
        return false;
    }
}