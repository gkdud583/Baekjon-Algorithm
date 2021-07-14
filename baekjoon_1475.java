import java.util.*;


class baekjoon_1475{
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        HashMap<Integer,Integer> count = new HashMap<>();

        int answer = 0;
        String input = sc.nextLine();
        
        for(int i=0; i<input.length(); i++){
            int num = input.charAt(i) - '0';
            int plusNum = num;

            if(num == 6 || num == 9){
                plusNum = 6;
                int sixCount = count.containsKey(6) ? count.get(6) : 0;
                int nineCount = count.containsKey(9) ? count.get(9) : 0;
                if(sixCount > nineCount){
                    plusNum = 9;
                }
            }
            int cmp = 0;
            if(count.containsKey(plusNum)){
                cmp = count.get(plusNum) + 1;
                count.replace(plusNum,cmp);

            }else{
                cmp = 1;
                count.put(plusNum,1);
    
            }
            answer = Math.max(answer,cmp);
        }
        System.out.println(answer);
    }
}