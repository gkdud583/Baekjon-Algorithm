import java.io.*;
import java.util.*;

class Location{
    int y;
    int x;
    Location(int y, int x){
        this.y = y;
        this.x = x;

    }
}
class Main
{
    static ArrayList<Integer>edges[];
    public static void main(String[]args) throws IOException{
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();
        int K = sc.nextInt();
        int X = sc.nextInt();

        edges = new ArrayList[N+1];
        for(int i=1; i<=N; i++){
            edges[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            edges[a].add(b);
        }
        int d[] = bfs(N,X);
        int count  = 0;
        for(int i=1; i<=N; i++){
            if(d[i] == K){
                count++;
                System.out.println(i);
            }
        }
        if(count == 0)
            System.out.println(-1);

    }
    static int[] bfs(int N, int X){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(X);

        boolean check[] = new boolean[N+1];
        check[X] = true;

        int d[] = new int[N+1];
        
        while(!queue.isEmpty()){
            int cur = queue.poll();

            for(int i=0; i<edges[cur].size(); i++){
                int nxt = edges[cur].get(i);
                if(check[nxt])
                    continue;
                d[nxt] = d[cur] + 1;
                check[nxt] = true;
                queue.add(nxt);
            }
        }
        return d;
    }
}