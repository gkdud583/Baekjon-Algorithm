import java.util.*;

class baekjoon_14470{
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);  
        
        int A = sc.nextInt();
        int B = sc.nextInt();
        int C = sc.nextInt();
        int D = sc.nextInt();
        int E = sc.nextInt();
        boolean isFreeze = A > 0 ? false : true;
        int cur = A;
        int t = 0;
        while(true){
            if(cur == B)
                break;
            if(cur == 0 && isFreeze){
                t += D;
                isFreeze = false;
            }
            else if(cur < 0){
                t += C;
                cur += 1;

            }else if(!isFreeze){
                t += E;
                cur += 1;
            }
        }
        System.out.println(t);
    }
}

