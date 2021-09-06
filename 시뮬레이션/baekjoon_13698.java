import java.util.*;



class baekjoon_13698{
    

    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);  

        int ball[] = new int[4];

        //작은 공은 왼쪽 컵, 큰 공은 오른쪽 컵
        ball[0] = 1;
        ball[3] = 2;
        String input[] = sc.nextLine().split("");
        
        for(int i=0; i<input.length; i++){
            String order = input[i];
            if(order.equals("A")){
                int temp = ball[1];
                ball[1] = ball[0];
                ball[0] = temp; 
            }else if(order.equals("B")){
                int temp = ball[2];
                ball[2] = ball[0];
                ball[0] = temp;
            }else if(order.equals("C")){
                int temp = ball[3];
                ball[3] = ball[0];
                ball[0] = temp;
            }else if(order.equals("D")){
                int temp = ball[2];
                ball[2] = ball[1];
                ball[1] = temp;
            }else if(order.equals("E")){
                int temp = ball[3];
                ball[3] = ball[1];
                ball[1] = temp;
            }else{
                int temp = ball[3];
                ball[3] = ball[2];
                ball[2] = temp;
            }
        }

        int mini = 0, big = 0;
        for(int i=0; i<4; i++){
            if(ball[i] == 1)
                mini = i + 1;
            if(ball[i] == 2)
                big = i + 1;
        }
        System.out.println(mini);
        System.out.println(big);

    }
    
}

