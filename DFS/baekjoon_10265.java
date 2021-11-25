import java.io.*;
import java.util.*;


class Scc{
    int cycle;
    int size;

    Scc(int cycle, int size){
        this.cycle = cycle;
        this.size = size;
    }
}
class Main{

    static int groupIndex = 1;
    static ArrayList<Scc> SCC = new ArrayList<>();
    static int group[];
    static boolean finished[];
    static Stack<Integer> stack = new Stack<>();
    
    static int input[];

    public static void main(String[]args) throws Exception{
        Scanner sc = new Scanner(System.in);
    
        int N = sc.nextInt();
        int K = sc.nextInt();

        group = new int[N+1];
        finished = new boolean[N+1];
        input = new int[N+1];
        
        for(int i=1; i<=N; i++){
            int n = sc.nextInt();
            input[i] = n;
        }
        
        findSCC(N,K);
        System.out.println(knapsack(N, K));

    }
    static void findSCC(int N, int K){
        boolean check[] = new boolean[N+1];
        SCC.add(new Scc(0,0));
        for(int i=1; i<=N; i++){
            if(!check[i]){
                dfs(N, K, i, check);
                
            }
        }
    }
    static void dfs(int N, int K, int i, boolean check[]){

 
        if(check[i] && !finished[i]){
            int cycleCnt = 0;
            while(!stack.isEmpty()){
                int nxt = stack.pop();
                cycleCnt++;
                finished[nxt] = true;
                if(i == nxt){
                    break;
                }

            }
            Scc scc = new Scc(cycleCnt, 0);
            SCC.add(scc);
            group[i] = groupIndex++;
            return;
        }
        if(finished[i])
            return;
        check[i] = true;
        stack.add(i);
        dfs(N, K, input[i], check);
        group[i] = group[input[i]];
        finished[i] = true;
        SCC.get(group[i]).size++;

        
    }

    static int knapsack(int N, int K){
        int dp[][] = new int[SCC.size()][K+1];
        
        for(int i=0; i<SCC.size(); i++)
            Arrays.fill(dp[i], -1);

        for(int i=1; i<SCC.size(); i++){ //물건
            int w = SCC.get(i).cycle;
            if(w > K)
                continue;
            int v = SCC.get(i).size - SCC.get(i).cycle;
            dp[i][w] = v;
            for(int j=1; j<=K; j++){ 
                if(j > w){
                    if(dp[i-1][j-w] >= 0)
                        dp[i][j] = Math.max(dp[i-1][j-w] + v, dp[i-1][j]);
                    else
                        dp[i][j] = Math.max(dp[i][j],dp[i-1][j]);
                }else   
                    dp[i][j] = Math.max(dp[i][j],dp[i-1][j]);
            }
        }
        int ans = 0;
        for(int i=1; i<=K; i++){
            if(dp[SCC.size()-1][i] >= 0){
                ans = Math.max(ans, dp[SCC.size()-1][i] + i);
            }
        }
        return ans > K ? K : ans;
    }
}
