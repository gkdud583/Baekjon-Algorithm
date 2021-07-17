import java.util.*;


class baekjoon_14499{
    //동쪽은 1, 서쪽은 2, 북쪽은 3, 남쪽은 4
    static int y = 0;
    static int x = 0;
    static int dy[] = {0,1,-1,0,0};
    static int dx[] = {0,0,0,-1,1};
    static int dice[] = {0,0,0,0,0,0,0};
    static boolean moveDice(int d,int map[][]){
        int r = map.length;
        int c = map[0].length;
        if(y + dy[d] < 0 || x + dx[d] < 0 || y + dy[d] >= c || x + dx[d] >= r)
            return false;
        int tempDice[] = (int[])dice.clone();
        if(d == 1){
            dice[2] = tempDice[4];
            dice[3] = tempDice[2];
            dice[1] = tempDice[3];
            dice[4] = tempDice[1];
        }else if(d == 2){
            dice[2] = tempDice[3];
            dice[1] = tempDice[4];
            dice[3] = tempDice[1];
            dice[4] = tempDice[2];
        }else if(d == 3){
            dice[2] = tempDice[5];
            dice[1] = tempDice[6];
            dice[6] = tempDice[2];
            dice[5] = tempDice[1];
        }else{
            dice[2] = tempDice[6];
            dice[1] = tempDice[5];
            dice[6] = tempDice[1];
            dice[5] = tempDice[2];
        }
       
       
        
        y = y + dy[d];
        x = x + dx[d];    
        if(map[x][y] == 0)
            map[x][y] = dice[2];
        else{
            dice[2] = map[x][y];
            map[x][y] = 0;
        }
        return true;
    }
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        int r = sc.nextInt();
        int c = sc.nextInt();
        x = sc.nextInt();
        y = sc.nextInt();
        int k = sc.nextInt();

        int map[][] = new int[r][c];
        
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++)
            {
                map[i][j] = sc.nextInt();
            }
        }

        StringBuffer sb = new StringBuffer();
        for(int i=0; i<k; i++){
            int d = sc.nextInt();
            if(moveDice(d,map))
                sb.append(dice[1]+"\n");
        }
        System.out.println(sb.toString());
    }
}