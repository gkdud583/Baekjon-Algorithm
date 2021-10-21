import java.io.*;
import java.util.*;


  

public class baekjoon_9466 {

    static int pick[];
    static int notTeamCnt = 0;
    public static void main(String[]args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        int T = Integer.parseInt(br.readLine());

        for(int i=0; i<T; i++){
            int N = Integer.parseInt(br.readLine());
            pick = new int[N+1];

            String input[] = br.readLine().split(" ");
            for(int j=0; j<N; j++){
                pick[j+1] = Integer.parseInt(input[j]);
            }

            solve(N);
            sb.append(notTeamCnt).append("\n");



        }
        System.out.print(sb);

    }
    static void solve(int N){
        boolean check[] = new boolean[N+1];
        notTeamCnt = N;

        for(int i=1; i<=N; i++){
            if(check[i])
                continue;
            else{
                dfs(i, check, new ArrayList<>());
            }
        }

    }
    static void dfs(int cur, boolean check[], ArrayList<Integer> team){
        if(check[cur]){
            if(team.contains((Integer)cur)){
                int start = team.indexOf((Integer)cur);
                int end = team.size();
                
                notTeamCnt -= (end - start);
            }
            return;
        }
        check[cur] = true;
        team.add(cur);
        dfs(pick[cur], check, team);
    }
}