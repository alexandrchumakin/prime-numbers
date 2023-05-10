package org.achumakin;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

public class PrimePoolCalc {

    public static void main(String[] args) throws InterruptedException {
        System.out.println(PrimePoolCalc.getPrimes(Integer.parseInt(args[0])));
    }

    public static List<Integer> getPrimes(int maxPrime) throws InterruptedException {
        var cores = Runtime.getRuntime().availableProcessors();
        var pool = Executors.newFixedThreadPool(cores * 2);
        var latch = new CountDownLatch(maxPrime - 2);
        Queue<Integer> numbersQueue = new ConcurrentLinkedQueue<>();
        for (int i = 2; i <= maxPrime; i++) {
            final int finalI = i;
            pool.submit(() -> {
                if (PrimeNumber.isPrime(finalI)) {
                    numbersQueue.add(finalI);
                }
                latch.countDown();
            });
        }
        latch.await();
        pool.shutdownNow();
        return numbersQueue.stream().toList();
    }

}
