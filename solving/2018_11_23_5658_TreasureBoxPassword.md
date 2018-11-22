# 2018.11.23.(금)
## [5658. [모의 SW 역량테스트] 보물상자 비밀번호](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXRUN9KfZ8DFAUo&)
1. 주어진 문자열을 한 칸씩 돌린 후 4등분하여 Set에 저장
```java
// 한 칸씩 돌림
for (int i = 0; i < passwordLen; i++) {
    char firstChar = passwordSb.charAt(0);
    passwordSb.append(firstChar);
    passwordSb.deleteCharAt(0);

    // 새로운 문자열을 4등분한 후 Set에 저장
    for (int j = 0; j < 4; j++) {
        int startIdx = j * passwordLen / 4;
        int endIdx = (j + 1) * passwordLen / 4;
        String subStr = passwordSb.substring(startIdx, endIdx);
        passwordSet.add(subStr);
    }

}
```
2. 각 Set을 Int형으로 List에 저장
```java
// 각 Set을 Int형으로 List에 저장
List<Integer> passwordLongList = new ArrayList<>();
for (String password : passwordSet) {
    passwordLongList.add(Integer.parseInt(password, 16));
}
```
3. 내림차순 정렬
```java
passwordLongList.sort(Collections.reverseOrder());
```
4. K 번째 요소 출력
```java
System.out.printf("#%d %d\n", i, passwordLongList.get(K-1));
```