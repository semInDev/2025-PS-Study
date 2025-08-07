import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String S = scanner.next();
        int K = scanner.nextInt();
        int M = scanner.nextInt();
        
        Map<Long, Integer> map = new HashMap<>();

        int len = S.length();
        long hash = 0;
        for (int i=0;i<K;i++) {
            hash = hash * 2 + (S.charAt(i) - '0');
        }
        map.put(hash, 1);

        for (int i=K;i<len;i++) {
            hash = hash * 2 - (long) (S.charAt(i-K) - '0') * (1L << K) + (S.charAt(i) - '0');
            map.put(hash, map.getOrDefault(hash, 0)+1);
        }

        for (int value : map.values()) {
            if (value >= M) {
                System.out.println(1);
                return;
            }
        }
        System.out.println(0);
    }
}
