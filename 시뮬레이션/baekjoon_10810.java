import java.util.*;



class baekjoon_10810{
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        int m = sc.nextInt();

        int basket[] = new int[n+1];

        for(int l=0; l<m; l++){
            int i = sc.nextInt();
            int j = sc.nextInt();
            int k = sc.nextInt();

            for(int a=i; a<=j; a++){
                basket[a] = k;
            }
        }

        for(int i=1; i<=n; i++){
            System.out.print(basket[i]+" ");
        }
    }    

}
