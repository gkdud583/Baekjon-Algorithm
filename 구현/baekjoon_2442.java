import java.util.*;

class baekjoon_2442{
   
    public static void main(String []args){
        Scanner sc = new Scanner(System.in);
        int n =sc.nextInt();
        int count = 1;
        for(int i=n-1; i>=0; i--){
            for(int j=0; j<i; j++)
                System.out.print(" ");
            for(int k=0; k<count; k++)
                System.out.print("*");
            count+=2;
            System.out.println();
        }

    }
}
