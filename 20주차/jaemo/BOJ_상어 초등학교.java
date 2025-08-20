import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    static int N;
    static Map<Integer, List<Integer>> favoriteStudentsInfo = new HashMap<>();
    static int[] dRow = {1, 0, -1, 0};
    static int[] dCol = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        List<Integer> sortedStudentsByOrder = new ArrayList<>();
        for (int i = 1; i <= N * N; i++) {
            String[] split = br.readLine().split(" ");
            int studentNum = Integer.parseInt(split[0]);
            sortedStudentsByOrder.add(studentNum);
            List<Integer> favoriteStudents = new ArrayList<>();
            for (int j = 1; j <= 4; j++) {
                favoriteStudents.add(Integer.parseInt(split[j]));
            }
            favoriteStudentsInfo.put(studentNum, favoriteStudents);
        }

        int[][] classroom = new int[N + 1][N + 1];
        for (int i = 0; i < N * N; i++) {
            int student = sortedStudentsByOrder.get(i);
            List<Integer> favoriteStudents = favoriteStudentsInfo.get(student);
            if (existsFavoriteStudent(classroom, favoriteStudents)) {
                List<Seat> favoriteStudentsSeats = findFavoriteStudentsSeats(classroom, favoriteStudents);
                if (favoriteStudentsSeats.size() > 1) {
                    int maxAdjFavorites = 0;
                    List<Seat> seats = new ArrayList<>();
                    for (int r = 1; r <= N; r++) {
                        for (int c = 1; c <= N; c++) {
                            if (classroom[r][c] == 0) {
                                int adjFavorites = 0;
                                for (int d = 0; d < 4; d++) {
                                    int nr = r + dRow[d];
                                    int nc = c + dCol[d];
                                    if (nr < 1 || nr > N || nc < 1 || nc > N) {
                                        continue;
                                    }
                                    if (favoriteStudents.contains(classroom[nr][nc])) {
                                        adjFavorites++;
                                    }
                                }
                                if (adjFavorites > maxAdjFavorites) {
                                    maxAdjFavorites = adjFavorites;
                                    seats.clear();
                                    seats.add(new Seat(r, c, 0));
                                    continue;
                                }
                                if (adjFavorites == maxAdjFavorites) {
                                    seats.add(new Seat(r, c, 0));
                                }
                            }
                        }
                    }
                    if (seats.size() > 1) {
                        List<Seat> maxAdjEmptiesSeats = findMaxAdjEmptiesSeat(classroom, seats);
                        if (maxAdjEmptiesSeats.size() > 1) {
                            Seat resultSeat = findSeatWithMinRowAndCol(maxAdjEmptiesSeats);
                            assignSeat(classroom, resultSeat, student);
                        } else {
                            assignSeat(classroom, maxAdjEmptiesSeats.get(0), student);
                        }
                        Seat resultSeat = findSeatWithMinRowAndCol(maxAdjEmptiesSeats);
                        assignSeat(classroom, resultSeat, student);
                    } else {
                        assignSeat(classroom, seats.get(0), student);
                    }
                } else {
                    List<Seat> list = findMaxAdjAdjEmptiesSeats(classroom, favoriteStudentsSeats.get(0));
                    if (list.isEmpty()) {
                        Seat resultSeat = findBestSeatWhenNotExistsFavoriteStudents(classroom);
                        assignSeat(classroom, resultSeat, student);
                    } else if (list.size() > 1) {
                        Seat resultSeat = findSeatWithMinRowAndCol(list);
                        assignSeat(classroom, resultSeat, student);
                    } else {
                        assignSeat(classroom, list.get(0), student);
                    }
                }
            } else {
                Seat resultSeat = findBestSeatWhenNotExistsFavoriteStudents(classroom);
                assignSeat(classroom, resultSeat, student);
            }
        }

        System.out.println(surveySatisfaction(classroom, sortedStudentsByOrder));
    }

    // 자리 배정
    public static void assignSeat(int[][] classroom, Seat seat, int student) {
        classroom[seat.r][seat.c] = student;
    }

    // 좋아하는 학생이 교실에 존재하는지 확인
    private static boolean existsFavoriteStudent(int[][] classroom, List<Integer> favoriteStudents) {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (favoriteStudents.contains(classroom[i][j])) {
                    return true;
                }
            }
        }
        return false;
    }

    // 교실에서 좋아하는 학생들의 자리 list 조회
    private static List<Seat> findFavoriteStudentsSeats(int[][] classroom, List<Integer> favoriteStudents) {
        List<Seat> favoriteStudentsSeats = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (favoriteStudents.contains(classroom[i][j])) {
                    int adjEmpties = 0;
                    for (int d = 0; d < 4; d++) {
                        int nr = i + dRow[d];
                        int nc = j + dCol[d];
                        if (nr < 1 || nr > N || nc < 1 || nc > N) {
                            continue;
                        }
                        if (classroom[nr][nc] == 0) {
                            adjEmpties++;
                        }
                    }
                    favoriteStudentsSeats.add(new Seat(i, j, adjEmpties));
                }
            }
        }
        return favoriteStudentsSeats;
    }

    private static List<Seat> findMaxAdjEmptiesSeat(int[][] classroom, List<Seat> seats) {
        int maxAdjEmpties = 0;
        List<Seat> resultSeats = new ArrayList<>();
        for (Seat seat : seats) {
            int adjEmpties = 0;
            for (int d = 0; d < 4; d++) {
                int nr = seat.r + dRow[d];
                int nc = seat.c + dCol[d];
                if (nr < 1 || nr > N || nc < 1 || nc > N) {
                    continue;
                }
                if (classroom[nr][nc] == 0) {
                    adjEmpties++;
                }
            }
            if (adjEmpties > maxAdjEmpties) {
                maxAdjEmpties = adjEmpties;
                resultSeats.clear();
                resultSeats.add(new Seat(seat.r, seat.c, maxAdjEmpties));
                continue;
            }
            if (adjEmpties == maxAdjEmpties) {
                resultSeats.add(new Seat(seat.r, seat.c, maxAdjEmpties));
            }
        }

        return resultSeats;
    }

    // 특정 위치의 주변 자리 중 인접 빈칸이 가장 많은 자리 list 조회
    private static List<Seat> findMaxAdjAdjEmptiesSeats(int[][] classroom, Seat seat) {
        List<Seat> maxAdjEmptiesSeats = new ArrayList<>();
        int maxAdjEmpties = 0;
        for (int d = 0; d < 4; d++) {
            int nr = seat.r + dRow[d];
            int nc = seat.c + dCol[d];
            if (nr < 1 || nr > N || nc < 1 || nc > N) {
                continue;
            }
            if (classroom[nr][nc] == 0) {
                int adjEmpties = 0;
                for (int dd = 0; dd < 4; dd++) {
                    int nnr = nr + dRow[dd];
                    int nnc = nc + dCol[dd];
                    if (nnr < 1 || nnr > N || nnc < 1 || nnc > N) {
                        continue;
                    }
                    if (classroom[nnr][nnc] == 0) {
                        adjEmpties++;
                    }
                }
                if (adjEmpties > maxAdjEmpties) {
                    maxAdjEmpties = adjEmpties;
                    maxAdjEmptiesSeats.clear();
                    maxAdjEmptiesSeats.add(new Seat(nr, nc, maxAdjEmpties));
                    continue;
                }
                if (adjEmpties == maxAdjEmpties) {
                    maxAdjEmptiesSeats.add(new Seat(nr, nc, maxAdjEmpties));
                }
            }
        }
        return maxAdjEmptiesSeats;
    }

    // 여러 자리 중 r, c가 최소인 자리 조회
    private static Seat findSeatWithMinRowAndCol(List<Seat> seats) {
        seats.sort((s1, s2) -> {
            if (s1.r == s2.r) {
                return s1.c - s2.c;
            }
            return s1.r - s2.r;
        });
        return seats.get(0);
    }

    // 교실에 좋아하는 학생이 존재하지 않을 때, 최적의 자리 조회
    private static Seat findBestSeatWhenNotExistsFavoriteStudents(int[][] classroom) {
        List<Seat> seats = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (classroom[i][j] == 0) {
                    int adjEmpties = 0;
                    for (int d = 0; d < 4; d++) {
                        int nr = i + dRow[d];
                        int nc = j + dCol[d];
                        if (nr < 1 || nr > N || nc < 1 || nc > N) {
                            continue;
                        }
                        if (classroom[nr][nc] == 0) {
                            adjEmpties++;
                        }
                    }
                    seats.add(new Seat(i, j, adjEmpties));
                }
            }
        }
        seats.sort((s1, s2) -> {
            if (s1.adjEmpties == s2.adjEmpties) {
                if (s1.r == s2.r) {
                    return s1.c - s2.c;
                }
                return s1.r - s2.r;
            }
            return s2.adjEmpties - s1.adjEmpties;
        });
        return seats.get(0);
    }

    // 만족도 조사
    public static int surveySatisfaction(int[][] classroom, List<Integer> sortedStudentsByOrder) {
        int totalScore = 0;
        for (int student : sortedStudentsByOrder) {
            for (int r = 1; r <= N; r++) {
                for (int c = 1; c <= N; c++) {
                    if (classroom[r][c] == student) {
                        int adjFavoriteStudents = 0;
                        for (int d = 0; d < 4; d++) {
                            int nr = r + dRow[d];
                            int nc = c + dCol[d];
                            if (nr < 1 || nr > N || nc < 1 || nc > N) {
                                continue;
                            }
                            if (favoriteStudentsInfo.get(student).contains(classroom[nr][nc])) {
                                adjFavoriteStudents++;
                            }
                        }
                        switch (adjFavoriteStudents) {
                            case 1:
                                totalScore += 1;
                                break;
                            case 2:
                                totalScore += 10;
                                break;
                            case 3:
                                totalScore += 100;
                                break;
                            case 4:
                                totalScore += 1000;
                                break;
                            default:
                                totalScore += 0;
                        }
                    }
                }
            }
        }
        return totalScore;
    }
}

class Seat {
    int r, c, adjEmpties;

    public Seat(int r, int c, int adjEmpties) {
        this.r = r;
        this.c = c;
        this.adjEmpties = adjEmpties;
    }
}
