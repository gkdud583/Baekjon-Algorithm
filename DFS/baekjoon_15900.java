import java.util.*;
import java.io.*;

class baekjoon_15900{
    static ArrayList<Integer> edges[];
    static int ans = 0;
    public static void main(String[]args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        edges = new ArrayList[N+1];
        
        for(int i=1; i<=N; i++)
            edges[i] = new ArrayList<>();

        for(int i=0; i<N-1; i++){
            String input[] = br.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);
            edges[a].add(b);
            edges[b].add(a);
        }

      
        boolean check[] = new boolean[N+1];
        check[1] = true;
        solve(1, 0, check);
        System.out.println(ans % 2 == 0 ? "No" : "Yes");
    }
 
    static void solve(int i, int cnt, boolean check[]){
        boolean isLeafNode = true;
        for(int j=0; j<edges[i].size(); j++){
            int nxt = edges[i].get(j);
            if(check[nxt])
                continue;
            isLeafNode = false;
            check[nxt] = true;
            solve(nxt, cnt+1, check);

        }
        if(isLeafNode)
            ans += cnt;
    }
     
}