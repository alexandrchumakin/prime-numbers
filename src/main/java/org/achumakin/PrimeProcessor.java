package org.achumakin;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PrimeProcessor {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        if (!args[0].matches("-?\\d+")) {
            throw new RuntimeException(String.format("'%s' is not a valid integer", args[0]));
        }
        System.out.println(PrimeProcessor.getPrimes(Integer.parseInt(args[0])));
        System.out.printf("Execution took %s seconds%n", (System.currentTimeMillis() - startTime) / 1000);
    }

    public static List<Integer> getPrimes(int maxPrime) {
        final int cores = Runtime.getRuntime().availableProcessors();
        // by default stream uses one less than the number of cores, but for simple functions we can increase it
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", Integer.toString(cores * 2));
        return IntStream.range(2, maxPrime)
                .parallel()
                .sorted()
                .filter(PrimeProcessor::isPrime)
                .boxed()
                .collect(Collectors.toList());
    }

    private static boolean isPrime(int num) {
        if (num == 2) {
            return true;
        }

        for (int i = 2; i <= num / 2; i++) {
            if ((num % i) == 0)
                return false;
        }

        return true;
    }

}
