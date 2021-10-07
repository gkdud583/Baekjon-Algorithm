import java.io.*;
import java.util.*;

class Location{
    int y;
    int x;
    Location(int y, int x){
        this.y = y;
        this.x = x;
    }
}

class Main
{
    static int count = 0;
    static ArrayList<Location> lockedDoor;
    static char map[][];
    public static void main(String[]args) throws IOException{
    

        Scanner sc = new Scanner(System.in);
        StringBuffer sb = new StringBuffer();

        int  TC = Integer.parseInt(sc.nextLine());
        for(int i=0; i<TC; i++){
            String input[] = sc.nextLine().split(" ");
            int H = Integer.parseInt(input[0]);
            int W = Integer.parseInt(input[1]);
            
            map = new char[H][W];


            for(int j=0; j<H; j++){
                input = sc.nextLine().split("");
                for(int k=0; k<W; k++){
                    map[j][k] = input[k].charAt(0);
                }
            }

            String key = sc.nextLine();
            char keyArr[] = key.toCharArray();
            StringBuffer sortedKey = new StringBuffer();
            for(int j=0; j<keyArr.length; j++){
                sortedKey.append(keyArr[j]);
            }
            
            lockedDoor = new ArrayList<>();
           
            
            Queue<Location> queue = new LinkedList<>();
            boolean check[][] = new boolean[H][W];

            count = 0;

            getBorder(queue, check, key, H, W);
            bfs(queue, check, H, W, key);
            
            sb.append(count).append("\n");

        }
        System.out.println(sb);
    }
    static void getBorder(Queue<Location> queue, boolean check[][], String key, int H, int W){
        
        
        for(int i=0; i<W; i++){
            
            if(map[0][i] != '*' && !check[0][i]){

                check[0][i] = true;
                char k = (char)(map[0][i] + 32);
                if(map[0][i] >= 'A' && map[0][i] <= 'Z' && key.indexOf(k) == -1){
                    lockedDoor.add(new Location(0,i));
                }else{
                    queue.add(new Location(0, i));
                    if(map[0][i] == '$')
                        count++;
                    
                    
                }

            }if(map[H-1][i] != '*' && !check[H-1][i]){
                check[H-1][i] = true;
                char k = (char)(map[H-1][i] + 32);
                if(map[H-1][i] >= 'A' && map[H-1][i] <= 'Z' && key.indexOf(k) == -1){
                    lockedDoor.add(new Location(H-1,i));
                    
                }else{
                    queue.add(new Location(H-1, i));
                    
                        
                    if(map[H-1][i] == '$')
                        count++;
                    

                }

            }
        }

        for(int i=0; i<H; i++){
            if(map[i][0] != '*' && !check[i][0]){
                check[i][0] = true;
                char k = (char)(map[i][0] + 32);
                if(map[i][0] >= 'A' && map[i][0] <= 'Z' && key.indexOf(k) == -1){
                    lockedDoor.add(new Location(i,0));
                }else{
                    queue.add(new Location(i, 0));
                    if(map[i][0] == '$')
                        count++;
                    

                }
                
                

            }if(map[i][W-1] != '*' && !check[i][W-1]){
                check[i][W-1] = true;
                char k = (char)(map[i][W-1] + 32);
                if(map[i][W-1] >= 'A' && map[i][W-1] <= 'Z' && key.indexOf(k) == -1){
                    lockedDoor.add(new Location(i,W-1));
                }else{
                    queue.add(new Location(i, W-1));
                    if(map[i][W-1] == '$')
                        count++;
                    

                }

                

            }

        }

    }
    
    static void bfs(Queue<Location> queue, boolean check[][], int H, int W, String key){
        
        int dy[] = {-1,1,0,0};
        int dx[] = {0,0,-1,1};


        while(!queue.isEmpty()){
            Location cur = queue.poll();

            
            for(int i=0; i<4; i++){
                int y = cur.y + dy[i];
                int x = cur.x + dx[i];

                
                if(y < 0 || x < 0 || y >= H || x >= W || map[y][x] == '*' || check[y][x])
                    continue;
                
                

                if(map[y][x] == '.'){
                    check[y][x] = true;
                    queue.add(new Location(y,x));
    
                }
                else if(map[y][x] >= 'A' && map[y][x] <= 'Z'){
                    String upper = String.valueOf(map[y][x]);
                    String lower = upper.toLowerCase();

                    check[y][x] = true;
                    if(key.indexOf(lower) != -1){
                        queue.add(new Location(y,x));

                    }else{
                        lockedDoor.add(new Location(y,x));
                    }
                }else if(map[y][x] >= 'a' && map[y][x] <= 'z'){

                    key = getKey(key, y, x);
                    isUnlockable(map[y][x], queue);
                    check[y][x] = true;
                    queue.add(new Location(y,x));

                }
                else if(map[y][x] == '$'){
                    check[y][x] = true;
                    queue.add(new Location(y,x));
                    count++;

                
                }
                
            }
            
        }
      

    }
    static String getKey(String key, int y, int x){
        StringBuffer temp = new StringBuffer(key);
        if(key.indexOf(map[y][x]) == -1){
            temp.append(map[y][x]);

        }
        char keyArr[] = temp.toString().toCharArray();
        Arrays.sort(keyArr);
        StringBuffer sortedKey = new StringBuffer();
        for(int j=0; j<keyArr.length; j++){
            sortedKey.append(keyArr[j]);
        }

        return sortedKey.toString();
    }
    static void isUnlockable(char key, Queue<Location> queue){
        for(int i=0, size=lockedDoor.size(); i<size; i++){
            Location door = lockedDoor.get(i);
            char lock = (char)(key - 32);

            if(map[door.y][door.x] == lock){
                queue.add(new Location(door.y, door.x));
                lockedDoor.remove(i);
                i--;
                size--;
            }
        }
    }
    
}
