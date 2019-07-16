package lecture;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/*
184.105.178.84      184.105.178.84
198.57.247.178      198.57.247.178
192.185.4.18        192.185.4.18
183.129.177.209     198.71.81.66
199.59.241.250      185.129.62.63
185.53.177.8        192.185.4.104
184.168.224.177     183.129.177.209
192.185.4.104       184.168.224.179
184.168.224.181     182.50.130.69
184.168.152.150     185.6.242.251
 */

public class Week13 {
    public static final String DATA_FILE = "data.csv";
    public static final String TEST_DATA_FILE = "data_test.csv";
    public static final double DAMPING_FACTOR = 0.99;
    public static final int ITERATION = 500;
    private static long start, end;

    public static void main(String[] args) {

        //프로그램이 시작하는 시점 계산
        start = System.currentTimeMillis();

        // 파일 읽기
        List<DataRow> dataList = readFile(DATA_FILE);

        // IP 주소 Set 초기화
        Set<String> ipAddressSet = dataList.stream().map(DataRow::getSrc).collect(Collectors.toSet());
        ipAddressSet.addAll(dataList.stream().map(DataRow::getDest).collect(Collectors.toList()));

        // 각 노드 Map 초기화
        Map<String, IPAddress> ipAddressMap = initMap(ipAddressSet, dataList);

        // 각 페이지별로 Rank 계산
        calculate(ipAddressMap);


        //프로그램이 끝나는 시점 계산
        end = System.currentTimeMillis();

        // 상위 10개 출력
        printInfo(ipAddressMap);
    }

    private static List<DataRow> readFile(String fileName) {
        List<DataRow> dataList = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] dataRow = line.split(",");
                DataRow tmpList = new DataRow(dataRow[0], dataRow[1]);
                dataList.add(tmpList);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataList;
    }

    private static Map<String, IPAddress> initMap(Set<String> ipAddressSet, List<DataRow> dataList) {
        Map<String, IPAddress> ipAddressMap = new HashMap<>();

        double initRank = 1.0 / (double) ipAddressSet.size();

        ipAddressSet.forEach(e -> {
            IPAddress ipAddress = new IPAddress(e);
            ipAddress.setRank(initRank);
            ipAddressMap.put(e, ipAddress);
        });

        for (DataRow dataRow : dataList) {
            IPAddress srcIP = ipAddressMap.get(dataRow.src);
            IPAddress destIP = ipAddressMap.get(dataRow.dest);
            destIP.addInBound(srcIP);
            srcIP.addOutBound(destIP);
        }

        return ipAddressMap;
    }

    private static void calculate(Map<String, IPAddress> ipAddressMap) {
        for (int i = 0; i < ITERATION; i++) {
            Map<String, IPAddress> temp = new HashMap<>(ipAddressMap);
            for (String key : ipAddressMap.keySet()) {
                double inboundRankSum = 0;

                for (IPAddress inbound : temp.get(key).getInbounds()) {
                    double pr = inbound.getRank();
                    int outboundSize = inbound.getOutbounds().size();
                    inboundRankSum += pr / (double) outboundSize;
                }

                // 새로운 랭크 계산식
                // (1-d)/N + d(PR(A)/C(A) + PR(B)/C(B) + ... + PR(n)/C(n))
                double newRank = (1 - DAMPING_FACTOR) / ipAddressMap.size() + DAMPING_FACTOR * inboundRankSum;

                ipAddressMap.get(key).setRank(newRank);
            }
        }
    }

    private static void printInfo(Map<String, IPAddress> ipAddressMap) {
        List<IPAddress> ipAddressList = new ArrayList<>(ipAddressMap.values());

        Collections.sort(ipAddressList);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            sb.append(ipAddressList.get(i).getAddress()).append('\n');
        }
        sb.append("iteration: ").append(ITERATION).append('\n');
        sb.append("time: ").append((end - start) / 1000.0).append("(s)\n");
        System.out.println(sb.toString());
    }

    static class DataRow {
        private final String src;
        private final String dest;

        public DataRow(String src, String dest) {
            this.src = src;
            this.dest = dest;
        }

        public String getSrc() {
            return src;
        }

        public String getDest() {
            return dest;
        }
    }

    static class IPAddress implements Comparable<IPAddress> {
        private final String address;
        private double rank;
        private List<IPAddress> inbounds;
        private List<IPAddress> outbounds;

        public IPAddress(String address) {
            this.address = address;
            this.inbounds = new ArrayList<>();
            this.outbounds = new ArrayList<>();
        }

        public String getAddress() {
            return address;
        }

        public double getRank() {
            return rank;
        }

        public void setRank(double rank) {
            this.rank = rank;
        }

        public List<IPAddress> getInbounds() {
            return inbounds;
        }

        public void addInBound(IPAddress references) {
            this.inbounds.add(references);
        }

        public List<IPAddress> getOutbounds() {
            return outbounds;
        }

        public void addOutBound(IPAddress references) {
            this.outbounds.add(references);
        }

        @Override
        public int compareTo(IPAddress o) {
            return -Double.compare(this.rank, o.getRank());
        }
    }
}
