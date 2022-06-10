import java.util.*;

class baekjoon_10103 {
    private static int changyoungScore = 100;
    private static int sangdeokScore = 100;
    public static void main(String[]args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        for(int i = 0; i < N; i++) {
            int changyoungNum = sc.nextInt();
            int sangdeokNum = sc.nextInt();

            fight(changyoungNum, sangdeokNum);
        }
        System.out.println(changyoungScore);
        System.out.println(sangdeokScore);
    }
    private static void fight(int changyoungNum, int sangdeokNum) {
        if (changyoungNum > sangdeokNum) {
            sangdeokScore -= changyoungNum;
            return;
        }
        if (changyoungNum < sangdeokNum) {
            changyoungScore -= sangdeokNum;
        }
    }
}

