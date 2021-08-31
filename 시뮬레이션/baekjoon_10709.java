import java.util.*;



class Location{
    int y;
    int x;
    Location(int y,int x){
        this.y = y;
        this.x = x;
    }
}
class baekjoon_10709{
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);  
        
        String input[] = sc.nextLine().split(" ");
        int R = Integer.parseInt(input[0]);
        int C = Integer.parseInt(input[1]);

        int time[][] = new int[R][C];
        Queue<Location> list = new LinkedList<>();
        for(int i=0; i<R; i++)
            Arrays.fill(time[i], -1);

        for(int i=0; i<R; i++){
            input = sc.nextLine().split("");

            for(int j=0; j<C; j++){
                if(input[j].equals("c")){
                    list.add(new Location(i,j));
                    time[i][j] = 0;
                    
                }
            }
        }
        solve(R,C,list,time);
        print(R,C,time);
    }
    static void solve(int R,int C,Queue<Location>list,int time[][]){
        int m = 0;
        
        while(true){
            m++;
            for(int i=0,size=list.size(); i<size;i++){
                Location loc = list.poll();
                int x = loc.x + 1;
                if(x < C){
                    list.add(new Location(loc.y,x));
                    if(time[loc.y][x] == -1)
                        time[loc.y][x] = m;
                    else if(time[loc.y][x] > -1){
                        time[loc.y][x] = Math.min(time[loc.y][x],m);
                    }
                }
            }
            if(list.size() == 0)
                break;
            
        }
    }
        
    static void print(int R,int C,int time[][]){
        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++)
                System.out.print(time[i][j]+" ");
            System.out.println();
        }
    }
}

