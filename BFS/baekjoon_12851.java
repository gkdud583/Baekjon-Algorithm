

import java.util.*;

class Move{
    int i;
    int count;

    Move(int i, int count){
        this.i = i;
        this.count = count;
    }
}
class Main{
    static final int MAX = 987654321;
    static final int SIZE = 100000;
    static int count = 0;
    
    static int dp[] = new int[SIZE+1];

    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int K = sc.nextInt();

        if(N == K){
            System.out.println(0);
            System.out.println(1);
        }else{
            Arrays.fill(dp, MAX);

            bfs(N,K);
            System.out.println(dp[K]);
            System.out.println(count);
        }
        
    }
    static void bfs(int N, int K){
        int d[] = {-1,1};
        Queue<Move> queue = new LinkedList<>();
        queue.add(new Move(N,0));
        while(!queue.isEmpty()){
            Move cur = queue.poll();

            for(int i=0; i<2; i++){
                int nxt = cur.i + d[i];

                if(nxt < 0 || nxt > SIZE || dp[nxt] < cur.count+1){
                    continue;
                }
                dp[nxt] = cur.count + 1;

                if(nxt == K){
                    count++;
                    continue;
                }
                queue.add(new Move(nxt, cur.count+1));


            }
            int nxt = cur.i * 2;
            if(nxt < 0 || nxt > SIZE || dp[nxt] < cur.count+1){
                continue;
            }
            dp[nxt] = cur.count + 1;

            if(nxt == K){
                count++;
                continue;
            }
            queue.add(new Move(nxt, cur.count+1));
        }

    }
    
}

