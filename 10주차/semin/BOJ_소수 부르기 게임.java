import java.io.*;
import java.util.*;

public class Main {
    private static final int MAX = 1000;
    private static final boolean[] nonPrime = new boolean[MAX + 1];

    public static void main(String[] args) throws IOException {
        sieve();

        Set<Integer> A = collectPrimes(read(), read());
        Set<Integer> B = collectPrimes(read(), read());

        int commonCount = 0;
        for (int p : B) if (A.contains(p)) commonCount++;

        String result = A.size() == B.size()
                ? (commonCount % 2 == 0 ? "yj" : "yt")
                : (A.size() < B.size() ? "yj" : "yt");

        System.out.println(result);
    }

    private static void sieve() {
        int limit = (int) Math.sqrt(MAX);
        for (int i = 2; i <= limit; i++) {
            if (!nonPrime[i]) {
                for (int j = i * i; j <= MAX; j += i) {
                    nonPrime[j] = true;
                }
            }
        }
    }

    private static Set<Integer> collectPrimes(int start, int end) {
        Set<Integer> primes = new HashSet<>();
        for (int i = start; i <= end; i++) {
            if (i > 1 && !nonPrime[i]) primes.add(i);
        }
        return primes;
    }

    private static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}
