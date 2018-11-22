# 2018.11.22.(목)
## 5653. [모의 SW 역량테스트] 줄기세포배양
- 문제를 잘 읽자!!
    1. 입력받은 N, M, K를 이용해 '배양 용기'의 최대 크기 계산
    ```java
        int xLength = M + 2 * K;
        int yLength = N + 2 * K;
    ```
    2. 해당 크기를 가진 '세포값'과 '수명' 2차원 배열 생성 
    ```java
        int[][] plate = new int[yLength][xLength];
        int[][] timeAttack = new int[yLength][xLength];
    ```
    3. '세포값 배열'과 '수명 배열' 각각의 정 가운데에 입력된 '세포값'과 '수명(세포값 * 2)'을 입력
    ```java
    int startXPoint = xLength / 2 - M / 2;
    int startYPoint = yLength / 2 - N / 2;

    for (int j = 0; j < N; j++) {
        StringTokenizer tokenizer2 = new StringTokenizer(br.readLine());
        for (int k = 0; k < M; k++) {
            int value = Integer.parseInt(tokenizer2.nextToken());
            plate[startYPoint + j][startXPoint + k] = value;
            timeAttack[startYPoint + j][startXPoint + k] = value * 2;
        }
    }
    ```
    4. K번 동안 세포 수명을 1씩 줄여주고, 증식상태가 된 세포는 증식을 진행.
        - 증식조건은 증식할 세포를 기준으로 상하좌우의 각 칸이
            - 아직 초기화 되어있지 않으면 '증식'
            - '같은 시간'에 초기화 되었지만, 해당 세포의 값보다 세포값이 작다면 '덮어씀'

    ```java
    for (int i = 0; i < K; i++) {
        // time attack!
        for (int j = 0; j < yLength; j++) {
            for (int k = 0; k < xLength; k++) {
                timeAttack[j][k]--;
            }
        }

        // process!
        for (int j = 0; j < yLength; j++) {
            for (int k = 0; k < xLength; k++) {
                int thisValue = plate[j][k];
                if (thisValue != 0 && timeAttack[j][k] == (thisValue - 1) ) {
                    int[] xArr = new int[]{k - 1, k + 1, k, k};
                    int[] yArr = new int[]{j, j, j - 1, j + 1};
                    for (int a = 0; a < 4; a++) {
                        int targetX = xArr[a];
                        int targetY = yArr[a];
                        int targetPlate = plate[targetY][targetX];
                        int targetTimeAttack = timeAttack[targetY][targetX];
                        // 아직 초기화 되어있지 않으면 '증식'
                        // '같은 시간'에 초기화 되었지만, 해당 세포의 값보다 세포값이 작다면 '덮어씀'
                        if (targetPlate == 0
                                || ((targetTimeAttack == targetPlate * 2) && (targetPlate < thisValue))) {
                            plate[targetY][targetX] = thisValue;
                            timeAttack[targetY][targetX] = thisValue * 2;
                        }
                    }
                }
            }
        }
    }
    ```
    5. 수명이 남아있는 세포의 수를 세어줌
    ```java
    int cnt = 0;
    for (int j = 0; j < yLength; j++) {
        for (int k = 0; k < xLength; k++) {
            if (timeAttack[j][k] > 0 && plate[j][k] != 0)
                cnt++;
        }
    }
    ```