package nsu.oop.car.factory.lab.model.factory.carfactory;

import nsu.oop.car.factory.lab.model.factory.storage.CarStorage;

public class Controller {
    private final CarStorage carStorage;
    private CarFactoryNet carFactoryNet;
    private int soldCounter;

    public Controller(CarStorage carStorage, CarFactoryNet carFactoryNet) {
        this.carFactoryNet = carFactoryNet;
        this.carStorage = carStorage;
        this.soldCounter = 0;
    }
    
    public Car bookCar() {
        Car car = carStorage.extractFirst();
        soldCounter++;
        return car;
    }

    public int getSoldCounter() {
        return soldCounter;
    }

    public CarStorage carStorage() {
        return carStorage;
    }
}
