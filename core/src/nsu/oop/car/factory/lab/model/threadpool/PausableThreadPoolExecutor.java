package nsu.oop.car.factory.lab.model.threadpool;

import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class PausableThreadPoolExecutor extends ThreadPoolExecutor {
    private boolean isPaused;
    private ReentrantLock lock;
    private Condition unpausedCondition;

    public PausableThreadPoolExecutor(int corePoolSize, int maximumPoolSize, int queueSize) {
        this(corePoolSize, maximumPoolSize, new ArrayBlockingQueue<Runnable>(queueSize));
    }

    protected PausableThreadPoolExecutor(int corePoolSize, int maximumPoolSize, BlockingQueue<Runnable> queue) {
        super(corePoolSize, maximumPoolSize, 1L, TimeUnit.SECONDS, queue);
        isPaused = false;
        lock = new ReentrantLock();
        unpausedCondition = lock.newCondition();
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        lock.lock();
        try {
            while(isPaused) unpausedCondition.await();
        } catch (InterruptedException e) {
            t.interrupt();
        } finally {
            lock.unlock();
        }
    }

    public void pause() {
        lock.lock();
        try {
            isPaused = true;
        } finally {
            lock.unlock();
        }
    }

    public void resume() {
        lock.lock();
        try {
            isPaused = false;
            unpausedCondition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public boolean isPaused() {
        lock.lock();
        boolean res;
        try {
            res = isPaused;
        } finally {
            lock.unlock();
        }
        return res;
    }
}
