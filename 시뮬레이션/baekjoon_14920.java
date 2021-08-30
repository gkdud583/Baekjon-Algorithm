import java.util.*;

class baekjoon_14920{
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);  
        
        int answer = 1;
        int n = sc.nextInt();
        while(true){
            if(n == 1){
                break;
            }
            if(n % 2 == 0){
                n = n / 2;
            }else{
                n = 3 * n + 1;
            }
            answer++;
            
        }
        System.out.println(answer);
    }
}

