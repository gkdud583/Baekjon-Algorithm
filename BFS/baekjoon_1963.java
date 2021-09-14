import java.util.*;

class baekjoon_1963{
    static final int SIZE = 10000;
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);  

        StringBuffer sb = new StringBuffer();
        int T = Integer.parseInt(sc.nextLine());
        for(int i=0; i<T; i++){
            String input[] = sc.nextLine().split(" ");
            String a = input[0];
            String b = input[1];
            if(a.equals(b))
                sb.append("0" + "\n");
            else{
                int ret = solve(a,b);
                sb.append(ret == 0 ? "Impossible" : ret).append("\n");
            }
        }
        System.out.println(sb);

    }
    static int solve(String a, String b){
        int numA = Integer.parseInt(a);
        int numB = Integer.parseInt(b);
        
        Queue<String> queue = new LinkedList<>();
        boolean check[] = new boolean[SIZE + 1];

        queue.add(a);
        check[numA] = true;

        int count[] = new int[SIZE + 1];
        
        while(!queue.isEmpty()){
            String cur = queue.poll();
            
            for(int i=0; i<4; i++){
                if(findNum(queue, check, count, i, SIZE, cur, b))
                    return count[numB]; 

            }

        }
        return 0;
    }

        
    static boolean findNum(Queue<String> queue, boolean check[], int count[], int index, int size, String a, String b){

        for(int i=0; i<10; i++){
            String nextStr = a.substring(0, index) + i + a.substring(index+1);
            int nextNum = Integer.parseInt(nextStr);
            if(nextNum >= 1000 && nextNum <= size && !check[nextNum] && isPrimeNum(nextNum)){

                queue.add(nextStr);
                check[nextNum] = true;
                count[nextNum] = count[Integer.parseInt(a)] + 1;

                if(nextStr.equals(b))
                    return true;

            }
        }
        return false;
    }
    static boolean isPrimeNum(int num){
        for(int i=2; i<=Math.sqrt(num); i++){
            if(num % i == 0)
                return false;

        }
        return true;
    }
    
    
}