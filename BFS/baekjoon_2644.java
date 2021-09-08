import java.util.*;

class Person{
    int i;
    int villager;

    Person(int i, int villager){
        this.i = i;
        this.villager = villager;
    }
}


class baekjoon_2644{
    
    
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);  

        int n = sc.nextInt();
        
        ArrayList<Integer> arr[] = new ArrayList[n+1];

        for(int i=1; i<=n; i++)
            arr[i] = new ArrayList<>();

        int a = sc.nextInt();
        int b = sc.nextInt();

        int m = sc.nextInt();

        for(int i=0; i<m; i++){
            int p = sc.nextInt();
            int c = sc.nextInt();

            arr[p].add(c);
            arr[c].add(p);
        }

        System.out.println(bfs(a,b,n,arr));

    }
    static int bfs(int a,int b,int n,ArrayList<Integer> arr[]){
        Queue<Person> queue = new LinkedList<>();
        queue.add(new Person(a,0));

        boolean check[] = new boolean[n+1];
        check[a] = true;
        
        while(!queue.isEmpty()){
            Person p = queue.poll();

            for(int i=0; i<arr[p.i].size(); i++){
                int nxt = arr[p.i].get(i);
                if(!check[nxt])
                {
                    if(nxt == b){
                        return p.villager + 1;
                    }
                    check[nxt] = true;
                    queue.add(new Person(nxt, p.villager+1));

                }
            }
        }
        return -1;
    }
    
    
}

