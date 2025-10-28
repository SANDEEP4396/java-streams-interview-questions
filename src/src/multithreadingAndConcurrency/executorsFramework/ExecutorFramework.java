package multithreadingAndConcurrency.executorsFramework;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ExecutorFramework {

    static class OldWayToUtilizeThreads {
        // This is the old way of creating and managing the threads
        public static void main(String[] args) throws InterruptedException {
            long startTime = System.currentTimeMillis();
            Thread[] threads = new Thread[9];
            for (int i = 1; i < 10; i++) {
                int finalI = i;
                threads[i - 1] = new Thread(() -> {
                    Long result = factorial(finalI);
                    System.out.println("Factorial of: " + finalI + " is -> " + result);
                });
                threads[i - 1].start();
            }
            for (Thread thread : threads) {
                thread.join();
            }
            System.out.println("Total Time: " + (System.currentTimeMillis() - startTime));
        }
    }

    static class NewWayToUseExecutorFrameWork {
        public static void main(String[] args) throws InterruptedException {
            long startTime = System.currentTimeMillis();
            ExecutorService executor = Executors.newFixedThreadPool(9);

            IntStream.range(1, 10).forEach(i -> executor.submit(() -> {
                Long result = factorial(i);
                System.out.println("Factorial of: " + i + " is -> " + result);
            }));

            executor.shutdown();
            // locks until all tasks have completed execution after a shutdown request,
            // or the timeout occurs, or the current thread is interrupted, whichever happens first.
            executor.awaitTermination(10, TimeUnit.SECONDS);
            System.out.println("Total Time: " + (System.currentTimeMillis() - startTime));

        }
    }


    private static Long factorial(int n)  {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        long result = 1L;
        if (n == 0)
            return result;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}
