package com.zaka;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

@SpringBootApplication
@Slf4j
@EnableAspectJAutoProxy
public class AuthenticationServer implements CommandLineRunner {

    @Value("${server.port}")
    Integer port;

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    ExecutorService executor;

    /*
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(AuthenticationServer.class);
    }

    /*
     *
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        log.info("Authentication server started at: {}", port);
        log.info("Authentication server started at: {}", port);
        log.info("Authentication server started at: {}", port);
        log.info("Authentication server started at: {}", port);

//        final List<A> cfs = List.of(
//                new A("aaaa", 3000),
//                new A("bbbb", 2000),
//                new A("cccc", 5000),
//                new A("dddd", 1000),
//                new A("ffff", 1000),
//                new A("eeeee", 7000)
//        );
//        var a = executor.invokeAll(cfs);
//        log.info("{}", a);
//
//        ExcelUtils.export(
//                SysUser.builder().userName("asd").email("thisEmail@gmail.com").role("ADMIN").build()
//        );
//
//        final List<SysUser> users = List.of(
//                SysUser.builder().userId(0).userName("á").build(),
//                SysUser.builder().userId(0).userName("á").build(),
//                SysUser.builder().userId(2).userName("à").build(),
//                SysUser.builder().userId(3).userName("ả").build(),
//                SysUser.builder().userId(3).userName("ả").build(),
//                SysUser.builder().userId(4).userName("ã").build(),
//                SysUser.builder().userId(3).userName("ả").build()
//        );
//        final Map<Integer, LinkedList<SysUser>> t = users.stream().collect(Collectors.groupingBy(SysUser::getUserId, Collectors.toCollection(LinkedList::new)));
    }

    private int countMinRoom() {
        LinkedHashMap<Integer, Integer> endTimes = new LinkedHashMap<>();

        List<List<Integer>> meetings = List.of(List.of(2, 8), List.of(3, 9), List.of(5, 11), List.of(3, 4), List.of(11, 15), List.of(8, 20));

        meetings.forEach(meeting -> {
            int startTime = meeting.get(0);
            int endTime = meeting.get(1);

            if (endTimes.isEmpty())
                endTimes.put(0, endTime);
            else {
                int amountLessThanEndTime = endTimes.values().stream().filter(value -> value < startTime ).toList().size();
                if (amountLessThanEndTime == 0) {
                    endTimes.put(endTimes.size(), endTime);
                } else {
                    var a = endTimes.entrySet().stream().filter(entry -> entry.getValue() <= startTime).findFirst();
                    endTimes.put(a.get().getKey(), endTime);
                }
            }
        });

        return endTimes.size();
    }

    @AllArgsConstructor
    public static class A implements Callable<String> {
        private String key;
        private long duration;

        @Override
        public String call() throws Exception {
            Thread.sleep(duration);
            return key;
        }
    }
}
