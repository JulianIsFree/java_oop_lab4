package nsu.oop.car.factory.lab.model.factory.providers;

import nsu.oop.car.factory.lab.model.factory.storage.DetailStorage;
import nsu.oop.car.factory.lab.model.factory.details.DetailTypeA;

import java.util.logging.Logger;

public class AddDetailTypeATask extends AddDetailTask<DetailTypeA> {
    public AddDetailTypeATask(DetailStorage<DetailTypeA> detailStorage, DetailTypeA detail, Logger logger) {
        super(detailStorage, detail, logger);
    }
}
