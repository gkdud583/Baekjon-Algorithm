import java.io.*;
import java.util.*;

class Node{
    int parent;
    boolean isLeft;
    int left;
    int right;
    int leftChildCount;
    int rightChildCount;

    Node(int parent, boolean isLeft, int left, int right, int leftChildCount, int rightChildCount){
        this.parent = parent;
        this.isLeft = isLeft;
        this.left = left;
        this.right = right;
        this.leftChildCount = leftChildCount;
        this.rightChildCount = rightChildCount;
    }
    void update(boolean isLeft, int left, int right, int leftChildCount, int rightChildCount){
        this.isLeft = isLeft;
        this.left = left;
        this.right = right;
        this.leftChildCount = leftChildCount;
        this.rightChildCount = rightChildCount;

    }
}

  

public class baekjoon_2250 {

    static Node node[];
    static ArrayList<Integer> tree[]; //각 레벨에 있는 노드
    public static void main(String[]args) throws Exception{
        Scanner sc = new Scanner(System.in);


        int N = sc.nextInt();
        node = new Node[N+1];
        tree = new ArrayList[N+1];
        

        for(int i=1; i<=N; i++){
            tree[i] = new ArrayList<>();
            node[i] = new Node(0,false,0,0,0,0);
        }

        
        for(int i=1; i<=N; i++){
            int n = sc.nextInt();
            int left = sc.nextInt();
            int right = sc.nextInt();

            node[n].update(false, left == -1 ? 0 : left, right == -1 ? 0 : right, 0, 0);
            
            if(left != -1)
                node[left].parent = n;
            if(right != -1)
                node[right].parent = n;

        }

        int root = findRoot(N);
        node[root].update(false, node[root].left, node[root].right, 0, 0);

        
        getChildCount(root, 1);

        int ans[] = getWidth(N);



        System.out.print(ans[0]+" "+ans[1]);




    }
    static int findRoot(int N){
        for(int i=1; i<=N; i++){
            if(node[i].parent == 0)
                return i;
        }
        return 0;
    }

    static int getChildCount(int i, int level){
        
        tree[level].add(i);
        
        if(node[i].left != 0){
            node[node[i].left].isLeft = true;
            node[i].leftChildCount += getChildCount(node[i].left, level+1) + 1;
        }
        if(node[i].right != 0){
            node[node[i].right].isLeft = false;
            node[i].rightChildCount += getChildCount(node[i].right, level+1) + 1;
        }
        return node[i].leftChildCount + node[i].rightChildCount;

    }

    static int[] getWidth(int N){
        int ans[] = new int[2];
        int x[] = new int[N+1]; //각 노드별 x위치

        x[1] = N/2+1;

        int l = 2;

        int maxLevel = 1, maxWidth = 1; //루트노드의 값으로 초기화

        while(tree.length > l && tree[l].size() > 0){

            int min=987654321, max = 0;
            for(int i=0; i<tree[l].size(); i++){

                int cur = tree[l].get(i);
                int curX = getX(cur, l, x);
                x[cur] = curX;
                min = Math.min(min, curX);
                max = Math.max(max, curX);

            }
            int width = max - min + 1;
            if(maxWidth < width)
            {
                maxWidth = width;
                maxLevel = l;
            }
            l++;
        }
        ans[0] = maxLevel;
        ans[1] = maxWidth;
        return ans;

    }

    static int getX(int cur, int l, int x[]){
        int curX = 0;
        if(node[cur].isLeft){

            int rightChildCount = node[cur].rightChildCount;
            curX = x[node[cur].parent] - rightChildCount - 1;
            x[cur] = curX;

        }else{
            int leftChildCount = node[cur].leftChildCount;
            curX = x[node[cur].parent] + leftChildCount + 1;
            x[cur] = curX;
        }
        return curX;

    }

}