import java.util.*;

class Location{
    int x;
    int y;
    Location(int x,int y){
        this.x = x;
        this.y = y;
    }
}

class baekjoon_17779{
    static int TOTAL = 0;
    static final int SIZE = 6;
    static int MIN = 987654321;
    static int POPULATION[] = new int[SIZE];
    static boolean CHECK[][];
    
    static boolean checkIndex(int x, int y, int n){
        if(x < 1 || y < 1 || x > n || y > n)
            return false;
        return true;
    }
   
    static void ward5(int map[][], int r, int c, int d1,int d2){

        int n = map.length - 1;

        for(int i=0; i<=d1; i++){
            int x = r + i;
            int y = c - i;
            if(!checkIndex(x,y,n) || CHECK[x][y])
                continue;
            CHECK[x][y] = true;
          
           

        }

        for(int i=0; i<=d2; i++){
            int x = r + i;
            int y = c + i;
            if(!checkIndex(x,y,n) || CHECK[x][y])
                continue;
            CHECK[x][y] = true;
     
          

          


        }

        for(int i=0; i<=d2; i++){
            int x =  r + d1 + i;
            int y =  c - d1 + i;
            if(!checkIndex(x,y,n) || CHECK[x][y])
                continue;
            CHECK[x][y] = true;
         
         
            

        }
        for(int i=0; i<=d1; i++){
            int x = r + d2 + i;
            int y = c + d2 - i;
            if(!checkIndex(x,y,n)  || CHECK[x][y])
                continue;
            CHECK[x][y] = true;
         
          
           


        }
       



        
    }
    static void ward4(int map[][],int r,int c,int d1,int d2){
        int n = map.length - 1;
        for(int i=n; i>r+d2; i--){
            for(int j=n; j>=c-d1+d2; j--){
                if(!checkIndex(i,j,n) || CHECK[i][j])
                    break;
                CHECK[i][j] = true;
                POPULATION[4] += map[i][j];
                
        

            }
        }
        
    }
    static void ward3(int map[][],int r,int c,int d1,int d2){
        int n = map.length - 1;
        for(int i=r+d1; i<=n; i++){
            for(int j=1; j<c-d1+d2; j++){
                if(!checkIndex(i,j,n) || CHECK[i][j])
                    break;
                CHECK[i][j] = true;
                POPULATION[3] += map[i][j];


            }
        }
    }
    static void ward2(int map[][],int r,int c,int d1,int d2){
        int n = map.length - 1;
        for(int i=1; i<=r+d2; i++){
            for(int j=n; j>c; j--){
                if(!checkIndex(i,j,n) || CHECK[i][j])
                    break;
                CHECK[i][j] = true;
                POPULATION[2] += map[i][j];


            }
        }
    }
    static void ward1(int map[][],int r,int c,int d1,int d2){
        int n = map.length - 1;
        for(int i=1; i<r+d1; i++){
            for(int j=1; j<=c; j++){
                if(!checkIndex(i,j,n) || CHECK[i][j])
                    break;
                CHECK[i][j] = true;
                POPULATION[1] += map[i][j];


            }
        }
    }

    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        int map[][] = new int[n+1][n+1];
        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){
                map[i][j] = sc.nextInt();
                TOTAL += map[i][j];
            }
        }

        for(int i=1; i<=n; i++){ //x
            for(int j=1; j<=n; j++){ //y
                for(int k=1; k<=n; k++){ //d1
                    for(int m=1; m<=n; m++){ //d2
                        if(i < i+k+m){
                            if(j-k < j && j < j+m){
                                CHECK = new boolean[n+1][n+1];
                                POPULATION = new int[SIZE];
                              
                               
                                ward5(map,i,j,k,m);
                                ward1(map,i,j,k,m);
                                ward2(map,i,j,k,m);
                                ward3(map,i,j,k,m);
                                ward4(map,i,j,k,m);

                           
                                int otherSum = 0;
                                for(int l=1; l<SIZE-1; l++){
                                    otherSum += POPULATION[l];
                                }
                                POPULATION[5] = TOTAL - otherSum;
                                Arrays.sort(POPULATION);
                                //구역을 하나도 포함하지 않은 지역구 있다면,
                                if(POPULATION[1] == 0)
                                    continue;
                                int cmp = POPULATION[POPULATION.length-1] - POPULATION[1];
                               
                                MIN = Math.min(MIN,cmp);

                            }
                            
                        }
                    }
                }
            }
        }

        System.out.println(MIN);

    }

}
