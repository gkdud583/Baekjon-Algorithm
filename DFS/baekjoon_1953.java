import java.io.*;
import java.util.*;





class Main{

    static ArrayList<Integer> list[];
    static boolean check[];
    static TreeSet<Integer> white = new TreeSet<>();
    static TreeSet<Integer> blue = new TreeSet<>();

    public static void main(String[]args) throws Exception{
        Scanner sc = new Scanner(System.in);
    
        int N = sc.nextInt();

        list = new ArrayList[N+1];
        check = new boolean[N+1];


        for(int i=1; i<=N; i++)
            list[i] = new ArrayList<>();

        for(int i=1; i<=N; i++){
            int n = sc.nextInt();
            for(int j=0; j<n; j++){
                int p = sc.nextInt();
                list[i].add(p);
                list[p].add(i);
            }
        }

        for(int i=1; i<=N; i++){
            if(!check[i]){
                check[i] = true;
                white.add(i);
                solve(N, i, 0);
            }
        }

        if(white.size() == N){
            blue.add(white.pollFirst());
        }

        StringBuffer sb = new StringBuffer();
        sb.append(white.size()).append("\n");

        for(int v : white){
            sb.append(v).append(" ");
        }
        sb.append("\n");

        sb.append(blue.size()).append("\n");

        for(int v : blue){
            sb.append(v).append(" ");
        }
        System.out.println(sb);


    
    }
    static void solve(int N, int i, int team){
        if(team == 0){
            for(int j=0; j<list[i].size(); j++){
                int nxt = list[i].get(j);
                if(!check[nxt]){
                    check[nxt] = true;
                    blue.add(nxt);
                    solve(N, nxt, 1);
                }
            }
        }else{
            for(int j=0; j<list[i].size(); j++){
                int nxt = list[i].get(j);
                if(!check[nxt]){
                    check[nxt] = true;
                    white.add(nxt);
                    solve(N, nxt, 0);
                }
            }
        }
    }
    
}