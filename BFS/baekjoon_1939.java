import java.util.*;


class Edge{
    int spot;
    int weight;

    Edge(int spot, int weight){
        this.spot = spot;
        this.weight = weight;
    }
    
}
class Main
{
    static final int MAX = 987654321;
    static ArrayList<Edge>[] edges;
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        edges = new ArrayList[N+1];

        for(int i=0; i<=N; i++)
            edges[i] = new ArrayList<>();

        int min = MAX, max = 0;
        for(int i=0; i<M; i++){
            int A = sc.nextInt();
            int B = sc.nextInt();
            int C = sc.nextInt();
                    
            min = Math.min(min, C);
            max = Math.max(max, C);

            edges[A].add(new Edge(B,C));
            edges[B].add(new Edge(A,C));
            
           
        }
        int A = sc.nextInt();
        int B = sc.nextInt();

        System.out.println(binarySearch(min,max,N,A,B));


        
       
    }
    static int binarySearch(int left, int right, int N, int A, int B){
        int ans = 0;
        while(left <= right){
            int mid = (left + right) / 2;

            if(bfs(N,A,B,mid)){
                ans = mid;
                left = mid + 1;
            }else
                right = mid - 1;

        }
        return ans;
    }
    static boolean bfs(int N, int A, int B, int v){
        Queue<Edge> queue = new LinkedList<>();
        boolean check[] = new boolean[N+1];
        check[A] = true;
        queue.add(new Edge(A,987654321));

        while(!queue.isEmpty()){
            Edge cur = queue.poll();

            for(int i=0; i<edges[cur.spot].size(); i++){
                Edge nxt = edges[cur.spot].get(i);
                if(check[nxt.spot] || nxt.weight < v)
                    continue;
                if(nxt.spot == B){
                    return true;
                }
                check[nxt.spot] = true;
                queue.add(new Edge(nxt.spot, Math.min(cur.weight, nxt.weight)));


            }
        }
        return false;
    }
   
}