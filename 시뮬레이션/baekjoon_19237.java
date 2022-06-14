import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;

class Smell {

  private final int y;
  private final int x;
  private final int owner;
  private int duration;
  private boolean isExists;


  public Smell(int y, int x, int owner, int duration) {
    this.y = y;
    this.x = x;
    this.owner = owner;
    this.duration = duration;
    this.isExists = true;
  }

  public boolean isExists() {
    return isExists;
  }

  public boolean equals(int y, int x, int owner) {
    return this.isExists && this.y == y && this.x == x && this.owner == owner;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Smell smell = (Smell) o;
    return y == smell.y && x == smell.x && owner == smell.owner && duration == smell.duration
      && isExists == smell.isExists;
  }

  @Override
  public int hashCode() {
    return Objects.hash(y, x, owner, duration, isExists);
  }

  public void reproduce(int duration) {
    this.duration = duration;
  }

  public void reduceDuration(int smellMap[][]) {
    this.duration -= 1;
    if (this.duration == 0) {
      this.isExists = false;
      smellMap[this.y][this.x] = 0;
    }
  }
}

class Shark {

  private final int num;
  private int y;
  private int x;
  private int d;
  private boolean isAlive;
  private final int priority[][] = new int[Main.DIRECTION_SIZE + 1][Main.DIRECTION_SIZE];

  public Shark(int num, int y, int x) {
    this.num = num;
    this.y = y;
    this.x = x;
    this.isAlive = true;
  }

  public void setDirection(int d) {
    this.d = d;
  }

  public void setPriority(int order, int curDirection, int direction) {
    priority[curDirection][order] = direction;
  }

  public void spreadSmell(int[][] smellMap, HashSet smells, int duration) {
    if (smellMap[this.y][this.x] == this.num) {
      Smell foundSmell = (Smell) smells.stream().filter((obj) -> {
                                         Smell smell = (Smell) obj;
                                         return smell.equals(this.y, this.x, this.num);
                                       })
                                       .findFirst().get();
      foundSmell.reproduce(duration);
      return;
    }
    smellMap[this.y][this.x] = this.num;
    smells.add(new Smell(this.y, this.x, this.num, duration));
  }

  public boolean isAlive() {
    return isAlive;
  }

  public void move(int map[][], int newMap[][], int[][] smellMap, Shark sharks[]) {
    for (int i = 0; i < Main.DIRECTION_SIZE; i++) {
      int newD = priority[this.d][i];
      int newY = this.y + Main.dy[newD];
      int newX = this.x + Main.dx[newD];

      if (isOutOfArray(newY, newX, map.length)) {
        continue;
      }

      if (smellMap[newY][newX] == 0) {
        if (isAnyShark(newMap, newY, newX)) {
          if (!canSurvive(newMap, newY, newX)) {
            this.die();
            return;
          }
          sharks[newMap[newY][newX]].die();
        }

        newMap[newY][newX] = this.num;
        this.y = newY;
        this.x = newX;
        this.d = newD;
        return;
      }
    }

    for (int i = 0; i < Main.DIRECTION_SIZE; i++) {
      int newD = priority[this.d][i];
      int newY = this.y + Main.dy[newD];
      int newX = this.x + Main.dx[newD];

      if (isOutOfArray(newY, newX, map.length)) {
        continue;
      }

      if (smellMap[newY][newX] == this.num) {
        newMap[newY][newX] = this.num;
        this.y = newY;
        this.x = newX;
        this.d = newD;
        return;
      }
    }
  }

  private boolean isAnyShark(int map[][], int y, int x) {
    return map[y][x] != 0;
  }

  private boolean isOutOfArray(int y, int x, int length) {
    return y <= 0 || y >= length || x <= 0 || x >= length;
  }

  private void die() {
    this.isAlive = false;
    Main.aliveSharksSize -= 1;
  }

  private boolean canSurvive(int map[][], int y, int x) {
    return map[y][x] >= this.num;
  }
}

class Main {

  public static int aliveSharksSize;
  public static final int MAX_TIME = 1000;
  public static final int DIRECTION_SIZE = 4;
  // 위 아래 왼쪽 오른쪽
  public static final int dy[] = {0, -1, 1, 0, 0};
  public static final int dx[] = {0, 0, 0, -1, 1};
  private static Shark[] sharks;
  private static int[][] smellMap;
  private static final HashSet smells = new HashSet();
  private static int map[][];

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    int M = sc.nextInt();
    int K = sc.nextInt();

    aliveSharksSize = M;
    map = new int[N + 1][N + 1];
    sharks = new Shark[M + 1];
    smellMap = new int[N + 1][N + 1];

    for (int i = 1; i <= N; i++) {
      for (int j = 1; j <= N; j++) {
        int v = sc.nextInt();
        map[i][j] = v;
        if (v != 0) {
          sharks[v] = new Shark(v, i, j);
        }
      }
    }

    for (int i = 1; i <= M; i++) {
      sharks[i].setDirection(sc.nextInt());
    }

    for (int i = 1; i <= M; i++) {
      for (int j = 1; j <= DIRECTION_SIZE; j++) {
        for (int k = 0; k < DIRECTION_SIZE; k++) {
          sharks[i].setPriority(k, j, sc.nextInt());
        }
      }
    }

    System.out.println(solve(M, K));
  }

  private static int solve(int sharkSize, int duration) {

    spreadSmell(sharkSize, duration);
    for (int time = 1; time <= MAX_TIME; time++) {
      int tempMap[][] = new int[map.length][map.length];

      moveSharks(sharkSize, tempMap);
      reduceSmellDuration();
      spreadSmell(sharkSize, duration);

      if (aliveSharksSize == 1) {
        return time;
      }

      map = tempMap.clone();
    }
    return -1;
  }

  private static void reduceSmellDuration() {
    smells.stream().forEach((obj) -> {
      Smell smell = (Smell) obj;
      if (smell.isExists()) {
        smell.reduceDuration(smellMap);
      }
    });
  }

  private static void moveSharks(int sharkSize, int[][] tempMap) {
    for (int i = 1; i <= sharkSize; i++) {
      if (sharks[i].isAlive()) {
        sharks[i].move(map, tempMap, smellMap, sharks);
      }
    }
  }

  private static void spreadSmell(int sharkSize, int duration) {
    for (int i = 1; i <= sharkSize; i++) {
      if (sharks[i].isAlive()) {
        sharks[i].spreadSmell(smellMap, smells, duration);
      }
    }
  }
}