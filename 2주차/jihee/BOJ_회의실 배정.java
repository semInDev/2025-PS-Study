import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * N개의 회의실 사용표 만들기
 * 회의가 겹치지 않게 회의실을 사용할 수 있는 회의의 최대 개수 구하기
 * 회의가 끝나는 것과 동시에 다음 회의 시작 O
 * 사직시간과 끝나는 시간 같을 수 있음
 * -> 빨리 끝나면서 겹치는 수가 없는 것 선택
 */
public class Main {
    static int[][] arr;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N][2];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken()); //시작
            arr[i][1] = Integer.parseInt(st.nextToken()); //끝
        }

        //끝나는 시간을 기준으로 정렬
        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                //같다면 시작 시간 빠른순으로 정렬
                if (o1[1] == o2[1]) {
                    return o1[0] - o2[0];
                }

                return o1[1] - o2[1];
            }
        });

        int answer = 0;
        int end = 0;

        for (int i = 0; i < N; i++) {
            if (end <= arr[i][0]) {
                answer++;
                end = arr[i][1];
            }

        }
        System.out.println(answer);

    }
}