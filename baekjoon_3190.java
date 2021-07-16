import java.util.*;
class Location{
    int y;
    int x;
    Location(int y,int x){
        this.y = y;
        this.x = x;
    }
}

class baekjoon_3190{
    //0은 북쪽,1 남쪽,2 동쪽 3서쪽
    static Queue<Location> body_info = new LinkedList<>(); 
    static HashMap<Integer,String> directionList = new HashMap<>();

    static int forward_y[] = {-1,1,0,0};
    static int forward_x[] = {0,0,1,-1};
    static int turn_left[] = {3,2,0,1};
    static int turn_right[] = {2,3,1,0};
    static int second = 0;
    //사과위치 1, 뱀 몸 2
    static void dfs(int head_y,int head_x,int d,int map[][]){
        second++;
        
        int n = map.length;
        int move_y = head_y + forward_y[d];
        int move_x = head_x + forward_x[d];

        //이동했는데 벽이거나 꼬리이면 끝.
        if(move_y >= n || move_x >= n || move_y < 0 || move_x < 0 || map[move_y][move_x] == 2)
            return;
        //머리 옮김.
        //이동한곳에 사과가 있으면 사과없어지고 꼬리 움직이지x
        //이동한곳에 사과가 없으면 꼬리 움직임
        if(map[move_y][move_x] != 1){
        
            Location tail = body_info.poll();
            int tail_y = tail.y;
            int tail_x = tail.x;
            map[tail_y][tail_x] = 0;
            
        }

        map[move_y][move_x] = 2;
        body_info.add(new Location(move_y,move_x));
        //다 이동하고 해당 초에 방향 전환이 있다면 방향 전환함.
        if(directionList.containsKey(second)){
            if(directionList.get(second).equals("L")){
                //왼쪽
                d = turn_left[d];
            }else{
                //오른쪽
                d = turn_right[d];
            }
        }
        dfs(move_y,move_x,d,map);

        
        
    }
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        int map[][] = new int[n][n];
        int k = Integer.parseInt(sc.nextLine());
        for(int i=0; i<k; i++){
            String input[] = sc.nextLine().split(" ");
            int y = Integer.parseInt(input[0]);
            int x = Integer.parseInt(input[1]);
            map[y-1][x-1] = 1; //사과가 있는 위치
        }
        int l = Integer.parseInt(sc.nextLine());
        for(int i=0; i<l; i++){
            String input[] = sc.nextLine().split(" ");
            int s = Integer.parseInt(input[0]);
            String d = input[1];
            directionList.put(s,d);
            
        }
        body_info.add(new Location(0,0));
        map[0][0] = 2;
        dfs(0,0,2,map);
        System.out.println(second);
   
    }
}