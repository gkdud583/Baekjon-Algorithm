
import java.util.*;
import java.io.*;



public class baekjoon_13265 {
    static ArrayList<Integer> edge[];
    public static void main(String[]args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        int T = Integer.parseInt(br.readLine());

        for(int i=0; i<T; i++){
            String input[] = br.readLine().split(" ");
            int N = Integer.parseInt(input[0]);
            int M = Integer.parseInt(input[1]);

            edge = new ArrayList[N+1];
            for(int j=1; j<=N; j++){
                edge[j] = new ArrayList<>();
            }
            

            for(int j=0; j<M; j++){
                input = br.readLine().split(" ");
                int a = Integer.parseInt(input[0]);
                int b = Integer.parseInt(input[1]);

                edge[a].add(b);
                edge[b].add(a);
            }
            sb.append(solve(N,M)).append("\n");

        }
        System.out.println(sb);

    }
    static String solve(int N, int M){
        int check[] = new int[N+1];

        for(int i=1; i<=N; i++){
            if(check[i] == 0){
                check[i] = 1;
                String ret = dfs(i,1,check);
                if(ret.equals("impossible"))
                    return ret;
            }
        }
        return "possible";
    }
    static String dfs(int i, int color, int check[]){
        for(int j=0; j<edge[i].size(); j++){
            int nxt = edge[i].get(j);

            if(check[nxt] == color)
                return "impossible";
            if(check[nxt] != 0)
                continue;
            
            if(color == 1){
                check[nxt] = 2;
                if(dfs(nxt, 2, check).equals("impossible"))
                    return "impossible";
            }else{
                check[nxt] = 1;
                if(dfs(nxt, 1, check).equals("impossible"))
                    return "impossible";
            }
        }
        return "possible";
    }
}