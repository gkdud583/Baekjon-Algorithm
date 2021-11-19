import java.util.*;




class Main{ 
    static final int DY[] = {1,0};
    static final int DX[] = {0,1};
    static int max = -987654321;
    static int min = 987654321;
    static int map[][];
    public static void main(String[]args) throws Exception{
        Scanner sc = new Scanner(System.in);

        int N = Integer.parseInt(sc.nextLine());

        map = new int[N][N];

        for(int i=0; i<N; i++){
            String input[] = sc.nextLine().split(" ");
            for(int j=0; j<N; j++){
                if(input[j].equals("+"))
                    map[i][j] = 6;
                else if(input[j].equals("-"))
                    map[i][j] = 7;
                else if(input[j].equals("*"))
                    map[i][j] = 8;
                else{
                    map[i][j] = Integer.parseInt(input[j]);
                }


            }
        }
        boolean check[][] = new boolean[N][N];
        check[0][0] = true;
        dfs(N, 0, 0, map[0][0], 0, check);
        System.out.print(max+" "+min);
    }
    static void dfs(int N, int y, int x, int v, int oper, boolean check[][]){
        if(y == N-1 && x == N-1){
            max = Math.max(max, v);
            min = Math.min(min, v);
        }
        for(int i=0; i<2; i++){
            int ny = y + DY[i];
            int nx = x + DX[i];

            if(ny < 0 || nx < 0 || ny >= N || nx >= N || check[ny][nx])
                continue;
            int src = v;
            if(map[ny][nx] >= 6){
                oper = map[ny][nx];
            }else{
                if(oper == 6){
                    v += map[ny][nx];
                }else if(oper == 7){
                    v -= map[ny][nx];
                }else{
                    v *= map[ny][nx];
                }
            }
            check[ny][nx] = true;
            dfs(N, ny, nx, v, oper, check);
            
            check[ny][nx] = false;
            v = src;

        }
        
    }
}