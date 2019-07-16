package lecture;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Week14 {
    private static final String DATA_FILE = "iris.csv";
    private static final int DATA_SIZE = 150;
    private static final int COLUMN_SIZE = 4;
    private static final int CLUSTER_SIZE = 3;

    private static long start, end;

    private static Random random = new Random();

    private static List<Flower> dataList;
    private static List<Flower> centerList;
    private static List<Flower> oldCenterList;
    private static List<Set<Flower>> cluster;

    public static void main(String[] args) {
        // 파일 읽기
        dataList = readFile(DATA_FILE);

        // 랜덤 클러스터
        setRandomCluster();

        // cal
        do {
            expectation();
            minimization();
        } while (isChanged());

        // 정답 출력
        print(centerList);
    }

    private static void setRandomCluster() {
        oldCenterList = new ArrayList<>();
        centerList = new ArrayList<>();

        for (int i = 0; i < CLUSTER_SIZE; i++) {
            // 여러 임의의 점의 각 컬럼이 삽입되도록
            Flower randomFlower = dataList.get(random.nextInt(DATA_SIZE));

            centerList.add(randomFlower);
        }
    }

    private static void expectation() {
        cluster = new ArrayList<>();

        for (int i = 0; i < CLUSTER_SIZE; i++) {
            cluster.add(new HashSet<>());
        }

        for (Flower flower : dataList) {
            int closestIdx = 0;
            float minDistance = Float.MAX_VALUE;

            for (int i = 0; i < CLUSTER_SIZE; i++) {
                float distance = flower.getDistanceWith(centerList.get(i));

                if (minDistance > distance) {
                    minDistance = distance;
                    closestIdx = i;
                }
            }
            cluster.get(closestIdx).add(flower);
        }
    }

    private static void minimization() {
        oldCenterList = new ArrayList<>(centerList);
        centerList.clear();

        for (int i = 0; i < CLUSTER_SIZE; i++) {
            float newPlateLength = 0;
            float newPlateSize = 0;
            float newPetalLength = 0;
            float newPetalSize = 0;

            for (Flower flower : cluster.get(i)) {
                newPlateLength += flower.getPlateLength();
                newPlateSize += flower.getPlateSize();
                newPetalLength += flower.getPetalLength();
                newPetalSize += flower.getPetalSize();
            }

            newPlateLength /= cluster.get(i).size();
            newPlateSize /= cluster.get(i).size();
            newPetalLength /= cluster.get(i).size();
            newPetalSize /= cluster.get(i).size();

            centerList.add(new Flower(newPlateLength, newPlateSize, newPetalLength, newPetalSize));
        }
    }

    private static boolean isChanged() {
        Collections.sort(oldCenterList);
        Collections.sort(centerList);

        if (oldCenterList.size() > 0) {
            for (int i = 0; i < CLUSTER_SIZE; i++) {
                Flower oldCenter = oldCenterList.get(i);
                Flower thisCenter = centerList.get(i);

                if (!oldCenter.equals(thisCenter)) {
                    return true;
                }
            }
        } else {
            return true;
        }

        return false;
    }

    private static void print(List<Flower> centerList) {
        for (int i = 0; i < CLUSTER_SIZE; i++) {
            System.out.printf("%.1f %.1f %.1f %.1f\n"
                    , centerList.get(i).getPlateLength()
                    , centerList.get(i).getPlateSize()
                    , centerList.get(i).getPetalLength()
                    , centerList.get(i).getPetalSize());
        }
    }

    static class Flower implements Comparable<Flower> {
        private final float plateLength;
        private final float plateSize;
        private final float petalLength;
        private final float petalSize;

        Flower(float plateLength, float plateSize, float petalLength, float petalSize) {
            this.plateLength = plateLength;
            this.plateSize = plateSize;
            this.petalLength = petalLength;
            this.petalSize = petalSize;
        }

        public float getPlateLength() {
            return plateLength;
        }

        public float getPlateSize() {
            return plateSize;
        }

        public float getPetalLength() {
            return petalLength;
        }

        public float getPetalSize() {
            return petalSize;
        }

        @Override
        public int compareTo(Flower o) {
            if (this.plateLength != o.plateLength) {
                return Float.compare(this.plateLength, o.plateLength);
            } else if (this.plateSize != o.plateSize) {
                return Float.compare(this.plateSize, o.plateSize);
            } else if (this.petalLength != o.petalLength) {
                return Float.compare(this.petalLength, o.petalLength);
            }
            return Float.compare(this.petalSize, o.petalSize);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Flower flower = (Flower) o;
            return Float.compare(flower.plateLength, plateLength) == 0 &&
                    Float.compare(flower.plateSize, plateSize) == 0 &&
                    Float.compare(flower.petalLength, petalLength) == 0 &&
                    Float.compare(flower.petalSize, petalSize) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(plateLength, plateSize, petalLength, petalSize);
        }

        public static float getDistance(Flower f1, Flower f2) {
            return (f1.plateLength - f2.plateLength) * (f1.plateLength - f2.plateLength)
                    + (f1.plateSize - f2.plateSize) * (f1.plateSize - f2.plateSize)
                    + (f1.petalLength - f2.petalLength) * (f1.petalLength - f2.petalLength)
                    + (f1.petalSize - f2.petalSize) * (f1.petalSize - f2.petalSize);
        }

        public float getDistanceWith(Flower f1) {
            return (f1.plateLength - this.plateLength) * (f1.plateLength - this.plateLength)
                    + (f1.plateSize - this.plateSize) * (f1.plateSize - this.plateSize)
                    + (f1.petalLength - this.petalLength) * (f1.petalLength - this.petalLength)
                    + (f1.petalSize - this.petalSize) * (f1.petalSize - this.petalSize);
        }
    }

    private static List<Flower> readFile(String fileName) {
        List<Flower> dataList = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] dataRow = line.split(",");
                Flower temp = new Flower(Float.valueOf(dataRow[0]),
                        Float.valueOf(dataRow[1]),
                        Float.valueOf(dataRow[2]),
                        Float.valueOf(dataRow[3]));
                dataList.add(temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataList;
    }

}
