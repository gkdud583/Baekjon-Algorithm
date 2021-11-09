import java.util.*;
import java.util.function.Supplier;

public class baekjoon_14267 {
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();

        int superior[] = new int[N+1];

        int compliment[] = new int[N+1];

        for(int i=1; i<=N; i++){
            superior[i] = sc.nextInt();
        }

        for(int i=0; i<M; i++){
            int j = sc.nextInt();
            int w = sc.nextInt();

            compliment[j] += w;
        }


        boolean check[] = new boolean[N+1];
        check[1] = true;
        System.out.print(compliment[1]+" ");
        for(int i=2; i<=N; i++){
            if(check[i]){
                System.out.print(compliment[i]+" ");
            }else{
                if(!check[superior[i]]){
                    check[i] = true;
                    dfs(i, superior, compliment, check);
                }else{
                    check[i] = true;
                    compliment[i] += compliment[superior[i]];
                }
                System.out.print(compliment[i]+" ");
            }
           
        }

    }
    static int dfs(int i, int superior[], int compliment[], boolean check[]){
        if(!check[superior[i]]){
            check[superior[i]] = true;
            compliment[i] += dfs(superior[i], superior, compliment, check);
        }else{
            compliment[i] += compliment[superior[i]];
        }
        
        return compliment[i];
        
    }
}
