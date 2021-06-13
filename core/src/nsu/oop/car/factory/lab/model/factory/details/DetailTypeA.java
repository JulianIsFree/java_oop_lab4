package nsu.oop.car.factory.lab.model.factory.details;

public class DetailTypeA extends Detail {
    public DetailTypeA(int id) {
        super(id);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof DetailTypeA))
            return false;

        return this.getId() == ((DetailTypeA) o).getId();
    }
}
