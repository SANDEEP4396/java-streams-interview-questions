package locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private int balance = 100;
    // Reentrant lock is designed to avoid deadlocks. if you keep trying to acquire the lock again in a loop, it will handle it
    private final Lock lock = new ReentrantLock();

    //Using synchronized can cause intrinsic lock which won't be release until t1 is complete
    //So use extrinsic lock to manage your threads
    public void withDraw(int amount)  {
        System.out.println(Thread.currentThread().getName() + " attempting to withdraw amount: " + amount);
        try {
            if (lock.tryLock(1000, TimeUnit.MILLISECONDS)){
                if (balance >= amount) {
                    System.out.println(Thread.currentThread().getName() + " proceeding with withdrawal");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        System.out.println("Interrupting thread");
                        Thread.currentThread().interrupt();
                    }
                    finally {
                        lock.unlock();
                    }
                    balance -= amount;
                    System.out.println("Completed withdrawal, Remaining amount: " + balance);
                } else {
                    System.out.println(Thread.currentThread().getName() + " insufficient balance");
                }
            }else {
                System.out.println(Thread.currentThread().getName()+" Could not acquire the lock will try again");
            }
        } catch (InterruptedException e) {
            // Good practice
            System.out.println("Interrupting thread");
            Thread.currentThread().interrupt();
        }
        if (Thread.currentThread().isInterrupted()){
            System.out.println("Clean up");
        }

    }
}

class Test {
    public static void main(String[] args) throws InterruptedException {
        BankAccount bankAccount = new BankAccount();
        Runnable task = () -> {
            try {
                bankAccount.withDraw(50);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
        Thread t1 = new Thread(task, "Thread 1");
        Thread t2 = new Thread(task, "Thread 2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Transaction Complete, take your card");
    }
}
