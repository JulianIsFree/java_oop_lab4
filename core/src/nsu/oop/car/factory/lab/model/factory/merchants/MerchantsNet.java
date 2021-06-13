package nsu.oop.car.factory.lab.model.factory.merchants;

import nsu.oop.car.factory.lab.model.factory.carfactory.Controller;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class MerchantsNet implements Runnable {
    private final Logger logger;
    private final Controller factoryController;
    private MerchantsPool merchants;
    private AtomicInteger delay;
    private int poolSize;

    public MerchantsNet(int delay, int numberOfMerchants, Logger logger, Controller factoryController) {
        this.logger = logger;
        this.factoryController = factoryController;
        this.merchants = new MerchantsPool(numberOfMerchants, logger);
        this.delay = new AtomicInteger(delay);
        this.poolSize = numberOfMerchants;
    }

    public void setDelay(int delay) {
        this.delay.set(delay);
    }

    public int getDelay() {
        return delay.get();
    }

    @Override
    public void run() {
        while(true) {
            try{
                Thread.sleep(delay.get());
                factoryController.carStorage().waitUntilNotEmpty();
                for (int i = 0; i < poolSize; ++i)
                    merchants.execute(new SellCarTask(logger, factoryController));
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public void setPoolSize(int value) {
        if (value > poolSize) {
            merchants.setMaximumPoolSize(value);
            merchants.setCorePoolSize(value);
        } else {
            merchants.setCorePoolSize(value);
            merchants.setMaximumPoolSize(value);
        }

        poolSize = value;
    }
}
