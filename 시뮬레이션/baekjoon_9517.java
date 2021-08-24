import java.util.*;



class baekjoon_9517{
   
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);

        int k = Integer.parseInt(sc.nextLine());
        int n = Integer.parseInt(sc.nextLine());

        boolean isBreak = false;
        int ans = k;
        int curPeople = k;
        int curTime  = 0;
        for(int i=0; i<n; i++){
            String input[] = sc.nextLine().split(" ");
            int t = Integer.parseInt(input[0]);
            String z = input[1];

            curTime += t;
            if(!isBreak && curTime >= 210){
                ans = curPeople;
                isBreak = true;
            }

            //T,N,P
            if(z.equals("T")){
                curPeople = curPeople == 8 ? 1 : curPeople + 1;
            }
       }
       System.out.println(ans);
    }
}