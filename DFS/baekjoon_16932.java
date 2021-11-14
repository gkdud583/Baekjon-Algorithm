import java.util.*;
import java.io.*;

class Location{
    int y;
    int x;
    Location(int y, int x){
        this.y = y;
        this.x = x;
    }
   
}
class Main{
    static final int DY[] = {-1,1,0,0};
    static final int DX[] = {0,0,-1,1};
    static int groupIndex = 1;
    static int groupSize[];
    static int group[][];
    static int map[][];
    static ArrayList<Location> zeroList = new ArrayList<>();
    
    public static void main(String[]args) throws Exception{

        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 

        String input[] = br.readLine().split(" ");

        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);


        map = new int[N][M]; 
        group = new int[N][M]; 
        groupSize = new int[N*M+1]; 

        for(int i=0; i<N; i++){
            input = br.readLine().split(" ");
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(input[j]);

                if(map[i][j] == 0)
                    zeroList.add(new Location(i,j));
            }
        }

        System.out.println(solve(N,M));

    }
    

    static int solve(int N, int M){
        int max = 0;

        
        boolean check[][] = new boolean[N][M];
        for(int i=0; i<zeroList.size(); i++){
            Location loc = zeroList.get(i);
            //상하좌우 그룹의 값을 더함
            HashSet<Integer> groupCheck = new HashSet<>();
            for(int k=0; k<4; k++){
                int y = loc.y + DY[k];
                int x = loc.x + DX[k];

                if(y < 0 || x < 0 || y >= N || x >= M)
                    continue;

                //이미 해당 그룹 값을 더해주었거나 주위에 있는 요소가 그룹요소가 아님
                if(map[y][x] == 0)
                    continue;

                //그룹 사이즈를 이전에 구한적이 없음
                if(!check[y][x]){
                    check[y][x] = true;
                    group[y][x] = groupIndex;

                    groupSize[groupIndex] = getGroupSize(N,M,y,x,check) + 1;
                    
                    
                    groupIndex++;
                }
                groupCheck.add(group[y][x]);

            }
            int sum = 1;
            for(int size : groupCheck)
                sum += groupSize[size];
            max = Math.max(max, sum); 
        }
        return max;
    }

    static int getGroupSize(int N, int M, int y, int x, boolean check[][]){
        int size = 0;
        for(int i=0; i<4; i++){
            int ny = y + DY[i];
            int nx = x + DX[i];

            if(ny < 0 || nx < 0 || ny >= N || nx >= M || check[ny][nx] || map[ny][nx] == 0)
                continue;
            
            check[ny][nx] = true;
            group[ny][nx] = groupIndex;
            size += getGroupSize(N, M, ny, nx, check) + 1;
            
        }
        return size;
    }
}