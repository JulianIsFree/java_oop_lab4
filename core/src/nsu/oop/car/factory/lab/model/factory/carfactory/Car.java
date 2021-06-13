package nsu.oop.car.factory.lab.model.factory.carfactory;

import nsu.oop.car.factory.lab.model.factory.storage.Entity;
import nsu.oop.car.factory.lab.model.factory.details.DetailTypeA;
import nsu.oop.car.factory.lab.model.factory.details.DetailTypeB;
import nsu.oop.car.factory.lab.model.factory.details.DetailTypeC;

public class Car extends Entity {
    private final DetailTypeA detailA;
    private final DetailTypeB detailB;
    private final DetailTypeC detailC;
    private final int id;

    public Car(int id, DetailTypeA detailA, DetailTypeB detailB, DetailTypeC detailC) {
        this.detailA = detailA;
        this.detailB = detailB;
        this.detailC = detailC;
        this.id = id;
    }

    @Override
    public String toString() {
        return "{" + this.getClass().toString() + ", " + id  + ", " + detailA + ", " + detailB + ", " + detailC + "}";
    }
}
