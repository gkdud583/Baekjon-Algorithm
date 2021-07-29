import java.util.*;

class Descending implements Comparator<Map.Entry<Integer, Integer>>{
	
	@Override
        public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
            if(o1.getValue() < o2.getValue())
                return -1;
            else if(o1.getValue() > o2.getValue())
                return 1;
            else{
                if(o1.getKey() < o2.getKey())
                    return -1;
                else if(o1.getKey() > o2.getKey())
                    return 1;
                else 
                    return 0;
            }
        }
}
class baekjoon_17140{
    static final int MAX = 100;
    static ArrayList<Integer> ROW_ARRAY[];
    static ArrayList<Integer> COL_ARRAY[];
    static int ROW_COUNT = 3;
    static int COL_COUNT = 3;



    static boolean check(int r,int c, int k){
        if(ROW_ARRAY[r-1].size() >= c){
            if(ROW_ARRAY[r-1].get(c-1) == k){
                return true;
            }
        }
        return false;
    }
    static void updateColArray(){
        for(int i=0; i<COL_COUNT; i++){
            ArrayList<Integer> list = new ArrayList<>();
            for(int j=0; j<ROW_COUNT; j++){
                list.add(ROW_ARRAY[j].get(i));
            }
            COL_ARRAY[i] = list;
        }
    }
    static void updateRowArray(){
        for(int i=0; i<ROW_COUNT; i++){
            ArrayList<Integer> list = new ArrayList<>();
            for(int j=0; j<COL_COUNT; j++){
                list.add(COL_ARRAY[j].get(i));
            }
            ROW_ARRAY[i] = list;
        }
    }
    static void fillRowArray(int maxCount){
        for(int i=0; i<ROW_COUNT; i++){
            while(ROW_ARRAY[i].size() < maxCount)
                ROW_ARRAY[i].add(0);
        }
    }
    static void fillColArray(int maxCount){
        for(int i=0; i<COL_COUNT; i++){
            while(COL_ARRAY[i].size() < maxCount)
                COL_ARRAY[i].add(0);
        }
    }
    static void calculationC(){
        int maxCount = 0;
        for(int i=0; i<COL_COUNT; i++){
            HashMap<Integer,Integer> count = new HashMap<>();
            for(int j=0; j<ROW_COUNT; j++){
                int num = ROW_ARRAY[j].get(i);
                if(!count.containsKey(num)){
                    count.put(num,1);
                }else{
                    count.replace(num,count.get(num) + 1);
                }
            }
            List<Map.Entry<Integer, Integer>> entryList = new LinkedList<>(count.entrySet());
            entryList.sort(new Descending());
            ArrayList<Integer> list = new ArrayList<>();
            for(Map.Entry<Integer, Integer> entry : entryList){
                int key = entry.getKey();
                int value = entry.getValue();
                if(key != 0){
                    list.add(key);
                    list.add(value);
                }
            }
            maxCount = Math.max(maxCount,list.size());
            COL_ARRAY[i] = list;


        }
        ROW_COUNT = maxCount;
        fillColArray(maxCount);
        updateRowArray();

    }
    static void calculationR(){

        int maxCount = 0;
        for(int i=0; i<ROW_COUNT; i++){
           HashMap<Integer,Integer> count = new HashMap<>();
            for(int j=0; j<ROW_ARRAY[i].size(); j++){
                int num = ROW_ARRAY[i].get(j);
                if(!count.containsKey(num)){
                    count.put(num,1);
                }else{
                    count.replace(num,count.get(num) + 1);
                }
            }
            List<Map.Entry<Integer, Integer>> entryList = new LinkedList<>(count.entrySet());
            entryList.sort(new Descending());
            ArrayList<Integer> list = new ArrayList<>();
            for(Map.Entry<Integer, Integer> entry : entryList){
                int key = entry.getKey();
                int value = entry.getValue();
                if(key != 0){
                    list.add(key);
                    list.add(value);
    
                }
            }
            ROW_ARRAY[i] = list;
            maxCount = Math.max(maxCount,list.size());

        }
        COL_COUNT = maxCount;
        fillRowArray(maxCount);
        updateColArray();
    }
    static int getSecond(int r, int c, int k){
        int s = 0;
        while(s <= 100){
            if(check(r,c,k)){
                break;
            }
            if(ROW_COUNT >= COL_COUNT){
                calculationR();
            }else{
                calculationC();
            }
            s++;
        }
        return check(r,c,k) ? s : -1;
    }
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);

        int r = sc.nextInt();
        int c = sc.nextInt();
        int k = sc.nextInt();

        ROW_ARRAY = new ArrayList[MAX];
        COL_ARRAY = new ArrayList[MAX];



        for(int i=0; i<MAX; i++){
            ROW_ARRAY[i] = new ArrayList<>();
            COL_ARRAY[i] = new ArrayList<>();
        }
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                int n = sc.nextInt();
                ROW_ARRAY[i].add(n);
            }
        }
        System.out.println(getSecond(r,c,k));
        
    }
}
