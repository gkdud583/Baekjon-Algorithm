import java.util.*;
import java.io.*;


class baekjoon_16947{
    static StringBuffer sb = new StringBuffer();
    static boolean map[];
    static int dist[];
    static boolean ringLineCheck[];
    static ArrayList<Integer> edges[];
    public static void main(String[]args) throws Exception{
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        ringLineCheck = new boolean[N+1];
        dist = new int[N+1];
        map = new boolean[N+1];
        edges = new ArrayList[N+1];

        for(int i=1; i<=N; i++){
            edges[i] = new ArrayList<>();
        }

        for(int i=0; i<N; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();

            edges[a].add(b);
            edges[b].add(a);
        }

        int check[] = new int[N+1];
        check[1] = 1;
        
        ArrayList<Integer> ringLineList = new ArrayList<Integer>();
        ringLineList.add(1);
        findRingLine(N, 1, 1, check, ringLineList);

        getAnswer(N);
        System.out.print(sb);


    }
    static boolean findRingLine(int N, int s, int p, int check[], ArrayList<Integer> ringLineList){

        if(ringLineList.size() > 1 && check[s] == 2){

            int start = ringLineList.indexOf(s);

            for(int i=start; i<ringLineList.size(); i++){
                ringLineCheck[ringLineList.get(i)] = true;
            }
            return true;
        }
        for(int i=0; i<edges[s].size(); i++){
            int nxt = edges[s].get(i);
            if(nxt == p)
                continue;
            check[nxt]++;
            ringLineList.add(nxt);
            if(findRingLine(N, nxt, s, check, ringLineList))
                return true;
            ringLineList.remove((Integer)nxt);
        }
        return false;
    }

    static void getAnswer(int N){
        for(int i=1; i<=N; i++){
            if(ringLineCheck[i])
                sb.append(0).append(" ");
            else{
                boolean check[] = new boolean[N+1];
                check[i] = true;
                sb.append(getNearestRingStation(i, 0, check)).append(" ");
            }
        }

    }

    static int getNearestRingStation(int i, int d, boolean check[]){
        int min = 987654321;
        for(int j=0; j<edges[i].size(); j++){
            int nxt = edges[i].get(j);
            if(check[nxt])
                continue;
            if(dist[nxt] != 0){
                return dist[nxt];
            }
            if(ringLineCheck[nxt]){
                return d+1;
            }
            check[nxt] = true;
            min = Math.min(min, getNearestRingStation(nxt, d+1, check));
        }
        return min;
    }
}
