class Solution {
    public long solution(int width, int height) {
        long square = 0;
        double h = (double) height;
        double w = (double) width;

        for(int x = 1; x < width; x++) {
            double fractional = h * x / w % 1;
            if (fractional == 0) {
                square += h * x / w;
            } else {
                square += h * x / w - fractional;
            }
        }
        return square * 2;
    }
}
