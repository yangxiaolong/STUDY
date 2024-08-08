package mt;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @auther yangxiaolong
 * @create 2024/7/19
 */
public class CompletableFutureTest {

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);

        CompletableFuture<String> supplyAsync = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "supplyAsync 1");
            return "hello";
        }, threadPool);
        CompletableFuture<String> stringCompletableFuture = supplyAsync.thenApplyAsync(s -> {
            System.out.println(Thread.currentThread().getName() + "thenApplyAsync 2");
            return s + " world";
        }, threadPool);
        CompletableFuture<String> stringCompletableFuture1 = stringCompletableFuture.thenApplyAsync(s -> {
            System.out.println(Thread.currentThread().getName() + "thenApplyAsync 3");
            return s;
        }, threadPool);
        stringCompletableFuture1.thenAccept(s -> {
            System.out.println(Thread.currentThread().getName() + "thenAccept 4");
            threadPool.shutdown();
        });
    }

}
