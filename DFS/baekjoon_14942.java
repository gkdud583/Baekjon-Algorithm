import java.io.*;
import java.util.*;

class Section{
    int s;
    int e;
    int w;
    Section(int s, int e, int w){
        this.s = s;
        this.e = e;
        this.w = w;
    }
}
class Main{ 

    static int index[];
    static int subTreeIndex[][];
    static ArrayList<Integer> edge[];

    static int in = 1;

    static ArrayList<Section> list = new ArrayList<>();
    public static void main(String[]args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 

        String input[] = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        index = new int[N+1];
        subTreeIndex = new int[N+1][1];
        edge = new ArrayList[N+1];
        
        for(int i=1; i<=N; i++)
            edge[i] = new ArrayList<>();
        input = br.readLine().split(" ");
        for(int i=1; i<N; i++){
            edge[i+1].add(Integer.parseInt(input[i]));
            edge[Integer.parseInt(input[i])].add(i+1);
        }

        boolean check[] = new boolean[N+1];
        check[1] = true;
        index[1] = in++;
        findIndex(1, check);

        check = new boolean[N+1];
        check[1] = true;
        findSubTreeIndex(1, check);

        for(int i=0; i<M; i++){
            //쿼리
            input = br.readLine().split(" ");
            int comm = Integer.parseInt(input[0]);
            int n = index[Integer.parseInt(input[1])];
            if(comm == 1){
                //1번 쿼리
                int w = Integer.parseInt(input[2]);

                list.add(new Section(n, subTreeIndex[n][0], w));

            }else{
                //2번 쿼리
                int sum = 0;
                for(int j=0; j<list.size(); j++){
                    Section sec = list.get(j);
                    if(n >= sec.s && n <= sec.e)
                        sum += sec.w;

                }
                System.out.println(sum);
            }

        }

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
    static int findSubTreeIndex(int i, boolean check[]){
        subTreeIndex[index[i]][0] = index[i];
        for(int j=0; j<edge[i].size(); j++){
            int nxt = edge[i].get(j);
            if(check[nxt])
                continue;
            check[nxt] = true;
            subTreeIndex[index[i]][0] = findSubTreeIndex(nxt, check);

        }
        return subTreeIndex[index[i]][0]; //끝 인덱스를 리턴
    }
}