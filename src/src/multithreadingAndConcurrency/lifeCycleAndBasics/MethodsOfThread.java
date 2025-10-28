package multithreadingAndConcurrency.lifeCycleAndBasics;

public class MethodsOfThread extends Thread{
    public MethodsOfThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        for(int i =1;i<10;i++){
            try {
                System.out.println(Thread.currentThread().getName()+": Priority: "+ Thread.currentThread().getPriority()+": count"+ i);
                Thread.sleep(500);
                Thread.yield(); // Give chance for other threads.
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MethodsOfThread mediumPriority = new MethodsOfThread("Medium Thread");
        MethodsOfThread lowPriorityThread = new MethodsOfThread("Low Thread");
        MethodsOfThread maxPriorityThread = new MethodsOfThread("Max Thread");
     //   mt.setName("Main Thread"); OR use this to set the name instead of constructor
        mediumPriority.setPriority(Thread.NORM_PRIORITY);
        lowPriorityThread.setPriority(Thread.MIN_PRIORITY);
        maxPriorityThread.setPriority(Thread.MAX_PRIORITY);
        lowPriorityThread.start();
        mediumPriority.start();
        maxPriorityThread.start();

        maxPriorityThread.join();
        lowPriorityThread.join();
        mediumPriority.join();

        System.out.println("finished executing all threads");

        //Set priority of the Thread.

    }
}

class DemonThread extends Thread{
    @Override
    public void run() {
        while (true){
            System.out.println("running thread");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DemonThread demonThread = new DemonThread();
        demonThread.setDaemon(true); // Exits the thread once the main is finished
        demonThread.start();
      //  demonThread.join(); then set daemon fails
        DemonThread userThread = new DemonThread();
        userThread.start(); // Keeps running because user thread
        System.out.println("finished");
    }
}