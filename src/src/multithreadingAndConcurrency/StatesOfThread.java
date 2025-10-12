package multithreadingAndConcurrency;

public class StatesOfThread extends Thread{
    @Override
    public void run() {
        System.out.println("RUNNING");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        StatesOfThread statesOfThread = new StatesOfThread();
        System.out.println("Current State: "+statesOfThread.getState());
        statesOfThread.start();
        System.out.println("Current State: "+statesOfThread.getState());
        Thread.sleep(100);
        System.out.println("Current State: "+statesOfThread.getState());
        statesOfThread.join(); // Waits until the main thread finishes executing
        System.out.println("Current State: "+statesOfThread.getState());
    }
}
