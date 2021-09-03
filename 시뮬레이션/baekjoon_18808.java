import java.util.*;



class baekjoon_18808{
    static int[][] rotateSticker(int r, int R,int C,int sticker[][]){
        int temp[][] = new int[C][R];
        for(int i=0; i<C; i++){ //열
            int k = R - 1;
            for(int j=0; j<R; j++){ //행
                temp[i][k--] = sticker[j][i];
            }
            
            
            
        }
        return temp;
    }
    static boolean stickSticker(int N,int M,int sticker[][],int laptop[][]){
        for(int i=0; i<N; i++){ 
            for(int j=0; j<M; j++){ 
                if(isPossible(i,j,N,M,laptop,sticker)){
                    return true;
                }
            }
        }
        return false;
    }
    static boolean isPossible(int a,int b, int N, int M, int laptop[][],int sticker[][]){
        
        int temp[][] = new int[N][M];
        for(int i=0; i<N; i++)
            temp[i] = laptop[i].clone();

        int n = a;
        int m = b;
        for(int i=0; i<sticker.length; i++){
            m = b;
            for(int j=0; j<sticker[0].length; j++){
                if(sticker[i][j] == 1){
                    if(n >= N || m >= M || temp[n][m] == 1){
                        return false;
                    }
                    temp[n][m] = 1;
                }
                m++;
            }
            n++;
        }
        
        for(int i=0; i<N; i++)
            laptop[i] = temp[i].clone();
        return true;
    }
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);  

        int N = sc.nextInt(); //세로
        int M = sc.nextInt(); //가로

        int laptop[][] = new int[N][M];
        int K = sc.nextInt(); //스티커 개수
        
        for(int i=0; i<K; i++){
            int R = sc.nextInt();
            int C = sc.nextInt();

            int sticker[][] = new int[R][C];
            
            for(int j=0; j<R; j++){
                for(int k=0; k<C; k++){
                    sticker[j][k] = sc.nextInt();
                    //0은 스티커가 붙지 않은칸,1은 스티커가 붙은칸
                }
            }

            for(int r=0; r<4; r++){
                if(r > 0)
                    sticker = rotateSticker(r,sticker.length,sticker[0].length,sticker);

                if(stickSticker(N,M,sticker,laptop)){
                    break;
                }

            }
        }
        int count = 0;
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(laptop[i][j] == 1)
                    count++;
            }
        }
        System.out.println(count);

    }
    
}

