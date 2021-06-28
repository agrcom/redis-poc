package multithreading;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

class Processor {

  private int value = 0;
  private List<Integer> collection = new ArrayList<>();
  private final Object lock = new Object();
  private static final int UPPER_LIMIT = 10;
  private static final int LOWER_LIMIT = 0;

  public void producer() throws InterruptedException {
    synchronized (lock) {
      while (true) {
        if (collection.size() == UPPER_LIMIT) {
          System.out.println("Waiting for consumer to consume values... ");
          lock.wait();
        } else {
          System.out.println("Adding " + value);
          collection.add(value++);
          lock.notify();
        }
        Thread.sleep(500);
      }
    }
  }

  public void consumer() throws InterruptedException {
    synchronized (lock) {
      while (true) {
        if (collection.size() == LOWER_LIMIT) {
          System.out.println("Waiting for adding values... ");
          value = 0;
          lock.wait();
          System.out.println("Stopped consuming values");
        } else {
          System.out.println("Consuming " + (collection.size() - 1));
          collection.remove(collection.size() - 1);
          lock.notify();
          // notify will be executed untl theare are operation for this thread or until it will be in WAITING STATE
        }
        Thread.sleep(500);
      }
    }
  }
}

public class App {

  public static void main(String[] args) {
    Processor processor = new Processor();

    Thread t1 =
        new Thread(
            new Runnable() {
              @Override
              public void run() {
                try {
                  processor.producer();
                } catch (InterruptedException e) {
                  e.printStackTrace();
                }
              }
            });

    Thread t2 =
        new Thread(
            new Runnable() {
              @Override
              public void run() {
                try {
                  processor.consumer();
                } catch (InterruptedException e) {
                  e.printStackTrace();
                }
              }
            });

    t1.start();
    t2.start();
  }
}
