import java.util.*;

class Ball{
    int r;
    int c;
    int m;
    int s;
    int d;

    Ball(int r, int c, int m, int s, int d){
        this.r = r;
        this.c = c;
        this.m = m;
        this.s = s;
        this.d = d;

    }
    @Override
    public boolean equals(Object obj) {
        Ball ball = (Ball)obj;
        if(ball.r == this.r && ball.c == this.c && 
            ball.m == this.m && ball.s == this.s && ball.d == this.d)
            return true;
        return false;
    }

}

class baekjoon_20056{
    static ArrayList<Ball> MAP[][];
    static ArrayList<Ball> BALL_LIST = new ArrayList<>();
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();
        int K = sc.nextInt();

        MAP = new ArrayList[N][N];
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++)
                MAP[i][j] = new ArrayList<>();
        }
        for(int i=0; i<M; i++){
            int r = sc.nextInt() - 1;
            int c = sc.nextInt() - 1;
            int m = sc.nextInt();
            int s = sc.nextInt();
            int d = sc.nextInt();

            BALL_LIST.add(new Ball(r,c,m,s,d));

        }
        System.out.println(getTotalMass(N, K));
    }

    static int getTotalMass(int N,int K){
        for(int i=0; i<K; i++){
            moveBall(N); //모든 파이어볼이 이동
            checkBall(N); //2개이상의 파이어볼이 있으면
        }
        
        int totalMess = 0;
        for(int i=0,size=BALL_LIST.size(); i<size; i++){
            totalMess += BALL_LIST.get(i).m;
        }
        return totalMess;
    }

    static void moveBall(int N){
        //모든 파이어볼이 이동

        int dy[] = {-1,-1,0,1,1,1,0,-1};
        int dx[] = {0,1,1,1,0,-1,-1,-1};
        for(int i=0,size=BALL_LIST.size(); i<size; i++){
            Ball ball = BALL_LIST.get(i);
            MAP[ball.r][ball.c].remove(ball);
            int y = (ball.r + N + dy[ball.d] * (ball.s % N)) % N;
            int x = (ball.c + N + dx[ball.d] * (ball.s % N)) % N;

            ball.r = y;
            ball.c = x;
            MAP[y][x].add(ball);
        }
    }

    static void checkBall(int N){
        int oddOrEven[] = {0,2,4,6};
        int other[] = {1,3,5,7};
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                int size = MAP[i][j].size();
                if(size >= 2){
                    boolean isOdd=false,isEven=false;
                    int m = 0, s = 0;
                    while(!MAP[i][j].isEmpty()){
                        Ball ball = MAP[i][j].get(0);
                        MAP[i][j].remove(ball);
                        BALL_LIST.remove(ball);
                        m += ball.m;
                        s += ball.s;
                        if(ball.d % 2 == 0)
                            isEven = true;
                        else
                            isOdd = true;
                      
                    }
                    m = m / 5;
                    s = s / size;
                    
                    if(m != 0){
                        for(int k=0; k<4; k++){
                            Ball newBall = null;
                            if(isEven && !isOdd || !isEven && isOdd){
                                newBall = new Ball(i,j,m,s,oddOrEven[k]);
                            }else{
                                newBall = new Ball(i,j,m,s,other[k]);
                            }
                            MAP[i][j].add(newBall);
                            BALL_LIST.add(newBall);
                        }
                    }
                    
                }
            }
        }
    }
}
