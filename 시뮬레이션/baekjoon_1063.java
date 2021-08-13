import java.util.*;

class Location{
    int y;
    int x;
    Location(int y,int x){
        this.y = y;
        this.x = x;
    }
}
class baekjoon_1063{
    static void moveking(String com, Location kingLoc, Location stoneLoc){
        String comArr[] = {"R","L","B","T","RT","LT","RB","LB"};
        int dy[] = {0,0,-1,1,1,1,-1,-1};
        int dx[] = {1,-1,0,0,1,-1,1,-1};

        for(int i=0; i<comArr.length; i++){
            if(com.equals(comArr[i])){
                int ky = kingLoc.y + dy[i];
                int kx = kingLoc.x + dx[i];

                if(ky < 1 || kx < 1 || ky > 8 || kx > 8){
                    return;
                }else if(stoneLoc.y == ky && stoneLoc.x == kx){
                    int sy = stoneLoc.y + dy[i];
                    int sx = stoneLoc.x + dx[i];
                    if(sy < 1 || sx < 1 || sy > 8 || sx > 8){
                        return;
                    }
                    stoneLoc.y = sy;
                    stoneLoc.x = sx;
                }
                kingLoc.y = ky;
                kingLoc.x = kx;
            }
        }
    }

    
    public static void main(String[]args) throws CloneNotSupportedException{
        Scanner sc = new Scanner(System.in);
        

        String input[] = sc.nextLine().split(" ");
        int x = input[0].charAt(0) - 64;
        int y = input[0].charAt(1) - 48;

        Location kingLoc = new Location(y,x);
        
        x = input[1].charAt(0) - 64;
        y = input[1].charAt(1) - 48;

        Location stoneLoc = new Location(y,x);
        
        int n = Integer.parseInt(input[2]);

        for(int i=0; i<n; i++){
            String com = sc.nextLine();
            moveking(com, kingLoc, stoneLoc);
        }
        System.out.println(String.valueOf((char)(kingLoc.x + 64))+kingLoc.y);
        System.out.println(String.valueOf((char)(stoneLoc.x + 64))+stoneLoc.y);




    }
    

}


