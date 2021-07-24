import java.util.*;
import java.util.Map.Entry;
class Curve{
    int y1;
    int x1;
    int y2;
    int x2;
    int d; //방향이 가로인지 세로인지
    int b; //기준이 앞인지 뒤인지
    
    Curve(int y1,int x1, int y2,int x2,int d, int b){
        this.y1 = y1;
        this.x1 = x1;
        this.y2 = y2;
        this.x2 = x2;
        this.d = d;
        this.b = b;
    }    
}
class baekjoon_15685{
    static final int SIZE = 100;
    static HashMap<Integer,ArrayList<Integer>> SPOT_LIST = new HashMap<>();

    static ArrayList<Curve> CURVE_INFOS = new ArrayList<>();
 
    static int getCount(){
        int count = 0;

        int dy[] = {0,1,1};
        int dx[] = {1,0,1};
        Set<Integer> keySet = SPOT_LIST.keySet();
        for (Integer key : keySet) {
            ArrayList<Integer> list = SPOT_LIST.get(key);
            for(int i=0; i<list.size(); i++){
                boolean isTrue = true;
                for(int j=0; j<3; j++){
                    int y = key + dy[j];
                    int x = list.get(i) + dx[j];
                    if(!SPOT_LIST.containsKey(y) || !SPOT_LIST.get(y).contains(x))
                    {
                        isTrue = false;
                        break;
                    }  
                }
                if(isTrue)
                    count++;
            }
        }
        return count;
    }
    static void getDragonCurve(int y, int x,int i,int g){
        if(i > g){
            return;
        }
        for(int j = CURVE_INFOS.size()-1; j>=0; j--){
            Curve cur = CURVE_INFOS.get(j);
            int spot_y = y;
            int spot_x = x;
            //가로면 0,세로이면1
            if(cur.d == 0){
                //가로
                if(cur.b == 0){
                    //앞점 기준
                    spot_y = y + 1;
                }else{
                    //뒷점 기준
                    spot_y = y - 1;
                }
                int b = 0;
                if(y < spot_y)
                    b = 1;
                CURVE_INFOS.add(new Curve(y,x,spot_y,x,1,b));
            }else{
                //세로
               
                if(cur.b == 0){
                    //앞점 기준
                    spot_x = x - 1;
                }else{
                    //뒷점 기준
                    spot_x = x + 1;
                }
                int b = 0;
                if(x < spot_x)
                    b = 1;
                CURVE_INFOS.add(new Curve(y,x,y,spot_x,0,b));
            }
            
            addList(y, x);
            addList(spot_y,spot_x);

            y = spot_y;
            x = spot_x;

        }
        getDragonCurve(y,x,i+1,g);
        
    }
    static void addList(int y, int x){
        if(SPOT_LIST.containsKey(y)) 
        {
            if(SPOT_LIST.get(y).contains(x))
                return;
            else{
                ArrayList<Integer> list = SPOT_LIST.get(y);
                list.add(x);
                SPOT_LIST.replace(y, list);
            }
        }else{
            ArrayList<Integer> list = new ArrayList<>();
            list.add(x);
            SPOT_LIST.put(y,list);
    
    
        }
    }
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        
        for(int i=0; i<n; i++){

            //0은 오른쪽,1은 위,2는 왼쪽,3은 아래
            CURVE_INFOS.clear();
            int dy[] = {0,-1,0,1};
            int dx[] = {1,0,-1,0};
            int x = sc.nextInt();
            int y = sc.nextInt();
            int d = sc.nextInt();
            int g = sc.nextInt();


            int move_y = y + dy[d];
            int move_x = x + dx[d];
            

            //방향 결정
            if(d == 0 || d == 2){
                d = 0;
            }else{
                d = 1;
            }
            
            int b = 0;
            //기준점 결정
            if(d == 0){
                if(x < move_x)
                    b = 1;
            }else{
                if(y < move_y)
                    b = 1;
            }
            
            addList(y,x);
            addList(move_y,move_x);

            CURVE_INFOS.add(new Curve(y,x,move_y,move_x,d,b));

            getDragonCurve(move_y,move_x,1,g);

        }
        System.out.println(getCount());

        
    }
        
}



