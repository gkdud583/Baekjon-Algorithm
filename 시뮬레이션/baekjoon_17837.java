import java.util.*;


class Location{
    int index;
    int y;
    int x;
    int d;
    Location(int index,int y,int x,int d){
        this.index = index;
        this.y = y;
        this.x = x;
        this.d = d;
    }
    @Override
    public boolean equals(Object obj) {
        Location loc = (Location)obj;
        return loc.index == this.index ? true : false;
    }
}
class Chess{
    int c;
    ArrayList<Location> list = new ArrayList<>();

    Chess(int c){
        this.c = c;
    }
}


class baekjoon_17837{
    static Chess CHESS[][];

    static ArrayList<Location> PIECE_LOCATIOIN = new ArrayList<>();

    static boolean checkChess(int n){
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(CHESS[i][j].list.size() >= 4)
                    return true;
            }
        }
        return false;
    }
    static int movePiece(int n, int k){
        //1은 오른쪽, 2은 왼쪽, 3는 위, 4은 아래
        int dy[] = {0,0,0,-1,1};
        int dx[] = {0,1,-1,0,0};
  
        for(int i=1; i<=1000; i++){
            for(int j=0; j<k; j++){
                                
                Location cur = PIECE_LOCATIOIN.get(j);
              
                //이동하려는 칸
                int y = cur.y + dy[cur.d];
                int x = cur.x + dx[cur.d];

                //0은 흰색, 1은 빨간색, 2는 파란색

                if(y < 0 || x < 0 || y >= n || x >= n || CHESS[y][x].c == 2){
                    int index = CHESS[cur.y][cur.x].list.indexOf(cur);
                    CHESS[cur.y][cur.x].list.remove(cur);
                    PIECE_LOCATIOIN.remove(cur);

                    if(cur.d == 1)
                        cur.d = 2;
                    else if(cur.d == 2)
                        cur.d = 1;
                    else if(cur.d == 3)
                        cur.d = 4;
                    else
                        cur.d = 3;
        
                    y = cur.y + dy[cur.d];
                    x = cur.x + dx[cur.d];

                    CHESS[cur.y][cur.x].list.add(index,cur);
                    PIECE_LOCATIOIN.add(j, cur);
                    if(y < 0 || x < 0 || y >= n || x >= n || CHESS[y][x].c == 2){
                        if(checkChess(n)){
                            return i;
                        }
                        continue;
                    }
                }
                if(CHESS[y][x].c == 0){
                    //흰색인 경우

                    int size = CHESS[cur.y][cur.x].list.size();
                    for(int m=CHESS[cur.y][cur.x].list.indexOf(cur); m<size; size--){
                        Location nxt = CHESS[cur.y][cur.x].list.get(m);
                        CHESS[cur.y][cur.x].list.remove(m);

                        int index = PIECE_LOCATIOIN.indexOf(nxt);
                        PIECE_LOCATIOIN.remove(index);
                        
                        y = nxt.y + dy[cur.d];
                        x = nxt.x + dx[cur.d];
                        
                        Location newLoc = new Location(nxt.index, y, x, nxt.d);
                        CHESS[y][x].list.add(newLoc);
                        PIECE_LOCATIOIN.add(index, newLoc);
                    }


                }else if(CHESS[y][x].c == 1){
                    //빨간색
                    for(int m=CHESS[cur.y][cur.x].list.size()-1; m>=0; m--){
                        Location nxt  = CHESS[cur.y][cur.x].list.get(m);
                        CHESS[cur.y][cur.x].list.remove(m);

                        int index = PIECE_LOCATIOIN.indexOf(nxt);
                        PIECE_LOCATIOIN.remove(index);
                        
                        y = nxt.y + dy[cur.d];
                        x = nxt.x + dx[cur.d];
                        
                        Location newLoc = new Location(nxt.index, y, x, nxt.d);
                        CHESS[y][x].list.add(newLoc);
                        PIECE_LOCATIOIN.add(index, newLoc);

                        if(nxt.index == cur.index)
                            break;
                    }
                } 
                if(checkChess(n)){
                    return i;
                }
            } 
            
        }
        return -1;
    }
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        CHESS = new Chess[n][n];

       
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                CHESS[i][j] = new Chess(sc.nextInt());
            }
        }

        for(int i=0; i<k; i++){
            int r = sc.nextInt();
            int c = sc.nextInt();
            int d = sc.nextInt();
            CHESS[r-1][c-1].list.add(new Location(i+1,r-1,c-1,d));
            PIECE_LOCATIOIN.add(new Location(i+1,r-1,c-1,d));
        }

        System.out.println(movePiece(n,k));

    }
    

}


 
