import java.util.*;



class baekjoon_1331{
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);  

        int dx[] = {2,2,1,1,-1,-1,-2,-2};
        int dy[] = {-1,1,-2,2,-2,2,-1,1};

        boolean check[][] = new boolean[6][6];

        String input = sc.nextLine();
        int first_x = input.charAt(0) - 'A';
        int first_y = input.charAt(1) - '1';
        int cur_x = first_x;
        int cur_y = first_y;

        check[cur_y][cur_x] = true;
        
        for(int i=1; i<36; i++){
            String nxt = sc.nextLine();
            int nx = nxt.charAt(0) - 'A';
            int ny = nxt.charAt(1) - '1';
            if(check[ny][nx]){
                //이미 방문했음
                System.out.println("Invalid");
                return;
            }else{
                boolean isValid = false;
                for(int j=0; j<8; j++){
                    int x = cur_x + dx[j];
                    int y = cur_y + dy[j];

                    if(y < 0 || x < 0 || y >= 6 || x >= 6)
                        continue;
                    if(nx == x && ny == y){
                        isValid = true;
                        check[ny][nx] = true;
                        cur_x = nx;
                        cur_y = ny;
                        break;
                    }
                }
                if(!isValid){
                    System.out.println("Invalid");
                    return;
                }
            }
           
        
        }
        for(int i=0; i<8; i++){
            if(first_x == cur_x + dx[i] && first_y == cur_y + dy[i]){
                System.out.println("Valid");
                return;
            }
        }
        System.out.println("Invalid");
        
        
    }
    
}

