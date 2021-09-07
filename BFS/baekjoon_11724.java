import java.util.*;



class baekjoon_11724{
    

    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);  

        int N = sc.nextInt();
        int M = sc.nextInt();

        ArrayList<Integer> edges[] = new ArrayList[N+1];
        for(int i=1; i<=N; i++){
            edges[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();

            //무방향 간선
            edges[a].add(b);
            edges[b].add(a);

        }
        System.out.println(solve(N, edges));
    }
    static int solve(int N, ArrayList<Integer>edges[]){
        int count = 0;
        boolean check[] = new boolean[N+1];
        
        for(int i=1; i<=N; i++){
            if(!check[i]){
                count++;
                bfs(i, edges, check);
            }
        }
        return count;
    }
    static void bfs(int start, ArrayList<Integer> edges[], boolean check[]){
        Queue<Integer> queue = new LinkedList<>();

        queue.add(start);
        check[start] = true;

        while(!queue.isEmpty()){
            int p = queue.poll();
            for(int i=0; i<edges[p].size(); i++){
                int nxt = edges[p].get(i);
                if(check[nxt])
                    continue;
                check[nxt] = true;
                queue.add(nxt);
            }
        }

    }
    
}

