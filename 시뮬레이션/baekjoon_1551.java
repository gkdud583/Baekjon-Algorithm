import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] input = sc.nextLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);

        Queue<Integer> A = new LinkedList<>();

        String[] numbers = sc.nextLine().split(",");
        for (int i = 0; i < N; i++) {
            A.add(Integer.parseInt(numbers[i]));
        }

        boolean isATurn = true;
        for (int i = 0; i < K; i++) {
            if (isATurn) {
                A = solve(A, new LinkedList<>());
                isATurn = false;
                continue;
            }
            isATurn = true;
            A = solve(A, new LinkedList<>());
        }
        while (!A.isEmpty()) {
            System.out.print(A.poll());
            if (!A.isEmpty()) {
                System.out.print(",");
            }
        }
    }

    private static Queue<Integer> solve(Queue<Integer> src, Queue<Integer> dest) {
        while (src.size() >= 2) {
            int bef = src.poll();
            int nxt = src.peek();
            dest.add(nxt - bef);
        }
        return dest;
    }
}