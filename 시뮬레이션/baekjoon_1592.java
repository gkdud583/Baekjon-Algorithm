import java.util.*;



class baekjoon_1592{

    static int getCatcher(boolean isOdd, int n, int l, int cur){
        Deque<Integer> stack = new ArrayDeque<>();
        for(int i=1; i<=n; i++){
            stack.addLast(i);
        }
        int catcher = 0;
    
        if(isOdd){
            //시계
            while(true){
                int p = stack.pollFirst();
                stack.addLast(p);

                if(p == cur){
                    break;
                }
            }
            for(int i=0; i<l; i++){
                int p = stack.pollFirst();
                stack.addLast(p);
            }
            catcher = stack.peekLast();
        }else{
            //반시계
            while(true){
                int p = stack.pollLast();
                stack.addFirst(p);

                if(p == cur){
                    break;
                }
            }

            for(int i=0; i<l; i++){
                int p = stack.pollLast();
                stack.addFirst(p);

                
            }
            catcher = stack.peekFirst();

        }
        return catcher;
    }
    public static void main(String[]args) throws CloneNotSupportedException{
        Scanner sc = new Scanner(System.in);
        
        //1-n
        int n = sc.nextInt();
        int m = sc.nextInt();
        int l = sc.nextInt();

        int catcher = 1;
        int catchCount[] = new int[n+1];
        int total = 0;
        while(true){
            
            catchCount[catcher]++;
            if(catchCount[catcher] >= m){
                break;
            }
            if(catchCount[catcher] % 2 == 0){
                //반시계 L만큼
                catcher = getCatcher(false, n, l, catcher);
            }else{
                //시계 L만큼
                catcher = getCatcher(true, n, l, catcher);
            }
        
            total++;

        }
       

        System.out.println(total);
       
        
    }    

}
