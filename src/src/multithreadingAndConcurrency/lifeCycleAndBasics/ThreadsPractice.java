package multithreadingAndConcurrency.lifeCycleAndBasics;

/**
 * Thread lifecycle
 * "New": A thread is not started yet but it's created
 * "Runnable": After a thread is started it's ready to run and waiting for CPU time.
 * "Running": The thread is in this state when it's executing
 * "Blocked/waiting": A thread is in this state when it's waiting for a resource used by another process.
 * "Terminated": A thread is in this state when it has finished executing.
 */
public class ThreadsPractice {
    public static void main(String[] args) {
        System.out.println("Hello this is a process");

        World world = new World(); // New: Thread created
        MultiThreadPractice multiThreadPractice = new MultiThreadPractice(); // New: Thread created
        world.start(); // In Runnable state
        // This will use the same thread as main so we need to follow a different step for multithread
        // multiThreadPractice.run();
        // Method 2 If implementing runnable then you need to assign this to a thread
        Thread th = new Thread(multiThreadPractice);
        th.start();
        for (int i = 0; i < 1000; i++) {
            System.out.println("Hello -> " + Thread.currentThread().getName());
        }
    }
}

// Can either extend a thread or implement runnable interface
class World extends Thread {
   // Running state
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("World ->" + Thread.currentThread().getName());
        }
    }
}

class MultiThreadPractice implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("Practicing Multithread ->" + Thread.currentThread().getName());
        }
    }
}