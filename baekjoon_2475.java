import java.util.*;

class baekjoon_2475{
   
    public static void main(String []args){
        Scanner sc = new Scanner(System.in);
        int num[] = new int[5];
        int serial = 0;
        for(int i=0; i<num.length; i++){
            num[i] = sc.nextInt();
            serial += Math.pow(num[i],2.0);
        }
        System.out.println(serial % 10);
        
        

    }
}