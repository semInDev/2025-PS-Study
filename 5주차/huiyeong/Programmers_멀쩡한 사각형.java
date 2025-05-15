class Solution {
    public long solution(int w, int h) {
        long W = (long)w;
        long H = (long)h;
        long answer = gcd(w, h);

        return W * H - (W+H-answer);
    }
    private long gcd (long a, long b) {
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}
