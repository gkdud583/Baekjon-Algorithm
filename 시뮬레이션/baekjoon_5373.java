import java.util.*;





class Cube{
    char side[][];
    Cube(){
        this.side = new char[3][3];
        
    }
}
class baekjoon_5373{
    //0은 위면, 1은 아랫면,2는 앞쪽, 3은 뒤쪽,4는 왼쪽, 5는 오른쪽
    
    static Cube cube[] = new Cube[6];

    static void init(){
        char color[]  = {'w','y','r','o','g','b'};
        
        for(int i=0; i<6; i++){
            //각면을 채워넣어야함.
            cube[i] = new Cube();
            for(int j=0; j<3; j++){
                for(int k=0; k<3; k++){
                    cube[i].side[j][k] = color[i];
                }
            }
        }
    }
   
    static void rotateSide(Cube temp[],int side,char d){
        if(d == '+'){
            //시계방향
            int k = 0, m = 2;
            for(int i=0; i<3; i++){ //열
                m = 2;
                for(int j=0; j<3; j++){ //행
                    temp[side].side[k][m] = cube[side].side[j][i];
                    m--;
                }
                k++;
            }
        }else{
            int k = 2, m = 0;
            for(int i=0; i<3; i++){ //열
                m = 0;
                for(int j=0; j<3; j++){ //행
                    
                    temp[side].side[k][m] = cube[side].side[j][i];
                    m++;
                }
                k--;
            }
        }
    }
    static void rotateCube(char side, char d){
        Cube temp[] = new Cube[6];
        for(int i=0; i<6; i++){
            temp[i] = new Cube();
            for(int j=0; j<3; j++){
                for(int k=0; k<3; k++)
                    temp[i].side[j][k] = cube[i].side[j][k];
            }
        }
        if(side == 'F'){
            //0은 위,1은 아래 ,2는 왼쪽,3은 오른쪽
            //0은 위,1은 아래,2는 앞,3은 뒤,4 왼쪽,5오른쪽
            if(d == '+'){
               
                for(int i=0; i<3; i++){
                    temp[5].side[i][0] = cube[0].side[2][i];
                }

                for(int i=0,j=2; i<3; i++){
                    temp[1].side[0][i] = cube[5].side[j--][0];
                }
                for(int i=0; i<3; i++){
                    temp[4].side[i][2] = cube[1].side[0][i];
                }
                for(int i=0,j=2; i<3; i++){
                    temp[0].side[2][j--] = cube[4].side[i][2];
                }
                rotateSide(temp,2,d);
                
            }else{
                
                for(int i=0,j=2; i<3; i++){
                    temp[4].side[j--][2] = cube[0].side[2][i];
                }
                
                for(int i=0; i<3; i++){
                    temp[1].side[0][i] = cube[4].side[i][2];
                }
                for(int i=0,j=2; i<3; i++){
                    temp[5].side[i][0] = cube[1].side[0][j--];
                }

                for(int i=0; i<3; i++){
                    temp[0].side[2][i] = cube[5].side[i][0];
                }
                
                rotateSide(temp,2,d);

            }
        }else if(side == 'B'){
            //뒤

            //0은 위,1은 아래 ,2는 왼쪽,3은 오른쪽
            //0은 위,1은 아래,2는 앞,3은 뒤,4 왼쪽,5오른쪽
            if(d == '+'){

                for(int i=0,j=2; i<3; i++){
                    temp[4].side[i][0] = cube[0].side[0][j--];
                }
                for(int i=0; i<3; i++){
                    temp[1].side[2][i] = cube[4].side[i][0];
                }
                
                for(int i=0,j=2; i<3; i++){
                    temp[5].side[i][2] = cube[1].side[2][j--];
                }
                for(int i=0; i<3; i++){
                    temp[0].side[0][i] = cube[5].side[i][2];
                }

                rotateSide(temp, 3, d);
               


            }else{
                for(int i=2; i>=0; i--)
                    temp[5].side[i][2] = cube[0].side[0][i];

                for(int i=0,j=2; i<3; i++){
                    temp[1].side[2][i] = cube[5].side[j--][2];
                }

                for(int i=0; i<3; i++){
                    temp[4].side[i][0] = cube[1].side[2][i];
                }
                for(int i=0,j=2; i<3; i++){
                    temp[0].side[0][j--] = cube[4].side[i][0];
                }

                rotateSide(temp, 3, d);
            }
        }else if(side == 'U'){
            //0은 위,1은 아래 ,2는 왼쪽,3은 오른쪽
            //0은 위,1은 아래,2는 앞,3은 뒤,4 왼쪽,5오른쪽

            if(d == '+'){
                for(int i=2; i>=0; i--){
                    temp[5].side[0][i] = cube[3].side[0][i];
                }

                for(int i=2; i>=0; i--){
                    temp[2].side[0][i] = cube[5].side[0][i];
                }
                for(int i=2; i>=0; i--){
                    temp[4].side[0][i] = cube[2].side[0][i];
                }
                for(int i=2; i>=0; i--){
                    temp[3].side[0][i] = cube[4].side[0][i];
                }

                rotateSide(temp, 0, d);
               
            }else{
                for(int i=2; i>=0; i--){
                    temp[4].side[0][i] = cube[3].side[0][i];
                }
                for(int i=2; i>=0; i--){
                    temp[2].side[0][i] = cube[4].side[0][i];
                }
                for(int i=2; i>=0; i--){
                    temp[5].side[0][i] = cube[2].side[0][i];
                }
                for(int i=2; i>=0; i--){
                    temp[3].side[0][i] = cube[5].side[0][i];
                }

                rotateSide(temp, 0, d);
               

            }
        }else if(side == 'D'){
            //0은 위,1은 아래 ,2는 왼쪽,3은 오른쪽
            //0은 위,1은 아래,2는 앞,3은 뒤,4 왼쪽,5오른쪽

            if(d == '+'){

                for(int i=0; i<3; i++){
                    temp[5].side[2][i] = cube[2].side[2][i];
                }
                for(int i=0; i<3; i++){
                    temp[3].side[2][i] = cube[5].side[2][i];
                }

                for(int i=0; i<3; i++){
                    temp[4].side[2][i] = cube[3].side[2][i];
                }

                for(int i=0; i<3; i++){
                    temp[2].side[2][i] = cube[4].side[2][i];
                }

                rotateSide(temp, 1, d);
              

            }else{
                for(int i=0; i<3; i++){
                    temp[4].side[2][i] = cube[2].side[2][i];
                }

                for(int i=0; i<3; i++){
                    temp[3].side[2][i] = cube[4].side[2][i];
                }

                for(int i=0; i<3; i++){
                    temp[5].side[2][i] = cube[3].side[2][i];
                }

                for(int i=0; i<3; i++){
                    temp[2].side[2][i] = cube[5].side[2][i];
                }

                rotateSide(temp, 1, d);
              

            }
        }else if(side == 'L'){
            //0은 위,1은 아래 ,2는 왼쪽,3은 오른쪽
            //0은 위,1은 아래,2는 앞,3은 뒤,4 왼쪽,5오른쪽

            if(d == '+'){
                for(int i=0; i<3; i++){
                    temp[2].side[i][0] = cube[0].side[i][0];
                }
                for(int i=0; i<3; i++){
                    temp[1].side[i][0] = cube[2].side[i][0];
                }
                for(int i=0,j=2; i<3; i++){
                    temp[3].side[i][2] = cube[1].side[j--][0];
                }
                for(int i=0,j=2; i<3; i++){
                    temp[0].side[j--][0] = cube[3].side[i][2];
                }

                rotateSide(temp, 4, d);
              

            }else{
                for(int i=0,j=2; i<3; i++){
                    temp[3].side[j--][2] = cube[0].side[i][0];
                }
                for(int i=0,j=2; i<3; i++){
                    temp[1].side[i][0] = cube[3].side[j--][2];
                }

                for(int i=0; i<3; i++){
                    temp[2].side[i][0] = cube[1].side[i][0];
                }

                for(int i=0; i<3; i++){
                    temp[0].side[i][0] = cube[2].side[i][0];
                }

                rotateSide(temp, 4, d);
               
            }
        }else{
            //0은 위,1은 아래 ,2는 왼쪽,3은 오른쪽
            //0 위,1아래,2앞,3뒤,4왼쪽,5오른쪽

            if(d == '+'){

                for(int i=0,j=2; i<3; i++){
                    temp[3].side[j--][0] = cube[0].side[i][2];
                }
                for(int i=0,j=2; i<3; i++){
                    temp[1].side[i][2] = cube[3].side[j--][0];
                }
                for(int i=0; i<3; i++){
                    temp[2].side[i][2] = cube[1].side[i][2];
                }
                for(int i=0; i<3; i++){
                    temp[0].side[i][2] = cube[2].side[i][2];
                }

                rotateSide(temp, 5, d);
               
            }else{
               

                for(int i=0; i<3; i++){
                    temp[2].side[i][2] = cube[0].side[i][2];
                }
                for(int i=0; i<3; i++){
                    temp[1].side[i][2] = cube[2].side[i][2];
                }
                for(int i=0,j=2; i<3; i++){
                    temp[3].side[j--][0] = cube[1].side[i][2];
                }
                for(int i=0,j=2; i<3; i++){
                    temp[0].side[j--][2] = cube[3].side[i][0];
                }

                rotateSide(temp, 5, d);
               
            }
        }
        //새로운 배열 다시 복사
        for(int i=0; i<6; i++){
            for(int j=0; j<3; j++){
                for(int k=0; k<3; k++)
                    cube[i].side[j][k] = temp[i].side[j][k];
            }
        }

        
    }
    static void printCubeTop(){
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                System.out.print(cube[0].side[i][j]);
            }
            System.out.println();
        }

        
     
        
    }
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        

        int tc = Integer.parseInt(sc.nextLine());
        for(int i=0; i<tc; i++){
            int n = Integer.parseInt(sc.nextLine());
            String input = sc.nextLine();
            init();

            String tok[] = input.split(" ");
            for(int j=0; j<n; j++){

                char side = tok[j].charAt(0);
                //u윗면, d:아랫면,f:앞면, b:뒷면,l:왼쪽, r:오른쪽
                char d = tok[j].charAt(1); //+시계방향, - 반시계방향

                rotateCube(side,d);
            }
            printCubeTop();
        }
            
    }

}


 
