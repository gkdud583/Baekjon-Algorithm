import java.util.*;

public class baekjoon_3584 {
    static int parent[];
    static int ans = 0;
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);

        StringBuffer sb = new StringBuffer();
        int T = sc.nextInt();


        for(int i=0; i<T; i++){
            int N = sc.nextInt();
            parent = new int[N+1];    
            for(int j=0; j<N-1; j++){
                int A = sc.nextInt();
                int B = sc.nextInt();
                parent[B] = A;
            }
            int a = sc.nextInt();
            int b = sc.nextInt();

            solve(N,a,b);
            sb.append(ans).append("\n");
        }
        System.out.print(sb);
    }
    static void solve(int N, int a, int b){
        boolean check[] = new boolean[N+1];

        check[a] = true;

        findAncestorOfA(a, check);
        findAncestorOfB(b, check);
    }
    static void findAncestorOfA(int i, boolean check[]){
        if(parent[i] == 0)
            return;
        
        check[parent[i]] = true;
        findAncestorOfA(parent[i], check);
        
    }
    static void findAncestorOfB(int i, boolean check[]){
        if(parent[i] == 0)
            return;
        if(check[parent[i]]){
            ans = parent[i];
            return;
        }
        findAncestorOfB(parent[i], check);
    }
}
