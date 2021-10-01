import java.io.*;
import java.util.*;

class Member{
    int index;
    int score;

    Member(int index, int score){
        this.index = index;
        this.score = score;
    }
}

class Main
{
    static ArrayList<Member> list = new ArrayList<>();
    static ArrayList<Integer> relationship[];
    public static void main(String[]args) throws IOException{
    
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        relationship = new ArrayList[N+1];
        for(int i=1; i<=N; i++){
            relationship[i] = new ArrayList<>();
        }
        while(true){
            int a = sc.nextInt();
            int b = sc.nextInt();
            if(a == -1 && b == -1)
                break;
            relationship[a].add(b);
            relationship[b].add(a);
        }


        solve(N);

        Collections.sort(list, new Comparator<Member>(){
            @Override
            public int compare(Member o1, Member o2) {
                return o1.score - o2.score;
            }
        });

        StringBuffer sb = new StringBuffer();

        int min = list.get(0).score;
        System.out.print(min + " ");

        sb.append(list.get(0).index).append(" ");

        int size = 1;
        for(int i=1; i<list.size(); i++){
            if(min == list.get(i).score){
                sb.append(list.get(i).index).append(" ");
                size++;
            }else  
                break;
        }

        System.out.println(size);

        System.out.println(sb);


    }
    static void solve(int N){
        for(int i=1; i<=N; i++){
            list.add(new Member(i, bfs(i, N)));
        }
    }

    static int bfs(int s, int N){
        Queue<Member> queue = new LinkedList<>();
        boolean check[] = new boolean[N+1];

        queue.add(new Member(s, 0));
        check[s] = true;

        int ret = 0;
        while(!queue.isEmpty()){
            Member cur = queue.poll();
            for(int i=0; i<relationship[cur.index].size(); i++){
                int nxt = relationship[cur.index].get(i);
                if(check[nxt])
                    continue;
                ret = Math.max(ret, cur.score + 1);
                queue.add(new Member(nxt, cur.score+1));
                check[nxt] = true;
            }
        }

        return ret;
    }
}