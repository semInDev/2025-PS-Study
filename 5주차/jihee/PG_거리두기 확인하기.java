class Solution {
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    public int[] solution(String[][] places) {
        int[] answer = new int[5];

        for (int i = 0; i < 5; i++) {
            if (isSafe(places[i])) {
                answer[i] = 1;
            } else {
                answer[i] = 0;
            }
        }

        return answer;
    }

    private static boolean isSafe(String[] place) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (place[i].charAt(j) == 'P') {
                    if (!check(i, j, place)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private static boolean check(int x, int y, String[] place) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || ny < 0 || nx >= 5 || ny >= 5) continue;

            if (place[nx].charAt(ny) == 'P') return false;

            if (place[nx].charAt(ny) == 'O') {
                for (int k = 0; k < 4; k++) {
                    int kx = nx + dx[k];
                    int ky = ny + dy[k];

                    if (kx < 0 || ky < 0 || kx >= 5 || ky >= 5) continue;
                    if (kx == x && ky == y) continue;

                    if (place[kx].charAt(ky) == 'P') {
                        // 대각선인 경우
                        if (Math.abs(kx - x) == 1 && Math.abs(ky - y) == 1) {
                            if (!(place[kx].charAt(y) == 'X' && place[x].charAt(ky) == 'X')) {
                                return false;
                            }
                        } else { // 직선 방향
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }
}