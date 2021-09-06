import java.util.*;



class baekjoon_1052{
    

    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);  

        int N = sc.nextInt();
        int K = sc.nextInt();

        int answer = 0;
        for(;; answer++){
            int tempN = N;
            int count = 0;
            while(tempN > 0){
                
                if(tempN % 2 > 0){
                    count++;
                }
                tempN /= 2;
            }
            if(count <= K)
            {
                break;
            }
            N++;
            
        }
        System.out.println(answer);
    }
    
}

