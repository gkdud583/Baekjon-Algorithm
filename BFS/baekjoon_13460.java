import java.util.*;


class State{
    int RY;
    int RX;
    int BY;
    int BX;
    int count;
    char map[][];

    State(int RY, int RX, int BY, int BX,int count,char map[][]){
        this.RY = RY;
        this.RX = RX;
        this.BY = BY;
        this.BX = BX;
        this.count = count;
        this.map = map;
    }
    @Override
    public boolean equals(Object obj) {
        State state = (State)obj;
        if(RY == state.RY && RX == state.RX &&  BY == state.BY && BX == state.BX)
            return true;
        return false;
    }
}
class baekjoon_13460{
    
    
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);  

        String input[] = sc.nextLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        char map[][] = new char[N][M];

        int RY=0,RX=0,BY=0,BX=0;
        int ZY=0,ZX=0;
        for(int i=0; i<N; i++){
            String line = sc.nextLine();
            for(int j=0; j<M; j++){
                if(line.charAt(j) == 'R'){
                    RY = i;
                    RX = j;
                }else if(line.charAt(j) == 'B'){
                    BY = i;
                    BX = j;
                }else if(line.charAt(j) == 'O'){
                    ZY = i;
                    ZX = j;
                }
                map[i][j] = line.charAt(j);
            }
        }

        System.out.println(bfs(ZY,ZX,RY,RX,BY,BX,N,M,map));

    }
    static int bfs(int ZY, int ZX, int RY, int RX, int BY, int BX, int N, int M,char map[][]){
        Queue<State> queue = new LinkedList<>();
        queue.add(new State(RY,RX,BY,BX,0,map));

        while(!queue.isEmpty()){
            State state = queue.poll();
            if(state.count >= 10)
                break;
            for(int j=0; j<4; j++){

                char tempMap[][] = coppyMap(N, M, state.map);
                int newLoc[] = tilt(j,ZY,ZX,state.RY,state.RX,state.BY,state.BX,N,M,tempMap);
                if(newLoc != null){
                    if(newLoc[0] == ZY && newLoc[1] == ZX){
                        return state.count + 1;
                    }
                    State newState = new State(newLoc[0],newLoc[1],newLoc[2],newLoc[3],state.count+1,tempMap);
                    if(!queue.contains(newState) && !state.equals(newState))
                        queue.add(newState);
                } 
            }
            
        }
        return -1;
    }
    static char[][] coppyMap(int N,int M,char map[][]){
        char coppyMap[][] = new char[N][M];

        for(int i=0; i<N; i++)
            coppyMap[i] = map[i].clone();

        return coppyMap;
    }
    static int[] tilt(int d,int ZY, int ZX, int RY, int RX, int BY, int BX, int N, int M,char map[][]){
        char tempMap[][] = new char[N][M];
        for(int i=0; i<N; i++)
            tempMap[i] = map[i].clone();

        int ret[] = new int[4];
        ret[0] = RY;
        ret[1] = RX;
        ret[2] = BY;
        ret[3] = BX;

        //0은 위 1은 아래 2는 왼쪽 3은 오른쪽

        if(d == 0){
            if(RY < BY){
                for(int i=RY; i>=0; i--){
                    if(tempMap[i][RX] != '.' && tempMap[i][RX] != 'R'){
                        ret[0] = tempMap[i][RX] == 'O' ? i : i + 1;
                        tempMap[RY][RX] = '.';
                        if(ret[0] != ZY || RX != ZX){
                            tempMap[ret[0]][RX] = 'R';
                        }
                        break;
                        
                    }
                }
                for(int i=BY; i>=0; i--){
                    if(tempMap[i][BX] != '.' && tempMap[i][BX] != 'B'){

                        ret[2] = tempMap[i][BX] == 'O' ? i : i + 1;
                        tempMap[BY][BX] = '.';
                        if(ret[2] == ZY && BX == ZX){
                            return null;
                        }else{
                            tempMap[ret[2]][BX] = 'B';
                            break;

                        }
                        
                    }
                }

            }else{
                for(int i=BY; i>=0; i--){
                    if(tempMap[i][BX] != '.' && tempMap[i][BX] != 'B'){
                        ret[2] = tempMap[i][BX] == 'O' ? i : i + 1;
                        tempMap[BY][BX] = '.';
                        if(ret[2] == ZY && BX == ZX){
                            return null;
                        }else{
                            tempMap[ret[2]][BX] = 'B';
                            break;
                        }
                        
                    }
                }
                for(int i=RY; i>=0; i--){
                    if(tempMap[i][RX] != '.' && tempMap[i][RX] != 'R'){
                        ret[0] = tempMap[i][RX] == 'O' ? i : i + 1;
                        tempMap[RY][RX] = '.';
                        if(ret[0] != ZY || RX != ZX){
                            tempMap[ret[0]][RX] = 'R';
                        }
                        break;
                        
                    }
                }
            }
        }
        else if(d == 1){
            if(RY > BY){
                for(int i=RY; i<N; i++){
                    if(tempMap[i][RX] != '.' && tempMap[i][RX] != 'R'){
                        ret[0] = tempMap[i][RX] == 'O' ? i : i - 1;
                        tempMap[RY][RX] = '.';
                        if(ret[0] != ZY || RX != ZX){
                            tempMap[ret[0]][RX] = 'R';
                        }
                        break;
                    }
                }
                for(int i=BY; i<N; i++){
                    if(tempMap[i][BX] != '.' && tempMap[i][BX] != 'B'){
                        ret[2] = tempMap[i][BX] == 'O' ? i : i - 1;
                        tempMap[BY][BX] = '.';
                        if(ret[2] == ZY && BX == ZX){
                            return null;
                        }else{
                            tempMap[ret[2]][BX] = 'B';
                            break;

                        }
                        
                    }
                }

            }else{
                for(int i=BY; i<N; i++){
                    if(tempMap[i][BX] != '.' && tempMap[i][BX] != 'B'){
                        ret[2] = tempMap[i][BX] == 'O' ? i : i - 1;
                        tempMap[BY][BX] = '.';
                        if(ret[2] == ZY && BX == ZX){
                            return null;
                        }else{
                            tempMap[ret[2]][BX] = 'B';
                            break;

                        }
                        
                    }
                }
                for(int i=RY; i<N; i++){
                    if(tempMap[i][RX] != '.' && tempMap[i][RX] != 'R'){
                        ret[0] = tempMap[i][RX] == 'O' ? i : i - 1;
                        tempMap[RY][RX] = '.';
                        if(ret[0] != ZY || RX != ZX){
                            tempMap[ret[0]][RX] = 'R';
                        }
                        break;
                    }
                }
            }
        }
        else if(d == 2){
            if(RX < BX){
                for(int i=RX; i>=0; i--){
                    if(tempMap[RY][i] != '.' && tempMap[RY][i] != 'R'){
                        ret[1] = tempMap[RY][i] == 'O' ? i : i + 1;
                        tempMap[RY][RX] = '.';
                        if(ret[1] != ZX || RY != ZY){
                            tempMap[RY][ret[1]] = 'R';
                        }
                        break;
                    }
                }
                for(int i=BX; i>=0; i--){
                    if(tempMap[BY][i] != '.' && tempMap[BY][i] != 'B'){
                        ret[3] = tempMap[BY][i] == 'O' ? i : i + 1;
                        tempMap[BY][BX] = '.';
                        if(ret[3] == ZX && BY == ZY){
                            return null;
                        }else{
                            tempMap[BY][ret[3]] = 'B';
                            break;

                        }
                        
                    }
                }

            }else{
                for(int i=BX; i>=0; i--){
                    if(tempMap[BY][i] != '.' && tempMap[BY][i] != 'B'){
                        ret[3] = tempMap[BY][i] == 'O' ? i : i + 1;
                        tempMap[BY][BX] = '.';
                        if(ret[3] == ZX && BY == ZY){
                            return null;
                        }else{
                            tempMap[BY][ret[3]] = 'B';
                            break;

                        }
                        
                    }
                }
                for(int i=RX; i>=0; i--){
                    if(tempMap[RY][i] != '.' && tempMap[RY][i] != 'R'){
                        ret[1] = tempMap[RY][i] == 'O' ? i : i + 1;
                        tempMap[RY][RX] = '.';
                        if(ret[1] != ZX || RY != ZY){
                            tempMap[RY][ret[1]] = 'R';
                        }
                        break;
                    }
                }
            }
        }
        else if(d == 3){
            if(RX > BX){
                for(int i=RX; i<M; i++){
                    if(tempMap[RY][i] != '.' && tempMap[RY][i] != 'R'){
                        ret[1] = tempMap[RY][i] == 'O' ? i : i - 1;
                        tempMap[RY][RX] = '.';
                        if( ret[1] != ZX || RY != ZY){
                            tempMap[RY][ ret[1]] = 'R';
                        }
                        break;
                    }
                }
                for(int i=BX; i<M; i++){
                    if(tempMap[BY][i] != '.' && tempMap[BY][i] != 'B'){
                        ret[3] = tempMap[BY][i] == 'O' ? i : i - 1;
                        tempMap[BY][BX] = '.';
                        if(ret[3] == ZX && BY == ZY){
                            return null;
                        }else{
                            tempMap[BY][ret[3]] = 'B';
                            break;

                        }
                        
                    }
                }

            }else{
                for(int i=BX; i<M; i++){
                    if(tempMap[BY][i] != '.' && tempMap[BY][i] != 'B'){
                        ret[3] = tempMap[BY][i] == 'O' ? i : i - 1;
                        tempMap[BY][BX] = '.';
                        if(ret[3] == ZX && BY == ZY){
                            return null;
                        }else{
                            tempMap[BY][ret[3]] = 'B';
                            break;

                        }
                        
                    }
                }
                for(int i=RX; i<M; i++){
                    if(tempMap[RY][i] != '.' && tempMap[RY][i] != 'R'){
                        ret[1] = tempMap[RY][i] == 'O' ? i : i - 1;
                        tempMap[RY][RX] = '.';
                        if(ret[1] != ZX || RY != ZY){
                            tempMap[RY][ret[1]] = 'R';
                        }
                        break;
                    }
                }
            }
        }
        for(int i=0; i<N; i++)
            map[i] = tempMap[i].clone();
        return ret;
    }
    
}

