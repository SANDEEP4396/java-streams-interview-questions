package multithreadingAndConcurrency.threadCommunicationAndSafety;

public class ThreadCommunication {
    public static void main(String[] args) {
        SharedResources sharedResources = new SharedResources();
        Thread producerThread = new Thread(new Producer(sharedResources));
        Thread consumerThread = new Thread(new Consumer(sharedResources));
        producerThread.start();
        consumerThread.start();
    }
}

class SharedResources {
    private int data;
    private boolean hasData;

    public synchronized void produce(int value) throws InterruptedException {
        while (hasData){
            wait();
        }
        data = value;
        hasData = true;
        System.out.println("Produced: "+value);
        notify();
    }

    public synchronized int consume() throws InterruptedException {
        while (!hasData){
            wait();
        }
        hasData =false;
        System.out.println("Consumed: "+data);
        notify();
        return data;
    }
}

class Producer implements Runnable{
    private final SharedResources resources;

    public Producer(final SharedResources resources){
        this.resources = resources;
    }

    @Override
    public void run() {
        for (int i=0; i<10;i++){
            try {
                resources.produce(i);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
class Consumer implements Runnable {
    private final SharedResources resources;

    public Consumer(final SharedResources resources) {
        this.resources = resources;
    }

    @Override
    public void run() {
        for(int i=0;i<10;i++){
            try {
              int value = resources.consume();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}