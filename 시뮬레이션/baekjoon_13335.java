import java.util.*;

class Truck{
    int len;
    int weight;

    Truck(int len, int weight){
        this.len = len;
        this.weight = weight;
    }
}

class baekjoon_13335{
   
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);

        Queue<Integer> truckList = new LinkedList<>();
        Queue<Truck> bridge = new LinkedList<>();

        int n = sc.nextInt();
        int w = sc.nextInt();
        int L = sc.nextInt();

    
        for(int i=0; i<n; i++){
            truckList.add(sc.nextInt());
        }
        
        int totalWeight = 0;
        int t = 0;

        while(true){
            ++t;
            for(int i=0,size=bridge.size(); i<size; i++){
                Truck truck = bridge.poll();
                if(truck.len - 1 > 0){
                    bridge.add(new Truck(truck.len-1, truck.weight));
                }else{
                    totalWeight -= truck.weight;
                }
            }
            //새로운 트럭 다리위에 올림
            if(!truckList.isEmpty()){
                int weight = truckList.peek();
                if(totalWeight + weight <= L)
                {
                    truckList.poll();
                    bridge.add(new Truck(w, weight));
                    totalWeight += weight;
                }
            }

            if(bridge.size() == 0 && truckList.size() == 0){
                break;
            }
        }
        System.out.println(t);
       
    }
}