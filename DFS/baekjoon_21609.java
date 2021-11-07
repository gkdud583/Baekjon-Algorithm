import java.util.*;

class Location{
    int y;
    int x;
    Location(int y, int x){
        this.y = y;
        this.x = x;
    }
}
class BlockGroup{
    ArrayList<Location> blockList;
    Location standardBlock;
    int rainbowBlockCnt;
    int c;

    BlockGroup(ArrayList<Location> blockList, Location standardBlock, int rainbowBlockCnt, int c){
        this.blockList = blockList;
        this.standardBlock = standardBlock;
        this.rainbowBlockCnt = rainbowBlockCnt;
        this.c = c;
    }
}
class baekjoon_21609{
    static final int DY[] = {-1,1,0,0};
    static final int DX[] = {0,0,-1,1};
    static BlockGroup bg;
    static boolean isBlockGroup = false;

    static int map[][];
    public static void main(String[]args) throws Exception{
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        
        map = new int[N][N];

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                map[i][j] = sc.nextInt();
            }
        }

        System.out.println(solve(N));

    }
    static int solve(int N){
        int score = 0;
        while(true){
            findBlockGroup(N);
            if(!isBlockGroup) //블록그룹이 있는지 확인
                return score;
            score += removeBlockGroup(); //점수를 리턴

            dropBlock(N); //격자에 중력이 작용
            map = rotateBlock(N); ///격자가 90도 반시계 방향으로 회전
            dropBlock(N); //다시 격자에 중력이 작용
        }
    }
    static void findBlockGroup(int N){

        isBlockGroup = false;
        bg = new BlockGroup(new ArrayList<>(), new Location(N-1,N-1), 0, -2); 
        boolean globalCheck[][] = new boolean[N][N];

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(map[i][j] >= 0){
                    boolean check[][] = new boolean[N][N];
                    ArrayList<Location> blockList = new ArrayList<>();
                    blockList.add(new Location(i,j));
                    check[i][j] = true;
                    if(map[i][j] > 0){
                        globalCheck[i][j] = true;
                        dfs(N,i,j,new BlockGroup(blockList, new Location(i,j), 0, map[i][j]),globalCheck, check);
                    }else if(map[i][j] == 0)
                        dfs(N,i,j,new BlockGroup(blockList, new Location(N-1,N-1), 1, -2),globalCheck,check);
                    
                }
            }
        }
    }
    static void dfs(int N, int y, int x, BlockGroup group, boolean globalCheck[][],  boolean check[][]){
        for(int i=0; i<4; i++){
            int ny = y + DY[i];
            int nx = x + DX[i];

            if(ny < 0 || nx < 0 || ny >= N || nx >= N || globalCheck[ny][nx] || check[ny][nx])
                continue;
            //검정블록 또는 빈 곳 이거나 일반블록인데 색깔이 다른경우
            if(map[ny][nx] < 0 || (group.c != -2 && map[ny][nx] != 0 && group.c != map[ny][nx]))
                continue;
            if(map[ny][nx] > 0){
                //일반 블록
                group.c = map[ny][nx];
                globalCheck[ny][nx] = true;
                //기준블록 갱신
                if(group.standardBlock.y > ny)
                    group.standardBlock = new Location(ny,nx);
                else if(group.standardBlock.y == ny && group.standardBlock.x > nx)
                    group.standardBlock = new Location(ny,nx);
            }
            if(map[ny][nx] == 0){
                //무지개 블록
                group.rainbowBlockCnt++;
            }

            check[ny][nx] = true;
            group.blockList.add(new Location(ny,nx));
            dfs(N, ny, nx, group, globalCheck, check);
          

        }
        //더이상 갈 블록이 없다.
        if(group.blockList.size() >=2 && group.c != -2){
            //하나의 블록 그룹을 찾았음.
            isBlockGroup = true;
            
            if(group.blockList.size() > bg.blockList.size()){
                bg = group;
            }
            else if(group.blockList.size() == bg.blockList.size()){
                if(group.rainbowBlockCnt > bg.rainbowBlockCnt){
                    bg = group;
                }
                else if(group.rainbowBlockCnt == bg.rainbowBlockCnt){
                    if(group.standardBlock.y > bg.standardBlock.y){
                        bg = group;
                    }
                    else if(group.standardBlock.y == bg.standardBlock.y){
                        if(group.standardBlock.x > bg.standardBlock.x){
                            bg = group;
                        }
                    }
                }
            }  
        }


    }
    static int removeBlockGroup(){
        //빈칸이면 -2
        int count = bg.blockList.size();
        for(int i=0; i<bg.blockList.size(); i++){
            Location nxt = bg.blockList.get(i);
            map[nxt.y][nxt.x] = -2;
        }
        return count * count;

    }
    static void dropBlock(int N){
        for(int i=N-1; i>=0; i--){ //열
            for(int j=N-1; j>=0; j--){ //행
                //검은블록x, 빈 칸도 아님
                if(map[j][i] != -1 && map[j][i] != -2){
                    for(int k=j+1; k<N; k++){ //행
                        if(map[k][i] != -2) //빈칸이 아니면
                            break;
                        map[k][i] = map[k-1][i];
                        map[k-1][i] = -2;
                    }
                }
            }
        }

    }
    static int[][] rotateBlock(int N){
        //격자가 90도 반시계 방향으로 회전한다.
        int tempMap[][] = new int[N][N];

        int c = N-1;
        int r = 0;
        for(int i=0; i<N; i++){
            r = 0;
            for(int j=0; j<N; j++){
                tempMap[i][j] = map[r++][c];
            }
            c--;
        }
        return tempMap;
    }
}

