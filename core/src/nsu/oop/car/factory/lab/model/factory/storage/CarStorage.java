package nsu.oop.car.factory.lab.model.factory.storage;

import nsu.oop.car.factory.lab.model.factory.carfactory.Car;

public class CarStorage extends EntityStorage<Car> {
    public CarStorage(int maxSize) {
        super(maxSize);
    }
}
