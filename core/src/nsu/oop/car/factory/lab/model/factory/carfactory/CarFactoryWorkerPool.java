package nsu.oop.car.factory.lab.model.factory.carfactory;

import nsu.oop.car.factory.lab.model.threadpool.PausableThreadPoolExecutor;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CarFactoryWorkerPool extends PausableThreadPoolExecutor {
    class RejectedCreateCarTaskHandler implements RejectedExecutionHandler {
        private final Logger logger;
        public RejectedCreateCarTaskHandler(Logger logger) {
            this.logger = logger;
        }

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            logger.log(Level.WARNING, "Can't assign new task to build a car");
        }
    }

    public CarFactoryWorkerPool(int size, Logger logger) {
        super(size, size, size);
        setRejectedExecutionHandler(new RejectedCreateCarTaskHandler(logger));
    }
}
