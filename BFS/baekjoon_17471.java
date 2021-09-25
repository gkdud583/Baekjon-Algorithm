import java.util.*;



class Main
{
    static final int MAX = 987654321;
    static int people[];
    static ArrayList<Integer> edges[];
    static int min = MAX;
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        people = new int[N+1];
        edges = new ArrayList[N+1];

        for(int i=0; i<=N; i++){
            edges[i] = new ArrayList<>();
        }

        for(int i=1; i<=N; i++){
            people[i] = sc.nextInt();
        }

        for(int i=1; i<=N; i++){
            int num = sc.nextInt();
            for(int j=0; j<num; j++){
                edges[i].add(sc.nextInt());
            }
        }

        solve(1, N, new StringBuilder(), new StringBuilder());
        System.out.println(min == MAX ? -1 : min);


    }
    static void solve(int i, int N, StringBuilder wardA, StringBuilder wardB){
        if(i > N){
            if(wardA.length() >= 1 && wardB.length() >= 1)
                bfs(N, wardA, wardB);
           
            return;
        }
        StringBuilder tempWardA = new StringBuilder(wardA);
        StringBuilder tempWardB = new StringBuilder(wardB);

        tempWardA.append(i+" ");
        tempWardB.append(i+" ");

        solve(i+1, N, tempWardA, wardB);
        solve(i+1, N, wardA, tempWardB);


    }
    static void bfs(int N, StringBuilder wardA, StringBuilder wardB) {

        String wardAStr[] = wardA.toString().split(" ");
        String wardBStr[] = wardB.toString().split(" ");

        int ward[] = new int[N+1];

        for(int i=0; i<wardAStr.length; i++){
            ward[Integer.parseInt(wardAStr[i])] = 1;
        }

        for(int i=0; i<wardBStr.length; i++){
            ward[Integer.parseInt(wardBStr[i])] = 2;
        }

        int wardAPeople = 0, wardBPeople = 0;
        
        

        Queue<Integer> queue = new LinkedList<>();
        queue.add(Integer.parseInt(wardAStr[0]));

        boolean check[] = new boolean[N+1];
        check[Integer.parseInt(wardAStr[0])] = true;
        
        
        while(!queue.isEmpty()){
            int cur = queue.poll();

            for(int i=0; i<edges[cur].size(); i++){
                int nxt = edges[cur].get(i);
                if(check[nxt] || ward[nxt] == 2)
                    continue;
                
                check[nxt] = true;
                queue.add(nxt);



            }

        }

        for(int i=0; i<wardAStr.length; i++){
            if(!check[Integer.parseInt(wardAStr[i])]){
                return;
            }
            wardAPeople += people[Integer.parseInt(wardAStr[i])];
        }

        check = new boolean[N+1];
        check[Integer.parseInt(wardBStr[0])] = true;
        queue.clear();
        queue.add(Integer.parseInt(wardBStr[0]));

        while(!queue.isEmpty()){
            int cur = queue.poll();
            for(int i=0; i<edges[cur].size(); i++){
                int nxt = edges[cur].get(i);
                if(check[nxt] || ward[nxt] == 1)
                    continue;
                
                check[nxt] = true;
                queue.add(nxt);

            }

        }

        for(int i=0; i<wardBStr.length; i++){
            if(!check[Integer.parseInt(wardBStr[i])]){
                return;
            }
            wardBPeople += people[Integer.parseInt(wardBStr[i])];
        }

        min = Math.min(min, Math.abs(wardAPeople - wardBPeople));
        

    }
}