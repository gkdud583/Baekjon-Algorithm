import java.util.*;



class baekjoon_3028{
    static void swap(int cup[], int a, int b){
        int temp = cup[a];
        cup[a] = cup[b];
        cup[b] = temp;   
    }
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);

        int cup[] = new int[3];
        cup[0] = 1;

        String input[] = sc.nextLine().split("");
        
        for(int i=0; i<input.length; i++){
            String s = input[i];
            if(s.equals("A")){
                //1번2번 바꿈
                swap(cup, 0, 1);
            }else if(s.equals("B")){
                //2번3번 바꿈
                swap(cup, 1, 2);
            }else{
                //1번3번 바꿈
                swap(cup, 0, 2);
            }
        }

        for(int i=0; i<3; i++){
            if(cup[i] == 1)
                System.out.println(i+1);
        }

        
    }
}