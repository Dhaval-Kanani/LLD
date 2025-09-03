package org.modules.ThreadTutorials;

import java.util.concurrent.CompletableFuture;

public class Threads {
    public static void main(String[] args){
        System.out.println("------------------------------");
        System.out.println("CompletableFuture Use cases - ");

        // 1. Use Case of supplyAsync
        System.out.println("Use Case of supplyAsync - ");
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(()-> "World");
        // Use case of thenAccept
        f1.thenAccept(result ->{
            System.out.println("Hello " + result);
        });

        // Use Case of Join
        String result = f1.join();
        System.out.println("Result from join: " + result);

        System.out.println("------------------------------");

        // 2. Use Case of runAsync
        System.out.println("Use Case of runAsync - ");
        CompletableFuture<Void> f2 = CompletableFuture.runAsync(()->{
            System.out.println("Running Async.....");
        });

        f2.join();

        System.out.println("------------------------------");

        // 3. Use case of ApplyAsync
        System.out.println("Use Case of applyAsync - ");
        CompletableFuture<String> f3 = CompletableFuture.supplyAsync(() -> "f3");

        CompletableFuture<Integer> f4 = f3.thenApplyAsync(r3 ->{
            System.out.println("Thread: " + Thread.currentThread().getName());
            System.out.println("Applying to: " + r3);
            System.out.println("ApplyAsync returns result in the format of applied result. Here Int");
            return r3.length();
        });

        f4.thenAccept(r4 ->{
            System.out.println("Thread: " + Thread.currentThread().getName());
            System.out.println(r4);
        });

        System.out.println("------------------------------");

        // 4. Use Case of ComposeAsync
        System.out.println("Use Case of composeAsync - ");
        CompletableFuture<String> f5 = CompletableFuture.supplyAsync(()->"f5");

        CompletableFuture<String> f6 = f5.thenComposeAsync(r5 -> {
            System.out.println("ApplyAsync returns result in the format CompletableFuture.");
            System.out.println("CompletableFuture wraps the result automatically.");
            return CompletableFuture.supplyAsync(()-> r5 + " composed to f6");
        });

        f6.thenAccept(r6 -> {
            System.out.println(r6);
        });

        // Regular versions (use same thread or fork-join pool)
        //.thenApply(...)
        //.thenCompose(...)

        // Async versions (definitely use a separate thread)
        //.thenApplyAsync(...)
        //.thenComposeAsync(...)
    }
}
