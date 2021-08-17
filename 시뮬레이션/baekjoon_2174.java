

import java.util.*;


class Robot{

    int y;
    int x;
    int d; //0은 N,1은 E, 2는 S, 3은 W

    Robot(int y, int x, int d){

        this.y = y;
        this.x = x;
        this.d = d;
    }

    @Override
    public boolean equals(Object obj) {
        Robot robot = (Robot)obj;
        if(robot.y == this.y && robot.x == this.x)
            return true;
        return false;
    }

}


class baekjoon_2174{
    static  StringBuffer SB = new StringBuffer();

    static ArrayList<Robot> ROBOT_LIST = new ArrayList<>();
    static final int DIRECTION_SIZE = 4;


    static boolean moveForward(int r, int c, int robotNum, Robot robot){
        //0은 N,1은 E, 2는 S, 3은 W
        int dy[] = {1,0,-1,0};
        int dx[] = {0,1,0,-1};
        
        int y = robot.y + dy[robot.d];
        int x = robot.x + dx[robot.d];
        if(y < 1 || x < 1 || y > r || x > c){
            SB.append("Robot " + robotNum +" crashes into the wall" + "\n");
            return false;
        }else{
            Robot f = new Robot(y,x,0);
            if(ROBOT_LIST.contains(f)){
                int Y = ROBOT_LIST.indexOf(f) + 1;
                SB.append("Robot " + robotNum + " crashes into robot " + Y + "\n");

                return false;
            }
        }
        robot.y = y;
        robot.x = x;
        return true;
       
    }
    static void rotateLeft(Robot robot){
        for(int i=0; i<3; i++)
            rotateRight(robot);
        
    }
    static void rotateRight(Robot robot){
        int d = (robot.d + 1) % DIRECTION_SIZE;
        robot.d = d;
    }
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        String direction[] = {"N","E","S","W"};

        String input[] = sc.nextLine().split(" ");
        int a = Integer.parseInt(input[0]); //가로
        int b = Integer.parseInt(input[1]); //세로
        
        input = sc.nextLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        for(int i=0; i<n; i++){
            input = sc.nextLine().split(" ");
            int x = Integer.parseInt(input[0]);
            int y = Integer.parseInt(input[1]);
            String d = input[2];
            int dir = 0;
            for(int j=0; j<DIRECTION_SIZE; j++){
                if(d.equals(direction[j])){
                    dir = j;
                    break;
                }
            }
            ROBOT_LIST.add(new Robot(y,x,dir));
        }

        for(int i=0; i<m; i++){
            // 각각의 명령은 명령을 내리는 로봇, 명령의 종류(위에 나와 있는), 명령의 반복 회수로 나타낸다.
            // 각 명령의 반복 회수는 1이상 100이하이다.
            input = sc.nextLine().split(" ");
            
            int robotNum = Integer.parseInt(input[0]);
            String command = input[1]; 
            int count = Integer.parseInt(input[2]);

            //L: 로봇이 향하고 있는 방향을 기준으로 왼쪽으로 90도 회전한다.
            //R: 로봇이 향하고 있는 방향을 기준으로 오른쪽으로 90도 회전한다.
            //F: 로봇이 향하고 있는 방향을 기준으로 앞으로 한 칸 움직인다.
            Robot robot = ROBOT_LIST.get(robotNum-1);

            for(int j=0; j<count; j++){
                if(command.equals("L"))
                    rotateLeft(robot);
                else if(command.equals("R"))
                    rotateRight(robot);
                else{
                    //F
                    moveForward(b, a, robotNum, robot);
                    
                }
               
            }
        }
        if(SB.length() > 0){
            String output = SB.toString().split("\n")[0];
            System.out.println(output);
        }else
            System.out.println("OK");
        
    }    

}
