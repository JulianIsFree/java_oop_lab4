package nsu.oop.car.factory.lab.model.factory.carfactory;

import nsu.oop.car.factory.lab.model.threadpool.Task;
import nsu.oop.car.factory.lab.model.factory.details.DetailTypeA;
import nsu.oop.car.factory.lab.model.factory.details.DetailTypeB;
import nsu.oop.car.factory.lab.model.factory.details.DetailTypeC;
import nsu.oop.car.factory.lab.model.factory.storage.CarStorage;
import nsu.oop.car.factory.lab.model.factory.storage.DetailStorage;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CreateCarTask extends Task {
    private final CarStorage carStorage;
    private final DetailStorage<DetailTypeA> detailStorageA;
    private final DetailStorage<DetailTypeB> detailStorageB;
    private final DetailStorage<DetailTypeC> detailStorageC;

    private final int id;
    private DetailTypeA detailA;
    private DetailTypeB detailB;
    private DetailTypeC detailC;
    private final CarFactoryNet carFactoryNet;

    private final Logger logger;

    public CreateCarTask(int id, CarStorage carStorage, DetailStorage<DetailTypeA> detailStorageA,
                         DetailStorage<DetailTypeB> detailStorageB, DetailStorage<DetailTypeC> detailStorageC,
                         Logger logger, CarFactoryNet carFactoryNet) {
        this.id = id;
        this.carStorage = carStorage;
        this.detailStorageA = detailStorageA;
        this.detailStorageB = detailStorageB;
        this.detailStorageC = detailStorageC;

        this.carFactoryNet = carFactoryNet;

        this.logger = logger;

        this.detailA = null;
        this.detailB = null;
        this.detailC = null;
    }

    @Override
    public void run() {

        if (detailA == null) {
            detailA = detailStorageA.extractFirst();
            logger.log(Level.FINE, "Detail " + detailA + " is extracted for car " + id);

        }

        if (detailB == null) {
            detailB = detailStorageB.extractFirst();
            logger.log(Level.FINE, "Detail " + detailB + " is extracted for car " + id);
        }

        if (detailC == null) {
            detailC = detailStorageC.extractFirst();
            logger.log(Level.FINE, "Detail " + detailC + " is extracted for car " + id);
        }

        Car car = new Car(id, detailA, detailB, detailC);
        carStorage.add(car);
        logger.log(Level.FINE, "Car " + car + " is added to storage");
        carFactoryNet.submitTask();
    }

}
