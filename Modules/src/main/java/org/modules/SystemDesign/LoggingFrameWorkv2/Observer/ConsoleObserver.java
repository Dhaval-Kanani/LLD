package org.modules.SystemDesign.LoggingFrameWorkv2.Observer;

import org.modules.SystemDesign.LoggingFrameWorkv2.LogMessage;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;

public class ConsoleObserver implements LogObserver {
    private BlockingDeque<LogMessage> logQueue;
    private AtomicBoolean isRunning = new AtomicBoolean(true);

    public ConsoleObserver() {
        this.logQueue = new LinkedBlockingDeque<>();
        consumeLogs();
    }

    private void consumeLogs(){
        CompletableFuture.runAsync(()->{
            while(isRunning.get() || !logQueue.isEmpty()){
                LogMessage logMessage = logQueue.poll();
                if(logMessage!=null){
                    System.out.println("Console: " + logMessage.getDetailedMessage());
                }
            }
        });
    }

    @Override
    public void log(LogMessage logMessage) {
        logQueue.offer(logMessage);
    }

    public void stop(){
        isRunning.set(false);
    }
}
