import java.io.*;
import java.util.*;

class Location{
    int y;
    int x;
    int c;
    int keys[];
    Location(int y, int x, int c, int keys[]){
        this.y = y;
        this.x = x;
        this.c = c;
        this.keys = keys;
    }
}
class Main
{
    static final int MAX = 987654321;
    static final int SIZE = 6;
    static int min = MAX;
    static String map[][];
    static Location start;
    public static void main(String[]args) throws IOException{
        Scanner sc = new Scanner(System.in);

        String input[] = sc.nextLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        map = new String[N][M];

        for(int i=0; i<N; i++){
            input = sc.nextLine().split("");
            for(int j=0; j<M; j++){
                map[i][j] = input[j];
                if(map[i][j].equals("0")){
                    int keys[] = new int[SIZE];
                    start = new Location(i,j,0,keys);
                }
            }
        }
        bfs(N,M);
        System.out.println(min == MAX? -1 : min);

    }
    static void bfs(int N, int M){
        boolean check[][][][][][][][] = new boolean[N][M][2][2][2][2][2][2];
        check[start.y][start.x][0][0][0][0][0][0] = true;
        Queue<Location> queue = new LinkedList<>();
        int keys[] = new int[SIZE];
        queue.add(new Location(start.y, start.x, 0, keys));

        int dy[] = {-1,1,0,0};
        int dx[] = {0,0,-1,1};
        
        while(!queue.isEmpty()){
            Location cur = queue.poll();

            for(int i=0; i<4; i++){
                int y = cur.y + dy[i];
                int x = cur.x + dx[i];

                if(y < 0 || x < 0 || y >= N || x >= M || map[y][x].equals("#"))
                    continue;

                char v = map[y][x].charAt(0);
                if(v >= 'A' && v <= 'F'){
                    //문
                    if(cur.keys[(int)v - 65] == 1 && !check[y][x][cur.keys[0]][cur.keys[1]][cur.keys[2]][cur.keys[3]][cur.keys[4]][cur.keys[5]]){
                        check[y][x][cur.keys[0]][cur.keys[1]][cur.keys[2]][cur.keys[3]][cur.keys[4]][cur.keys[5]] = true;
                        queue.add(new Location(y,x,cur.c+1,coppyKeys(cur.keys)));
                    }
                }else if(v >= 'a' && v <= 'f'){
                    //키
                    int tempKeys[] = coppyKeys(cur.keys);


                    if(cur.keys[(int)v - 97] == 0){
                        tempKeys[(int)v - 97] = 1;
                    }
                
                    if(!check[y][x][tempKeys[0]][tempKeys[1]][tempKeys[2]][tempKeys[3]][tempKeys[4]][tempKeys[5]]){
                        check[y][x][tempKeys[0]][tempKeys[1]][tempKeys[2]][tempKeys[3]][tempKeys[4]][tempKeys[5]] = true;
                        queue.add(new Location(y,x,cur.c+1,tempKeys));
                    }
                    
                }
                else{
                    //빈곳이거나 출구
                    if(map[y][x].equals("1")){
                        min = Math.min(min, cur.c + 1);
                    }else{
                        if(!check[y][x][cur.keys[0]][cur.keys[1]][cur.keys[2]][cur.keys[3]][cur.keys[4]][cur.keys[5]]){

                            check[y][x][cur.keys[0]][cur.keys[1]][cur.keys[2]][cur.keys[3]][cur.keys[4]][cur.keys[5]] = true;
                            queue.add(new Location(y,x,cur.c+1,coppyKeys(cur.keys)));
                        }
                    }

                }
            }
        }

    }
    static int[] coppyKeys(int keys[]){
        int tempKeys[] = new int[SIZE];
        for(int i=0; i<SIZE; i++){
            tempKeys[i] = keys[i];
        }
        return tempKeys;
    }
   
}