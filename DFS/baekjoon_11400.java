import java.util.*;


class Edge{
    int a;
    int b;
    Edge(int a, int b){
        this.a = Math.min(a,b);
        this.b = Math.max(a,b);
    }
}
class baekjoon_11400{
    static int order = 1;
    static ArrayList<Edge> ans = new ArrayList<>();
    static ArrayList<Integer> edges[];
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);

        int V = sc.nextInt();
        int E = sc.nextInt();

        edges = new ArrayList[V+1];
        
        for(int i=1; i<=V; i++){
            edges[i] = new ArrayList<>();
        }

        for(int i=0; i<E; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();

            edges[a].add(b);
            edges[b].add(a);
        }


        int orders[] = new int[V+1];


       
        dfs(1,1,orders);
        Collections.sort(ans, new Comparator<Edge>(){
            @Override
            public int compare(Edge o1, Edge o2) {
                if(o1.a < o2.a)
                    return -1;
                else if(o1.a > o2.a)
                    return 1;
                else{
                    if(o1.b < o2.b)
                        return -1;
                    else if(o1.b > o2.b)
                        return 1;
                    else
                        return 0;
                }
            }
        });
        System.out.println(ans.size());
        
        for(int i=0; i<ans.size(); i++){
            System.out.println(ans.get(i).a + " " + ans.get(i).b);
        }
    }

    //순서, 숫자
    static int dfs(int parent, int cur, int orders[]){
        orders[cur] = order++;
        int min = orders[cur];
        for(int i=0; i<edges[cur].size(); i++){

            int nxt = edges[cur].get(i);


            //돌아온길 다시 돌아가면 continue;
            if(parent == nxt)continue;
           
            //이미 구한값이라면
            if(orders[nxt] != 0){
                min = Math.min(min, orders[nxt]);
                continue;
            }

            //구하지 않은 값이라면
            int ret = dfs(cur, nxt, orders);

            if(ret > orders[cur]){
                //해당 간선이 단절선이다.
                ans.add(new Edge(cur, nxt));
            }
            min = Math.min(min, ret);
        }
        return orders[cur] = min;
    }
}