import java.util.*;
import java.io.*;


class Main{ 
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Integer> edges[];
    static ArrayList<Integer> childs[];
    public static void main(String[]args) throws Exception{

        int N = Integer.parseInt(sc.nextLine());

        edges = new ArrayList[N+1];
        childs = new ArrayList[N+1];

        for(int i=1; i<=N; i++){
            edges[i] = new ArrayList<>();
            childs[i] = new ArrayList<>();
        }
        for(int i=0; i<N-1; i++)
        {
            String input[] = sc.nextLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);

            edges[a].add(b);
            edges[b].add(a);
        }

        boolean check[] = new boolean[N+1];
        check[1] = true;
        dfs(1, check);

        System.out.println(isCorrect(N) == false ? 0 : 1);
    }
    static void dfs(int i, boolean check[]){
        for(int j=0; j<edges[i].size(); j++){
            int nxt = edges[i].get(j);
            if(check[nxt])
                continue;
            check[nxt] = true;
            childs[i].add(nxt);
            dfs(nxt, check);

            for(int k=0; k<childs[nxt].size(); k++){
                childs[i].add(childs[nxt].get(k));
            }
        }
    }
    static boolean isCorrect(int N){
        boolean check[] = new boolean[N+1];

        String input[] = sc.nextLine().split(" ");

        if(Integer.parseInt(input[0]) == 1){

            check[1] = true;
            for(int i=1; i<N; i++){
                int nxt = Integer.parseInt(input[i]);
                if(check[nxt])
                    return false;
                else{
                    check[nxt] = true;
                    i += childs[nxt].size();
                    for(int j=0; j<childs[nxt].size(); j++){
                        check[childs[nxt].get(j)] = true;
                    }
                }
            }
        }else
            return false;
        for(int i=1; i<=N; i++){
            if(!check[i])
                return false;
        }
        return true;
        
    }
}