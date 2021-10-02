import java.io.*;
import java.util.*;

class Move{
    int num;
    int count;

    Move(int num, int count){
        this.num = num;
        this.count = count;
    }
}

class Main
{
    static final int SIZE = 100;
    static final int MAX = 987654321;
    static int map[] = new int[SIZE+1];
    public static void main(String[]args) throws IOException{
    
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); //사다리수
        int M = sc.nextInt(); //뱀의수

        for(int i=0; i<N+M; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            
            map[a] = b;
        }

        System.out.println(bfs());
        


    }
    static int bfs(){
        Queue<Move> queue = new LinkedList<>();
        queue.add(new Move(1, 0));

        int count[] = new int[SIZE+1];
        Arrays.fill(count, MAX);
        count[1] = 0;
        
        while(!queue.isEmpty()){
            Move cur = queue.poll();
        
            if(map[cur.num] != 0){
                
                int nxt = map[cur.num];
                if(nxt > SIZE || count[nxt] <= cur.count)
                    continue;
              
                count[nxt] = cur.count;
                queue.add(new Move(nxt, cur.count));
                
                
            }else{
                for(int i=1; i<=6; i++){
                    int nxt = cur.num + i;
                    if(nxt > SIZE || count[nxt] <= cur.count + 1)
                        continue;

                    count[nxt] = cur.count + 1;
                    queue.add(new Move(nxt, cur.count+1));
                }
            }
        }
        return count[SIZE];
    }
}