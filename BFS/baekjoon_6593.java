import java.io.*;
import java.util.*;



class Location{
    int h;
    int y;
    int x;
    int count;
    Location(int h, int y, int x, int count){
        this.h = h;
        this.y = y;
        this.x = x;
        this.count = count;
    }
}

class Main
{
    static String building[][][];
    static Location start;
    static Location end;
    public static void main(String[]args) throws IOException{
        // Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        while(true){
            String input[] = br.readLine().split(" ");
            int L = Integer.parseInt(input[0]);
            int R = Integer.parseInt(input[1]);
            int C = Integer.parseInt(input[2]);

            if(L == 0 && R == 0 && C == 0)
                break;
            building = new String[L][R][C];

            for(int i=0; i<L; i++){
                for(int j=0; j<R; j++){
                    input = br.readLine().split("");
                    for(int k=0; k<C; k++){
                        building[i][j][k] = input[k];
                        if(input[k].equals("S")){
                            start = new Location(i,j,k,0);
                        }else if(input[k].equals("E")){
                            end = new Location(i,j,k,0);
                        }
                    }
                }
                br.readLine();
            }

            sb.append(solve(L,R,C)+"\n");

        }
        System.out.println(sb);
    }
    static String solve(int H, int R, int C){
        Queue<Location> queue = new LinkedList<>();
        boolean check[][][] = new boolean[H][R][C];
        queue.add(new Location(start.h, start.y, start.x, 0));
        check[start.h][start.y][start.x] = true;

        //동서남북상하
        int dh[] = {0,0,0,0,-1,1};
        int dy[] = {0,0,1,-1,0,0};
        int dx[] = {1,-1,0,0,0,0};

        while(!queue.isEmpty()){
            Location cur = queue.poll();
            if(cur.h == 1 && cur.y == 3 && cur.x == 4){
                int t = 3;
            }
            for(int i=0; i<6; i++){
                int h = cur.h + dh[i];
                int y = cur.y + dy[i];
                int x = cur.x + dx[i];

                if(h < 0 || y < 0 || x < 0 || h >= H || y >= R || x >= C || check[h][y][x] || building[h][y][x].equals("#"))
                    continue;

                if(h == end.h && y == end.y && x == end.x)
                    return "Escaped in "+ (cur.count+1) + " minute(s).";
                check[h][y][x] = true;
                queue.add(new Location(h,y,x,cur.count+1));

            }
        }
        return "Trapped!";

    }
}