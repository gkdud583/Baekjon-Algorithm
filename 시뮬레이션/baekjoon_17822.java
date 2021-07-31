import java.util.*;

class DiskNum{
    int diskNum;
    int index;
    DiskNum(int diskNum,int index){
        this.diskNum = diskNum;
        this.index = index;
    }
}

class baekjoon_17822{
    static int COUNT = 0;
    static ArrayList<Integer> DISK_LIST[];
    static int getDiskSum(int n){
        int sum = 0;
        for(int i=1; i<=n; i++){
            ArrayList<Integer> disk = DISK_LIST[i]; 
            for(int j=0; j<disk.size(); j++){
                sum += disk.get(j);
            }
        }
        return sum;
    }
    static void updateDisk(int n){
        int sum = 0;
        int count = 0;

        for(int i=1; i<=n; i++){
            for(int j=0; j<DISK_LIST[i].size(); j++){
                if(DISK_LIST[i].get(j) != 0){
                    sum += DISK_LIST[i].get(j);
                    count++;
                }
            }
        }

        double avg = count == 0 ? 0 : sum / (double)count;

        for(int i=1; i<=n; i++){
            ArrayList<Integer> newList = new ArrayList<>();
            for(int j=0; j<DISK_LIST[i].size(); j++){
                int cur = DISK_LIST[i].get(j);

                if(cur == 0 || cur == avg)
                    newList.add(cur);
                else if(cur > avg){
                    newList.add(cur - 1);
                }else{
                    newList.add(cur + 1);
                }
            }
            DISK_LIST[i] = newList;

        }

    }
    static void removeDisk(ArrayList<Integer> temp[],int i,int n){
        ArrayList<Integer> disk = DISK_LIST[i];
        ArrayList<Integer> tempDisk = temp[i];
        
        for(int j=0; j<disk.size(); j++){
            boolean isTrue = false;
            int cur = disk.get(j);
            
            if(cur != 0){
                int bef = j - 1 < 0 ? disk.size()-1 : j - 1;
                int nxt = j + 1 > disk.size()-1 ?  0 : j + 1;
                
                if(i-1 >= 1){
                    if(DISK_LIST[i-1].size() > j && cur == DISK_LIST[i-1].get(j)){
                        
                        temp[i-1].remove(j);
                        temp[i-1].add(j,0);
                        isTrue = true;
                        COUNT++;
    
                    }
    
                }if(i + 1 <= n){
                    if(DISK_LIST[i + 1].size() > j && cur == DISK_LIST[i + 1].get(j)){

                        temp[i + 1].remove(j);
                        temp[i + 1].add(j,0);
                        isTrue = true;
                        COUNT++;
                       
    
                    }
                }
                if(cur == disk.get(bef)){
                    
                    tempDisk.remove(bef);
                    tempDisk.add(bef,0);
                    isTrue = true;
                    COUNT++;
                }
                if(cur == disk.get(nxt)){
                    tempDisk.remove(nxt);
                    tempDisk.add(nxt,0);
                    isTrue = true;
                    COUNT++;
                }
    
            }
            if(isTrue){
                tempDisk.remove(j);
                tempDisk.add(j,0);
            }
        }
        temp[i] = tempDisk;
        
    }
    static void turnDisk(int i, int d, int k){
        ArrayList<Integer> disk = DISK_LIST[i];
        for(int j=0; j<k; j++){
            if(d == 0){
                //시계방향
                int last = disk.get(disk.size()-1);
                disk.remove(disk.size()-1);
                disk.add(0,last);
                
            }else{
                //반시계방향
                int first = disk.get(0);
                disk.remove(0);
                disk.add(disk.size(),first);
            }
        }
    }
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int t = sc.nextInt();

        DISK_LIST = new ArrayList[n+1];

        for(int i=0; i<=n; i++){
            DISK_LIST[i] = new ArrayList<>();
        }
        
        for(int i=1; i<=n; i++){
            for(int j=0; j<m; j++){
                DISK_LIST[i].add(sc.nextInt());
            }
        }

        for(int i=0; i<t; i++){
            COUNT = 0;

            int x = sc.nextInt();
            int d = sc.nextInt();
            int k = sc.nextInt();

            for(int j=1; j<=n; j++){
                if(j % x == 0){
                    turnDisk(j,d,k);
                }
            }
            ArrayList<Integer> temp[] = new ArrayList[n+1];
            for(int j=1; j<=n; j++){
                temp[j] = (ArrayList<Integer>)DISK_LIST[j].clone();
            }
            for(int j=1; j<=n; j++)
                removeDisk(temp,j,n);
            for(int j=1; j<=n; j++){
                DISK_LIST[j] = (ArrayList<Integer>)temp[j].clone();
            }
            if(COUNT == 0){
                updateDisk(n);
            }

        }        
        System.out.println(getDiskSum(n));
        
    }

}
