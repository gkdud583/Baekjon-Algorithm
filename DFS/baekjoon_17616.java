import java.util.*;
import java.io.*;


class Main{ 

    static int count = 0;
    static ArrayList<Integer> higherScoreThanMeList[];
    static ArrayList<Integer> lowerScoreThanMeList[];
    public static void main(String[]args) throws Exception{
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        higherScoreThanMeList = new ArrayList[N+1];
        lowerScoreThanMeList = new ArrayList[N+1];


        for(int i=1; i<=N; i++){
            higherScoreThanMeList[i] = new ArrayList<>();
            lowerScoreThanMeList[i] = new ArrayList<>();

        }

        int M = sc.nextInt();

        int X = sc.nextInt();
        for(int i=0; i<M; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();

            lowerScoreThanMeList[a].add(b);
            higherScoreThanMeList[b].add(a);

        }

        int ret[] = solve(N, X);

        System.out.println(ret[0]+" "+ret[1]);

    }
    static int[] solve(int N, int X){
        int ret[] = new int[2];
        //나보다 점수가 낮은 사람 


        
        boolean check[] = new boolean[N+1];
        check[X] = true;
        count = 0;
        dfs(X, check, lowerScoreThanMeList);
    
        ret[1] = N - count;

        Arrays.fill(check, false);

        //나보다 점수가 높은 사람
        check[X] = true;
        count = 0;
        dfs(X, check, higherScoreThanMeList);

        ret[0] = count + 1;
        return ret;
    }
    static void dfs(int i, boolean check[], ArrayList<Integer> list[]){
        for(int j=0; j<list[i].size(); j++){
            int nxt = list[i].get(j);
            if(check[nxt])
                continue;
            count++;
            check[nxt] = true;
            dfs(nxt, check, list);

        }
    }
}