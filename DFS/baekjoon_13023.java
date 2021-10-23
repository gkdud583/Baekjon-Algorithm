import java.io.*;
import java.util.*;


  

public class baekjoon_13023 {

    static final int MAX = 4;
    static boolean isPossible = false;
    static ArrayList<Integer> relationship[];
    public static void main(String[]args) throws Exception{
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        relationship = new ArrayList[N];
        
        for(int i=0; i<N; i++){
            relationship[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            relationship[a].add(b);
            relationship[b].add(a);
        }

        System.out.println(solve(N));
    }
    static int solve(int N){
        for(int i=0; i<N; i++){
            boolean check [] = new boolean[N];
            isPossible = false;
            check[i] = true;
            dfs(0, i, check);
            if(isPossible)
                return 1;
        }
        return 0;
    }
    static void dfs(int size, int cur, boolean check[]){
        if(size >= MAX){
            isPossible = true;
            return;
        }
        for(int i=0; i<relationship[cur].size(); i++){
            int nxt = relationship[cur].get(i);

            if(check[nxt])
                continue;
            check[nxt] = true;
            dfs(size+1, nxt, check);
            check[nxt] = false;
        }

    }
}