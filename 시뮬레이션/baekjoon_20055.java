import java.util.*;



class Location{
    int index;
    int y;
    int x;
    Location(int index, int y, int x){
        this.index = index;
        this.y = y;
        this.x = x;
    }
    @Override
    public boolean equals(Object obj) {
        Location loc = (Location)obj;
        if(loc.y == this.y && loc.x == this.x){
            return true;
        }
        return false;
    }
}
class baekjoon_20055{
    static ArrayList<Location> ROBOT_LIST = new ArrayList<>();
    static ArrayList<Location> LOC_LIST = new ArrayList<>(); 
    static int DURABILITY_ARR[][]; 

    static boolean checkCell(int n, int k){
        int count = 0;
        
        for(int i=0; i<2; i++){
            for(int j=0; j<n; j++){
                if(DURABILITY_ARR[i][j] == 0)
                    count++;
            }
        }
        return count >= k ? true : false;
    }

    static void putRobot(){

        if(DURABILITY_ARR[0][0] > 0){
            ROBOT_LIST.add(new Location(0, 0, 0));
            DURABILITY_ARR[0][0] -= 1;
        }
        
    }
    static boolean getRobot(int n){

        Location arrival = new Location(2, 0, n-1);
        if(ROBOT_LIST.contains(arrival)){
            ROBOT_LIST.remove(arrival);
            return true;
        }
        return false;
    }
    static void moveRobot(int n){

        for(int i=0, size = ROBOT_LIST.size(); i<size; i++){
            Location curRobot = ROBOT_LIST.get(i);
            Location nxtLoc  = LOC_LIST.get(curRobot.index + 1);
            
            if(DURABILITY_ARR[nxtLoc.y][nxtLoc.x] >= 1 &&
                    !ROBOT_LIST.contains(nxtLoc)){
                    
                    curRobot.y = nxtLoc.y;
                    curRobot.x = nxtLoc.x;
                    curRobot.index = nxtLoc.index;
                    DURABILITY_ARR[nxtLoc.y][nxtLoc.x] -= 1;

                    if(getRobot(n)){
                        i--;
                        size--;
                    }
            }
        }

    }
    static void moveConveyor(int n){
        //로봇 회전
        for(int i=0; i<ROBOT_LIST.size(); i++){
            Location curRobot = ROBOT_LIST.get(i);
            curRobot.index = curRobot.index + 1;
            curRobot.y = LOC_LIST.get(curRobot.index).y;
            curRobot.x = LOC_LIST.get(curRobot.index).x;
        }

        
        //컨베이어 벨트 회전
        int durabilityArrTemp[][] = new int[2][n];

        for(int i=0; i<n-1; i++){
            durabilityArrTemp[0][i+1] = DURABILITY_ARR[0][i];
        }
        
        durabilityArrTemp[1][n-1] = DURABILITY_ARR[0][n-1];
        
        for(int i=n-1; i>=1; i--){
            durabilityArrTemp[1][i-1] = DURABILITY_ARR[1][i];
        }

        durabilityArrTemp[0][0] = DURABILITY_ARR[1][0];

        DURABILITY_ARR = durabilityArrTemp;
        
       
        getRobot(n);
       
    }
    public static void main(String[]args) throws CloneNotSupportedException{
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        int k = sc.nextInt();

        int index = 0;
        for(int i=0; i<n; i++){
            LOC_LIST.add(new Location(index++,0,i));
        }
        for(int i=n-1; i>=0; i--){
            LOC_LIST.add(new Location(index++,1,i));
        }

        DURABILITY_ARR = new int[2][n];
        
        for(int i=0; i<n; i++){
            DURABILITY_ARR[0][i] = sc.nextInt();
        }
        for(int i=n-1; i>=0; i--){
            DURABILITY_ARR[1][i] = sc.nextInt();
        }
      

        for(int p=1;;p++){
            //컨베이어 이동
            moveConveyor(n);
            // 로봇 이동
            moveRobot(n);
            // 로봇 올림
            putRobot();
            // 종료 확인
            if(checkCell(n, k)){
                System.out.println(p);
                break;
            }

        }
        
    }    

}


