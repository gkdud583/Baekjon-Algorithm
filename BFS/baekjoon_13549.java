import java.util.*;

class Move{
    int i;
    int j;
    Move(int i, int j){
        this.i = i;
        this.j = j;
    }
}


class baekjoon_13549{
    static final int SIZE = 100000;
    static final int MAX = 987654321;
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);  

        int N = sc.nextInt();
        int K = sc.nextInt();

        if(N == K)
            System.out.println(0);
        else{
            int count[] = solve(N,K);
            System.out.println(count[K]);
        }
    }
    static int[] solve(int N, int K){
        Queue<Move> queue = new LinkedList<>();
        queue.add(new Move(N,0));


        int count[] = new int[SIZE+1];
        for(int i=0; i<=SIZE; i++){
            count[i] = MAX;
        }

        int d[] = {-1,1};

        while(!queue.isEmpty()){
            Move cur = queue.poll();


            for(int i=0; i<2; i++){
                int nxt = cur.i + d[i];

                if(nxt < 0 || nxt > SIZE || count[nxt] <= cur.j + 1)
                    continue;
                
                count[nxt] = cur.j + 1;
                queue.add(new Move(nxt, count[nxt]));

            }
            //순간 이동 하는 경우

            int nxt = cur.i * 2;
            if(nxt < 0 || nxt > SIZE || count[nxt] <= cur.j)
                continue;
            
            count[nxt] = cur.j;
            queue.add(new Move(nxt, count[nxt]));
            
        }
        return count;

        
    }
}