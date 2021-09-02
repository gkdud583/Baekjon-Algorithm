import java.util.*;


class baekjoon_8911{
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);  
        StringBuffer sb = new StringBuffer();
        int T = Integer.parseInt(sc.nextLine());
        for(int i=0; i<T; i++){
            String commands = sc.nextLine();
            int ret[] = move(commands);
            int min_y = ret[0];
            int min_x = ret[1];
            int max_y = ret[2];
            int max_x = ret[3];

            int size = (Math.abs(max_y - min_y)) * (Math.abs(max_x - min_x));
            sb.append(size+"\n");
        }

        System.out.println(sb);
    }
    static int[] move(String commands){
        //0은 북,1은 동,2는 남,3은 서
        int forward_dy[] = {1,0,-1,0};
        int forward_dx[] = {0,1,0,-1};


        int backward_dy[] = {-1,0,1,0};
        int backward_dx[] = {0,-1,0,1};

     
        int d = 0;
        int cur_y = 0;
        int cur_x = 0;

        
        int min_x = 0, max_x = 0;
        int min_y = 0, max_y = 0;
        for(int i=0; i<commands.length(); i++){
            char command = commands.charAt(i);
            if(command == 'L'){
                if(d == 0)
                    d = 3;
                else
                    d -= 1;
            }else if(command == 'R'){
                if(d == 3)
                    d = 0;
                else
                    d += 1;
            }else if(command == 'F'){
                cur_y = cur_y + forward_dy[d];
                cur_x = cur_x + forward_dx[d];

                min_x = Math.min(min_x,cur_x);
                max_x = Math.max(max_x,cur_x);

                min_y = Math.min(min_y,cur_y);
                max_y = Math.max(max_y,cur_y);
            }else if(command == 'B'){
                cur_y = cur_y + backward_dy[d];
                cur_x = cur_x + backward_dx[d];

                min_x = Math.min(min_x,cur_x);
                max_x = Math.max(max_x,cur_x);

                min_y = Math.min(min_y,cur_y);
                max_y = Math.max(max_y,cur_y);
            }
        }
        int ret[] = new int[4];
        ret[0] = min_y;
        ret[1] = min_x;
        ret[2] = max_y;
        ret[3] = max_x;
        return ret;
    }
}

