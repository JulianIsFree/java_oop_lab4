package nsu.oop.car.factory.lab.model.factory.providers;

import nsu.oop.car.factory.lab.model.threadpool.PausableThreadPoolExecutor;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProviderPool extends PausableThreadPoolExecutor {
    class RejectedCreateDetailTaskHandler implements RejectedExecutionHandler {
        private final Logger logger;
        public RejectedCreateDetailTaskHandler(Logger logger) {
            this.logger = logger;
        }

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            logger.log(Level.WARNING, "Provider's detail was rejected");
        }
    }

    public ProviderPool(int size, Logger logger) {
        super(size, size, size);
        setRejectedExecutionHandler(new RejectedCreateDetailTaskHandler(logger));
    }
}
