import java.util.*;


class State{
    int botle[];
    State(int botle[]){
        this.botle = botle;

    }
}
class Main
{
    static int max[] = new int[3];

    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);

        int A = sc.nextInt();
        int B = sc.nextInt();
        int C = sc.nextInt();


        max[0] = A;
        max[1] = B;
        max[2] = C;

        boolean check[][] = new boolean[A+1][C+1];
        solve(A,B,C,check);

        StringBuffer sb = new StringBuffer();
        
        for(int i=0; i<=C; i++){
            if(check[0][i])
                sb.append(i+" ");
        }

        System.out.println(sb);

    }
    static void solve(int A, int B, int C, boolean check[][]){
        Queue<State> queue = new LinkedList<>();
        int[] arr = {0,0,C};
        queue.add(new State(arr));

        check[0][C] = true;

        while(!queue.isEmpty()){
            arr = queue.poll().botle;
            for(int i=0; i<3; i++){
                if(arr[i] == 0)
                    continue;
                for(int j=0; j<3; j++){
                    if(i == j)
                        continue;
                    //한 물통이 비거나, 다른 한 물통이 가득 찰 때까지 물을 부을 수 있다.
                    int tempArr[] = arr.clone();

                    int amount = Math.min(tempArr[i], max[j] - arr[j]);
                    tempArr[j] += amount;
                    tempArr[i] -= amount;

                    if(check[tempArr[0]][tempArr[2]])
                        continue;
                    check[tempArr[0]][tempArr[2]] = true;
                    int[] newState = {tempArr[0],tempArr[1],tempArr[2]};
                    queue.add(new State(newState));
                }
            }
        }


    }
    
}