package multithreadingAndConcurrency.executorsFramework;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class RunnableVsCallable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> future = executorService.submit(()-> "Hello I am Callable returning value");
        Future<?> future2 = executorService.submit(()-> System.out.println("Hello I am runnable and I wont return value"));
        Future<String> tFuture = executorService.submit(()-> System.out.println("I am runnable but I can return a value you except"), "Success");

        System.out.println(future.get()); // Callable throws exception
        System.out.println("Is Runnable executed? "+ future2.isDone());
        System.out.println("Is the executor finished? "+executorService.isTerminated());
        System.out.println(tFuture.get());
        executorService.shutdown();
        Thread.sleep(1000);
        System.out.println("Is the executor finished? "+executorService.isTerminated());


        System.out.println("==========================");
        //Invokes all the threads.
        ExecutorService executorService2 = Executors.newFixedThreadPool(3);
        Callable<Integer> callable1 = () ->{ Thread.sleep(1000);return  1;};
        Callable<Integer> callable2 = () -> 2;
        Callable<Integer> callable3 = () -> 3;
        List<Callable<Integer>> futureList = Arrays.asList(callable1,callable2,callable3);
        System.out.println("----------Executing list of callable-------");
       List<Future<Integer>> result =  executorService2.invokeAll(futureList);
        for(Future<Integer> f: result){
            System.out.println("Res: "+f.get());
        }
       Integer i = executorService2.invokeAny(futureList);
        System.out.println("Invoke any returned the result of : "+i);
        executorService2.shutdown();
    }
}
