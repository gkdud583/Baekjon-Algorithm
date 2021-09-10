import java.util.*;



class baekjoon_1389{
    
    
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);  

        int N = sc.nextInt();
        int M = sc.nextInt();

        ArrayList<Integer> relationship[] = new ArrayList[N+1];
        for(int i=1; i<=N; i++){
            relationship[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();

            if(!relationship[a].contains(b)){
                relationship[a].add(b);
                relationship[b].add(a);
            }
        }

        System.out.println(solve(N, relationship));
    }
    static int solve(int N,ArrayList<Integer> relationship[]){
        int min = 987654321;
        int people = 0;
        for(int i=1; i<=N; i++){
            int KB = bfs(i, N, relationship);
            if(min > KB){
                min = KB;
                people = i;
            }
        }
        return people;
    }
    static int bfs(int s, int N, ArrayList<Integer> relationship[]){
        boolean check[] = new boolean[N+1];
        check[s] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);

        int KB = 0;
        int count[] = new int[N+1];
        count[s] = 0;
        while(!queue.isEmpty()){
            int p = queue.poll();

            for(int i=0; i<relationship[p].size(); i++){
                int nxt = relationship[p].get(i);
                if(!check[nxt]){
                    queue.add(nxt);
                    check[nxt] = true;
                    count[nxt] = count[p] + 1;
                    KB += count[nxt];
                }
            }
        }
        return KB;
    }
}
