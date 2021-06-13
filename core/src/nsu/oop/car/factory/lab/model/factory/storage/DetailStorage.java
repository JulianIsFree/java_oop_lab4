package nsu.oop.car.factory.lab.model.factory.storage;

import nsu.oop.car.factory.lab.model.factory.details.Detail;

public class DetailStorage<T extends Detail> extends EntityStorage<T> {
    public DetailStorage(int maxSize) {
        super(maxSize);
    }
}
