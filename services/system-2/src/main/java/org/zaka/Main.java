package org.zaka;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.yaml.snakeyaml.util.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        getServerIndex(4, List.of(0, 2, 4, 5), List.of(7, 4, 1, 4));
    }

    public static void getServerIndex(int n, List<Integer> arrival, List<Integer> burstTimes) {

        int totalRequests = arrival.size();
        int[] completionTimes = new int[totalRequests];
        int[] waitingTimes = new int[totalRequests];

        List<Integer> availableServers = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            availableServers.add(i);
        }

        List<Integer> a = new ArrayList<>();
        for (int i = 0; i < totalRequests; i++) {
            int arrivalTime = arrival.get(i);
            int bt = burstTimes.get(i);

            int minCompletionTime = Integer.MAX_VALUE;
            int selectedServer = -1;

            for (int server : availableServers) {
                if (completionTimes[server] <= arrivalTime && completionTimes[server] < minCompletionTime) {
                    minCompletionTime = completionTimes[server];
                    selectedServer = server;
                }
            }
            a.add(selectedServer);
        }
        log.info("{}", a);
    }
}