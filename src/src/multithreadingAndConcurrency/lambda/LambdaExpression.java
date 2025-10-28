package multithreadingAndConcurrency.lambda;

public class LambdaExpression {
    public static void main(String[] args) {
        Runnable runnable = () -> System.out.println("Hey you just used Lambda");
        Thread t1 = new Thread(runnable);
        //OR
        Thread t2 = new Thread(()-> System.out.println("Thread 2 running")) ;

        t1.start();
        t2.start();
    }
}
