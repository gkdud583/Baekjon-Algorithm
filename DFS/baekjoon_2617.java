import java.util.*;


class baekjoon_2617{
    static ArrayList<Integer> heavier[];
    static ArrayList<Integer> lighter[];
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        heavier = new ArrayList[N+1];
        lighter = new ArrayList[N+1];

        for(int i=1; i<=N; i++){
            heavier[i] = new ArrayList<>();
            lighter[i] = new ArrayList<>();
        }
        for(int i=0; i<M; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();

            heavier[b].add(a);
            lighter[a].add(b);
        }
        System.out.println(solve(N));

    }
    static int solve(int N){
        int cnt = 0;

        for(int i=1; i<=N; i++){
            boolean check[] = new boolean[N+1];
            check[i] = true;
            if(getHeavierCnt(i, check, 0) >= (N+1)/2){
                cnt++;
                continue;
            }
            check = new boolean[N+1];
            check[i] = true;

            if(getLighterCnt(i, check, 0) >= (N+1)/2){
                cnt++;
            }


        }
        return cnt;
    }
    static int getHeavierCnt(int i, boolean check[], int count){
        for(int j=0; j<heavier[i].size(); j++){
            int nxt = heavier[i].get(j);
            if(check[nxt])
                continue;
            check[nxt] = true;
            count += getHeavierCnt(nxt, check, 0) + 1;
        }
        return count;
    }

    static int getLighterCnt(int i, boolean check[], int count){
        for(int j=0; j<lighter[i].size(); j++){
            int nxt = lighter[i].get(j);
            if(check[nxt])
                continue;
            check[nxt] = true;
            count += getLighterCnt(nxt, check, 0) + 1;
        }
        return count;
    }
}