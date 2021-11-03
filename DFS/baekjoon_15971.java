import java.util.*;

class Edge{
    int n;
    int d;
    Edge(int n,int d){
        this.n = n;
        this.d = d;
    }
}


class baekjoon_15971{
    static final int MAX = 98765432;
    static ArrayList<Edge> edges[];
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        int []distOfR1 = new int[N+1];
        int []distOfR2 = new int[N+1];

        Arrays.fill(distOfR1, MAX);
        Arrays.fill(distOfR2, MAX);

        edges = new ArrayList[N+1];
        for(int i=1; i<=N; i++){
            edges[i] = new ArrayList<>();
        }
        int r1 = sc.nextInt();
        int r2 = sc.nextInt();


        
        for(int i=0; i<N-1; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            int d = sc.nextInt();

            edges[a].add(new Edge(b,d));
            edges[b].add(new Edge(a,d));
        }

        if(r1 == r2){
            System.out.println(0);
            return;
        }
        distOfR1[r1] = 0;
        distOfR2[r2] = 0;
        getDist(r1, distOfR1, 0);
        getDist(r2, distOfR2, 0);

    
        System.out.println(solve(N, distOfR1, distOfR2));


    }
    static void getDist(int start, int distOfR[], int dist){
        for(int i=0; i<edges[start].size(); i++){
            Edge edge = edges[start].get(i);
            if(distOfR[edge.n] <= dist + edge.d)
                continue;
            distOfR[edge.n] = dist + edge.d;
            getDist(edge.n,distOfR,dist+edge.d);

        }
    }
    static int solve(int N, int distOfR1[], int distOfR2[]){
        int min = MAX;

        for(int i=1; i<=N; i++){
            for(int j=0; j<edges[i].size(); j++){
                Edge nxt = edges[i].get(j);
                
                int cmp1 = distOfR1[i] + distOfR2[nxt.n];
                int cmp2 = distOfR1[nxt.n] + distOfR2[i];

                min = Math.min(min, Math.min(cmp1, cmp2));
            }
        }
        return min;
    }
}

