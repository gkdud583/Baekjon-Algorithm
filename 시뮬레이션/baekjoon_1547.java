import java.util.*;

class baekjoon_1547{
    private static int ballLoc = 1;
    public static void main(String[]args) {
        Scanner sc = new Scanner(System.in);

        int M = sc.nextInt();
        for (int i = 0; i < M; i++) {
            int cup1 = sc.nextInt();
            int cup2 = sc.nextInt();

            change(cup1, cup2);
        }
        System.out.println(ballLoc);
    }

    private static void change(int cup1, int cup2) {
        if (cup1 == ballLoc) {
            ballLoc = cup2;
            return;
        }

        if (cup2 == ballLoc) {
            ballLoc = cup1;
        }
    }
}

