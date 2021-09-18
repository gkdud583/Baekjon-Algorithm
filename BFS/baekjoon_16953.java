

import java.util.*;

class Num{
    int i;
    int count;

    Num(int i, int count){
        this.i = i;
        this.count = count;
    }
}
class baekjoon_9205{
    static final int MAX = 1000000000;
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        
        int A = sc.nextInt();
        int B = sc.nextInt();

        System.out.println(bfs(A,B));
    }
    static int bfs(int A,int B){
        Queue<Num> queue = new LinkedList<>();
        queue.add(new Num(A, 0));

        boolean check[] = new boolean[MAX+1];
        while(!queue.isEmpty()){
            Num cur = queue.poll();
            
            String num1 = String.valueOf(cur.i) + "1";
            long parseNum = Long.parseLong(num1);
            

            if(parseNum <= B && !check[(int)parseNum])
            {
                if(parseNum == B)
                    return cur.count + 2;
                check[(int)parseNum] = true;
                queue.add(new Num((int)parseNum, cur.count + 1));
            }

            long num2 = cur.i * 2;
            if(num2 <= B && !check[(int)num2]){
                if(num2 == B)
                    return cur.count + 2;
                check[(int)num2] = true;
                queue.add(new Num((int)num2, cur.count + 1));
            }
               
            
            
        }
        return -1;
    }
    
       

}

