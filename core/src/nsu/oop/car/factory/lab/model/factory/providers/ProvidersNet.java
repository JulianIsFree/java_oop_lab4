package nsu.oop.car.factory.lab.model.factory.providers;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public abstract class ProvidersNet implements Runnable {
    private AtomicInteger delay;
    protected int poolSize;
    protected final Logger logger;
    protected final ProviderPool providerPool;
    protected final Random random;

    public ProvidersNet(int delay, int poolSize, Logger logger) {
        this.delay = new AtomicInteger(delay);
        this.poolSize = poolSize;
        this.logger = logger;
        this.providerPool = new ProviderPool(poolSize, logger);
        this.random = new Random(System.currentTimeMillis());
    }

    public int getDelay() {
        return delay.get();
    }

    public void setDelay(int delay) {
        this.delay.set(delay);
    }

    protected abstract void execute();

    //TODO: wait until free space
    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(delay.get());
                execute();
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
