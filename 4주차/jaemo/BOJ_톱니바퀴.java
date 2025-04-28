import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static final int WHEEL_CNT = 4;
    private static final int POLE_CNT = 8;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<int[]> wheels = new ArrayList<>();
        for (int i = 0; i < WHEEL_CNT; i++) {
            wheels.add(new int[POLE_CNT]);
        }
        for (int i = 0; i < WHEEL_CNT; i++) {
            String[] split = br.readLine().split("");
            int[] wheel = wheels.get(i);
            for (int j = 0; j < POLE_CNT; j++) {
                wheel[j] = Integer.parseInt(split[j]);
            }
        }

        int locationCnt = Integer.parseInt(br.readLine());
        for (int k = 0; k < locationCnt; k++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int wheelNum = Integer.parseInt(st.nextToken()) - 1;
            int baseDirection = Integer.parseInt(st.nextToken());

            // 각 회전 방향 설정
            int[] locArr = new int[WHEEL_CNT];
            locArr[wheelNum] = baseDirection;
            int direction = baseDirection;
            for (int i = wheelNum; i < WHEEL_CNT - 1; i++) {
                int[] a = wheels.get(i);
                int[] b = wheels.get(i + 1);
                if (a[2] != b[6]) {
                    locArr[i + 1] = direction * -1;
                    direction *= -1;
                } else {
                    break;
                }
            }
            direction = baseDirection;
            for (int i = wheelNum; i > 0; i--) {
                int[] a = wheels.get(i);
                int[] b = wheels.get(i - 1);
                if (a[6] != b[2]) {
                    locArr[i - 1] = direction * -1;
                    direction *= -1;
                } else {
                    break;
                }
            }

            // 실제 회전
            for (int i = 0; i < WHEEL_CNT; i++) {
                int locDirection = locArr[i];
                if (locDirection == 0) {
                    continue;
                }

                int[] wheel = wheels.get(i);
                if (locDirection == -1) {
                    locateLeft(wheel);
                } else {
                    locateRight(wheel);
                }
            }
        }

        System.out.println(calculateScore(wheels));
    }

    private static int calculateScore(List<int[]> wheels) {
        int score = 0;
        int num = 1;
        for (int i = 0; i < WHEEL_CNT; i++) {
            int[] wheel = wheels.get(i);
            if (wheel[0] == 0) {
                score += 0;
            } else {
                score += num;
            }
            num *= 2;
        }
        return score;
    }

    public static void locateLeft(int[] wheel) {
        int left = wheel[0];
        for (int i = 0; i < POLE_CNT - 1; i++) {
            wheel[i] = wheel[i + 1];
        }
        wheel[POLE_CNT - 1] = left;
    }

    public static void locateRight(int[] wheel) {
        int right = wheel[POLE_CNT - 1];
        for (int i = POLE_CNT - 2; i >= 0; i--) {
            wheel[i + 1] = wheel[i];
        }
        wheel[0] = right;
    }
}

// 각 톱니의 극은 8개
// 방향 : 1=시계, -1=반시계
// A 회전 시 B와 맞닿은 극 다르면 B는 반대 회전, 같으면 회전 X

// 총 k번 회전시킨 이후 4개의 톱니바퀴 점수 총합 구하기
