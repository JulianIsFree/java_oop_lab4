package nsu.oop.car.factory.lab.model.factory.carfactory;

import nsu.oop.car.factory.lab.model.factory.details.DetailTypeA;
import nsu.oop.car.factory.lab.model.factory.details.DetailTypeB;
import nsu.oop.car.factory.lab.model.factory.details.DetailTypeC;
import nsu.oop.car.factory.lab.model.factory.storage.CarStorage;
import nsu.oop.car.factory.lab.model.factory.storage.DetailStorage;

import java.util.Random;
import java.util.logging.Logger;

public class CarFactoryNet implements Runnable {
    private final Logger logger;

    private int carFactorySize;
    private final CarFactoryWorkerPool carFactory;
    private final Random random;

    private final DetailStorage<DetailTypeA> storageA;
    private final DetailStorage<DetailTypeB> storageB;
    private final DetailStorage<DetailTypeC> storageC;
    private final CarStorage carStorage;

    public CarFactoryNet(int numberOfWorkers, Logger logger, DetailStorage<DetailTypeA> storageA,
                         DetailStorage<DetailTypeB> storageB, DetailStorage<DetailTypeC> storageC, CarStorage carStorage) {
        this.logger = logger;
        this.storageA = storageA;
        this.storageB = storageB;
        this.storageC = storageC;
        this.carStorage = carStorage;

        this.carFactorySize = numberOfWorkers;
        this.carFactory = new CarFactoryWorkerPool(numberOfWorkers, logger);
        this.random = new Random(System.currentTimeMillis());

    }

    @Override
    public void run() {
        for (int i = 0; i < carFactorySize; ++i) {
            submitTask();
        }
    }

    public void submitTask() {
        carFactory.execute(new CreateCarTask(random.nextInt(), carStorage, storageA, storageB, storageC, logger, this));
    }

    public void setPoolSize(int value) {
        if (value > carFactorySize) {
            carFactory.setMaximumPoolSize(value);
            carFactory.setCorePoolSize(value);
        } else {
            carFactory.setCorePoolSize(value);
            carFactory.setMaximumPoolSize(value);
        }

        carFactorySize = value;
    }
}
