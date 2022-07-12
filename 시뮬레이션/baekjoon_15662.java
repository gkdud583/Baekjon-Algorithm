import java.util.Scanner;

class GearWheel {

  private static final int LEFT_COMPARE_POLE_INDEX = 6;
  private static final int RIGHT_COMPARE_POLE_INDEX = 2;

  private int num;
  private boolean poles[] = new boolean[baekjoon_15662.POLE_SIZE]; // 0은 N극 1은 S극

  public GearWheel(int num) {
    this.num = num;
  }

  public void setPole(int i, boolean v) {
    poles[i] = v;
  }

  public boolean isSPole() {
    return this.poles[0];
  }

  public void rotate(int beforeGearWheelNum, boolean direction) {
    int poleSize = poles.length;
    boolean rotatePoles[] = new boolean[poleSize];
    if (direction) {
      for (int i = 0; i < poleSize; i++) {
        rotatePoles[(i + 1) % poleSize] = this.poles[i];
      }
    } else {
      rotatePoles[poleSize - 1] = this.poles[0];
      for (int i = 1; i < poleSize; i++) {
        rotatePoles[i - 1] = this.poles[i];
      }
    }

    if (num - 1 >= 1 && beforeGearWheelNum != num - 1) {
      if (baekjoon_15662.gearWheels[num - 1].isNotSame(poles[LEFT_COMPARE_POLE_INDEX],
        RIGHT_COMPARE_POLE_INDEX)) {
        baekjoon_15662.gearWheels[num - 1].rotate(num, !direction);
      }
    }
    if (num + 1 < baekjoon_15662.gearWheels.length && beforeGearWheelNum != num + 1) {
      if (baekjoon_15662.gearWheels[num + 1].isNotSame(poles[RIGHT_COMPARE_POLE_INDEX],
        LEFT_COMPARE_POLE_INDEX)) {
        baekjoon_15662.gearWheels[num + 1].rotate(num, !direction);
      }
    }
    this.poles = rotatePoles;
  }

  private boolean isNotSame(boolean pole, int index) {
    return this.poles[index] != pole;
  }
}

public class baekjoon_15662 {

  public static final int POLE_SIZE = 8;
  public static GearWheel[] gearWheels;

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int T = Integer.parseInt(sc.nextLine());

    gearWheels = new GearWheel[T + 1];
    for (int i = 1; i <= T; i++) {
      gearWheels[i] = new GearWheel(i);
      String[] input = sc.nextLine().split("");
      for (int j = 0; j < POLE_SIZE; j++) {
        int pole = Integer.parseInt(input[j]);
        gearWheels[i].setPole(j, pole == 0 ? false : true);
      }
    }

    int K = Integer.parseInt(sc.nextLine());
    for (int i = 0; i < K; i++) {
      String[] input = sc.nextLine().split(" ");
      int num = Integer.parseInt(input[0]);
      boolean direction = Integer.parseInt(input[1]) == -1 ? false : true;

      gearWheels[num].rotate(num, direction);
    }

    int sum = 0;
    for (int i = 1; i <= T; i++) {
      if (gearWheels[i].isSPole()) {
        sum++;
      }
    }

    System.out.println(sum);
  }
}
