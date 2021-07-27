import java.util.*;
import java.util.Map.Entry;


class Shark{
    int y;
    int speed;
    int direction;

    int size;
    Shark(int y,int speed, int direction, int size ){
        this.y = y;
        this.speed = speed;
        this.direction = direction;

        this.size = size;
    }
    @Override
    public boolean equals(Object obj) {
        Shark shark = (Shark)obj;
        if(this.y == shark.y)
            return true;
        return false;
    }
}
class baekjoon_17143{
    static HashMap<Integer,ArrayList<Shark>> SHARK_INFO = new HashMap<>();
    static void moveShark(int r,int c){
        HashMap<Integer,ArrayList<Shark>> temp = new HashMap<>();
        for (Entry<Integer, ArrayList<Shark>> entrySet : SHARK_INFO.entrySet()) {
            int x = entrySet.getKey();
            ArrayList<Shark> pq = entrySet.getValue();
            for(int i=0; i<pq.size(); i++){
                Shark shark = pq.get(i);
                int temp_x = x;
                int y = shark.y;
                int d = shark.direction;
                for(int k=0; k<shark.speed; k++){
                    // d가 1인 경우는 위, 2인 경우는 아래, 3인 경우는 오른쪽, 4인 경우는 왼쪽
                    if(d == 1 ){
                        //위
                        if(y-1 <= 0){
                            d = 2;
                            y++;
                        }else{
                            y--;
                        }
                    }else if(d == 2){
                        //아래
                        if(y+1 > r){
                            d = 1;
                            y--;
                        }else{
                            y++;
                        }
                    }else if(d == 3){
                        if(temp_x+1 > c){
                            d = 4;
                            temp_x--;
                        }else{
                            temp_x++;
                        }
                    }else if(d == 4){
                        if(temp_x-1 <= 0){
                            d = 3;
                            temp_x++;
                        }else{
                            temp_x--;
                        }
                    }
                }
                shark.direction = d;
                shark.y = y;
                if(temp.containsKey(temp_x)){
                    if(temp.get(temp_x).contains(shark))
                    {
                        int index = temp.get(temp_x).indexOf(shark);
                        if(temp.get(temp_x).get(index).size < shark.size){
                            temp.get(temp_x).remove(index);
                            temp.get(temp_x).add(shark);
                        }
    
                    }
                    else{
                        ArrayList<Shark> list = temp.get(temp_x);
                        list.add(shark);
                        temp.replace(temp_x, list);
                    }
                }else{
                    ArrayList<Shark> list = new ArrayList<>();
                    list.add(shark);
                    temp.put(temp_x,list);
                }

            }
        }
        SHARK_INFO = temp;

    }
    static int catchShark(int location){
        /**
         * 
         * 낚시왕이 있는 열에 있는 상어 중에서 땅과 제일 가까운 상어를 잡는다. 
         * 상어를 잡으면 격자판에서 잡은 상어가 사라진다.

         */
        if(SHARK_INFO.containsKey(location)){
            Collections.sort(SHARK_INFO.get(location),new Comparator<Shark>(){
                @Override
                public int compare(Shark o1, Shark o2) {
                    return o1.y - o2.y;
                }
            });
    
            int size = SHARK_INFO.get(location).get(0).size;
            SHARK_INFO.get(location).remove(0);
            return size;

        }

        return 0;
    }
    static int getSharkSize(int r,int c){
        int totalSize = 0;
        int location = 0;
        while(location <= c){
            location++;
            totalSize += catchShark(location);
            moveShark(r,c);
        }
        return totalSize;
    }
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        int r = sc.nextInt();
        int c = sc.nextInt();
        int m = sc.nextInt();

        for(int i=0; i<m; i++){
            int y = sc.nextInt();
            int x = sc.nextInt();
            int s = sc.nextInt(); //속력
            int d = sc.nextInt(); //방향
            int z = sc.nextInt(); //크기

            ArrayList<Shark> pq = null;
            pq = SHARK_INFO.containsKey(x) ? SHARK_INFO.get(x) : new ArrayList<>();
            pq.add(new Shark(y,s,d,z));

            if(SHARK_INFO.containsKey(x)){
                SHARK_INFO.replace(x,pq);
            }else{
                SHARK_INFO.put(x,pq);
            }

        }

        System.out.println(getSharkSize(r,c));
        

    }
        
}



