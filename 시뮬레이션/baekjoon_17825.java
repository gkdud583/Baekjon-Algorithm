import java.util.*;

class Horse {
    private Node cur;

    public Horse(Node cur) {
        this.cur = cur;
    }

    public Node getCur() {
        return cur;
    }

    public void rollbackLocation(Node node) {
        cur.leave();
        cur = node;
        cur.arrive();
    }

    public boolean isMovable(int count) {
        if (cur.isEnd()) // 말이 도착 칸에 있어서 움직일 수 없음.
            return false;
        return move(true, count);
    }

    public boolean move(boolean isSimulate, int count) {
        if (!isSimulate) {
            cur.leave();
        }

        Node node = cur.next(true);
        for (int i = 1; i < count; i++) {
            if (node.isEnd()) {
                break;
            }
            node = node.next(false);
        }

        if (!node.isEnd() && node.isAnyHorses()) {
            return false;
        }

        if (!isSimulate) {
            cur = node;
            if (!cur.isEnd()) {
                cur.arrive();
            }
        }

        return true;
    }
}
class Node {
    private static final int END = -1;
    private int score;
    private boolean isBlue;
    private Node blueNext;
    private Node redNext;
    private int count;

    public Node(int score, boolean isBlue, Node blueNext, Node redNext) {
        this.score = score;
        this.isBlue = isBlue;
        this.blueNext = blueNext;
        this.redNext = redNext;
        this.count = 0;
    }

    public int getScore() {
        return score;
    }

    public Node next(boolean isStart) {
        if (isStart && this.isBlue) {
            return blueNext;
        }
        return redNext;
    }

    public boolean isEnd() {
        if (this.score == END) {
            return true;
        }
        return false;
    }

    public void arrive() {
        count++;
    }

    public void leave() {
        count--;
    }

    public boolean isAnyHorses() {
        if (this.count >= 1) {
            return true;
        }
        return false;
    }
}
class baekjoon_17825 {
    private static final int HORSE_SIZE = 4;
    private static final int GAME_SIZE = 10;
    private static int answer = 0;
    private static Node start = null;
    private static Horse horses[] = new Horse[HORSE_SIZE];
    private static int[] input = new int[GAME_SIZE];

    public static void main(String[]args) {
        Scanner sc = new Scanner(System.in);
        initMap();
        initHorse();

        for (int i = 0; i < GAME_SIZE; i++) {
            input[i] = sc.nextInt();
        }

        simulate(0, 0);
        System.out.println(answer);
    }

    private static void simulate(int i, int score) {
        if (i >= GAME_SIZE) {
            answer = Math.max(answer, score);
            return;
        }

        for (int j = 0; j < HORSE_SIZE; j++) {
            if (horses[j].isMovable(input[i])) {
                Node temp = horses[j].getCur();
                horses[j].move(false, input[i]);
                if (horses[j].getCur().isEnd()) {
                    simulate(i+1, score);
                }
                else {
                    simulate(i+1, score + horses[j].getCur().getScore());
                }
                horses[j].rollbackLocation(temp);
            }
        }
    }

    private static void initHorse() {
        for(int i = 0; i < HORSE_SIZE; i++) {
            horses[i] = new Horse(start);
            start.arrive();
        }
    }

    public static void initMap() {
        Node end = new Node(-1, false, null, null);
        Node forty = new Node(40, false, null, end);
        Node thirtyfive = new Node(35, false, null, forty);
        Node thirty = new Node(30, false, null, thirtyfive);
        Node twentyfive = new Node(25, false, null, thirty);

        Node thirtyeight = new Node(38, false, null, forty);
        Node thirtysix = new Node(36, false, null, thirtyeight);
        Node thirtyfour = new Node(34, false, null, thirtysix);
        Node thirtytwo = new Node(32, false, null, thirtyfour);

        Node twentysix = new Node(26, false, null, twentyfive);
        Node twentyseven = new Node(27, false, null, twentysix);
        Node twentyeight = new Node(28, false, null, twentyseven);
        Node thirty2 = new Node(30, true, twentyeight, thirtytwo);

        Node twentyeight2 = new Node(28, false, null, thirty2);
        Node twentysix2 = new Node(26, false, null, twentyeight2);
        Node twentyfour = new Node(24, false, null, twentysix2);
        Node twentytwo = new Node(22, false, null, twentyfour);

        Node twentyfour2 = new Node(24, false, null, twentyfive);
        Node twentytwo2 = new Node(22, false, null, twentyfour2);
        Node twenty = new Node(20, true, twentytwo2, twentytwo);

        Node nineteen = new Node(19, false, null, twentyfive);
        Node sixteen = new Node(16, false, null, nineteen);
        Node thirteen = new Node(13, false, null, sixteen);

        Node eighteen = new Node(18, false, null, twenty);
        Node sixteen2 = new Node(16, false, null, eightteen);
        Node fourteen = new Node(14, false, null, sixteen2);
        Node twelve = new Node(12, false, null, forteen);
        Node ten = new Node(10, true, thirteen, twelve);

        Node eight = new Node(8, false, null, ten);
        Node six = new Node(6, false, null, eight);
        Node four = new Node(4, false, null, six);
        Node two = new Node(2, false, null, four);

        start = new Node(0, false, null, two);
    }
}

