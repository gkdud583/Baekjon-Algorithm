import java.util.*;

class Edge{
    int node;
    int dist;

    Edge(int node, int dist){
        this.node = node;
        this.dist = dist;
    }
}
public class baekjoon_1240{
    static ArrayList<Edge> edges[];
    static int ans = 0;
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        StringBuffer sb = new StringBuffer();
        int N = sc.nextInt();
        int M = sc.nextInt();

        edges = new ArrayList[N+1];
        
        for(int i=1; i<=N; i++){
            edges[i] = new ArrayList<>();
        }

        for(int i=0; i<N-1; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            int d = sc.nextInt();

            edges[a].add(new Edge(b,d));
            edges[b].add(new Edge(a,d));
        }

        for(int i=0; i<M; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            boolean check[] = new boolean[N+1];
            check[a] = true;
            ans = 0;
            dfs(a, b, 0, check);
            sb.append(ans).append("\n");
        }



        System.out.println(sb);

    }
    static boolean dfs(int a, int b, int d, boolean check[]){
        for(int j=0; j<edges[a].size(); j++){
            Edge nxt = edges[a].get(j);


            if(nxt.node == b){
                ans = d + nxt.dist;
                return true;
            }
            if(check[nxt.node])
                continue;
            check[nxt.node] = true;
            if(dfs(nxt.node, b, d + nxt.dist, check))
                return true;

            
        }
        return false;
    }
}