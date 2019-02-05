package baekjoon;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * https://www.acmicpc.net/problem/1058
 * 1058. 친구
 *
 * 2-친구 조건
 * - 나와 A가 친구여야 한다
 * - A가 나의 친구의 친구여야 한다
 *
 * 그니깐 걍 각각 친구랑, 친구의 친구까지 세면 됨
 */

public class P1058_Friend {

    private static int N;
    private static Vertex[] vertices;
    private static boolean[][] twoFriendMatrix;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            N = Integer.parseInt(br.readLine());

            vertices = new Vertex[N];
            twoFriendMatrix = new boolean[N][N];

            for (int i = 0; i < N; i++) {
                vertices[i] = new Vertex(i);
            }

            // 입력
            for (int i = 0; i < N; i++) {
                StringBuilder sb = new StringBuilder(br.readLine());

                for (int j = 0; j < N; j++) {
                    if (sb.charAt(j) == 'Y') {
                        // i -> j
                        vertices[i].getFriendList().add(vertices[j]);
                    }
                }
            }

            // 해결
            for (int i = 0; i < N; i++) {
                Vertex thisVertex = vertices[i];

                for (Vertex friend : thisVertex.getFriendList()) {
                    twoFriendMatrix[i][friend.getNumber()] = true;

                    for (Vertex friendOfFriend : friend.getFriendList()) {
                        if (friendOfFriend.getNumber() != i) {
                            twoFriendMatrix[i][friendOfFriend.getNumber()] = true;
                        }
                    }
                }
            }

            int max = 0;

            for (int i = 0; i < N; i++) {
                int cnt = 0;

                for (int j = 0; j < N; j++) {
                    if (twoFriendMatrix[i][j]) {
                        cnt++;
                    }
                }

                max = Math.max(max, cnt);
            }

            bw.write(String.valueOf(max));
            bw.flush();
        }
    }

    static class Vertex {
        private int number;
        private List<Vertex> friendList;

        public Vertex(int number) {
            this.number = number;
            this.friendList = new ArrayList<>();
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public List<Vertex> getFriendList() {
            return friendList;
        }

        public void setFriendList(List<Vertex> friendList) {
            this.friendList = friendList;
        }
    }
}
