import java.util.*;


class baekjoon_1173{
    
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);  
        
        int N = sc.nextInt();
        int m = sc.nextInt();
        int M = sc.nextInt();
        int T = sc.nextInt();
        int R = sc.nextInt();

        int t = 0;
        int count = 0;
        int cur_m = m;

        while(true){
            t++;
            if(cur_m + T <= M){
                cur_m += T;
                count++;
                if(count == N){
                    System.out.println(t);
                    return;
                }
            }else{
                cur_m = cur_m-R<m ? m : cur_m-R;
                if(cur_m==m && m+T>M){
                    System.out.println(-1);
                    return;
                }
                
            }
        }
    }
}
