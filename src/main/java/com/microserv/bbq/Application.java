package com.microserv.bbq;

import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.stream.Collectors;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

//        String testStr0 = repStr("post:/{user}/one/getting/{id}");
//        System.out.println(testStr0 + " : " + DigestUtil.md5Hex(testStr0));
//
//        String testStr1 = repStr("post:/{user}/one/getting/{name}");
//        System.out.println(testStr1 + " : " + DigestUtil.md5Hex(testStr1));
//
//        String testStr2 = repStr("post:/{user}/one/getting/{username}");
//        System.out.println(testStr2 + " : " + DigestUtil.md5Hex(testStr2));
    }

    public static String repStr(String restfulStr) {
        if (restfulStr == null || restfulStr.length() == 0) return "";
        StringBuilder sb = new StringBuilder();
        int leftC = 0;
        for (int i = 0; i < restfulStr.length(); i++) {
            char character = restfulStr.charAt(i);
            if (character == '{') {
                sb.append(character).append("s");
                leftC += 1;
            }
            if (character == '}') {
                leftC -= 1;
            }

            if (leftC == 0)
                sb.append(character);
        }
        return sb.toString();

    }

    @Data
    public static class CallDataResult {
        private String ans;

        public static CallDataResult valueOf(Function<Integer, CallDataResult> function, Integer intNum) {
            return function.apply(intNum);
        }
    }

    public List<CallDataResult> transform(List<Integer> list, Function<Integer, CallDataResult> function) {
        List<CallDataResult> results = list.stream()
                .map(e -> CompletableFuture.supplyAsync(
                        () -> function.apply(e),
                        Executors.newScheduledThreadPool(10)
                ))
                .collect(Collectors.toList())
                .stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());

        System.out.println(results);
        return results;
    }
}
