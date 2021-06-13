package nsu.oop.car.factory.lab.model.factory.providers;

import nsu.oop.car.factory.lab.model.threadpool.Task;
import nsu.oop.car.factory.lab.model.factory.storage.DetailStorage;
import nsu.oop.car.factory.lab.model.factory.details.Detail;

import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AddDetailTask<T extends Detail> extends Task {
    private final T detail;
    private final DetailStorage<T> detailStorage;
    private final Logger logger;

    public AddDetailTask(DetailStorage<T> detailStorage, T detail, Logger logger) {
        this.detail = detail;
        this.detailStorage = detailStorage;
        this.logger = logger;
    }

    @Override
    public void run() {
//        synchronized (detailStorage) {
//            while (detailStorage.getCurrentSize() >= detailStorage.getMaxSize()) {
//                try {
//                    detailStorage.wait();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }

            detailStorage.add(detail);
            logger.log(Level.FINE, "Detail " + detail.getId() + " has proceeded to the storage");

    }
}
