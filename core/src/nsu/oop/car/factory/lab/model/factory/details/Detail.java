package nsu.oop.car.factory.lab.model.factory.details;

import nsu.oop.car.factory.lab.model.factory.storage.Entity;

public abstract class Detail extends Entity {
    private int id;

    public Detail(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public abstract boolean equals(Object o);

    @Override
    public String toString() {
        return "{" + this.getClass().toString() + ", " + id + "}";
    }
}
