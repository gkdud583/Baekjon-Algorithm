import java.util.*;


class baekjoon_1244{
   
    static void changeState(int switchArray[],int j) {
        if(switchArray[j] == 0)
            switchArray[j] = 1;
        else
            switchArray[j] = 0;
    }

    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int switchArray[] = new int[n+1];

        for(int i=1; i<=n; i++){
            switchArray[i] = sc.nextInt();
        }

        int m = sc.nextInt();
        for(int i=0; i<m; i++){
            int gender = sc.nextInt();
            int num = sc.nextInt();
            if(gender == 1){
                //남
                for(int j=1; j<=n; j++){
                    if(j % num == 0){
                        changeState(switchArray,j);
                    }
                }
            }else{
                //여
                changeState(switchArray,num);

                int k = num + 1;

                for(int j=num-1; j>=1 && k<=n;){
                    if(switchArray[j] == switchArray[k]){
                        changeState(switchArray,j);
                        changeState(switchArray,k);
                        j--;
                        k++;


                    }else 
                        break;
                }


            }   
    
        }
        for(int i=1; i<=n; i++){
            int j = i;
            for(j=i; j<i+20 && j <= n; j++)
                System.out.print(switchArray[j]+" ");
            i = j - 1;
            System.out.println();
        }

    }

}
