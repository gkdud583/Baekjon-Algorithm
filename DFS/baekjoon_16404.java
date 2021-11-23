import java.io.*;
import java.util.*;





class Main{
    static final int MAX = 100001;
    static int index[]; //노드의 인덱스를 bfs방문 인덱스로 저장
    static int section[]; //노드가 가지는 범위의 end인덱스
    static int segTree[]; //세그먼트 트리 배열
    static int lazy[]; // lazy propagation을 위한 배열
    static ArrayList<Integer> edge[]; //에지 정보를 저장할 배열

    static int in = 1;
    public static void main(String[]args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        String input[] = br.readLine().split(" ");

        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);


        init(N);
        

        input = br.readLine().split(" ");
        
        for(int i=1; i<N; i++){
            int n = Integer.parseInt(input[i]);
            edge[n].add(i+1);
        }

        index[1] = in++;
        boolean check[] = new boolean[N+1];
        check[1] = true;
        findIndex(1, check);

        check = new boolean[N+1];
        check[1] = true;

        findSection(1, check);

        for(int i=0; i<M; i++){
            input = br.readLine().split(" ");

            int comm = Integer.parseInt(input[0]);
            int n = Integer.parseInt(input[1]);

            if(comm == 1){
                //직원 i가 w만큼 이익/손해를 본다.
                int w = Integer.parseInt(input[2]);
                updateSection(1, 1, N, index[n], section[index[n]], w);
            }else{                
                sb.append(sum(1, 1, N, index[n])).append("\n");
            }


            
        }
        System.out.println(sb);

    }
    static void init(int N){
        edge = new ArrayList[N+1];
        index = new int[N+1];
        section = new int[N+1];
        for(int i=1; i<=N; i++)
            edge[i] = new ArrayList<>();

        int h = (int)Math.ceil(baseLog(N, 2));
        int tree_size = (int)Math.pow(2, h+1) - 1;
        segTree = new int[tree_size];
        lazy = new int[tree_size];
    }
    static double baseLog(double x, double base){
        return Math.log(x) / Math.log(base);
    }
    static void findIndex(int i, boolean check[]){
        for(int j=0; j<edge[i].size(); j++){
            int nxt = edge[i].get(j);

            if(check[nxt])
                continue;
            check[nxt] = true;
            index[nxt] = in++;
            findIndex(nxt, check);
        }
        
    }
    static int findSection(int i, boolean check[]){
        section[index[i]] = index[i];
        for(int j=0; j<edge[i].size(); j++){
            int nxt = edge[i].get(j);
            if(check[nxt])
                continue;
            check[nxt] = true;
            int end = findSection(nxt, check);
            section[index[i]] = Math.max(section[index[i]], end);
        }
        return section[index[i]];
    }

 
    static void propagate(int node, int start, int end){
        if(lazy[node] != 0){
            segTree[node] += (end-start+1) * lazy[node];
            if(start != end){
                lazy[node*2] += lazy[node];
                lazy[node*2+1] += lazy[node];
            }
            lazy[node] = 0;
        }
    }
    static void updateSection(int node, int start, int end, int i, int j, int diff){
        propagate(node, start, end);
        if(j < start || i > end)
            return;
        if(i <= start && end <= j){
            segTree[node] += (end-start+1)*diff;
            if(start != end){
                lazy[node*2] += diff;
                lazy[node*2+1] += diff;
            }
            return;
        }

        updateSection(node*2, start, (start+end)/2, i, j, diff);
        updateSection(node*2+1, (start+end)/2+1, end, i, j, diff);

        segTree[node] = segTree[node*2] + segTree[node*2+1];
    }
    static int sum(int node, int start, int end, int i){
        propagate(node, start, end);

        if(start > i || end < i)
            return 0;
        if(start == end)
            return segTree[node];
        return sum(node*2, start, (start+end)/2, i) + sum(node*2+1, (start+end)/2+1, end, i);

    }
}