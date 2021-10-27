import java.io.*;
import java.util.*;


  

public class baekjoon_1058 {

    static boolean friends[][];
    public static void main(String[]args) throws Exception{
        Scanner sc = new Scanner(System.in);

        int N  = Integer.parseInt(sc.nextLine());

        friends = new boolean[N][N];

        for(int i=0; i<N; i++){
            String input[] = sc.nextLine().split("");
            for(int j=0; j<N; j++)
            {
                if(input[j].equals("N")){
                    friends[i][j] = false;
                }else
                    friends[i][j] = true;
            }
        }

        System.out.println(solve(N));



    }
    static int solve(int N){
        int max = 0;


        for(int i=0; i<N; i++){
            boolean check[] = new boolean[N];
            check[i] = true;
            max = Math.max(max, dfs(N, i, 0, check));
        }

        return max;
    }
    static int dfs(int N, int i, int d, boolean[]check){
        if(d >= 2){
            return 0;
        }
        
        Queue<Integer> queue = new LinkedList<>();
        for(int j=0; j<N; j++){
            if(check[j] || !friends[i][j])
                continue;
            check[j] = true;
            queue.add(j);
        }
        int cnt = 0;
        while(!queue.isEmpty()){
            cnt += dfs(N, queue.poll(), d+1,check) + 1;
        }
        return cnt;
    }
}