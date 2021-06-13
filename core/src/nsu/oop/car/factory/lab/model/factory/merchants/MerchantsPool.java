package nsu.oop.car.factory.lab.model.factory.merchants;

import nsu.oop.car.factory.lab.model.threadpool.PausableThreadPoolExecutor;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MerchantsPool extends PausableThreadPoolExecutor {
    private class RejectedSellTaskHandler implements RejectedExecutionHandler {
        private Logger logger;
        public RejectedSellTaskHandler(Logger logger) {
            this.logger = logger;
        }

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            logger.log(Level.WARNING, "Can't assign new car selling task");
        }
    }

    public MerchantsPool(int size, Logger logger) {
        super(size, size, size);
        setRejectedExecutionHandler(new RejectedSellTaskHandler(logger));
    }
}
