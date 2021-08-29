import java.util.*;

class baekjoon_16918{
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);  
        
        String input[] = sc.nextLine().split(" ");
        int R = Integer.parseInt(input[0]);
        int C = Integer.parseInt(input[1]);
        int N = Integer.parseInt(input[2]);

        int map[][] = new int[R][C];

        for(int i=0; i<R; i++){
            input = sc.nextLine().split("");
            for(int j=0; j<C; j++){
                if(input[j].equals("O")){
                    map[i][j] = 0;
                }else{
                    map[i][j] = -1;
                }
                
            }
        }

        getMapState(map,N);
        print(map);
    }

    static void getMapState(int map[][], int N){
        //0초엔 일부칸에 폭탄 설치
        //1초엔 아무것도 하지 않는다

        int s = 1;
        while(s < N){
            s++;
            if(s % 2 == 0){
                //폭탄이 설치되지 않은 모든칸에 폭탄 설치
                setUpBomb(s,map);
            }else{
                //1초가 지난후 3초전에 설치된 폭탄이 모두 폭발한다.
                setOffBomb(s-3,map);
            }
        }
    }

    
    static void setUpBomb(int s,int map[][]){
        int R = map.length;
        int C = map[0].length;

        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                if(map[i][j] == -1){
                    map[i][j] = s;
                    
                }
            }
        }
        
    }
    static void setOffBomb(int s,int map[][]){
        int dy[] = {-1,1,0,0};
        int dx[] = {0,0,-1,1};
        int R = map.length;
        int C = map[0].length;
        int temp[][] = new int[R][C];
        for(int i=0; i<R; i++)
            temp[i] = map[i].clone();
        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                if(map[i][j] == s){
                    temp[i][j] = -1;
                    for(int k=0; k<4; k++){
                        int y = i + dy[k];
                        int x = j + dx[k];
                        if(y < 0 || x < 0 || y >= R || x >= C){
                            continue;
                        }
                        temp[y][x] = -1;
                    }
                }
            }
        }
        
        for(int i=0; i<R; i++)
            map[i] = temp[i].clone();
    }
    static void print(int map[][]){
        int R = map.length;
        int C = map[0].length;
        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                if(map[i][j] == -1)
                    System.out.print(".");
                else
                    System.out.print("O");
            }
            System.out.println();
        }
    }
}

