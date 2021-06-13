package nsu.oop.car.factory.lab.model.factory.merchants;

import nsu.oop.car.factory.lab.model.factory.carfactory.Car;
import nsu.oop.car.factory.lab.model.factory.carfactory.Controller;
import nsu.oop.car.factory.lab.model.threadpool.Task;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SellCarTask extends Task {
    private final Logger logger;
    private final Controller factoryController;

    public SellCarTask(Logger logger, Controller factoryController) {
        this.logger = logger;
        this.factoryController = factoryController;
    }

    @Override
    public void run() {
        Car car = factoryController.bookCar();
        logger.log(Level.FINE, "Car " + car + " is sold");
    }
}
