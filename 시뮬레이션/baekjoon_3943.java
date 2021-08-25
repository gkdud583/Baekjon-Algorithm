import java.util.*;


class baekjoon_3943{
    
    static int solve(int n){

        int max = n;
        while(n != 1){
            if(n % 2 == 0)
                n/=2;
            else
                n=n*3+1;
            max = Math.max(max, n);
        }

        return max;

    }
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);    

        int t = sc.nextInt();

        for(int i=0; i<t; i++){
            int n = sc.nextInt();
            System.out.println(solve(n));
        }
        
    }
}
