import java.util.*;


class baekjoon_2490{
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        
        for(int i=0; i<3; i++){
            int yutCount[] = new int[2];
            StringTokenizer st = new StringTokenizer(sc.nextLine());
            
            for(int j=0; j<4; j++){
                int yut = Integer.parseInt(st.nextToken());
                yutCount[yut]++;

            }
            //도(배 한 개, 등 세 개), 개(배 두 개, 등 두 개), 걸(배 세 개, 등 한 개), 윷(배 네 개), 모(등 네 개)
            //배 0, 등 1
            int zeroCount = yutCount[0];
            int oneCount = yutCount[1];
            if(zeroCount == 1 && oneCount == 3)
                sb.append("A"+"\n");
            else if(zeroCount == 2 && oneCount == 2)
                sb.append("B"+"\n");
            else if(zeroCount == 3 && oneCount == 1)
                sb.append("C"+"\n");
            else if(zeroCount == 4)
                sb.append("D"+"\n");    
            else
                sb.append("E"+"\n");  


        }
        System.out.print(sb);
    }
}