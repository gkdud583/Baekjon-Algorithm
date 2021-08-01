import java.util.*;


class baekjoon_2947{
    static final int SIZE = 5;
    static boolean check(int wood[]){
        int cmp [] = (int[])wood.clone();
        Arrays.sort(cmp);
        for(int i=0; i<SIZE; i++){
            if(wood[i] != cmp[i])
                return false;
        }
        return true;
    }
    static void changeWood(int wood[],int a,int b){
        if(wood[a] > wood[b]){
            int temp = wood[a];
            wood[a] = wood[b];
            wood[b] = temp;
            
            for(int i=0; i<SIZE; i++){
                System.out.print(wood[i]+" ");
            }
            System.out.println();
        }
    }
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        int wood[] = new int[SIZE];

        for(int i=0; i<5; i++)
            wood[i] = sc.nextInt();
        
        boolean isBreak = false;
        while(true){
            for(int i=0; i+1<SIZE; i++){
                changeWood(wood,i,i+1);
                if(check(wood)){
                    isBreak = true;
                    break;
                }
            }
            if(isBreak)
                break;
        }
    }

}
