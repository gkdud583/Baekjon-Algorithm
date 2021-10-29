import java.util.*;

class Location{
    int i;
    int x;
    int y;
    int r;
    Location(int i, int x, int y, int r){
        this.i = i;
        this.x = x;
        this.y = y;
        this.r = r;
    }
}
class baekjoon_10216{
    static ArrayList<Location> camps;
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        StringBuffer sb = new StringBuffer();
        int T = sc.nextInt();
        
        for(int i=0; i<T; i++){
            camps = new ArrayList<>();
            int N = sc.nextInt();
            for(int j=0; j<N; j++){
                int X = sc.nextInt();
                int Y = sc.nextInt();
                int R = sc.nextInt();

                camps.add(new Location(j,X,Y,R));
            }
            sb.append(solve()).append("\n");    
        }
        
        System.out.print(sb);


    }
    static int solve(){
        boolean check[] = new boolean[camps.size()];
        int cnt = 0;
        for(int i=0; i<camps.size(); i++){
            Location camp = camps.get(i);
            
            if(!check[camp.i]){
                check[camp.i] = true;
                dfs(camp, check);
                cnt++;
            }
           
        }
        return cnt;
    }
    static void dfs(Location camp, boolean check[]){


        for(int i=0; i<camps.size(); i++){
            Location otherCamp = camps.get(i);
            if(otherCamp.i == camp.i)
                continue;
            if(!check[otherCamp.i] && checkDist(camp, otherCamp)){
                check[otherCamp.i] = true;
                dfs(otherCamp, check);
            }
        }

    }
    static boolean checkDist(Location camp, Location otherCamp){
        return Math.sqrt(Math.pow(camp.x - otherCamp.x, 2) + Math.pow(camp.y - otherCamp.y, 2)) <= camp.r +otherCamp.r; 
    }
    
}