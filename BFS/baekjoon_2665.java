import java.io.*;
import java.util.*;

class Move{
    int y;
    int x;
    int count;
    Move(int y, int x, int count){
        this.y = y;
        this.x = x;
        this.count = count;
    }
}


class Main
{
    static final int MAX = 987654321;
    static int map[][];
    public static void main(String[]args) throws IOException{
        Scanner sc = new Scanner(System.in);
        
        int N = Integer.parseInt(sc.nextLine());

        map = new int[N][N];

        for(int i=0; i<N; i++){
            String input[] = sc.nextLine().split("");
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(input[j]);
            }
        }

        System.out.println(bfs(N));
    }
    static int bfs(int N){
        Queue<Move> queue = new LinkedList<>();
        int count[][] = new int[N][N];
        queue.add(new Move(0,0,0));
        for(int i=0; i<N; i++)
            Arrays.fill(count[i], MAX);

        int dy[] = {-1,1,0,0};
        int dx[] = {0,0,-1,1};
        while(!queue.isEmpty()){
            Move cur = queue.poll();
            for(int i=0; i<4; i++){
                int y = cur.y + dy[i];
                int x = cur.x + dx[i];
                
                if(y < 0 || x < 0 || y >= N || x >= N)
                    continue;


                if(map[y][x] == 1){
                    if(count[y][x] > cur.count)
                    {
                        queue.add(new Move(y,x,cur.count));
                        count[y][x] = cur.count;
                    }
                }
                else{
                    if(count[y][x] > cur.count + 1){
                        queue.add(new Move(y,x,cur.count+1));
                        count[y][x] = cur.count + 1;
                    }
                       
                }

                
            }
        }
        return count[N-1][N-1];
    }
}