import java.util.*;



class Customer{
    int sy;
    int sx;
    int ay;
    int ax;
    int d;
    Customer(int sy, int sx, int ay, int ax, int d){
        this.sy = sy;
        this.sx = sx;
        this.ay = ay;
        this.ax = ax;
        this.d = d;
    }
  
}
class Location{
    int y;
    int x;
    Location(int y,int x){
        this.y = y;
        this.x = x;
    }
}
class baekjoon_19238{
    
    static int dist[][];
    static void sortCustomer(int n, int map[][], Location texi, ArrayList<Customer> customerList){
        for(int i=0; i<n; i++)
            Arrays.fill(dist[i], -1);
        bfs(n, map, texi, new Location(n+1,n+1));

        for(int i=0; i<customerList.size(); i++){
            Customer cur = customerList.get(i);
            cur.d = dist[cur.sy][cur.sx];
        
        }
        Collections.sort(customerList, new Comparator<Customer>(){
            @Override
            public int compare(Customer o1, Customer o2) {
                if(o1.d > o2.d)
                    return 1;
                else if(o1.d < o2.d)
                    return -1;
                else{
                    if(o1.sy > o2.sy)
                        return 1;
                    else if(o1.sy < o2.sy)
                        return -1;
                    else{
                        if(o1.sx > o2.sx)
                            return 1;
                        else if(o1.sx < o2.sx)
                            return -1;
                        else
                            return 0;
                    }
                }
            }
        });
    }
    static int startTaxiService(int curFuel, int n, int map[][], Location texi, ArrayList<Customer> customerList){
        while(!customerList.isEmpty()){
            sortCustomer(n, map, texi, customerList);
            Customer customer = customerList.get(0);
            customerList.remove(0);

            texi.y = customer.sy;
            texi.x = customer.sx;

            int ad = bfs(n, map, texi, new Location(customer.ay, customer.ax));

            //손님의 출발위치나 도착위치로 못가는 경우
            if(customer.d == -1 || ad == -1){
                return -1;
            }
            int totalFuel = customer.d + ad;

            //연료 부족으로 출발 위치 또는 도착 위치로 못가는경우
            if(totalFuel > curFuel)
                return -1;
            
            curFuel = curFuel - totalFuel + (ad * 2);
            texi.y = customer.ay;
            texi.x = customer.ax;



        }
        return curFuel;

    }
    static int bfs(int n, int map[][], Location texi, Location arrival){
        int dy[] = {-1,1,0,0};
        int dx[] = {0,0,-1,1};
        boolean check[][] = new boolean[n][n];
        if(texi.y == arrival.y && texi.x == arrival.x){
            return 0;
        }
        check[texi.y][texi.x] = true;
        dist[texi.y][texi.x] = 0;
        Queue<Location> queue = new LinkedList<>();
        queue.add(texi);

        while(!queue.isEmpty()){
            Location cur = queue.poll();
            for(int i=0; i<4; i++){
                int y = cur.y + dy[i];
                int x = cur.x + dx[i];
                if(y < 0 || x < 0 || y >= n || x >= n || map[y][x] == 1 || check[y][x])
                    continue;
                check[y][x] = true;
                queue.add(new Location(y,x));
                dist[y][x] = dist[cur.y][cur.x] + 1;

                if(y == arrival.y && x == arrival.x){
                    return dist[y][x];
                }

            }
        }
        return -1;
        
    }
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        int m = sc.nextInt();
        int f = sc.nextInt();

        dist = new int[n][n];
        int map[][] = new int[n][n];
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                map[i][j] = sc.nextInt();
            }
        }
        int y = sc.nextInt() - 1;
        int x = sc.nextInt() - 1;
        Location texi = new Location(y,x);

        ArrayList<Customer> customerList = new ArrayList<>();

        for(int i=0; i<m; i++){
            int sy = sc.nextInt() - 1;
            int sx = sc.nextInt() - 1;
            int ay = sc.nextInt() - 1;
            int ax = sc.nextInt() - 1;

            customerList.add(new Customer(sy, sx, ay, ax, 0));

        }
       
        

    

        System.out.println(startTaxiService(f, n, map, texi, customerList));
        
      
        
    }    

}
