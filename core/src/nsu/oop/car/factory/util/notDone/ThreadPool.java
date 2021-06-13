package nsu.oop.car.factory.util.notDone;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public abstract class ThreadPool {
    private enum State {
        INITIALISED,
        RUN,
        TERMINATED,
        EXCEPTION
    }

    class Worker implements Runnable {
        private final ThreadPool threadPool;
        private final Runnable task;
        private State state;
        public Worker(Runnable task, ThreadPool threadPool) {
            this.threadPool = threadPool;
            this.task = task;

            state = State.INITIALISED;
        }

        //TODO: check if it is correct in terms of Java OOP design
        @Override
        public void run() {
            state = State.RUN;
            threadPool.beforeTask(this, task);
            try {
                task.run();
            } catch (Exception exception) {
                threadPool.handleInterrupted(task, exception);
                state = State.EXCEPTION;
            }

            threadPool.afterTask(this, task);

            if (state.equals(State.RUN))
                state = State.TERMINATED;
        }

        State getState() {
            return state;
        }
    }


    //TODO: initialise
    private ReentrantReadWriteLock lockForTasks;
    private ReentrantReadWriteLock lockForThreads;
    private final RejectedTaskHandler rejectedTaskHandler;

    private int maxSize;
    private int coreSize;

    //TODO: implement using of these fields
    private int currentFreeThreadsCount;
    private int currentThreadsCount;
    private ArrayBlockingQueue<Worker> threads;

    private BlockingQueue<Runnable> tasks;

    public ThreadPool(int coreSize, int maxSize, BlockingQueue<Runnable> tasks, RejectedTaskHandler rejectedTaskHandler) {
        assert coreSize <= maxSize;

        threads = new ArrayBlockingQueue<>(maxSize, true);
        this.coreSize = coreSize;
        this.maxSize = maxSize;

        this.currentFreeThreadsCount = 0;
        this.currentThreadsCount = 0;

        this.lockForThreads = new ReentrantReadWriteLock();
        this.lockForTasks = new ReentrantReadWriteLock();
        this.tasks = tasks;
        this.rejectedTaskHandler = rejectedTaskHandler;
    }

    public void submit(Runnable task) throws Exception {
        if (currentFreeThreadsCount == 0) {
            tryCreateNewFreeThread();
            if (currentFreeThreadsCount == 0) {
                rejectedTaskHandler.handleRejected(task);
                return;
            }
        }
        //TODO: execute
    }


    public RejectedTaskHandler getRejectedTaskHandler() {
        return rejectedTaskHandler;
    }

    protected abstract void beforeTask(Worker thread, Runnable task); //TODO: implement picking from free thread queue
    protected abstract void afterTask(Worker thread, Runnable task); //TODO: implement reverse to beforeTask(...)
    protected abstract void handleInterrupted(Runnable task, Exception exception); //TODO: implement
    protected abstract void tryCreateNewFreeThread();
}
