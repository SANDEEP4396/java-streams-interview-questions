package synchronization;

public class Counter {
    private int count =0;

    //Either make the entire method synchronized or block of code
    public void increment(){
        synchronized (this){
            count++;
        }
    }


    public int getCount() {
        return count;
    }
}

class TestCounter{
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        MyThread t1 = new MyThread(counter);
        MyThread t2 = new MyThread(counter);
        MyThread t3 = new MyThread(counter);
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        System.out.println(counter.getCount());
    }
}

class MyThread extends Thread{
    private final Counter counter;
    public MyThread(Counter counter){
        this.counter=counter;
    }
    @Override
    public void run() {
        for(int i=0;i<1000;i++){
            counter.increment();
        }
    }
}