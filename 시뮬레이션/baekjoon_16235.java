import java.util.*;



class Tree{
    int age;
    boolean isDead;

    Tree(int age,boolean isDead){
        this.age = age;
        this.isDead = isDead;
    }
}
class baekjoon_16235{
    static LinkedList<Tree> TREE_ARRAY[][];
    static int curNutrientsshment[][];
    static int supplyOfNutrientsshment[][];
    static void processSpring(int n){
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                LinkedList<Tree> trees = TREE_ARRAY[i][j];
                LinkedList<Tree> temp = new LinkedList<>();
                Collections.sort(trees,new Comparator<Tree>(){
                    @Override
                    public int compare(Tree o1, Tree o2) {
                        return o1.age - o2.age;
                    }
                });
                int nutrientsshment = curNutrientsshment[i][j];
                Iterator<Tree> iterator = trees.iterator();

                while(iterator.hasNext()){
                    Tree tree = iterator.next();
                    int treeAge = tree.age;
                    boolean isDead = tree.isDead;
                    if(isDead)
                        temp.add(new Tree(treeAge,true));
                    else if(treeAge > nutrientsshment)
                        temp.add(new Tree(treeAge,true));
                    else{
                        temp.add(new Tree(treeAge+1,false));
                        nutrientsshment -= treeAge;
                    }



                }
                TREE_ARRAY[i][j] = temp;
                curNutrientsshment[i][j] = nutrientsshment;
            }
        }
        

    }
    static void precessSummer(int n){

         for(int i=0; i<n; i++){
             for(int j=0; j<n; j++){
                LinkedList<Tree> trees = TREE_ARRAY[i][j];
                Iterator<Tree> iterator = trees.iterator();

                while(iterator.hasNext()){
                    Tree tree = iterator.next();
                    int treeAge = tree.age;
                    boolean isDead = tree.isDead;
                    if(isDead){
                        //죽은나무
                        iterator.remove();
                        int nutrientsshment = (int)(treeAge / 2.0);
                        curNutrientsshment[i][j] += nutrientsshment;
                    }

                }
             }
         }
    }
    static void processAutumn(int n){

         int dy[] = {-1,-1,-1,0,0,1,1,1};
         int dx[] = {-1,0,1,-1,1,-1,0,1};
         for(int i=0; i<n; i++){
             for(int j=0; j<n; j++){
                LinkedList<Tree> trees = TREE_ARRAY[i][j];
                Iterator<Tree> iterator = trees.iterator();

                while(iterator.hasNext()){
                    Tree tree = iterator.next();
                    int treeAge = tree.age;
                    if(treeAge % 5 == 0){
                        //주변 8곳에 나이가 1인 나무 번식
                        for(int l=0; l<8; l++){
                            int y = i + dy[l];
                            int x = j + dx[l];
                            
                            if(y < 0 || x < 0 || y >= n || x >= n)
                                continue;
                            TREE_ARRAY[y][x].add(new Tree(1,false));
                        }
                    }
                }
             }
         }
    }

    static void processWinter(int n){
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                curNutrientsshment[i][j] += supplyOfNutrientsshment[i][j];
            }
        }
    }

    static int getTreeCount(int n){
        int sum = 0;
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                sum += TREE_ARRAY[i][j].size();
            }
        }
        return sum;
    }
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();

        curNutrientsshment = new int[n][n];
        supplyOfNutrientsshment = new int[n][n];
        TREE_ARRAY = new LinkedList[n][n];

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++)
                TREE_ARRAY[i][j] = new LinkedList<Tree>();
        }

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                supplyOfNutrientsshment[i][j] = sc.nextInt();
                curNutrientsshment[i][j] = 5;
            }

        }

        for(int i=0; i<m; i++){
            int x = sc.nextInt() - 1;
            int y = sc.nextInt() - 1;
            int z = sc.nextInt();
            
            TREE_ARRAY[x][y].add(new Tree(z,false));
        }

        for(int i=1; i<=k; i++){
            processSpring(n);
            precessSummer(n);
            processAutumn(n);
            processWinter(n);

        }
        System.out.println(getTreeCount(n));
        
    }
        
}



