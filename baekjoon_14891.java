import java.util.*;


class RotateInfo{
    int index;
    int d;
    RotateInfo(int index,int d){
        this.index = index;
        this.d = d;

    }
}

class baekjoon_14891{
    static final int size = 4;
    static void getRoatateGear(ArrayList<Integer> gearList[],ArrayList<RotateInfo> list,int p,int i,int d){
        if(i - 1 >= 0 && i - 1 != p){
            if(gearList[i - 1].get(2) != gearList[i].get(6)){
                list.add(new RotateInfo(i - 1, d * -1));
                getRoatateGear(gearList,list, i, i-1, d * -1);
            }
        }
        if(i + 1 < size && i + 1 != p){
            if(gearList[i + 1].get(6) != gearList[i].get(2)){

                list.add(new RotateInfo(i + 1, d * -1));
                getRoatateGear(gearList,list, i, i+1, d * -1);

            }
        }

        
    }
    static void moveGear(ArrayList<Integer> gear,int d){
        //방향이 1인 경우는 시계 방향이고, -1인 경우는 반시계 방향
        if(d == 1){
            int last = gear.get(gear.size()-1);
            gear.remove(gear.size()-1);
            gear.add(0, last);
        }else{
            //d == -1
            int first = gear.get(0);
            gear.remove(0);
            gear.add(first);
        }
    }
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> gearList[] = new ArrayList[size];
        for(int i=0; i<size; i++)
            gearList[i] = new ArrayList<>();
        for(int i=0; i<size; i++){
            String gearState[] = sc.nextLine().split("");
            for(int j=0; j<8; j++){
                gearList[i].add(Integer.parseInt(gearState[j]));
            }
        }
       
        int k = sc.nextInt();
        for(int i=0; i<k; i++){
            int gearNum = sc.nextInt() - 1;
            int d = sc.nextInt();
            ArrayList<RotateInfo> list = new ArrayList<>();
            list.add(new RotateInfo(gearNum,d));
            //recursion으로 회전 톱니바퀴 구하기.
            getRoatateGear(gearList,list, gearNum, gearNum, d);
            for(int j=0; j<list.size(); j++){
                RotateInfo info = list.get(j);
                moveGear(gearList[info.index], info.d);
            }
        }
        int sum = 0;
        int score = 1;
        for(int i=0; i<size; i++){
            if(gearList[i].get(0) == 1)
                sum += score;
            score *= 2;
        }
        System.out.println(sum);
    }
}

