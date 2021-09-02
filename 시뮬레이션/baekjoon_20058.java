import java.util.*;


class Location{
    int y;
    int x;
    Location(int y, int x){
        this.y = y;
        this.x = x;
    }
}

class baekjoon_20058{
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);  
        
        int N = sc.nextInt();
        int Q = sc.nextInt();

        int size = (int)Math.pow(2,N);
        int map[][] = new int[size][size];
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                map[i][j] = sc.nextInt();
            }
        }

        for(int i=0; i<Q; i++){
            int L = (int)Math.pow(2,sc.nextInt());
            rotate(map,size,L);
           
            melt(map,size);
         
        }
       
        int ret[] = getAnswer(map,size);
        System.out.println(ret[0]);
        System.out.println(ret[1]);

        
    }
    static void rotate(int map[][],int size,int L){
        int temp[][] = new int[size][size];
        for(int i=0; i<size;){ //열
            for(int j=0; j<size;){ //행
                int y = j;
                for(int k=i; k<i+L; k++){ //열
                
                    int x = i + L - 1;
                    for(int l=j; l<j+L; l++){ //행
                        temp[y][x--] = map[l][k];

                    }
                    y++;
                }
                j += L;
            }
            i += L;
            
        }
        for(int i=0; i<size; i++){
            map[i] = temp[i].clone();
        }
       
    }

    static void melt(int map[][],int size){
        int dy[] = {-1,1,0,0};
        int dx[] = {0,0,-1,1};
        int temp[][] = new int[size][size];
        for(int i=0; i<size; i++)
            temp[i] = map[i].clone();

        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                int count = 0;
                for(int k=0; k<4; k++){
                    int y = i + dy[k];
                    int x = j + dx[k];

                    if(y < 0 || x < 0 || y >= size || x >= size || map[y][x] <= 0){
                        count++;
                    }

                    if(count >= 2){
                        temp[i][j]--;
                        break;
                    }

                }
                
            }
        }
        for(int i=0; i<size; i++)
            map[i] = temp[i].clone();
    }

    static int[] getAnswer(int map[][],int size){

        int ret[] = new int[2];

        boolean check[][] = new boolean[size][size];

        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                if(map[i][j] > 0){
                    ret[0] += map[i][j];
                    if(!check[i][j])
                        ret[1] = Math.max(ret[1],bfs(i,j,size,map,check));
                }

                
            }
        }
        return ret;
    }
    static int bfs(int i,int j,int size,int map[][],boolean check[][]){ 
        int dy[] = {-1,1,0,0};
        int dx[] = {0,0,-1,1};

        int ret = 1;
        Queue<Location> queue = new LinkedList<>();
        queue.add(new Location(i,j));
        check[i][j] = true;
        while(!queue.isEmpty()){
            Location loc = queue.poll();
            for(int k=0; k<4; k++){
                int y = loc.y + dy[k];
                int x = loc.x + dx[k];
                if(y < 0 || x < 0 || y >= size || x >= size || check[y][x] || map[y][x] <= 0){
                    continue;
                }
                ret++;
                check[y][x] = true;
                queue.add(new Location(y,x));
            }
        }
        return ret;
    }
}

