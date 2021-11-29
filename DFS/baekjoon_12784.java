import java.io.*;
import java.util.*;

class Edge{
    int n;
    int cost;

    Edge(int n, int cost){
        this.n = n;
        this.cost = cost;
    }
}

class Main{
    static ArrayList<Edge> edge[];
    public static void main(String[]args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        int T = Integer.parseInt(br.readLine());

        for(int i=0; i<T; i++){
            String input[] = br.readLine().split(" ");

            int N = Integer.parseInt(input[0]); //섬의 수
            int M = Integer.parseInt(input[1]); // 다리의 수

            edge = new ArrayList[N+1];
            
            for(int j=0; j<=N; j++)
                edge[j] = new ArrayList<>();

            for(int j=0; j<M; j++){
                input = br.readLine().split(" ");

                int a = Integer.parseInt(input[0]);
                int b = Integer.parseInt(input[1]);
                int c = Integer.parseInt(input[2]);
                
                edge[a].add(new Edge(b,c));
                edge[b].add(new Edge(a,c));
            }

            int sum = 0;
            boolean check[] = new boolean[N+1];
            check[1] = true;

            for(int j=0; j<edge[1].size(); j++){
                Edge nxt = edge[1].get(j);
                if(!check[nxt.n]){
                    int min = nxt.cost;
                    check[nxt.n] = true;
                    min = Math.min(min, dfs(nxt.n, check));
                    sum += min;
                }
                
            }
            sb.append(sum).append("\n");
        }
        System.out.println(sb);
    }
    static int dfs(int i, boolean check[]){
        int sum = 0;

        for(int j=0; j<edge[i].size(); j++){
            Edge nxt = edge[i].get(j);

            if(check[nxt.n])
                continue;
            check[nxt.n] = true;
            int min = nxt.cost;

            min = Math.min(min, dfs(nxt.n, check));
            sum += min;
        }

        return sum == 0 ? 987654321 : sum;
    }
}
    
