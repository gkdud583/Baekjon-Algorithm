import java.util.*;



class baekjoon_10813{
    
    public static void main(String[]args) throws CloneNotSupportedException{
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        int m = sc.nextInt();

        int basket[] = new int[n+1];

        for(int i=1; i<=n; i++)
            basket[i] = i;

        for(int i=0; i<m; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();

            int temp = basket[a];
            basket[a] = basket[b];
            basket[b] = temp;
        }

        for(int i=1; i<=n; i++){
            System.out.print(basket[i]+" ");
        }


    }
    

}


