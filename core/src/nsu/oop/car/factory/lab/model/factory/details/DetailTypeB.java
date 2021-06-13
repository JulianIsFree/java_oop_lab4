package nsu.oop.car.factory.lab.model.factory.details;

public class DetailTypeB extends Detail {
    public DetailTypeB(int id) {
        super(id);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof DetailTypeB))
            return false;

        return this.getId() == ((DetailTypeB) o).getId();
    }
}
