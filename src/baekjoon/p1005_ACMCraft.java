package baekjoon;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 1005. ACM Craft
 * <p>
 * 트리형태로 만들어서
 */

/*
2
4 4
10 1 100 10
1 2
1 3
2 4
3 4
4
8 8
10 20 1 5 8 7 1 43
1 2
1 3
2 4
2 5
3 6
5 7
6 7
7 8
7
 */

/*
120
39
 */

public class p1005_ACMCraft {
    private static final int NOT_CACHED = -1;

    private static int T, N, K, W;

    private static List<Building> buildingList;
    private static int[] timeCache;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringBuilder resultString = new StringBuilder();

            T = Integer.parseInt(br.readLine());

            for (int i = 1; i <= T; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                N = Integer.parseInt(st.nextToken());
                K = Integer.parseInt(st.nextToken());

                buildingList = new ArrayList<>(N + 1);
                timeCache = new int[N];

                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    int time = Integer.parseInt(st.nextToken());
                    Building building = new Building(j, time);

                    buildingList.add(building);
                    timeCache[j] = NOT_CACHED;
                }

                for (int j = 0; j < K; j++) {
                    st = new StringTokenizer(br.readLine());

                    int a = Integer.parseInt(st.nextToken());
                    int b = Integer.parseInt(st.nextToken());

                    Building fromBuilding = buildingList.get(a - 1);
                    Building toBuilding = buildingList.get(b - 1);

                    toBuilding.getRequestedBuildingList().add(fromBuilding);
                }

                W = Integer.parseInt(br.readLine());
                Building targetBuilding = buildingList.get(W - 1);

                resultString.append(solution(targetBuilding)).append('\n');
            }

            bw.write(resultString.toString());
            bw.flush();
        }
    }

    private static int solution(Building b) {
        // 종단점이면 리턴
        if (b.getRequestedBuildingList().size() == 0) {
            return b.getTime();
        } else if (timeCache[b.getNumber()] != NOT_CACHED) {
            return timeCache[b.getNumber()];
        } else {
            int max = 0;
            for (Building requestedBuilding : b.getRequestedBuildingList()) {
                int solution = solution(requestedBuilding);

                max = Math.max(max, solution);
            }

            timeCache[b.getNumber()] = max + b.getTime();

            return timeCache[b.getNumber()];
        }
    }

    static class Building {
        //실제 number -1
        private int number;
        private int time;
        private List<Building> requestedBuildingList;

        public Building(int number, int time) {
            this.number = number;
            this.time = time;
            requestedBuildingList = new ArrayList<>();
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public List<Building> getRequestedBuildingList() {
            return requestedBuildingList;
        }

        public void setRequestedBuildingList(List<Building> requestedBuildingList) {
            this.requestedBuildingList = requestedBuildingList;
        }
    }
}