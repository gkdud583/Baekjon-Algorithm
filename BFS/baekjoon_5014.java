import java.util.*;

class baekjoon_5014{
   

    
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);  

        int F = sc.nextInt();
        int S = sc.nextInt();
        int G = sc.nextInt();
        int U = sc.nextInt();
        int D = sc.nextInt();

        bfs(F, S, G, U, D);
        
        
        
    }
 
    static void bfs(int F, int S, int G, int U, int D){
        boolean check[] = new boolean[F+1];
        Queue<Integer> queue = new LinkedList<>();
        int d[] = {U,(D*-1)};
        int count[] = new int[F+1];

        check[S] = true;
        queue.add(S);

        while(!queue.isEmpty()){
            int cur = queue.poll();

            for(int i=0; i<2; i++){
                int nxt = cur + d[i];

                if(nxt < 1 || nxt > F || check[nxt])
                    continue;
                
                check[nxt] = true;    
                queue.add(nxt);
                count[nxt] = count[cur] + 1;
                
                if(nxt == G)
                {
                    break;
                }

                



            }

        }
        if(check[G])
            System.out.println(count[G]);
        else
            System.out.println("use the stairs");

    }
}