

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;




class baekjoon_1325{
    static int arr[];
    static int max = 0;
    public static void main(String[]args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<Integer> relationship[] = new ArrayList[N+1];

        for(int i=0; i<=N; i++){
            relationship[i] = new ArrayList<>();
        }
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            //a가 b를 신뢰한다.
            relationship[a].add(b);
        }

        arr = new int[N+1];

        for(int i=1; i<=N; i++){
            bfs(i, N, relationship);
            
        }

        int max = 0;
        for(int i=1; i<=N; i++){
            max = Math.max(max, arr[i]);
        }
        
        for(int i=1; i<=N; i++){
            if(arr[i] == max){
                bw.write(i + " ");
            }
        }
        
        bw.flush();
    }
    static void bfs(int start, int N, ArrayList<Integer>relationship[]){
      
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);

        boolean check[] = new boolean[N+1];
        check[start] = true;


        while(!queue.isEmpty()){
            int cur = queue.poll();

            for(int i=0; i<relationship[cur].size(); i++){
                int nxt = relationship[cur].get(i);

                if(!check[nxt]){
                    arr[nxt]++;
                    check[nxt] = true;
                    queue.add(nxt);
                }
            }
        }
      

    }
}