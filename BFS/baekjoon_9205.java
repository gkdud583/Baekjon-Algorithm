

import java.util.*;

class Move{
    int y;
    int x;
    int beerCount;
    ArrayList<Location> check;
    Move(int y, int x, int beerCount){
        this.y = y;
        this.x = x;
        this.beerCount = beerCount;
        check = new ArrayList<>();
    }
}
class Location{
    int y;
    int x;
    Location(int y, int x){
        this.y = y;
        this.x = x;
    }
    @Override
    public boolean equals(Object obj) {
        Location loc = (Location)obj;
        if(loc.y == this.y && loc.x == this.x)
            return true;
        return false;
    }
}
class baekjoon_9205{
    static Location home;
    static Location festival;
    static ArrayList<Location> list;
    static ArrayList<Location> check;
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        

        int T = sc.nextInt();
        for(int i=0; i<T; i++){
            int N = sc.nextInt();

            list = new ArrayList<>();
            check = new ArrayList<>();
            //상근이네 집
            home = new Location(sc.nextInt(), sc.nextInt());

            //편의점
            for(int j=0; j<N; j++){
                list.add(new Location(sc.nextInt(),sc.nextInt()));
            }

            //페스티벌
            festival = new Location(sc.nextInt(), sc.nextInt());
            list.add(new Location(festival.y,festival.x));

            bfs();



        }
    }    
    static void bfs(){
        Queue<Move> queue = new LinkedList<>();
        queue.add(new Move(home.y, home.x,20));

        while(!queue.isEmpty()){
            Move cur = queue.poll();

            for(int i=0; i<list.size(); i++){
                Location nxt = list.get(i);

                if(check.contains(nxt))
                    continue;
                int dist = Math.abs(cur.y - nxt.y) + Math.abs(cur.x - nxt.x);
                if(dist / 50.0 > cur.beerCount){
                    continue;

                }
                if(nxt.y == festival.y && nxt.x == festival.x){
                    System.out.println("happy");
                    return;
                }
                
                check.add(new Location(nxt.y,nxt.x));
                queue.add(new Move(nxt.y, nxt.x, 20));
            


            }
        }
        System.out.println("sad");
    }

}

