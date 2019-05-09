package com.javabase.concurrency.executor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

public abstract class AbstractExecutorService1 implements ExecutorService {
    protected <T> RunnableFuture newTaskFor(Callable<T> callable){
        return new FutureTask(callable);
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
        if (tasks == null) throw new NullPointerException();
        List<Future<T>> futureList = new ArrayList<>(tasks.size());
        boolean isDone = false;
        try {
            for (Callable<T> task:
                    tasks) {
                RunnableFuture<T> future = newTaskFor(task);
                futureList.add(future);
                execute(future);
            }
            for (int i = 0, size = tasks.size(); i < size; i++) {//这里要使用lambda表达式就得处理InterruptedException
                Future<T> future = futureList.get(i);
                if (!future.isDone()){
                    try {
                        future.get();
                    }
                    catch (CancellationException ignored){}
                    catch (ExecutionException ignored){}
                }
            }
            isDone = true;
            return futureList;
        } finally {
            if (!isDone){
                for (Future<T> future:
                     futureList) {
                    future.cancel(true);
                }
            }
        }
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException {
        if (tasks == null) throw new NullPointerException();
        List<Future<T>> futures = new ArrayList<>(tasks.size());
        boolean isDone = false;
        long nanos = unit.toNanos(timeout);

        try{
            tasks.forEach(t -> futures.add(newTaskFor(t)));

            final int size = tasks.size();
            final long deadLine = System.nanoTime() + nanos;

            for (int i = 0; i < size; i++) {//这里要修改nanos，因此不能使用lambda表达式
                if ((nanos = deadLine - System.nanoTime()) <= 0L)
                    return futures;
                execute((Runnable) futures.get(i));
            }
            for (int i = 0; i < size; i++) {//因为InterruptedException的原因，也不能使用lambda表达式
                if ((nanos = deadLine - System.nanoTime()) <= 0L)
                    return futures;
                Future<T> f = futures.get(i);
                try {
                    f.get(nanos, unit);
                } catch (CancellationException ignored) {
                } catch (ExecutionException ignored){
                } catch (TimeoutException e){
                    return futures;
                }
            }
            isDone = true;
            return futures;
        }
        finally {
            if (!isDone) {
                futures.forEach(f -> f.cancel(true));
            }
        }
    }
}
