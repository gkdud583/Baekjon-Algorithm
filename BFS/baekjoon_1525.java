import java.util.*;


class Move{
    int zero_y = 0;
    int zero_x = 0;
    int count;
    String mapStr;
    Move(int zero_y, int zero_x, int count, String mapStr){
        this.zero_y = zero_y;
        this.zero_x = zero_x;
        this.count = count;
        this.mapStr = mapStr;
    }
}
class Main
{

    static final int SIZE = 3;
    static final String ANSWER = "123456780";
    static Set<String> check = new HashSet<>();
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        int zero_y=0, zero_x=0;
        String mapStr = "";
        for(int i=0; i<SIZE; i++){
            for(int j=0; j<SIZE; j++){
                int num = sc.nextInt();
                if(num == 0){
                    zero_y = i;
                    zero_x = j;
                }
                mapStr += num;
            }

        }
        if(mapStr.equals(ANSWER))
            System.out.println(0);
        else
            System.out.println(bfs(zero_y, zero_x, mapStr));

    }
    static int bfs(int zero_y, int zero_x, String mapStr){
        Queue<Move> queue = new LinkedList<>();
        queue.add(new Move(zero_y, zero_x, 0, mapStr));
        check.add(mapStr);

        int dy[] = {-1,1,0,0};
        int dx[] = {0,0,-1,1};
        
        while(!queue.isEmpty()){
            Move cur = queue.poll();
            for(int i=0; i<4; i++){
                int y = cur.zero_y + dy[i];
                int x = cur.zero_x + dx[i];

                if(y < 0 || x < 0 || y >= SIZE || x >= SIZE)    
                    continue;
                

                String tempMapStr = swap(cur.mapStr, cur.zero_y*3+cur.zero_x, 3*y+x);
                if(check.contains(tempMapStr))
                    continue;
                if(tempMapStr.equals(ANSWER))
                    return cur.count + 1;
                check.add(tempMapStr);
                queue.add(new Move(y,x,cur.count+1,tempMapStr));
            }
        }
        return -1;

    }
    
    static String swap(String mapStr, int sIn, int dIn){
        StringBuilder sb = new StringBuilder (mapStr);
        char s = sb.charAt(sIn);
        char d = sb.charAt(dIn);

        sb.setCharAt(sIn, d);
        sb.setCharAt(dIn, s);

        return sb.toString();

    }
    
   
    
}