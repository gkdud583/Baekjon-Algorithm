import java.io.*;
import java.util.*;


  

public class baekjoon_1068 {
    static int[] parent;
    static boolean check[];
    static ArrayList<Integer> edges[];

    public static void main(String[]args) throws Exception{
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        edges = new ArrayList[N];
        parent = new int[N];
        check = new boolean[N];

        for(int i=0; i<N; i++){
            edges[i] = new ArrayList<>();
        }


        for(int i=0; i<N; i++){
            int p = sc.nextInt();
            if(p==-1){
                parent[i] = p;
                continue;
            }
            edges[p].add(i);
            parent[i] = p;
        }

        int removeNode = sc.nextInt();
        if(parent[removeNode] != -1)
            edges[parent[removeNode]].remove((Integer)removeNode);
        check[removeNode] = true;
        dfs(removeNode);

        System.out.println(getLeafNodeCnt(N));


    }

    static void dfs(int s){

        for(int i=0,size=edges[s].size(); i<size; i++){
            int nxt = edges[s].get(0);
            edges[parent[nxt]].remove((Integer)nxt);
            check[nxt] = true;

            dfs(nxt);
        }
    }

    static int getLeafNodeCnt(int N){
        int leafNodeCnt = 0;
        for(int i=0; i<N; i++){
            if(!check[i] && edges[i].size() == 0)
                leafNodeCnt++;
        }
        return leafNodeCnt;
    }
 
}