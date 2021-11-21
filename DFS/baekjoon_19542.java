import java.io.*;
import java.util.*;



class Main{ 
    static ArrayList<Integer> edge[];
    static int ans = 0;
    public static void main(String[]args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input[] = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int S = Integer.parseInt(input[1]);
        int D = Integer.parseInt(input[2]);

        edge = new ArrayList[N+1];
        for(int i=1; i<=N; i++)
            edge[i] = new ArrayList<>();
        
        for(int i=0; i<N-1; i++){
            input = br.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);
            edge[a].add(b);
            edge[b].add(a);
        }

        solve(N, S, D);
        System.out.println(ans * 2);

    }
    static void solve(int N, int S, int D){
        boolean check[] = new boolean[N+1];
        boolean ansCheck[] = new boolean[N+1];
        check[S] = true;
        dfs(S, S, D, check, ansCheck);
    }
    static int dfs(int i, int S, int D, boolean check[], boolean ansCheck[]){
        int maxLen = 0;
        boolean isLeafNode = true;
        for(int j=0; j<edge[i].size(); j++){
            int nxt = edge[i].get(j);

            if(check[nxt])
                continue;
            check[nxt] = true;
            isLeafNode = false;
            int ret = dfs(nxt, S, D, check, ansCheck) + 1;
            maxLen = Math.max(maxLen, ret);
            if(i != S && ret >= D){
                if(!ansCheck[i]){//서브트리가 2개 이상일때 현재 노드를 중복으로 더하지 않기 위함
                    ansCheck[i] = true; 
                    ans++;
                }
            }

        }
        if(isLeafNode){ 
            /*
            2 1 0
            1 2

            1
            */
            /*
            1 1 0

            0
            */
            if(i != S && D == 0 && !ansCheck[i]){
                ansCheck[i] = true;
                ans++;
            }

        }
        return maxLen;
    }
}

