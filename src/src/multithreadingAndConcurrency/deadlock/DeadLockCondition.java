package multithreadingAndConcurrency.deadlock;

public class DeadLockCondition {
 public static void main(String[] args) {

        Pen pen = new Pen();
        Paper paper = new Paper();
        Thread thread1 = new Thread(new Task1(pen, paper), "Thread-1");
        Thread thread2 = new Thread(new Task2(pen, paper), "Thread-2");

        thread1.start();
        thread2.start();
    }
}

class Pen {
    public synchronized void writeWithPenAndPaper(Paper paper) {
        System.out.println(Thread.currentThread().getName() + " is using pen" + this + " and trying to lock paper");
        paper.finishWriting();
    }

    public synchronized void finishWriting() {
        System.out.println(Thread.currentThread().getName() + " finished using pen " + this);
    }
}

class Paper {
    public synchronized void writeWithPaperAndPen(Pen pen) {
        System.out.println(Thread.currentThread().getName() + " is using paper" + this + " and trying to lock pen");
        pen.finishWriting();
    }

    public synchronized void finishWriting() {
        System.out.println(Thread.currentThread().getName() + " finished using paper " + this);
    }
}

class Task1 implements Runnable {
    private final Pen pen;
    private final Paper paper;

    public Task1(final Pen pen,final Paper paper) {
        this.pen = pen;
        this.paper = paper;
    }


    @Override
    public void run() {
        pen.writeWithPenAndPaper(paper); // Thread1 multithreadingAndConcurrency.locks pen and tries to lock paper.
    }
}

class Task2 implements Runnable {
    private final Pen pen;
    private final Paper paper;

    public Task2(final Pen pen, final Paper paper) {
        this.pen = pen;
        this.paper = paper;
    }


    @Override
    public void run() {
        synchronized (pen){
            paper.writeWithPaperAndPen(pen); // Acquires paper lock only when it has the pen lock to avoid multithreadingAndConcurrency.deadlock.
        }
     //   paper.writeWithPaperAndPen(pen); // Thread2 multithreadingAndConcurrency.locks paper and tries to lock pen.
    }
}