import java.util.*;


class baekjoon_10811{
    
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);  
        
        int n = sc.nextInt();
        int m = sc.nextInt();

        int basket[] = new int[n+1];

        for(int i=1; i<=n; i++)
            basket[i] = i;
        
        for(int i=0; i<m; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();

            int temp[] = basket.clone();

            for(int j=a,k=b; j<=b; j++){
                basket[j] = temp[k--];
            }
        }
        
        for(int i=1; i<=n; i++){
            System.out.print(basket[i]+" ");
        }
    }
}
