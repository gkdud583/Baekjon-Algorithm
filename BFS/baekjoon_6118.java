import java.io.*;
import java.util.*;

class Barn{
    int index;
    int dist;

    Barn(int index, int dist){
        this.index = index;
        this.dist = dist;
    }
}


class Main
{
    static ArrayList<Barn> barnList = new ArrayList<>();
    static ArrayList<Integer> roadList[];
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        roadList = new ArrayList[N+1];

        for(int i=1; i<=N; i++){
            roadList[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();

            roadList[a].add(b);
            roadList[b].add(a);

        }

        bfs(N);

        Collections.sort(barnList, new Comparator<Barn>(){
            @Override
            public int compare(Barn o1, Barn o2) {
                if(o1.dist < o2.dist)
                    return 1;
                else if(o1.dist > o2.dist)
                    return -1;
                else{
                    if(o1.index > o2.index){
                        return 1;
                    }else if(o1.index < o2.index)
                        return -1;
                    else
                        return 0;
                }
            }
        });

        int min = barnList.get(0).dist;
        System.out.print(barnList.get(0).index + " ");
        System.out.print(barnList.get(0).dist + " ");

        int count = 1;
        for(int i=1; i<barnList.size(); i++){
            Barn barn = barnList.get(i);
            if(barn.dist == min){
                count++;
            }else{
                break;
            }
        }

        System.out.print(count);




    }
    static void bfs(int N){
        Queue<Barn> queue = new LinkedList<>();
        boolean check[] = new boolean[N+1];

        queue.add(new Barn(1, 0));
        check[1] = true;

        while(!queue.isEmpty()){
            Barn cur = queue.poll();
            for(int i=0; i<roadList[cur.index].size(); i++){
                int nxt = roadList[cur.index].get(i);
                if(check[nxt])
                    continue;
                check[nxt] = true;
                queue.add(new Barn(nxt, cur.dist+1));
                barnList.add(new Barn(nxt, cur.dist+1));
            }
        }
    }
}