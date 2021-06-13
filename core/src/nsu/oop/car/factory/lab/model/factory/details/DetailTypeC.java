package nsu.oop.car.factory.lab.model.factory.details;

public class DetailTypeC extends Detail {
    public DetailTypeC(int id) {
        super(id);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof DetailTypeC))
            return false;

        return this.getId() == ((DetailTypeC) o).getId();
    }
}
