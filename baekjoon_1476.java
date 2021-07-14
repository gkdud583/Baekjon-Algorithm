import java.util.*;


class baekjoon_1476{
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        int E = sc.nextInt();
        int S = sc.nextInt();
        int M = sc.nextInt();
        int e=0,s=0,m=0;
        for(int year = 1; ;year++){
            e++;
            s++;
            m++;
            if(e > 15)
                e = 1;
            if(s > 28)
                s = 1;
            if(m > 19)
                m = 1;
            if(e == E && s == S && m == M){
                System.out.println(year);
                return;
            }

        }
    }
}