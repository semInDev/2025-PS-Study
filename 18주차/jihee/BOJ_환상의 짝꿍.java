import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static boolean[] isPrime;
    static List<Integer> primes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        isPrime = new boolean[2000001];
        primes = new ArrayList<>();
        Arrays.fill(isPrime, true);
        isPrime[1] = false;
        checkNumber();

        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long A = Long.parseLong((st.nextToken()));
            long B = Long.parseLong((st.nextToken()));
            long AB = A + B;
            if (AB < 4) {
                sb.append("NO").append("\n");
            } else if (AB % 2 == 0) {
                sb.append("YES").append("\n");

            } else {
                if (checkPrime(AB - 2)) {
                    sb.append("YES").append("\n");
                } else {
                    sb.append("NO").append("\n");
                }
            }

        }
        System.out.println(sb);

    }

    private static boolean checkPrime(long num) {
        if (num < 2000001) return isPrime[(int) num];

        for (Integer prime : primes) {
            if (num % prime == 0) return false;
        }
        return true;
    }

    private static void checkNumber() {
        for (int i = 2; i < 2000001; i++) {
            if (!isPrime[i]) continue;
            primes.add(i);
            for (int j = 2 * i; j < 2000001; j += i) {
                isPrime[j] = false;
            }
        }
    }
}
