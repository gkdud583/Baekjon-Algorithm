import java.io.*;
import java.util.*;


  

public class baekjoon_2668 {

    static boolean includeCheck[];
    static ArrayList<Integer> maxSet = new ArrayList<>();
    static int max = 0;
    static int arr[];
    public static void main(String[]args) throws Exception{
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        includeCheck = new boolean[N+1];
        arr = new int[N+1];


        for(int i=1; i<=N; i++){
            arr[i] = sc.nextInt();
        }

        solve(N);


        System.out.println(max);

        Collections.sort(maxSet);

        for(int i=0; i<maxSet.size(); i++){
            System.out.println(maxSet.get(i));
        }






    }
    static void solve(int N){
        for(int i=1; i<=N; i++){
            boolean array1[] = new boolean[N+1];
            boolean array2[]= new boolean[N+1];

            int array1Count[] = new int[N+1];
            int array2Count[] = new int[N+1];

            dfs(N, i, array1, array2, array1Count, array2Count);
            
        }
    }

    static void dfs(int N, int i, boolean array1[], boolean array2[], int array1Count[], int array2Count[]){
        if(array1[i] || array2[i]){  
            for(int j=1; j<=N; j++){
                if(array1Count[j] != array2Count[j])
                    return;
            }  
            for(int j=1; j<=N; j++){
                if(array1Count[j] > 0 && !includeCheck[j]){
                    includeCheck[j] = true;
                    maxSet.add(j);
                    max++;
                }
            }
            

            return;
        }
        array1[i] = true;

        array2[i] = true;

        array1Count[i]++;
        array2Count[arr[i]]++;

        dfs(N, arr[i], array1, array2, array1Count, array2Count);
    }
}