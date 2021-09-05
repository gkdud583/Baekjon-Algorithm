import java.util.*;


class Ant{
    String name;
    int d; //0은 오른쪽으로 1은 왼쪽으로

    Ant(String name, int d){
        this.name = name;
        this.d = d;
    }
    @Override
    protected Object clone() throws CloneNotSupportedException {
        // TODO Auto-generated method stub
        return super.clone();
    }
}
class baekjoon_3048{
    static ArrayList<Ant> group1 = new ArrayList<>();
    static ArrayList<Ant> group2 = new ArrayList<>();


    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);  

        String input[] = sc.nextLine().split(" ");
        int N1 = Integer.parseInt(input[0]);
        int N2 = Integer.parseInt(input[1]);

        input = sc.nextLine().split("");
        for(int i=0; i<input.length; i++){
            group1.add(new Ant(input[i],0));
        }

        input = sc.nextLine().split("");
        for(int i=0; i<input.length; i++){
            group2.add(new Ant(input[i],1));
        }
        int T = Integer.parseInt(sc.nextLine());
        
        jumpEachOther(T);
        StringBuffer sb = new StringBuffer();
        for(int i=group1.size()-1; i>=0; i--){
            sb.append(group1.get(i).name);
        }
        for(int i=0; i<group2.size(); i++){
            sb.append(group2.get(i).name);
        }

        System.out.println(sb);
    }
    static void jumpEachOther(int T){
        
        for(int t=0; t<T; t++){
            ArrayList<Ant> temp_group1 = (ArrayList<Ant>)group1.clone();
            ArrayList<Ant> temp_group2 = (ArrayList<Ant>)group2.clone();
            
            //첫번째 개미 먼저 비교
            if(group1.size() >= 1 && group2.size() >= 1){
                if(group1.get(0).d == 0 && group2.get(0).d == 1){
                    //첫번째 개미가 존재하고 서로 방향이 반대라면 바꾼다.
                    switchAnt(temp_group1,temp_group2,0,0);
                }
            }
            

            //그룹1개미들 비교
            for(int j=1; j<group1.size(); j++){
                if(group1.get(j).d == 0 && group1.get(j).d != group1.get(j-1).d){
                    //서로 반대라면 바꿈
                    switchAnt(temp_group1, temp_group1, j, j-1);
                }
            }

            //그룹2개미들 비교
            for(int j=1; j<group2.size(); j++){
                if(group2.get(j).d == 1 && group2.get(j).d != group2.get(j-1).d){
                    //서로 반대라면 바꿈
                    switchAnt(temp_group2, temp_group2, j, j-1);
                }
            }

            //다시 원복
            group1 = (ArrayList<Ant>)temp_group1.clone();
            group2 = (ArrayList<Ant>)temp_group2.clone();


        }
    }
    static void switchAnt(ArrayList<Ant>group1, ArrayList<Ant>group2,int a,int b){
        Ant temp1 = group1.get(a);
        Ant temp2 = group2.get(b);

        group1.remove(a);
        group1.add(a, new Ant(temp2.name, temp2.d));
        
        group2.remove(b);
        group2.add(b, new Ant(temp1.name, temp1.d));

        
    }
    
}

