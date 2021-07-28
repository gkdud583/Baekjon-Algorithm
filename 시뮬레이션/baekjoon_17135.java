import java.util.*;


class Location{
    int y;
    int x;
    Location(int y,int x){
        this.y = y;
        this.x = x;
    }
    @Override
    public boolean equals(Object obj) {
        Location loc = (Location)obj;
        if(loc.y == this.y && loc.x == this.x)
            return true;
        return false;
    }
}

class baekjoon_17135{
    static final int MAX = 987654321;
    static final int ARCHER_NUM = 3;
    static void moveEnemy(int n,ArrayList<Location> enemyLocation){
        for(int i=0,size = enemyLocation.size(); i<size; i++){
            Location cur = enemyLocation.get(i);
            
            int y = cur.y + 1;
            int x = cur.x;

            enemyLocation.remove(i);
            size--;
            i--;

            if(y >= n){
                continue;
            }
            enemyLocation.add(new Location(y,x));

        }
    }
    static int killEnemy(int d, int n, int m, String archerLocation[],ArrayList<Location> enemyLocation) throws NumberFormatException {
        
        
        int deadEnemyNum = 0;
        ArrayList<Location> temp = (ArrayList<Location>)enemyLocation.clone();
        for(int i=0; i<archerLocation.length; i++){
            int y = n;
            int x = Integer.parseInt(archerLocation[i]);
            int dist = MAX;
            Location enemy = null;
            for(int j=0; j<temp.size(); j++){
                Location cur = temp.get(j);
                int cmp = Math.abs(y - cur.y) + Math.abs(x - cur.x);
                if(cmp <= d && (cmp < dist || (cmp == dist && enemy.x > cur.x))){
                    dist = cmp;
                    enemy = cur;
                }
            }
            if(enemyLocation.contains(enemy)){
                deadEnemyNum++;
                enemyLocation.remove(enemy);
            }
        }
        return deadEnemyNum;
    }

    
    
    static void getArcherLocation(int c,ArrayList<String> list,boolean check[],String s,int i){
        if(i >= ARCHER_NUM){
            list.add(s);
            return;
        }
        for(int j=0; j<c; j++){
            if(check[j])
                continue;
            boolean temp[] = (boolean[])check.clone();
            temp[j] = true;
            getArcherLocation(c,list,temp,s+j+" ",i+1);
        }

    }
    static int getMaxEnemy(int d, int map[][],ArrayList<Location> enemyLocation){
        int max = 0;
        int r = map.length;
        int c = map[0].length;
        ArrayList<String> list = new ArrayList<>();
        getArcherLocation(c,list,new boolean[c],"",0);

        for(int i=0; i<list.size(); i++){
            String archerLocation[] = list.get(i).split(" ");
            ArrayList<Location> temp = (ArrayList<Location>)enemyLocation.clone();
            int cmp = 0;
            while(temp.size() > 0){
                cmp += killEnemy(d, r, c, archerLocation,temp);
                moveEnemy(r,temp);
            }
            max = Math.max(cmp,max);

        }
        return max;
    }
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int d = sc.nextInt();
        
        int map[][] = new int[n][m];
        ArrayList<Location> enemyLocation = new ArrayList<>();

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                map[i][j] = sc.nextInt();
                if(map[i][j] == 1){
                    enemyLocation.add(new Location(i,j));
                }
            }
        }
        System.out.println(getMaxEnemy(d, map, enemyLocation));
    }
        
}
