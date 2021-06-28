package multithreading;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
class Task implements Callable<String> {

  @Setter @Getter private long id;

  public Task(long id) {
    this.id = id;
  }

  @Override
  public String call() throws Exception {
    var duration = (long) (Math.random() * 5);
    TimeUnit.SECONDS.sleep(duration);
    log.info("Task with id " + id + " working on thread: " + Thread.currentThread().getId() + " with Duration: " + duration);
    return "id: " + id;
  }
}

@Slf4j
public class FixedThreadPool {
  public static void main(String[] args) {
    ExecutorService executorService = Executors.newFixedThreadPool(4);
    ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);

    for (int i = 0; i < 42; i++) {
      executorService.submit(new Task(i));
    }

    executorService.shutdown();

    try {
      executorService.awaitTermination(2, TimeUnit.SECONDS);
      log.info("Awaiting termination...");
    } catch (InterruptedException e) {
      log.error("InterruptedException during Awaiting termination...");
      e.printStackTrace();
    }
  }
}
